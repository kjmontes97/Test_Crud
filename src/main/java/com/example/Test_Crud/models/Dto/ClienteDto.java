package com.example.Test_Crud.models.Dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ClienteDto implements Serializable {

    private Integer id;
    private String nombre;
    private String apellido;
    private String correo;
}
