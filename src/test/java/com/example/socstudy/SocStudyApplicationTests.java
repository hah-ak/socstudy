package com.example.socstudy;

import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.main.document.Sample;
import com.example.socstudy.procInterface.mongoTestInterfaces.MongoDbInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@SpringBootTest
class SocStudyApplicationTests {

    @Value("classpath:/static/json/service-acct.json")
    private Resource resource;

//    @Value("${spring.data.mongodb.uri}")
//    private String uri;
//
//    @Value("${spring.data.mongodb.port}")
//    private String port;
    @Autowired
    private MongoDbInterface mongoDbInterface;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    @Test
    void contextLoads() {
        AuthorizationTokenVo a = new AuthorizationTokenVo();
        Field[] f = a.getClass().getDeclaredFields();
        Method[] m = a.getClass().getDeclaredMethods();
        a.getClass().getSimpleName();

//        System.out.println(uri);
//        try {
//            System.out.println(org.springframework.core.SpringVersion.getVersion());
//            ObjectMapper objectMapper = new ObjectMapper();
//            HashMap map = objectMapper.readValue(resource.getURL(),HashMap.class);
////            System.out.println(map.get("API_KEY"));
//        } catch (Exception exception) {
//            System.out.println("error");
//        }
//        String sentence = "HaEaLaLaObWORLDb";
//        String[] sentences = {"HaEaLaLaObWORLDb","aIaAM","AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR", "aaA","Aaa"};
//        for (String s:
//             sentences) {
//            System.out.println(solution(s));
//        }
        String sentence = "AAAaBaAbBBBBbCcBdBdBdBcCeBfBeGgGGjGjGRvRvRvRvRvR";
//        String sentence = "bBBBbCcBdBdBdBc";
//        System.out.println(solution(sentence));
//        mongoClientConn();
        lambdaTest();
    }

//    public String solution(String sentence) {
//        String answer = "";
//        String fail = "invalid";
//        ArrayList<Character> charList = new ArrayList<>();
//
//        Pattern p = Pattern.compile("([a-z])[A-Z]([a-z])[A-Z](\\2[A-Z])+\\1|[A-Z]([a-z])[A-Z](\\4[A-Z])+|([a-z])[A-Z]+\\6|[A-Z]+|[A-Z]([a-z])[A-Z]");
//        Matcher matcher = p.matcher(sentence);
//
//        boolean matcherFind = matcher.find();
//
//        if (matcherFind) {
//            while(matcherFind) {
//                String group = matcher.group();
//                char c = '0';
//                if (group.length() > 2) {
//                    c = group.charAt(0) > 90 ? group.charAt(0) : group.charAt(1);
//                }
//                if (charList.contains(c)) {
//                    return fail;
//                } else {
//                    charList.add(c);
//                    String s = group.replaceAll("[a-z]", "");
//                    answer += s + " ";
//                }
//                matcherFind = matcher.find();
//            }
//        } else {
//            return fail;
//        }
//        return answer.trim();
//    }

    public <T> String aaaaa(T a) {
        AuthorizationTokenVo authorizationTokenVo = a instanceof AuthorizationTokenVo ? ((AuthorizationTokenVo) a) : null;
        return "";
    }
    public void mongoClientConn() {
//        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017");
//        try(MongoClient mongoClient = MongoClients.create(connectionString)) {
//            MongoDatabase database = mongoClient.getDatabase("myMongoTest");
//            MongoCollection<Document> collection = database.getCollection("sample");
//            ObjectId id = collection.find(Filters.eq("StringItem","setItem")).first().getObjectId("_id");
//            Document getItem = collection.find(Filters.exists("referrence_id")).first();
//            System.out.println(collection.find(Filters.eq("_id",getItem.get("referrence_id"))));
//            Document refItem = collection.find(Filters.eq("_id",getItem.getObjectId("referrence_id"))).first();
//            System.out.println(getItem.toJson());
//            System.out.println(refItem.toJson());
//            Document insDoc = new Document();
//            insDoc.put("StringItem",collection.find(Filters.eq("StringItem","setItem")).first());
//            insDoc.put("stringItem2",collection.find(Filters.eq("StringItem2","aaa")).first());
//            insDoc.put("referrence_id",id);


//            collection.insertOne(insDoc);
//            collection.updateMany(Filters.exists("StringItem"),Updates.set("StringItem",Updates.set("StringItem","updateItem")));
//            collection.updateMany(collection.find(Filters.exists("StringItem"), Updates.set("StringItem","aaa"));
//            System.out.println(collection.find(Filters.eq("StringItem","string")).first());
//            Document doc = collection.find(Filters.eq("stringItem2","aaa")).first();
//            System.out.println(doc.toJson());
//        }
    }

    public void lambdaTest() {
        Sample sample = new Sample();
        sample.setStringItem("setItem");
        mongoDbInterface.save(sample);


        List<Sample> a1 = mongoDbInterface.findAll();
        System.out.println(a1);
        int[] ints = {12,16,33,21,56,31,23,14,36,23};

        Integer[] ints1 = Arrays.stream(ints).boxed().sorted((a,b)->{
            int aQuo = a/10;
            int bQuo = b/10;
            int aRe = a%10;
            int bRE = b%10;

            if (aQuo == bQuo) {
                if (aRe > bRE) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (a > b) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }).toArray(Integer[]::new);
//        Arrays.stream(ints1).forEach(System.out::println);

        ArrayList<testVo> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testVo a = new testVo((int) (Math.random() * 10),(int) (Math.random() * 10));
            arrayList.add(a);
        }
        arrayList.sort(Comparator.comparing(testVo::getA).thenComparing(testVo::getB,Comparator.reverseOrder()));

        arrayList.forEach(a->System.out.println(String.format("%d %d",a.getA(),a.getB())));

    }
    public class testVo {
        private int a;
        private int b;
        public testVo(int a, int b) {
            this.a =a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

}
