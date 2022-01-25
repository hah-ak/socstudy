package com.example.socstudy;

import com.example.socstudy.main.blizzard.vo.AuthorizationTokenVo;
import com.example.socstudy.main.document.Sample;
import com.example.socstudy.procInterface.mongoTestInterfaces.MongoDbInterface;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.util.*;


@SpringBootTest
class SocStudyApplicationTests {

    @Value("classpath:/static/json/service-acct.json")
    private Resource resource;

    private static final EnumMap<APIType, ApiKeyVo> API_MAP = new EnumMap<>(APIType.class);

//    @Value("${spring.data.mongodb.uri}")
//    private String uri;
//
//    @Value("${spring.data.mongodb.port}")
//    private String port;
    @Autowired
    private MongoDbInterface mongoDbInterface;
    @Autowired
    private DefaultListableBeanFactory defaultListableBeanFactory;

    enum APIType {
        BLIZZARD,GOOGLE;
    }
    public class ApiKeyVo {
        private String CLIENT_KEY;
        private String CLIENT_SECRET;
        private String API_KEY;
    }
    @Test
    void contextLoads() {
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            EnumMap<APIType, ApiKeyVo> map = objectMapper.readValue(resource.getURL(), new EnumMap<APIType, ApiKeyVo>(APIType.class).getClass());
//
//            API_MAP.putAll(map);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        String[] sts = new Gson().fromJson("[\"ba\",\"an\",\"nan\",\"ban\",\"n\"]",String[].class);
        int[][] puddles = new Gson().fromJson("[[2, 2]]",int[][].class);
        System.out.println(solution2(4,3,puddles));
//        System.out.println(solution(sts,"banana"));

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

    public int solution2(int m, int n, int[][] puddles) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>(n);
        for (int i = 0; i < arrayLists.size(); i++) {
            ArrayList<Integer> add = new ArrayList<>(m);
            Collections.fill(add,-1);
            arrayLists.set(i,add);
        }

        for (int i = 0; i < puddles.length; i++) {
            int puddle[] = puddles[i];
            arrayLists.get(puddle[1]-1).set(puddle[0]-1,-1);
        }
        dynamicProgramming(m-1,n-1, arrayLists);
        return 1 % 1000000007;
    }

    public void dynamicProgramming(int m, int n, ArrayList<ArrayList<Integer>> arrayLists) {

        if (n == arrayLists.size() && m == arrayLists.get(0).size()) {

        } else {
            if (arrayLists.get(n).get(m-1) == -1) {
                dynamicProgramming(n,m-1,arrayLists);
            }
            if (arrayLists.get(n-1).get(m) == -1) {
                dynamicProgramming(n-1,m,arrayLists);
            }
            arrayLists.get(n).set(m,arrayLists.get(n-1).get(m) + arrayLists.get(n).get(m-1));
        }

        if (m == 1) {
            arrayLists.get(n).set(m,arrayLists.get(n-1).get(m));
        }
        if (n == 1) {
            arrayLists.get(n).set(m,arrayLists.get(n).get(m-1));
        }


    }

    public int solution(String[] str, String t) {

        HashMap<Character, ArrayList<String>> strMap = new HashMap<>();
        String subString = t.substring(0,t.length());

        for (String s: str) {
            try {
                strMap.get(s.charAt(0)).add(s);
            } catch (Exception e) {
                ArrayList<String> subList = new ArrayList<>();
                subList.add(s);
                strMap.put(s.charAt(0),subList);
            }
        }

        strMap.forEach((character, strings) -> strings.sort(Comparator.comparingInt(String::length).reversed()));

        int result = recursion(subString,strMap,0);

        return result == 20000 ? -1 : result;
    }

    public int recursion(String subString,HashMap<Character,ArrayList<String>> strMap, int cnt) {
        if (subString.equals("")) {
            return cnt;
        }

        int result = 20000;

        char first = subString.charAt(0);
        try {
            for (String s: strMap.get(first)) {
                if (subString.substring(0,s.length()).equals(s)) {
                    String newSubString = subString.substring(s.length());
                    int newResult = recursion(newSubString, strMap, cnt + 1);
                    result = newResult < result ? newResult : result;
                }
            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }



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
