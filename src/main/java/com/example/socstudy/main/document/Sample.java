package com.example.socstudy.main.document;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;

@Document(collection = "sample")
@Getter
@Setter
public class Sample {

    @MongoId
    private ObjectId id;

    private String stringItem;
}
