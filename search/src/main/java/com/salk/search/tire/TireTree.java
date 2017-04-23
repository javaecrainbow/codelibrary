package com.salk.search.tire;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lijingjing on 2016/9/14.
 */
public class TireTree {
    private TrieNode root;

    public TireTree() {
        root = new TrieNode();
        root.setNodeChar('#');
    }

    public TrieNode getRoot() {
        return root;
    }

    public void setRoot(TrieNode root) {
        this.root = root;
    }

    public void addNode(TrieNode root, String word, String oriWord) {
        if (word == null || word.length() == 0) {
            return;
        }
        int k = word.charAt(0) - 'a';
        TrieNode[] childNodes = root.getChildNodes();
        if (childNodes[k] == null) {
            TrieNode node = new TrieNode();
            node.setNodeChar(word.charAt(0));
            root.addChildNode(k, node);
        }
        root.getChildNodes()[k].getWords().add(oriWord);
        String nextWord = word.substring(1);
        if (nextWord.length() == 0) {
            Integer frequency = root.getChildNodes()[k].getFrequency();
            frequency++;
        } else {
            addNode(root.getChildNodes()[k], nextWord, oriWord);
        }
    }


    public Set<String> search(TrieNode node, String word) {
        if (word.length() == 0) {
            return node.getWords();
        }
        int k = word.charAt(0) - 'a';
        String nextWord = word.substring(1);
        if (StringUtils.isBlank(nextWord)) {
            return node.getChildNodes()[k].getWords();
        }
        return search(node.getChildNodes()[k], nextWord);
    }

    public void delNode(TrieNode root, String word, String oriWord) {
        if (word == null || word.length() == 0) {
            return;
        }
        int k = word.charAt(0) - 'a';
        if (root.getChildNodes()[k] == null) {
            return;
        }
        String nextWord = word.substring(1);
        if (nextWord.length() == 0 && root.getChildNodes()[k].getFrequency() > 0) {
            Integer frequency = root.getChildNodes()[k].getFrequency();
            frequency--;
        }
        root.getChildNodes()[k].getWords().remove(oriWord);
        delNode(root.getChildNodes()[k], nextWord, oriWord);
    }

    public void updateNode(TrieNode root, String newWord, String oldWord, String oriWord) {
        delNode(root, oldWord, oriWord);
        addNode(root, newWord, oriWord);
    }

    public void update() {

    }

}
