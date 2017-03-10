package com.example;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    public static void main(String[] args)  throws Exception {

        //place where db folder will be created inside the project folder
        Schema schema = new Schema(1,"com.codekrypt.greendao.db");

        //Entity i.e. Class to be stored in the database // ie table LOG
        Entity word_entity= schema.addEntity("LOG");

        word_entity.addIdProperty(); //It is the primary key for uniquely identifying a row

        word_entity.addStringProperty("text").notNull();  //Not null is SQL constrain

        Entity meal_entity = schema.addEntity("Meal");

        meal_entity.addIdProperty();

        meal_entity.addDoubleProperty("price").notNull();
        meal_entity.addDoubleProperty("tip").notNull();
        meal_entity.addIntProperty("stars").notNull();
        meal_entity.addStringProperty("restaurant_name").notNull();
        meal_entity.addIntProperty("month").notNull();
        meal_entity.addIntProperty("day").notNull();
        meal_entity.addIntProperty("hour").notNull();
        meal_entity.addIntProperty("minute").notNull();
        meal_entity.addIntProperty("year").notNull();

        //  ./app/src/main/java/   ----   com/codekrypt/greendao/db is the full path
        new DaoGenerator().generateAll(schema, "./app/src/main/java/");

    }
}
