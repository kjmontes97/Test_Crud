package com.example.Test_Crud.services;

import com.example.Test_Crud.models.Cliente;
import com.example.Test_Crud.models.Dto.ClienteDto;

import java.util.List;

public interface IClienteService {

    List<Cliente> listAll();
    Cliente save(ClienteDto cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    boolean existById(Integer id);

}
