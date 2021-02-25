package lab_4;

import java.util.Collections;
import java.util.Vector;

import graphs.Edge;
import graphs.Graph;
import graphs.Vertex;

public class GraphOut {
	
	public String out(Graph g) {
		
		if (g == null) {
			return "Граф не задан";
		}
		
		if (g.vertexes().size() == 0) {
			return "Граф пуст";
		}
		
		Vector<Vertex> vertexList = g.vertexes();
		Vector<Vector<Edge>> vertexEdges = g.incidenceList();
		
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
