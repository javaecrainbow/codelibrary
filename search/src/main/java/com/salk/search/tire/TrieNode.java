package com.salk.search.tire;

import java.util.*;

/**
 * Created by lijingjing on 2016/9/14.
 */
public class TrieNode {
    private Integer frequency;
    private char nodeChar;
    private TrieNode[] childNodes;
    private Set<String> Words = new HashSet<String>();

    public TrieNode() {
        childNodes = new TrieNode[36];
        frequency = 0;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public char getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(char nodeChar) {
        this.nodeChar = nodeChar;
    }

    public TrieNode[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(TrieNode[] childNodes) {
        this.childNodes = childNodes;
    }
    public void addChildNode(int i,TrieNode trieNode){
        this.childNodes[i]=trieNode;
    }

    public Set<String> getWords() {
        return Words;
    }

    public void setWords(Set<String> words) {
        Words = words;
    }
}
