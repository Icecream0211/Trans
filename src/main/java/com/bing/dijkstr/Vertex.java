package com.bing.dijkstr;
/**
 * 节点
 */

public class Vertex {
    private final static int infinite_dis = Integer.MAX_VALUE;

    private String name;   //节点名字
    private boolean known;  //此节点是否已知

    private int adjuDist;   //此节点距离

    private Vertex parent;   //当前从初始化节点到此节点的最短路径下的父亲节点

    public Vertex() {
        this.known = false;
        this.adjuDist = infinite_dis;
        this.parent = null;
    }

    public Vertex(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public int getAdjuDist() {
        return adjuDist;
    }

    public void setAdjuDist(int adjuDist) {
        this.adjuDist = adjuDist;
    }


    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }


    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) {
            throw new ClassCastException("an object to compare with a Vertex");
        }

        if (this.name == null) {
            throw new NullPointerException("name of Vertex to be compared cannot be null!");

        }
        return this.name.equals(((Vertex) obj).getName());
    }


}