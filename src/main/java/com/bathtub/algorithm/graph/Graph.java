package com.bathtub.algorithm.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, E> {

    public interface WeightManager<E> {
        int compare(E w1, E w2);
        E add(E w1, E w2);
        E zero();
    }

    protected WeightManager<E> weightManager;

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public Graph() {
    }

    /**
     * 添加顶点
     */
    public abstract void addVertex(V v);

    /**
     * 删除顶点
     */
    public abstract void removeVertex(V v);

    /**
     * 顶点数量
     */
    public abstract int verticesSize();

    /**
     * 添加边
     */
    public abstract void addEdge(V from, V to);

    /**
     * 添加边
     * @param weigt 权值
     */
    public abstract void addEdge(V from, V to, E weigt);

    /**
     * 删除边
     */
    public abstract void removeEdge(V from, V to);

    /**
     * 边数量
     */
    public abstract int edgesSize();

    /**
     * 广度优先搜索
     */
    public abstract String bfs(V begin);

    /**
     * 深度优先搜索
     */
    public abstract String dfs(V begin);

    /**
     * 拓扑排序
     */
    public abstract List<V> topoSort();

    /**
     * prime算法求最小生成树
     */
    public abstract Set<EdgeInfo<V, E>> prime();

    /**
     * kruskal算法求最小生成树
     */
    public abstract Set<EdgeInfo<V, E>> kruskal();

    /**
     * dijkstra算法求最短路径
     */
    public abstract Map<V, PathInfo<V, E>> dijkstra(V begin);

    /**
     * Bellman-Ford算法求最短路径
     */
    public abstract Map<V, PathInfo<V, E>> bellmanFord(V begin) throws Exception;

    /**
     * Floyd 多源最短路径算法
     */
    public abstract Map<V, Map<V, PathInfo<V, E>>> floyd();

    /**
     * 打印
     */
    public abstract void printGraph();

    /**
     * 边信息
     */
    public static class EdgeInfo<V, E> {
        E weight;
        V from;
        V to;

        public EdgeInfo(E weight, V from, V to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "weight=" + weight +
                    ", from=" + from +
                    ", to=" + to +
                    '}';
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }
    }

    /**
     * 路径信息
     */
    public static class PathInfo<V, E> {
        List<EdgeInfo<V, E>> path = new LinkedList<>(); // 路径节点
        E weight; // 路径长度

        public PathInfo(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "path=" + path +
                    ", weight=" + weight +
                    '}';
        }

        public List<EdgeInfo<V, E>> getPath() {
            return path;
        }

        public void setPath(List<EdgeInfo<V, E>> path) {
            this.path = path;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }
    }
}
