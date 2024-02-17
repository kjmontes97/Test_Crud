package com.example.Test_Crud.services.Impl;

import com.example.Test_Crud.models.Cliente;
import com.example.Test_Crud.models.Dto.ClienteDto;
import com.example.Test_Crud.repositories.ClienteRepository;
import com.example.Test_Crud.services.IClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {

    private ClienteRepository clienteRepository;

    public ClienteImplService(ClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }
    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente= Cliente.builder()
                .id(clienteDto.getId())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .build();
        return clienteRepository.save(cliente);
    }
    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
    @Override
    public boolean existById(Integer id) {
        return clienteRepository.existsById(id);
    }

    @Override
    public List<Cliente> listAll() {
        return (List) clienteRepository.findAll();
    }
}
