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
	
	public void removeVertex(Vertex vertex) {
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
	
	public void removeEdge(Vertex vertex1, Vertex vertex2, int weight) {
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
	
	public Vector<Vector<Edge>> getIncidenceList() {
		Vector<Vector<Edge>> incidenceList = new Vector<>(vertexList.size());

		for (Vertex vertex : vertexList) {
			Vector<Edge> vEdges = new Vector<>();

			for (Edge edge : edgeList) {
				if (edge.hasVertex(vertex)) {
					vEdges.add(edge);
				}
			}

			incidenceList.add(vEdges);
		}

		return incidenceList;
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
			
			Vector<Edge> edges = getIncidenceList().get(vertexList.indexOf(start));
			for (Edge e : edges) {
				Vertex vertex = e.adjacentVertex(start);
				if (!V.contains(vertex) && !S.contains(vertex)) {
					S.add(vertex);
				}
			}
		}
		
		if (V.size() != vertexList.size()) {
			return false;
		}
		
		return true;
	}

	public String toString() {
		if (vertexList.size() == 0) {
			return "Graph is empty";
		}

		Vector<Vertex> vertexList = this.vertexList;
		Vector<Vector<Edge>> vertexEdges = this.getIncidenceList();

		Collections.sort(vertexList);

		String str = new String("");

		for (int i = 0; i < vertexList.size(); i++) {
			Vertex vertex = vertexList.get(i);
			str += vertex.number() + ": ";

			for (Edge edge: vertexEdges.get(i)) {
				str += edge.adjacentVertex(vertex).number() + " (";
				str += edge.weight() + ")";
				str += " -> ";
			}

			str += "\n";
		}

		return str;
	}
}
