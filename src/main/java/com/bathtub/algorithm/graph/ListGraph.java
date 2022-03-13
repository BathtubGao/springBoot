package com.bathtub.algorithm.graph;

import com.bathtub.algorithm.union.UnionFindNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import java.util.*;

/**
 * @author 17031612
 * @date 2021/12/30
 */
public class ListGraph<V, E> extends Graph<V, E>{
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public void printGraph(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, Vertex<V, E>> entry : vertices.entrySet()) {
            sb.append("顶点：").append(entry.getKey()).append("指向：[");
            for (Edge<V, E> edge : entry.getValue().outEdges) {
                sb.append(edge.to.value).append("(").append(edge.weight).append(")").append(" ");
            }
            sb.append("] 被指向：[");
            for (Edge<V, E> edge : entry.getValue().inEdges) {
                sb.append(edge.from.value).append("(").append(edge.weight).append(")").append(" ");
            }
            sb.append("] \n\r");
        }
        System.out.println(sb);
    }

    @Override
    public void addVertex(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new Vertex<>(v));
        }
    }

    @Override
    public void removeVertex(V v) {
        Vertex vertex = vertices.get(v);
        if (null == vertex) {
            return;
        }
        vertices.remove(v);
        for(Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            edges.remove(edge);
            iterator.remove();
        }

        for(Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator();iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            edges.remove(edge);
            iterator.remove();
        }
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weigt) {
        Vertex fromVertex = vertices.get(from);
        if (null == fromVertex) {
            fromVertex = new Vertex(from);
            vertices.put(from, fromVertex);
        }
        Vertex toVertex = vertices.get(to);
        if (null == toVertex) {
            toVertex = new Vertex(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E> edge = new Edge<V, E>(fromVertex, toVertex, weigt);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex fromVertex = vertices.get(from);
        Vertex toVertex = vertices.get(to);
        if (null == fromVertex || null == toVertex) {
            return;
        }
        Edge<V, E> edge = new Edge<V, E>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public String bfs(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) {
            return "";
        }
        Set<Vertex<V, E>> hasSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        Queue<Vertex<V, E>> vertexList = new LinkedList<>();
        vertexList.offer(vertex);
        hasSet.add(vertex);
        while(!vertexList.isEmpty()) {
            Vertex<V, E> each = vertexList.poll();
            sb.append(each.value).append(" ");
            Set<Edge<V, E>> edges = each.outEdges;
            for(Edge edge : edges) {
                if (!hasSet.contains(edge.to)) {
                    vertexList.offer(edge.to);
                    hasSet.add(edge.to);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String dfs(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) {
            return "";
        }
        Set<Vertex<V, E>> hasSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        dfs(vertex, sb, hasSet);
        return sb.toString();
    }


    private void dfs(Vertex<V, E> vertex, StringBuilder sb, Set<Vertex<V, E>> hasSet) {
        if (null == vertex) {
            return;
        }
        sb.append(vertex.value).append("  ");
        hasSet.add(vertex);
        Set<Edge<V, E>> edges = vertex.outEdges;
        for (Edge<V, E> edge : edges) {
            if (!hasSet.contains(edge.to)) {
                dfs(edge.to, sb, hasSet);
            }
        }
    }

    @Override
    public List<V>  topoSort() {
        if (MapUtils.isEmpty(vertices)) {
            return null;
        }
        List<V> topoList = new ArrayList<>(this.verticesSize());
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> vertexCount = new HashMap<>();
        for (Map.Entry<V, Vertex<V, E>> entry : vertices.entrySet()) {
            Vertex<V, E> vertexEntry = entry.getValue();
            vertexCount.put(vertexEntry, vertexEntry.inEdges.size());
            if (CollectionUtils.isEmpty(vertexEntry.inEdges)) {
                // 查找无入度顶点
                queue.offer(vertexEntry);
            }
        }
        while(!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            topoList.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int count = vertexCount.get(edge.to) - 1;
                if (count == 0) {
                    queue.offer(edge.to);
                } else {
                    vertexCount.put(edge.to, count);
                }
            }
        }

        return topoList;
    }

    @Override
    public Set<EdgeInfo<V, E>> prime() {
        if (MapUtils.isEmpty(vertices)) {
            return null;
        }
        Set<EdgeInfo<V, E>> result = new HashSet<>();
        Set<Vertex<V, E>> vertexSet = new HashSet<>();
        Vertex<V, E> begin = vertices.entrySet().iterator().next().getValue();
        vertexSet.add(begin);
        PriorityQueue<Edge<V, E>> queue = new PriorityQueue<>(edgeComparator);
        queue.addAll(begin.outEdges);
        while (!queue.isEmpty() && result.size() < verticesSize()) {
            Edge<V, E> edge = queue.poll();
            if (vertexSet.contains(edge.to)) continue;
            result.add(edge.info());
            vertexSet.add(edge.to);
            queue.addAll(edge.to.outEdges);
        }
        return result;
    }

    @Override
    public Set<EdgeInfo<V, E>> kruskal() {
        if (CollectionUtils.isEmpty(edges) || MapUtils.isEmpty(vertices)) return null;
        Set<EdgeInfo<V, E>> result = new HashSet<>();
        UnionFindNode<Vertex<V, E>> vertexUnion = new UnionFindNode<>(); // 建立并初始化并查集
        vertices.forEach((key, value) -> vertexUnion.init(value));
        PriorityQueue<Edge<V, E>> queue = new PriorityQueue<>(edgeComparator);
        queue.addAll(edges);
        while (!queue.isEmpty() && result.size() < verticesSize()) {
            Edge<V, E> edge = queue.poll(); // 最小边
            if (vertexUnion.isSame(edge.to, edge.from)) continue;
            vertexUnion.union(edge.to,edge.from); // 合并集合
            result.add(edge.info());
        }
        return result;
    }

    @Override
    public Map<V, PathInfo<V, E>> dijkstra(V begin) {
        if (null == begin) return null;
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) return null;
        Map<V, PathInfo<V, E>> result = new HashMap<>();
        result.put(vertex.value, new PathInfo<>(weightManager.zero()));
        PriorityQueue<Edge<V, E>> edgeQueue = new PriorityQueue(edgeComparator);
        vertex.outEdges.stream().forEach(each -> {
            edgeQueue.add(each);
        });
        while (!edgeQueue.isEmpty()) {
            Edge<V, E> edge = edgeQueue.poll();
            if (result.containsKey(edge.to.value)) continue; // 已存在
            relaxPath(edge, result);
            edgeQueue.addAll(edge.to.outEdges);
        }
        return result;
    }

    private boolean relaxPath(Edge<V, E> edge, Map<V, PathInfo<V, E>> pathMap) {
        PathInfo<V, E> fromPath = pathMap.get(edge.from.value);
        if(null == fromPath) return false; // from无路径时直接返回
        PathInfo<V, E> toPath = pathMap.get(edge.to.value);
        E newToWeight = weightManager.add(fromPath.weight, edge.weight);;
        if (null == toPath) {
            toPath = new PathInfo<>(newToWeight);
            toPath.getPath().addAll(fromPath.path);
            toPath.getPath().add(edge.info());
            pathMap.put(edge.to.value, toPath);
            return true;
        } else {
            E oldToWeight = toPath.weight;
            if (weightManager.compare(newToWeight, oldToWeight) < 0) {
                toPath = new PathInfo<>(newToWeight);
                toPath.getPath().addAll(fromPath.path);
                toPath.getPath().add(edge.info());
                pathMap.put(edge.to.value, toPath);
                return true;
            }
            return false;
        }
    }

    @Override
    public Map<V, PathInfo<V, E>> bellmanFord(V begin) throws Exception {
        if (null == begin) return null;
        Vertex<V, E> vertex = vertices.get(begin);
        if (null == vertex) return null;
        Map<V, PathInfo<V, E>> result = new HashMap<>();
        result.put(vertex.value, new PathInfo<>(weightManager.zero()));
        int num = verticesSize() - 1;
        while (num > 0) {
            edges.stream().forEach(each -> {
                relaxPath(each, result);
            });
            num--;
        }
        for(Edge<V, E> each : edges) {
            if (relaxPath(each, result)) {
                throw new Exception("存在负权环！");
            }
        }
        return result;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> floyd() {
        Map<V, Map<V, PathInfo<V, E>>> result = new HashMap<>();
        edges.stream().forEach(each -> {
            Map<V, PathInfo<V, E>> pathMap = result.get(each.from.value);
            if (null == pathMap) {
                pathMap = new HashMap<>();
                result.put(each.from.value, pathMap);
            }
            PathInfo<V, E> path = new PathInfo<>(each.weight);
            path.path.add(each.info());
            pathMap.put(each.to.value, path);
        });
        vertices.forEach((V k, Vertex<V, E> vertexK) -> {
            vertices.forEach((V i, Vertex<V, E> vertexI) -> {
                vertices.forEach((V j, Vertex<V, E> vertexJ) -> {
                    if (Objects.equals(k, i) || Objects.equals(i, j) || Objects.equals(k, j))
                        return;
                    PathInfo<V, E> pathI2J = null == result.get(i) ? null : result.get(i).get(j);
                    PathInfo<V, E> pathI2K = null == result.get(i) ? null : result.get(i).get(k);
                    PathInfo<V, E> pathK2J = null == result.get(k) ? null : result.get(k).get(j);
                    if (null == pathI2K) return;
                    if (null == pathK2J) return;
                    E weightIKJ = weightManager.add(pathI2K.weight, pathK2J.weight);
                    if (null != pathI2J && weightManager.compare(weightIKJ, pathI2J.weight) >= 0)  return;
                    if (null == pathI2J) {
                        pathI2J = new PathInfo<>(weightIKJ);
                        result.get(i).put(j, pathI2J);
                    } else {
                        pathI2J.path.clear();
                    }
                    pathI2J.weight = weightIKJ;
                    pathI2J.path.clear();
                    pathI2J.path.addAll(pathI2K.path);
                    pathI2J.path.addAll(pathK2J.path);
                });
            });
        });
        return result;
    }

    /**
     * 顶点
     */
    private class Vertex<V, E>{
        V value; // 顶点元素
        Set<Edge<V, E>> inEdges = new HashSet<>(); // 入边集合
        Set<Edge<V, E>> outEdges = new HashSet<>(); // 出边集合

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    /**
     * 边
     */
    private class Edge<V, E> {
        E weight; // 权值
        Vertex<V, E> from; // 来自顶点
        Vertex<V, E> to; // 指向顶点

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        EdgeInfo<V,E> info() {
            return new EdgeInfo<V, E>(this.weight, this.from.value, this.to.value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<V, E> edge = (Edge<V, E>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }


}
