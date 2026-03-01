package com.langchain4j_poc.request;

import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
    private int age;
    private String bio;
}