package com.loaves.tool_cabinet.pattern;

public class Node implements Comparable<Node>{

    int weight;
    Node lChild;
    Node rChild;
    public Node(int weight){
        this.weight = weight;
    }
    public Node(int weight,Node lChild,Node rChild){
        this.weight = weight;
        this.lChild = lChild;
        this.rChild = rChild;
    }
    @Override
    public int compareTo(Node o){
        return new Integer(this.weight).compareTo(new Integer(o.weight));
    }
}
