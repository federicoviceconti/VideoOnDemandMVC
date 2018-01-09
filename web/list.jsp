<%@page import="com.common.Field" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://utils.jsp.federico/" %>

<jsp:useBean id="utils" class="com.videoondemand.controller.ServletUtils"/>

<div class="content">
    <div class="part">
        <h2 class="text">Lista prodotti</h2>
        <h2 class="text">Ci sono ${requestScope["lenghtFilms"]} elementi nel carrello!</h2>
    </div>
    <div class="part">
        <h2 class="text">Opzioni</h2>
        <h3 class="text">Ordinamento</h3>
        <c:out value="${cookie[Field.ORDER].name}" />
        <c:out value="${cookie[Field.ORDER].value}" />
        <form action="ListServlet" method="POST">

            <div class="part">
                <select name="${Field.ORDER}">
                    <option value="asc" ${cookie[Field.ORDER].value.equals("asc") ? "selected='selected'" : ""}>
                        Crescente
                    </option>
                    <option value="desc" ${cookie[Field.ORDER].value.equals("desc") ? "selected='selected'" : ""}>
                        Decrescente
                    </option>
                </select>
            </div>

            <div class="part">
                <input type="hidden" name="${Field.TYPE}"
                       value="${Field.ORDER}"/>
                <input type="checkbox" name="ordinamento" value="save" /> Salva l'ordinamento
                <input type="submit" value="Ordina" />
            </div>
        </form>
    </div>

    <div class="part">
        <table>
            <tr>
                <th><p class="large white">Nome</p></th>
                <th><p class="large white">Anno</p></th>
                <th><p class="large white">Genere</p></th>
                <th><p class="large white">Copertina</p></th>
                <th><p class="large white">Modifica</p></th>
                <th><p class="large white">Cancella</p></th>
            </tr>

            <c:forEach items="${requestScope.films}" var="film">
                <tr class="clickable" data-href="add.jsp">
                    <td>
                        <p>${keyGenere = film.getFilm().getGenere().keySet(); film.getFilm().getNome()}
                        </p>
                    </td>
                    <td>
                        <p>${film.getFilm().getGenere().get(keyGenere.iterator().next())}
                        </p>
                    </td>
                    <td>
                        <p>${film.getFilm().getAnno()}
                        </p>
                    </td>
                    <td>
                        <a href='${film.getFilm().getFileName()}'>Copertina</a></td>
                    <td>
                        <p>
                            <a href='
                                <c:url value="AddServlet">
                                    <c:param name="type" value="${Field.MODIFY}"/>
                                    <c:param name="id" value="${film.getFilm().getId()}"/>
                                </c:url>
                            '>Modifica
                            </a>
                        </p>
                    </td>
                    <td>
                        <p>
                            <a href='
                                <c:url value="ListServlet">
                                    <c:param name="type" value="${Field.DELETE}"/>
                                    <c:param name="id" value="${film.getFilm().getId()}"/>
                                </c:url>
                            '>Cancella
                            </a>
                        </p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="footer-link">
            <a class="text" href="home.jsp">Ritorna alla home</a>
        </div>
    </div>
</div>