package com.example.socstudy.main;


import com.example.socstudy.main.document.Sample;
import com.example.socstudy.main.document.SampleAndSample;
import com.example.socstudy.main.document.OAuthDocument;
import com.example.socstudy.procInterface.mongoTestInterfaces.MongoDbInterface;
import com.example.socstudy.procInterface.mongoTestInterfaces.MongoDbInterface2;
import com.example.socstudy.procInterface.mongoOAuthInterfaces.MongoDbOAuthInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StartPage {

    private final MongoDbInterface mongoDbInterface;
    private final MongoDbInterface2 mongoDbInterface2;
    private final MongoDbOAuthInterface mongoInterface2;

    @Autowired
    public StartPage(MongoDbInterface mongoDbInterface, MongoDbInterface2 mongoDbInterface2, MongoDbOAuthInterface mongoInterface2) {
        this.mongoDbInterface = mongoDbInterface;
        this.mongoDbInterface2 = mongoDbInterface2;
        this.mongoInterface2 = mongoInterface2;

    }

    @PostMapping("/normal")
    public String noraml(@RequestBody String text) {
        try {

        } catch (Exception e) {

        }

        System.out.println(text);
        return text;
    }

    @GetMapping("/test")
    public SseEmitter abs() {
        long timeOut = 4000;
        SseEmitter sseEmitter = new SseEmitter(timeOut);
        try {
            sseEmitter.send(SseEmitter.event().data("obaks"));
        } catch (Exception e) {
            log.error("error in emit" + e);
        } finally {
            sseEmitter.complete();
        }
        return sseEmitter;
    }

    @GetMapping("/webSocket")
    public Object goWebsocket() {
        return "aaaa";
    }
    @GetMapping("/mongotest")
    public List<Sample> mongoTest() {
//        System.out.println(mongoDbInterface.findAll());
//        mongoDbInterface.findOne(Query.query(Criteria.where("StringItem").is("setItem")),Sample.class,"sample");
        List<Sample> returnList = mongoDbInterface.findAll();
        return returnList;
    }

    @PostMapping("/mongoinsert")
    public void mongoinsert(@RequestBody String s) {
        SampleAndSample sampleAndSample = new SampleAndSample();
        HashMap<String, String> map = new HashMap<>();
        OAuthDocument testTwo = new OAuthDocument();
        List<SampleAndSample> samples = mongoDbInterface2.fq("\"wefsef\"");
        System.out.println(samples);
        List<SampleAndSample> samples1 = mongoDbInterface2.fs();
//        List<SampleAndSample> samples2 = mongoDbInterface2.fsid(mongoDbInterface.findByStringItem("\"fesef\"").getId());
//        List<TestTwo> testTwos = mongoInterface2.findAllByIdExists();
        System.out.println(samples1);
//        map.put("itemkey",s);
//        testTwo.setADouble(12.543);
//        testTwo.setAInt(234);
        OAuthDocument a = mongoInterface2.save(testTwo);

        System.out.println(a);

//        mongoDbInterface.save(sample.getId())
//
//        sampleAndSample.setSample_id(sample.getId());
//        sampleAndSample.setMap(map);
//        mongoDbInterface.findByIdIsLike(sampleAndSample.getSample_id());
//
//        SampleAndSample result = mongoDbInterface2.save(sampleAndSample);

//        return result;
    }

}
