package com.casaSolaire.services.impl;

import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Client;
import com.casaSolaire.repository.ClientRepository;
import com.casaSolaire.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public ClientDto save(ClientDto clientDto) {
        return ClientDto.fromEntityToDto(
                clientRepository.save(
                        ClientDto.fromDtoToEntity(clientDto)
                )
        );

    }

    @Override
    public ClientDto findById(Long id) {
        if (id == null) {
            log.error("Client Id is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);

        return Optional.of(ClientDto.fromEntityToDto(client.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not client with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Client Id is null");
            return;
        }
        clientRepository.deleteById(id);

    }
}
