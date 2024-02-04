package com.dataprev.abono.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "TB_TRABALHADOR")
public class Trabalhador {
    @Id
    @Column(name="trabalhador_id")
    private Long cpf;
    @Column(name="nome")
    private String nome;
    @Column(name="nome_mae")
    private String nomeMae;
    @Column(name="data_nascimento")
    private Date nascimento;
    @Column(name="pis_pasep")
    private Long pisPasep;

    public Trabalhador(Long cpf, String nome, String nomeMae, Date nascimento, Long pisPasep) {
        this.cpf = cpf;
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.nascimento = nascimento;
        this.pisPasep = pisPasep;
    }

    public Trabalhador(Long cpf, String nome, Date nascimento, Long pisPasep) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.pisPasep = pisPasep;
        this.nomeMae = "";
    }

    public Trabalhador() {
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Long getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(Long pisPasep) {
        this.pisPasep = pisPasep;
    }
}
