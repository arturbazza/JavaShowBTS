package org.fundatec.btsShows;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class JavaBtsShows {

    public static void main(String[] args) {

        // Criar um cliente MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb+srv://root:pixel@btsshows.irwrj.mongodb.net/?retryWrites=true&w=majority&appName=btsShows");

        //String uri = "mongodb+srv://root:pixel@cluster0.mongodb.net/btsShows";
        //MongoClient mongoClient = MongoClients.create(uri);


        // Selecionar o banco de dados
        MongoDatabase database = mongoClient.getDatabase("btsShows");

        // Selecionar a coleção
        MongoCollection<Document> collection = database.getCollection("shows");

        // Inserir um documento na coleção
        Document show = new Document("show_id", 1)
                .append("location", "Seoul")
                .append("date", "2023-09-25")
                .append("available_tickets", 100000);
        collection.insertOne(show);

        // Exibir o documento inserido
        for (Document doc : collection.find()) {
            System.out.println(doc.toJson());
        }

        //Inserir Vários Documentos
        List<Document> shows = Arrays.asList(
                new Document("show_id", 2)
                        .append("location", "Tokyo")
                        .append("date", "2023-10-15")
                        .append("available_tickets", 50000),
                new Document("show_id", 3)
                        .append("location", "New York")
                        .append("date", "2023-11-05")
                        .append("available_tickets", 75000)
        );

        //Atualizar um Documento
        collection.updateOne(
                new Document("show_id", 1),
                new Document("$set", new Document("available_tickets", 95000))
        );

        //Deletar um Documento
        collection.deleteOne(new Document("show_id", 2));
        collection.insertMany(shows);

        // Fechar o cliente MongoDB
        mongoClient.close();
    }
}