package cmd_log;

import java.util.Collections;
import java.util.Vector;

import graph_classes.Edge;
import graph_classes.Graph;
import graph_classes.Vertex;

public class GraphOut {
	
	public String out(Graph g) {
		
		if (g == null) {
			return "���� �� �����";
		}
		
		if (g.vertexes().size() == 0) {
			return "���� ����";
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
