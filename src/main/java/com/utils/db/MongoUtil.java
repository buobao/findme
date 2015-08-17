package com.utils.db;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoOptions;
import org.apache.commons.lang3.StringUtils;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Created by dqf on 2015/8/14.
 */
public class MongoUtil {
    private static String HOST = "localhost";
    private static int PORT = 27017;
    private static int POOLSIZE = 100;
    private static int BLOCKSIZE = 100;
    private static String DB_NAME = "";
    private static Mongo mongo = null;

    private MongoUtil(){}

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("application");
        if(bundle == null){
            throw  new IllegalArgumentException("[application.properties] is not found!");
        }

        HOST = bundle.getString("spring.data.mongodb.url");
        PORT = Integer.valueOf(bundle.getString("spring.data.mongodb.port"));
        POOLSIZE = Integer.valueOf(bundle.getString("mongodb.poolsize"));
        BLOCKSIZE = Integer.valueOf(bundle.getString("mongodb.blocksize"));
        DB_NAME = bundle.getString("mongodb.dbname");

        initDBPrompties();
    }

    public static DB getDB(String dbName){
        if (dbName!=null && StringUtils.equals(dbName,"")){
            return mongo.getDB(dbName);
        }
        return mongo.getDB(DB_NAME);
    }

    private static void initDBPrompties(){
        try{
            mongo = new Mongo(HOST,PORT);
            MongoOptions opt = mongo.getMongoOptions();
            opt.connectionsPerHost = POOLSIZE;
            opt.threadsAllowedToBlockForConnectionMultiplier = BLOCKSIZE;
        }catch (UnknownHostException e){

        }catch (MongoException e){}
    }

}
