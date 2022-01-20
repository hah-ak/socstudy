package com.example.socstudy.procInterface.mongoTestInterfaces;

import com.example.socstudy.main.document.Sample;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;


public interface MongoDbInterface extends MongoRepository<Sample, String> {


    List<Sample> findAllByStringItem(String item);
    Sample findByStringItem(String item);
    Sample findSampleByStringItemIsLike(String item);
    Sample findByIdIsLike(ObjectId id);


}
