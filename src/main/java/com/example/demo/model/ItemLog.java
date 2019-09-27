package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "log")
public class ItemLog implements Serializable {

    @Id
    private String _id;
}
