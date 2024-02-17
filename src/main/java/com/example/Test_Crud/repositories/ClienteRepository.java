package com.example.Test_Crud.repositories;

import com.example.Test_Crud.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Integer> {
}
