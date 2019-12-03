package com.jg.thymeleaf.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {

    private String name;
    private String surname;
    private int age;

}
