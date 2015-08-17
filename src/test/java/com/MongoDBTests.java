package com;

import com.mongodb.*;
import com.utils.db.MongoUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dqf on 2015/8/14.
 */
public class MongoDBTests {
    @Test
    public void mongoTest(){
        DB myMongo = MongoUtil.getDB(null);
        myMongo.createCollection("testcollection", null);   //创建一个集合
        DBCollection userCollection = myMongo.getCollection("testcollection");
        BasicDBObject doc = new BasicDBObject("name", "MongoDB2").append("type", "database")
                .append("count", 5)
                .append("info", new BasicDBObject("x", 205).append("y", 434));
        //userCollection.insert(doc);

        //查询第一个
        DBObject ob1 = userCollection.findOne();
        //System.out.println(ob1);

        //条件查询
        BasicDBObject inQuery = new BasicDBObject();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(5);
        inQuery.put("count", new BasicDBObject("$in", list));
        DBCursor cursor = userCollection.find(inQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        //范围 2<count<6
        BasicDBObject gtQuery = new BasicDBObject();
        gtQuery.put("count", new BasicDBObject("$gt", 0).append("$lt", 6));
        DBCursor cursor1 = userCollection.find(gtQuery);
        while(cursor1.hasNext()) {
            System.out.println(cursor1.next());
        }

        System.out.println("-----------------------------------------");
        //count!=1
        BasicDBObject neQuery = new BasicDBObject();
        neQuery.put("count", new BasicDBObject("$ne", 1));
        DBCursor cursor2 = userCollection.find(neQuery);
        while(cursor2.hasNext()) {
            System.out.println(cursor2.next());
        }

        System.out.println("-----------------------------------------");
        //匹配
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("name",
                new BasicDBObject("$regex", "Mongo.*[1-3]")
                        .append("$options", "i"));

        System.out.println(regexQuery.toString());
        DBCursor cursor3 = userCollection.find(regexQuery);
        while (cursor3.hasNext()) {
            System.out.println(cursor3.next());
        }
    }
}
