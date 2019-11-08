package com.contactabilidad.push;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private String uuid = UUID.randomUUID().toString();
    private String name;
    private String device;
}
