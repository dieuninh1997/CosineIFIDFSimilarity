/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 *Văn bản
 * @author Admin
 */

/*
 * This class represents a corpus of documents.
 * It will create an inverted index for these documents.
 */
public class Corpus {

    /**
     * An arraylist of all documents in the corpus. de
     */
    private ArrayList<Document> documents;
    /**
     * The inverted index. It will map a term to a set of documents that contain
     * that term.
     */
    private HashMap<String, Set<Document>> invertedIndex;

    /**
     * The constructor - it takes in an arraylist of documents. It will generate
     * the inverted index based on the documents.
     *
     * @param documents the list of documents
     */
    public Corpus(ArrayList<Document> documents) {
        this.documents = documents;
        invertedIndex = new HashMap<>();

        createInvertedIndex();
    }

    /**
     * This method will create an inverted index.
     */
    private void createInvertedIndex() {
        System.out.println("Creating the inverted index");

        for (Document document : documents) {
            Set<String> terms = document.getTermList();
            //Voi moi tai lieu trong tap tai lieu
            //Lay ds tu tron tai lieu ra
            //Xet tung tu 
            for (String term : terms) {
                if (invertedIndex.containsKey(term)) {
                    //neu term da duoc tao invertedIndex
                    Set<Document> list = invertedIndex.get(term);
                    //tra ve documents chua term
                    list.add(document);
                } else {
                    //neu tu chua duoc tao invertedIndex
                    Set<Document> list = new TreeSet<Document>();
                    list.add(document);
                    invertedIndex.put(term, list);
                }
            }
        }
    }

    /**
     * This method returns the idf for a given term.
     *
     * @param term a term in a document
     * @return the idf for the term
     */
    public double getInverseDocumentFrequency(String term) {
        if (invertedIndex.containsKey(term)) {
            double size = documents.size();
            Set<Document> list = invertedIndex.get(term);
            double documentFrequency = list.size();

            // return Math.log10(size / documentFrequency);//log_e
            return Math.log(size / documentFrequency);//log_e
        } else {
            return 0;
        }
    }

    /**
     * @return the documents
     */
    public ArrayList<Document> getDocuments() {
        return documents;
    }

    /**
     * @return the invertedIndex
     */
    public HashMap<String, Set<Document>> getInvertedIndex() {
        return invertedIndex;
    }

}
