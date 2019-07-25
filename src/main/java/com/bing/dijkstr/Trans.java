package com.bing.dijkstr;

import com.bing.dijkstr.Dijkstra;
import com.bing.dijkstr.Edge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trans {
    public static void main(String[] args) {

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");

        List<Vertex> verList = new LinkedList<Vertex>();
        verList.add(A);
        verList.add(B);
        verList.add(C);
        verList.add(D);
        verList.add(E);
        //verList.add(F);
        //verList.add(G);


        Map<Vertex, List<Edge>> vertex_edgeList_map = new HashMap<Vertex, List<Edge>>();

        List<Edge> v1List = new LinkedList<Edge>();
        v1List.add(new Edge(A, B, 5));
        v1List.add(new Edge(A, D, 5));
        v1List.add(new Edge(A, E, 7));

        List<Edge> v2List = new LinkedList<Edge>();
        v2List.add(new Edge(B, C, 4));

        List<Edge> v3List = new LinkedList<Edge>();
        v3List.add(new Edge(C, D, 8));
        v3List.add(new Edge(C, E, 2));

        List<Edge> v4List = new LinkedList<Edge>();
        v4List.add(new Edge(D, C, 8));
        v4List.add(new Edge(D, E, 6));


        List<Edge> v5List = new LinkedList<Edge>();
        v5List.add(new Edge(E, B, 3));


        vertex_edgeList_map.put(A, v1List);
        vertex_edgeList_map.put(B, v2List);
        vertex_edgeList_map.put(C, v3List);
        vertex_edgeList_map.put(D, v4List);
        vertex_edgeList_map.put(E, v5List);
        //vertex_edgeList_map.put(v6, v6List);
        //vertex_edgeList_map.put(v7, v7List);


        Dijkstra g = new Dijkstra(verList, vertex_edgeList_map);
        //g.dijkstraTravasal(1, 3);
        //g.dijkstraTravasal(0, 4);
        Map<String,String> routeWight = g.dijkstraTravasal(4,4);
        //Map<String,String> routeWight = g.dijkstraTravasal(2,3);
        System.out.println(routeWight.get("path"));
        System.out.println(routeWight.get("wight"));


    }
}
