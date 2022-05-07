package ga.justreddy.wiki.api.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public final class Mongo {

    private static MongoDatabase database;
    private static MongoClient client;
    private static boolean MongoConnected = false;

    public static void connect(String URI) {
        client = new MongoClient(URI);
        MongoConnected = true;
        database = client.getDatabase("rparties");
    }

    public static MongoCollection<Document> getDatabase(String collection) {
        return database.getCollection(collection);
    }

    public static boolean isMongoConnected() {
        return MongoConnected;
    }

}
