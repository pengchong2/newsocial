package cn.flyaudio.flycodelibrary;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.flyaudio.flycodelibrary.utils.JSONUtils;

/**
 * @author newtrekWang
 * @fileName JSONUtilsTest
 * @createDate 2018/11/1 14:20
 * @email 408030208@qq.com
 * @desc Json工具测试类
 */
public class JSONUtilsTest {
    // 测试自定义数据类
    static class User{
        String name;
        int  age;
        boolean gender;
        long  year;
        List<Habbit> habbits;

        public void setHabbits(List<Habbit> habbits) {
            this.habbits = habbits;
        }

        public User(String name, int age, boolean gender, long year) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.year = year;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    ", year=" + year +
                    ", habbits=" + habbits +
                    '}';
        }
    }

    static class Habbit{
        String name;
        int type;

        public Habbit(String name, int type) {
            this.name = name;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Habbit{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }
    }

    @Test
    public void object2JsonString() {
        User user = new User("wang",80,true,3243L);
        List<Habbit> habbits = new ArrayList<>();
        habbits.add(new Habbit("sleep",1));
        habbits.add(new Habbit("eat",2));
        habbits.add(new Habbit("study",3));
        System.out.println(JSONUtils.object2JsonString(user));
        user.setHabbits(habbits);
        System.out.println(JSONUtils.object2JsonString(user));
        // test Map
        Map map = new HashMap<>();
        map.put("name","wang");
        map.put("age",20);
        map.put("gender",true);
        map.put("year",2000L);
        System.out.println(JSONUtils.object2JsonString(map));
    }
    @Test
    public void jsonString2Object(){
        // 基本类型
        int age = JSONUtils.jsonString2Object("3",int.class);
        System.out.println("int  : "+age);
        boolean gender = JSONUtils.jsonString2Object("true",boolean.class);
        System.out.println("bool  : "+gender);
        String  name = JSONUtils.jsonString2Object("wang",String.class);
        System.out.println("name  : "+name);
        // 数组
        int[] intArray = JSONUtils.jsonString2Object("[3,2,3,4]",int[].class);
        System.out.println("intArray  : "+ Arrays.toString(intArray));
        // 自定义类型
        String  json = "{\"name\":\"wang\",\"age\":80,\"gender\":true,\"year\":3243,\"habbits\":[{\"name\":\"sleep\",\"type\":1},{\"name\":\"eat\",\"type\":2},{\"name\":\"study\",\"type\":3}]}";
        User user = JSONUtils.jsonString2Object(json,User.class);
        System.out.println("user: "+user);
        // testMap
        Map map1 = JSONUtils.jsonString2Object("{\"name\":\"wang\",\"age\":80,\"gender\":true,\"year\":3243,\"array\":[1,2,3,4]}",Map.class);
        System.out.println("map1: "+map1);
        Map map2 = JSONUtils.jsonString2Object("{\"name\":\"wang\",\"age\":80,\"gender\":true,\"year\":3243,\"array\":[\"one\",\"two\",\"three\",\"four\"]}",Map.class);
        System.out.println("map2: "+map2);

    }

    @Test
    public void jsonString2List(){
        List<String > stringList = JSONUtils.jsonString2List("[\"wang\",\"zhang\",\"zhao\"]",String.class);
        System.out.println(stringList);
        User user = new User("wang",80,true,3243L);
        List<Habbit> habbits = new ArrayList<>();
        habbits.add(new Habbit("sleep",1));
        habbits.add(new Habbit("eat",2));
        habbits.add(new Habbit("study",3));
        user.setHabbits(habbits);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user);
        userList.add(user);
        String jsonString = JSONUtils.object2JsonString(userList);
        System.out.println("jsonString: "+jsonString);
        userList = JSONUtils.jsonString2List(jsonString,User.class);
        System.out.println(userList);
    }




}
