package mongodb;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.print.Doc;
import java.io.*;
import java.util.Properties;

public class MongoJDBC {

    public static MongoCollection<Document> getConnection() throws IOException {
        MongoDatabase mongoDatabase =null;
        MongoCollection<Document> collection = null;
        Properties properties = new Properties();
        InputStream inputStream = MongoJDBC.class.getResourceAsStream("/Mongodb.properties");
        properties.load(inputStream);
        String database = properties.getProperty("database");
        String collection1 = properties.getProperty("collection");
        int port = Integer.parseInt(properties.getProperty("port"));
        String url = properties.getProperty("url");
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( url, port );

            // 连接到数据库
            mongoDatabase = mongoClient.getDatabase(database);
            System.out.println("Connect to database successfully");

            //连接数据表
            collection = mongoDatabase.getCollection(collection1);
            System.out.println("集合 test 选择成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return collection;
    }

    public static void main(String[] args) throws IOException {

        MongoCollection<Document> connection = MongoJDBC.getConnection();
        Document document = new Document();
        document.append("name","ldiitao");
        FindIterable<Document> findIterable = connection.find(document);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next().get("password"));
        }
    }
}
