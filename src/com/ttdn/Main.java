/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        Document query = new Document("./documents/ds_IBM");
        Document d1 = new Document("./documents/ds_2012");
        Document d2 = new Document("./documents/ds_2013");
        Document d3 = new Document("./documents/ds_2014");
        Document d4 = new Document("./documents/ds_2015");
         */
        Document query = new Document("./documents/query.txt");
        Document d1 = new Document("./documents/ninh1.txt");
        Document d2 = new Document("./documents/ninh2.txt");
        
        ArrayList<Document> documents = new ArrayList<>();
        documents.add(query);
        documents.add(d1);
        documents.add(d2);
       // documents.add(d3);
      //  documents.add(d4);

        Corpus corpus = new Corpus(documents);
        VectorSpaceModel vectorSpace = new VectorSpaceModel(corpus);
        for (int i = 1; i < documents.size(); i++) {
            Document doc = documents.get(i);
            System.out.println("\nComparing to " + doc);
            System.out.println(vectorSpace.cosineSimilarity(query, doc));
        }

    }

}
