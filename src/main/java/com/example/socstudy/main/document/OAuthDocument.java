package com.example.socstudy.main.document;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "OAuth")
@Getter
@Setter
public class OAuthDocument {

    @MongoId
    private ObjectId id;

    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String scope;
    private String sub;
}
