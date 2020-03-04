package com.liusen.algorithms.chapter3_1;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    public Value get(Key key) {
        for(Node t =  first; t!= null; t = t.next){
            if(key.equals(t.key))
                return t.val;
        }
        return null;
    }
    public void put(Key key, Value val) {
        Node t = first;
        while(t != null){
            if(key.equals(t.key)){
                t.val = val;
                return;
            }
            t = t.next;
        }
        first = new Node(key, val, first);
    }
    public boolean contains(Key key)
    {
        for(Node t = first; t != null; t = t.next)
        {
            if(key.equals(t.key))
                return true;
        }
        return false;
    }
    public Iterable<Key> keys()
    {
        Queue<Key> q = new Queue<Key>();
        for (Node t = first; t != null; t = t.next)
            q.enqueue(t.key);
        return q;
    }
}
