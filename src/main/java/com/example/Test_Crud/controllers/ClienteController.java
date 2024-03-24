package com.example.Test_Crud.controllers;

import com.example.Test_Crud.models.Cliente;
import com.example.Test_Crud.models.Dto.ClienteDto;
import com.example.Test_Crud.models.payload.MensajeResponse;
import com.example.Test_Crud.services.IClienteService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private IClienteService clienteService;

    public ClienteController(IClienteService clienteService)
    {
        this.clienteService=clienteService;
    }
    @GetMapping("/clientes")
    public ResponseEntity<?> showAll()
    {
        List<Cliente> getList= clienteService.listAll();

        if(getList==null)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No hay registros!!")
                            .object(null)
                            .build()
                    ,HttpStatus.OK);
        }


        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(getList)
                        .build(),
                HttpStatus.OK
        );
    }
    @PostMapping("/cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto)
    {
        Cliente cliente_save= null;
        try
        {
            cliente_save = clienteService.save(clienteDto);
            clienteDto = ClienteDto.builder()
                        .id(cliente_save.getId())
                        .nombre(cliente_save.getNombre())
                        .apellido(cliente_save.getApellido())
                        .correo(cliente_save.getCorreo())
                        .build();

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(clienteDto)
                    .build(),
                    HttpStatus.CREATED);
        }
        catch(DataAccessException exDat)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDat.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id)
    {
        Cliente cliente_update = null;

        try
        {
            if(clienteService.existById(id))
            {   clienteDto.setId(id);
                cliente_update = clienteService.save(clienteDto);
                clienteDto = ClienteDto.builder()
                        .id(cliente_update.getId())
                        .nombre(cliente_update.getNombre())
                        .apellido(cliente_update.getApellido())
                        .correo(cliente_update.getCorreo())
                        .build();

                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Actualizado correctamente")
                        .object(clienteDto)
                        .build(),
                        HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la Base de Datos!!")
                        .object(null)
                        .build(),
                        HttpStatus.NOT_FOUND);
            }

        }
        catch(DataAccessException exDat)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(exDat.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        try
        {
            Cliente cliente_delete= clienteService.findById(id);
            clienteService.delete(cliente_delete);
            return new ResponseEntity<>(cliente_delete,HttpStatus.NO_CONTENT);
        }
        catch(DataAccessException exDat)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                        .mensaje(exDat.getMessage())
                        .object(null)
                        .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id)
    {
        Cliente cliente = clienteService.findById(id);

        if(cliente==null)
        {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("El registro que intenta buscar, no existe!!")
                            .object(null)
                            .build()
                    ,HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(
                MensajeResponse.builder()
                        .mensaje("")
                        .object(ClienteDto.builder()
                                .id(cliente.getId())
                                .nombre(cliente.getNombre())
                                .apellido(cliente.getApellido())
                                .correo(cliente.getCorreo())
                                .build())
                        .build(),
                HttpStatus.OK
        );
    }
}
