package com.mycompany.mavenproject1;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tss
 */
public class mainMongodb {

    public static void main(String[] args) {
        //set connessione
        MongoClient mongo = new MongoClient("localhost", 27017);
        //set schema db
        MongoDatabase database = mongo.getDatabase("dbregistro");
        //tabella collection
        MongoCollection<Document> registri = database.getCollection("registri");
        //ora siamo sulla tabella collection
        //creo record-documento
        Document document1 = new Document("title", "TSS java")
                .append("codice", "C1-112-2022-0")
                .append("anno gestione", "2021-2022");
        registri.insertOne(document1);
        Document document2 = new Document("title", "TSS reti")
                .append("codice", "C1-112-2022-1")
                .append("anno gestione", "2021-2022");
        Document document3 = new Document("title", "TSS web")
                .append("codice", "C1-112-2022-2")
                .append("anno gestione", "2021-2022");
        List<Document> list = new ArrayList<Document>();
        list.add(document2);
        list.add(document3);
        registri.insertMany(list);

        // Getting the iterable object
        FindIterable<Document> iterDoc = registri.find();
        int i = 1;
        // Getting the iterator
        Iterator doc = iterDoc.iterator(); //oggetto iteratore che cicla sull elenco di oggetti
        while (doc.hasNext()) {
            System.out.println(doc.next()); //doc.next estrae il documento dall elenco
            i++;
        }

    }

}
