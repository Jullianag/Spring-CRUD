package com.meuprojeto.animal.dto;

import com.meuprojeto.animal.entities.Cachorro;
import com.meuprojeto.animal.entities.CachorroStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CachorroDTO {

    private Long id;
    @Size(min = 2, max = 80, message = "Nome deve ter de 2 a 80 caracteres!")
    @NotBlank(message = "Campo obrigatório!")
    private String nome;
    private String raca;
    @Positive(message = "Idade não pode ser negativa!")
    private Integer idade;
    private CachorroStatus status;

    public CachorroDTO(Long id, String nome, String raca, Integer idade, CachorroStatus status) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.status = status;
    }

    public CachorroDTO(Cachorro entity) {
        id = entity.getId();
        nome = entity.getNome();
        raca = entity.getRaca();
        idade = entity.getIdade();
        status = entity.getStatus();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public CachorroStatus getStatus() {
        return status;
    }
}
