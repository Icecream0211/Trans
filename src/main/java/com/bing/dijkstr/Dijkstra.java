package com.bing.dijkstr;

import java.util.*;

public class Dijkstra {

    //定义顶点Vertex类
    private List<Vertex> vertexList; //图的顶点集
    private Map<Vertex, List<Edge>> ver_edgeList_map;  //图的每个顶点对应的有向边

    public Dijkstra(List<Vertex> vertexList, Map<Vertex, List<Edge>> ver_edgeList_map) {
        this.vertexList = vertexList;
        this.ver_edgeList_map = ver_edgeList_map;
    }
    public void setRoot(Vertex v) {
        v.setParent(null);
        v.setAdjuDist(0);
    }

    private void updateChildren(Vertex v) {

        if (v == null) {
            return;
        }
        if (ver_edgeList_map.get(v) == null || ver_edgeList_map.get(v).size() == 0) {
            return;
        }
        List<Vertex> childrenList = new LinkedList<Vertex>();
        List<Edge> vEdgeLists = ver_edgeList_map.get(v);
        for (Edge e : vEdgeLists) {
            Vertex childVertex = e.getEndVertex();
            //如果子节点之前未知,则把当前子节点加入更新列表
            if (!childVertex.isKnown()) {
                childVertex.setKnown(true);
                childVertex.setAdjuDist(v.getAdjuDist() + e.getWeight());
                childVertex.setParent(v);
                childrenList.add(childVertex);
            }
            //子节点之前已知,则比较子节点的adjudist&&nowDist
            int nowDist = v.getAdjuDist() + e.getWeight();
            if (nowDist >= childVertex.getAdjuDist()) {
                continue;
            } else {
                childVertex.setAdjuDist(nowDist);
                childVertex.setParent(v);
            }
        }
        //更新每一个子节点
        for (Vertex vc : childrenList) {
            updateChildren(vc);
        }
    }

    public Map<String,String> dijkstraTravasal(int ...args){
        System.out.println(args.length);
        if (args.length==2&&args[0]==args[1]){
            int[] newArgs = new int[3];
            newArgs = Arrays.copyOf(args, 3);
            newArgs[0] = args[0];
            int oriend = args[1];
            int newend = oriend+1;
            if (newend>=vertexList.size()){
                newend = oriend-1;
            }
            newArgs[1] = newend;
            newArgs[2] = oriend;
            args = newArgs;
        }
        Map<String,String> finalRouteWight = new HashMap<String, String>();
        for (int i=0;i<args.length;i++){
            if ((i+1)<=(args.length-1)&&args[args.length-1]<=(vertexList.size()-1)){
                int start = args[i];
                int end = args[i+1];
                Map<String,String> routeWight = this.dijkstraTravasalT(start,end);
                finalRouteWight.put("path",(finalRouteWight.get("path")==null?"":finalRouteWight.get("path"))+routeWight.get("path"));
                finalRouteWight.put("wight",String.valueOf(Integer.valueOf(finalRouteWight.get("wight")==null?"0":finalRouteWight.get("wight"))+ Integer.valueOf(routeWight.get("wight"))));
            }

        }
        return finalRouteWight;

    }


    //从初始节点开始递归更新邻接表

    /**
     * @param startIndex dijkstra遍历的起点节点下标
     * @param destIndex  dijkstra遍历的终点节点下标
     */
    public Map<String,String> dijkstraTravasalT(int startIndex, int destIndex) {

        //this.revort();

        Vertex start = vertexList.get(startIndex);
        Vertex dest = vertexList.get(destIndex);
        String path = "[" + dest.getName() + "]";
        setRoot(start);
        updateChildren(vertexList.get(startIndex));
        int shortest_length = dest.getAdjuDist();
        while ((dest.getParent() != null) && (!dest.equals(start))) {
            path = "[" + dest.getParent().getName() + "] --> " + path;
            dest = dest.getParent();
        }
        Map<String,String> routeWight = new HashMap<String, String>();
        routeWight.put("path",path);
        routeWight.put("wight",String.valueOf(shortest_length));

        System.out.println("[" + vertexList.get(startIndex).getName() + "] to [" +
                vertexList.get(destIndex).getName() + "] dijkstra shortest path:: " + path);
        System.out.println("shortest length::" + shortest_length);
        return routeWight;
    }

    private void revort() {
        for (Vertex v:vertexList){
            v.setKnown(false);
            v.setAdjuDist(0);
            v.setParent(null);
        }
    }
}

