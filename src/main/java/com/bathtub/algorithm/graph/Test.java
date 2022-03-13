package com.bathtub.algorithm.graph;

import java.util.Map;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws Exception {
        bellmanFord();
    }

    static void testPrime() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_02);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.prime();
        for (Graph.EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void testKruskal() {
        Graph<Object, Double> graph = undirectedGraph(Data.MST_02);
        Set<Graph.EdgeInfo<Object, Double>> infos = graph.kruskal();
        for (Graph.EdgeInfo<Object, Double> info : infos) {
            System.out.println(info);
        }
    }

    static void dijkstra() {
        Graph<Object, Double> graph = directedGraph(Data.SP);
        Map<Object, Graph.PathInfo<Object, Double>> sp = graph.dijkstra("A");
        if (sp == null) return;
        sp.forEach((Object v, Graph.PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }

    static void bellmanFord() throws Exception {
        Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
        Map<Object, Graph.PathInfo<Object, Double>> sp = graph.bellmanFord("A");
        if (sp == null) return;
        sp.forEach((Object v, Graph.PathInfo<Object, Double> path) -> {
            System.out.println(v + " - " + path);
        });
    }

    static void floyd() {
        Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> sp = graph.floyd();
        sp.forEach((Object from, Map<Object, Graph.PathInfo<Object, Double>> paths) -> {
            System.out.println(from + "---------------------");
            paths.forEach((Object to, Graph.PathInfo<Object, Double> path) -> {
                System.out.println(to + " - " + path);
            });
        });
    }

    static Graph.WeightManager<Double> myWeightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }

        @Override
        public Double zero() {
            return 0.0;
        }
    };

    /**
     * 有向图
     */
    private static Graph<Object, Double> directedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(myWeightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
            }
        }
        return graph;
    }

    /**
     * 无向图
     * @param data
     * @return
     */
    private static Graph<Object, Double> undirectedGraph(Object[][] data) {
        Graph<Object, Double> graph = new ListGraph<>(myWeightManager);
        for (Object[] edge : data) {
            if (edge.length == 1) {
                graph.addVertex(edge[0]);
            } else if (edge.length == 2) {
                graph.addEdge(edge[0], edge[1]);
                graph.addEdge(edge[1], edge[0]);
            } else if (edge.length == 3) {
                double weight = Double.parseDouble(edge[2].toString());
                graph.addEdge(edge[0], edge[1], weight);
                graph.addEdge(edge[1], edge[0], weight);
            }
        }
        return graph;
    }
}
