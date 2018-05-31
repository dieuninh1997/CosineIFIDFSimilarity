/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ttdn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 ***
 * This class represents one document. It will keep track of the term
 * frequencies.
 *
 * @author Admin
 */
public class Document implements Comparable<Document> {

    // từ - số lần xh của từ trong văn bản
    /**
     * A hashmap for term frequencies. Maps a term to the number of times this
     * terms appears in this document.
     */
    private final HashMap<String, Integer> termFrequency;

    /**
     * The name of the file to read.
     */
    //đọc tài liệu từ file
    private final String filename;

    public Document(String filename) {
        this.filename = filename;
        termFrequency = new HashMap<>();
        readFileAndPreProcess();
        for (String name : termFrequency.keySet()) {

            String key = name;
            String value = termFrequency.get(name).toString();
            System.out.println("TermFrequency : <" + key + "," + value + ">");
        }
    }

    /**
     * This method will read in the file and do some pre-processing. The
     * following things are done in pre-processing: Every word is converted to
     * lower case. Every character that is not a letter or a digit is removed.
     * We don't do any stemming. Once the pre-processing is done, we create and
     * update the
     */
    private void readFileAndPreProcess() {
        try {
            Scanner in = new Scanner(new File(filename));
            System.out.println("Reading file: " + filename + " and preprocessing");
            while (in.hasNext()) {
                String nextWord = in.next();
                String filteredWord = nextWord.replaceAll("[^A-Za-zđĐáàảãạăắằẳẵặâấầẩẫậđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵ0-9]", "").toLowerCase();
                if (!(filteredWord.equalsIgnoreCase(""))) {
                    if (termFrequency.containsKey(filteredWord)) {
                        int oldCount = termFrequency.get(filteredWord);
                        termFrequency.put(filteredWord, ++oldCount);
                    } else {
                        termFrequency.put(filteredWord, 1);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will return the term frequency for a given word. If this
     * document doesn't contain the word, it will return 0
     *
     * @param word The word to look for
     * @return the term frequency for this word in this document
     */
    public double getTermFrequency(String word) {
        if (termFrequency.containsKey(word)) {
            return termFrequency.get(word);
        } else {
            return 0;
        }
    }

    /**
     * This method will return a set of all the terms which occur in this
     * document. ko trùng
     *
     * @return a set of all terms in this document
     */
    public Set<String> getTermList() {
        return termFrequency.keySet();
    }

    @Override
    public int compareTo(Document o) {
        return filename.compareTo(o.getFilename());
    }

    public String getFilename() {
        return filename;
    }

    /**
     * This method is used for pretty-printing a Document object.
     *
     * @return the filename
     */
    public String toString() {
        return filename;
    }
}
