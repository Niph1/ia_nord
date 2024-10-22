package de.hbrs.ia.code;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ManagePersonalImpl implements ManagePersonal {

    private MongoClient mongoClient;
    private MongoDatabase database;
    public MongoCollection<Document> salesmenCollection;
    public MongoCollection<Document> performanceRecordCollection;

    public ManagePersonalImpl() {

        //unsure if this or iar-mongo.inf.h-brs.de:27017 was to be used. 
        mongoClient = MongoClients.create("mongodb://localhost:27017"); 

        //SmartHooverDB seems like a fitting name. 
        database = mongoClient.getDatabase("SmartHooverDB");

        //names self explanatory 
        salesmenCollection = database.getCollection("salesMen");
        performanceRecordCollection = database.getCollection("performanceRecords");
    }
    //CRUD C
    @Override
    public void createSalesMan(SalesMan salesMan) {
        salesmenCollection.insertOne(salesMan.toDocument());
    }
    
    //CRUD C/U depending on if a record already exists
    @Override
    public void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        performanceRecordCollection.insertOne(record.toDocument());
    }

    //CRUD R
    @Override
    public SalesMan readSalesMan(int sid) {
        Document doc = salesmenCollection.find(eq("sid", sid)).first();
        if (doc != null) {
            return SalesMan.fromDocument(doc);
        }
        return null;
    }
    
    //CRUD R
    @Override
    public List<SalesMan> readAllSalesMen() {
        List<SalesMan> salesMenList = new ArrayList<>();
        for (Document doc : salesmenCollection.find()) {
            salesMenList.add(SalesMan.fromDocument(doc));
        }
        return salesMenList;
    }

    //CRUD R
    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        List<SocialPerformanceRecord> records = new ArrayList<>();
        for (Document document : performanceRecordCollection.find(eq("sid", salesMan.getId()))) {
            records.add(SocialPerformanceRecord.fromDocument(document, salesMan));
        }
        return records;
    }

    //CRUD R, year integrated
    @Override
    public SocialPerformanceRecord readSocialPerformanceRecord(SalesMan salesMan, int year) {
        SocialPerformanceRecord record;
        Document document = performanceRecordCollection.find(eq("sid", salesMan.getId())).filter(eq("year", year)).first();
        record = (SocialPerformanceRecord.fromDocument(document, salesMan));
        return record;
    }

    //CRUD D
    @Override
    public void deleteSalesMan(int sid) {
        salesmenCollection.deleteOne(eq("sid", sid));
        performanceRecordCollection.deleteMany(eq("sid", sid));
    }


    public void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
