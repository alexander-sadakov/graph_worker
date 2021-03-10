package graph_classes;

import java.util.Collections;
import java.util.Vector;
import java.util.Random;

public class Prim {

	public Graph search(Graph graph) {
		Vector<Vertex> q = graph.vertexes();
		Vector<Vertex> v = new Vector<>();
		Vector<Edge> h = new Vector<>();
		Vector<Edge> t = new Vector<>();
		Vector<Vector<Edge>> es = graph.getIncidenceList();

		Logger.getInstance().print("Get vertexes from the original" 
				+ " graph:\n" + q.toString());
		Logger.getInstance().print("Get edges for each vertex from " 
				+ "the original graph:");
		Logger.getInstance().print(es);

		Random rand = new Random();
		int index = rand.nextInt(q.size());

		Vertex vertex = q.get(index);
		v.add(vertex);

		Logger.getInstance().print("�hoose a random vertex: " + vertex.toString());
		Logger.getInstance().print("A random index: " + index);
		Logger.getInstance().print("Add vertex in vertex array:\n" 
				+ v.toString() + "\n");

		try {
			do {
				for (Edge edge : es.get(index)) {
					if (!h.contains(edge) && !t.contains(edge)) {
						t.add(edge);
						Logger.getInstance().print("Add edge " + edge.toString() 
								+ " in Tree:\n" + t.toString() + "\n");
					}
				}
				Collections.sort(t);
				Logger.getInstance().print("Sort Tree:\n" + t.toString() + "\n");

				for (Edge edge : t) {
					if (!v.contains(edge.vertexes().first()) || 
							!v.contains(edge.vertexes().last())) {

						h.add(edge);
						Logger.getInstance().print("Add edge: " + edge.toString() 
								+ " in new edge list:\n" + h.toString() + "\n");

						t.remove(t.indexOf(edge));
						Logger.getInstance().print("Remove edge " + edge.toString() 
								+ " from Tree:\n" + t.toString() + "\n");

						index = v.contains(edge.vertexes().first()) ? 
								q.indexOf(edge.vertexes().last()) : 
								q.indexOf(edge.vertexes().first());

						vertex = q.get(index);
						Logger.getInstance().print("New vertex: " + vertex.toString());

						v.add(vertex);
						Logger.getInstance().print("Add vertex in vertex array:\n" 
								+ v.toString() + "\n");
						break;
					}
				}

			} while (v.size() != q.size());
		} catch (Exception e) {
			Logger.getInstance().print("Exception" + e.toString() + "\n");
			return null;
		}
		Collections.sort(h);
		Graph g = new Graph();
		g.addVertexes(v);
		g.addEdges(h);
		Logger.getInstance().print("return Graph" + "\n");
		return g;
	}

}
