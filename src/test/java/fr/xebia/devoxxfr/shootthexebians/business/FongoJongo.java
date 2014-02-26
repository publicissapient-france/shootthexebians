package fr.xebia.devoxxfr.shootthexebians.business;

import com.github.fakemongo.Fongo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.rules.ExternalResource;

public class FongoJongo extends ExternalResource {

    private final Jongo jongo;
    private static FongoJongo instance;

    private FongoJongo() {
        jongo = new Jongo(new Fongo("Test").getDB("Database"));
    }

    public static FongoJongo getInstance() {
        if (instance == null) {
            instance = new FongoJongo();
        }
        return instance;
    }

    @Override
    protected void after() {
        super.after();
        for (String collectionName : jongo.getDatabase().getCollectionNames()) {
            getCollection(collectionName).drop();
        }
    }

    public MongoCollection getCollection(String collectionName) {
        return jongo.getCollection(collectionName);
    }
}
