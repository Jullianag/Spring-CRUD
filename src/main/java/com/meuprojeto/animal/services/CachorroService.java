package com.meuprojeto.animal.services;

import com.meuprojeto.animal.dto.CachorroDTO;
import com.meuprojeto.animal.entities.Cachorro;
import com.meuprojeto.animal.repositories.CachorroRepository;
import com.meuprojeto.animal.services.exceptions.DatabaseException;
import com.meuprojeto.animal.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CachorroService {

    @Autowired
    private CachorroRepository repository;

    @Transactional(readOnly = true)
    public CachorroDTO findByid(Long id) {
        Cachorro cachorro = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Id não encontrado!"));
        return new CachorroDTO(cachorro);
    }

    @Transactional(readOnly = true)
    public Page<CachorroDTO> findAll(Pageable pageable) {
        Page<Cachorro> result = repository.findAll(pageable);
        return result.map(x -> new CachorroDTO(x));
    }

    @Transactional
    public CachorroDTO insert(CachorroDTO dto) {
        Cachorro entity = new Cachorro();
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new CachorroDTO(entity);
    }

    @Transactional
    public CachorroDTO update(Long id, CachorroDTO dto) {
        try {
            Cachorro entity = repository.getReferenceById(id);
            copyDtoEntity(dto, entity);
            entity = repository.save(entity);
            return new CachorroDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado!");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoEntity(CachorroDTO dto, Cachorro entity) {
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setRaca(dto.getRaca());
        entity.setStatus(dto.getStatus());
    }
}
