package com.videoondemand.controller;

import com.common.Field;
import com.dao.to.AssemblerDto;
import com.dao.to.FilmGenereDto;
import com.facade.FacadeFactory;
import com.facade.FacadeFactory.FacadeType;
import com.facade.FacadeFilm;
import com.videoondemand.model.Error;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Servlet implementation class Add
 */
@WebServlet("/AddServlet")
@MultipartConfig
public class AddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String NOT_CALLED = "not_called";
    private FacadeFilm facadeFilm;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
        facadeFilm = (FacadeFilm) FacadeFactory.getFacade(FacadeType.FILM);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String req = "";
        if (Validate.checkParams(request, "type", Field.EMPTY).equals(Field.MODIFY)) {
            int id = Integer.parseInt(request.getParameter("id"));
            FilmGenereDto film = facadeFilm.getFilmById(id);

            String genere = film.getFilm().getGenere().values().iterator().next();
            req = "?type=" + Field.MODIFY
                    + "&id=" + film.getFilm().getId()
                    + "&nome=" + film.getFilm().getNome()
                    + "&anno=" + film.getFilm().getAnno()
                    + "&genere=" + genere;
        }

        request.setAttribute(Field.ALL_GENERI, facadeFilm.getAllGeneri());
        request.getRequestDispatcher("add.jsp" + req).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Error> errorList = new ArrayList<>();

        String id = Validate.checkParams(request, "id", NOT_CALLED);
        String nome = Validate.checkParams(request, "nome", NOT_CALLED);
        String anno = Validate.checkParams(request, "anno", NOT_CALLED);
        String genere = Validate.checkParams(request, "genere", NOT_CALLED);

        UploadFile uploadFile = new UploadFile(Field.PATH, request);

        if (uploadFile.getFileName() != null && !nome.equals(NOT_CALLED) && !anno.equals(NOT_CALLED) && !genere.equals(NOT_CALLED)) {
            if (!nome.isEmpty() && !anno.isEmpty() && !genere.isEmpty() && !uploadFile.getFileName().isEmpty()) {
                new Thread(uploadFile).start();

                try {
                    FilmGenereDto dto = new AssemblerDto.AssemblerBuilder()
                            .setTitolo(nome)
                            .setAnno(anno)
                            .setIdGenere(genere)
                            .setFileName(uploadFile.getStaticFileName())
                            .build().getDto();

                    if (Validate.checkParams(request, Field.TYPE, Field.EMPTY).equals(Field.MODIFY)) {
                        facadeFilm.modifyFilm(Integer.parseInt(id), dto);
                    } else {
                        facadeFilm.addFilm(dto);
                    }
                    response.setStatus(200);
                    request.getRequestDispatcher("ListServlet").forward(request, response);
                } catch (NumberFormatException nfe) {
                    errorList.add(new Error("Il numero non è valido"));
                    response.setStatus(309);
                    request.setAttribute(Field.KEY_ERROR, errorList);
                    request.getRequestDispatcher("add.jsp").forward(request, response);
                }
            } else {
                if (nome.isEmpty()) {
                    errorList.add(new Error("Il nome non ha caratteri"));
                }

                if (nome.length() < 2) {
                    errorList.add(new Error("Il nome ha meno di 2 caratteri"));
                }

                if (genere.isEmpty()) {
                    errorList.add(new Error("Il genere non è valido"));
                }

                if(uploadFile.getFileName().isEmpty()) {
                    errorList.add(new Error("Il file sembra essere non valido!"));
                }

                response.setStatus(309);
                request.setAttribute(Field.KEY_ERROR, errorList);
                request.setAttribute(Field.ALL_GENERI, facadeFilm.getAllGeneri());
                request.getRequestDispatcher("add.jsp").forward(request, response);
            }
        }
    }

    private class UploadFile implements Runnable {
        private static final String FILE_UPLOAD = "file";
        //private static final String FILE_DESCRIPTION = "description";
        private String path = "";
        private Part filePart;
        private String fileName;

        public UploadFile(String path, HttpServletRequest request) throws IOException, ServletException {
            super();
            this.path = path;
            this.filePart = request.getPart(FILE_UPLOAD);
            this.fileName = getFileName();
        }

        private String getFileName() {
            if (filePart.getHeader("content-disposition") != null) {
                for (String content : filePart.getHeader("content-disposition").split(";")) {
                    if (content.trim().startsWith("filename")) {
                        String filePath = content.substring(content.indexOf('=') + 1).replace("\"", "");
                        String file = Paths.get(filePath).getName(Paths.get(filePath).getNameCount() - 1).toString();

                        return !file.isEmpty() ? file.substring(0, file.indexOf("."))
                                + "_" + UUID.randomUUID() + file.substring(file.indexOf(".")) : Field.EMPTY;
                    }
                }
            }

            return null;
        }

        public String getStaticFileName() {
            return this.fileName;
        }

        public String getCompletePath() {
            return path + File.separator + this.fileName;
        }

        @Override
        public void run() {
            try (OutputStream outputStream = new FileOutputStream(new File(getCompletePath()));
                 InputStream inputStream = filePart.getInputStream()) {
                int read = 0;
                final byte[] buffer = new byte[1024];

                while ((read = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, read);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }
}
