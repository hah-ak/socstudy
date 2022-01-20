package com.example.socstudy.main.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashMap;

@Document(collection = "sampleAndSample")
@Getter
@Setter
public class SampleAndSample {

    @MongoId
    private ObjectId id;

    private ObjectId sample_id;
    @Field(targetType = FieldType.IMPLICIT)
    private HashMap map;
}
