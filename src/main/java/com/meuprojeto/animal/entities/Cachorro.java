package com.meuprojeto.animal.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cachorro")
public class Cachorro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String raca;
    private Integer idade;
    private CachorroStatus status;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public Cachorro() {
    }

    public Cachorro(Long id, String nome, String raca, Integer idade, CachorroStatus status, Tutor tutor) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.status = status;
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public CachorroStatus getStatus() {
        return status;
    }

    public void setStatus(CachorroStatus status) {
        this.status = status;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
