package com.example.socstudy.procInterface.mongoOAuthInterfaces;

import com.example.socstudy.main.document.OAuthDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoDbOAuthInterface extends MongoRepository<OAuthDocument,String> {

    List<OAuthDocument> findAllByIdExists();
}
