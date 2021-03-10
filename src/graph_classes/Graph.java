package graph_classes;

import java.util.Collections;
import java.util.Vector;

public class Graph {
	private Vector<Edge> edgeList;
	private Vector<Vertex> vertexList;
	
	public Graph() {
		edgeList = new Vector<>();
		vertexList = new Vector<>();
	}
	
	public Vector<Vertex> vertexes() {
		return vertexList;
	}
	
	public Vector<Edge> edges() {
		return edgeList;
	}
	
	public void addVertex(Vertex vertex) {
		if (!vertexList.contains(vertex)) {
			vertexList.add(vertex);
			Collections.sort(vertexList);
		}
	}
	
	public void remVertex(Vertex vertex) {
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).hasVertex(vertex)) {
				edgeList.remove(i);
				i--;
			}
		}
		vertexList.remove(vertexList.indexOf(vertex));
	}
	
	public void addVertexes(Vector<Vertex> vs) {
		for (Vertex v : vs) {
			if (!vertexList.contains(v)) {
				vertexList.add(v);
			}
		}
		Collections.sort(vertexList);
	}
	
	public void addEdge(Vertex vertex1, Vertex vertex2, int weight) {
		if (!vertexList.contains(vertex1)) {
			addVertex(vertex1);
		}
		if (!vertexList.contains(vertex2)) {
			addVertex(vertex2);
		}
		Collections.sort(vertexList);

		Edge edge = new Edge(weight, vertex1, vertex2);
		if (!edgeList.contains(edge)) {
			edgeList.add(edge);
			Collections.sort(edgeList);
		}
	}
	
	public void addEdges(Vector<Edge> edges) {
		for (Edge edge: edges) {
			if (!edgeList.contains(edge)) {
				edgeList.add(edge);
				if (!vertexList.contains(edge.vertexes().first())) {
					vertexList.add(edge.vertexes().first());
				}
				if (!vertexList.contains(edge.vertexes().last())) {
					vertexList.add(edge.vertexes().last());
				}
			}
		}
		Collections.sort(vertexList);
		Collections.sort(edgeList);
	}
	
	public void remEdge(Vertex vertex1, Vertex vertex2, int weight) {
		if (!vertexList.contains(vertex1)) {
			return;
		}
		if (!vertexList.contains(vertex2)) {
			return;
		}
		
		Edge tmpEdge = new Edge(weight, vertex1, vertex2);
		if (!edgeList.contains(tmpEdge)) {
			return;
		}
		
		for (int i = 0; i < edgeList.size(); i++) {
			if (edgeList.get(i).equals(tmpEdge)) {
				edgeList.remove(i);
			}
		}
		Collections.sort(vertexList);
		Collections.sort(edgeList);
	}
	
	public boolean containsVertex(Vertex v) {
		return vertexList.contains(v);
	}
	
	public boolean containsEdge(Edge e) {
		return edgeList.contains(e);
	}
	
	public Vector<Vector<Edge>> incidenceList() {
		Vector<Vector<Edge>> iList = new Vector<>(vertexList.size());

		for (Vertex vertex : vertexList) {
			Vector<Edge> vEdges = new Vector<>();

			for (Edge edge : edgeList) {
				if (edge.hasVertex(vertex)) {
					vEdges.add(edge);
				}
			}

			iList.add(vEdges);
		}

		return iList;
	} 
	

	public boolean isConnected() {
		Vector<Vertex> V = new Vector<>();
		Vector<Vertex> S = new Vector<>();
		Vertex start;
		
		S.add(vertexList.get(0));
		while (!S.isEmpty()) {
			start = S.get(0);
			S.remove(0);
			V.add(start);
			
			Vector<Edge> edges = incidenceList().get(vertexList.indexOf(start));
			for (Edge e : edges) {
				Vertex vrtx = e.adjacentVertex(start);
				if (!V.contains(vrtx) && !S.contains(vrtx)) {
					S.add(vrtx);
				}
			}
		}
		
		if (V.size() != vertexList.size()) {
			return false;
		}
		
		return true;
	}
	
}
