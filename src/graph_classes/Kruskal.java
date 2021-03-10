package graph_classes;

import java.util.Collections;
import java.util.Vector;

public class Kruskal {

	Thread thread;

	private int findVertex(Vector<Vector<Vertex>> vs, Vector<Vertex> v) {
		if (v.size() > 1) {
			return-1;
		}
		
		int index = vs.indexOf(v);
		if (index != -1) {
			return index;
		}
		
		for (int i = 0; i < vs.size(); i++) {
			if (vs.get(i).contains(v.get(0))) {
				return i;
			}
		}
		return -1;
	}
	
	public Graph search(Graph g) {
		Vector<Edge> edges = g.edges();

		Vector<Vertex> vertexes = g.vertexes();

		Vector<Vector<Vertex>> vs = new Vector<>();

		Vector<Edge> h = new Vector<>();

		Collections.sort(edges);

		for (Vertex vertex : vertexes) {
			Vector<Vertex> vector = new Vector<>();
			vector.add(vertex);
			vs.add(vector);
		}

		for (int i = 0; vs.size() > 1 && i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			Vector<Vertex> first = new Vector<>();
			first.add(edge.vertexes().first());

			Vector<Vertex> last = new Vector<>();
			last.add(edge.vertexes().last());
			
			int j = findVertex(vs, first);
			int k = findVertex(vs, last);
			
			if (j != k) {
				if (j != -1 && k != -1) {
					vs.get(j).addAll(vs.get(k));
					vs.remove(k);
				}	
				h.add(edge);
			}
		}
		
		Graph graph = new Graph();
		graph.addVertexes(vs.get(0));
		graph.addEdges(h);
		return graph;
	}

}
