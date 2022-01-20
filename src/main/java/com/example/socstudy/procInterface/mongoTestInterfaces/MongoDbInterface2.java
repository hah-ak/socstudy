package com.example.socstudy.procInterface.mongoTestInterfaces;

import com.example.socstudy.main.document.SampleAndSample;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MongoDbInterface2 extends MongoRepository<SampleAndSample, String> {

    @Query(value = "{ 'map.itemkey' : {$eq:'\"wefsef\"'} }")
    List<SampleAndSample> fq(String itemKey);

    @Query(value = "{map : {$exists:true}}")
    List<SampleAndSample> fs();

    @Query(value = "{sample_id: ?0 }")
    List<SampleAndSample> fsid(ObjectId id);

}
