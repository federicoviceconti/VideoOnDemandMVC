package com.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "videoondemand", catalog = "")
public class FilmEntity {
    private int id;
    private String titolo;
    private int anno;
    private String regista;
    private String cast;
    private Integer durata;
    private String descrizione;
    private Timestamp dataCreazione;
    private Timestamp ultimaModifica;
    private String filename;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "titolo")
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Basic
    @Column(name = "anno")
    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    @Basic
    @Column(name = "regista")
    public String getRegista() {
        return regista;
    }

    public void setRegista(String regista) {
        this.regista = regista;
    }

    @Basic
    @Column(name = "cast")
    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    @Basic
    @Column(name = "durata")
    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    @Basic
    @Column(name = "descrizione")
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Basic
    @Column(name = "data_creazione")
    public Timestamp getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Timestamp dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    @Basic
    @Column(name = "ultima_modifica")
    public Timestamp getUltimaModifica() {
        return ultimaModifica;
    }

    public void setUltimaModifica(Timestamp ultimaModifica) {
        this.ultimaModifica = ultimaModifica;
    }

    @Basic
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return id == that.id &&
                anno == that.anno &&
                Objects.equals(titolo, that.titolo) &&
                Objects.equals(regista, that.regista) &&
                Objects.equals(cast, that.cast) &&
                Objects.equals(durata, that.durata) &&
                Objects.equals(descrizione, that.descrizione) &&
                Objects.equals(dataCreazione, that.dataCreazione) &&
                Objects.equals(ultimaModifica, that.ultimaModifica) &&
                Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, titolo, anno, regista, cast, durata, descrizione, dataCreazione, ultimaModifica, filename);
    }
}
