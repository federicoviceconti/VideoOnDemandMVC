package com.jpa;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "genere", schema = "videoondemand", catalog = "")
public class GenereEntity {
    private int id;
    private String nome;
    private String descrizione;
    private Timestamp dataCreazione;
    private Timestamp ultimaModifica;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenereEntity that = (GenereEntity) o;
        return id == that.id &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(descrizione, that.descrizione) &&
                Objects.equals(dataCreazione, that.dataCreazione) &&
                Objects.equals(ultimaModifica, that.ultimaModifica);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, descrizione, dataCreazione, ultimaModifica);
    }
}
