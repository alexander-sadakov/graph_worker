package cmd_log;

import graph_classes.*;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new Graph();
		graph.addEdge(new Vertex(0), new Vertex(2), 1);
		graph.addEdge(new Vertex(1), new Vertex(3), 3);
		graph.addEdge(new Vertex(0), new Vertex(1), 4);
		graph.addEdge(new Vertex(2), new Vertex(3), 5);
		graph.addEdge(new Vertex(2), new Vertex(1), 2);
		graph.addEdge(new Vertex(0), new Vertex(1), 0);

		graph.addEdge(new Vertex(3), new Vertex(4), 6);

		System.out.println("�������� ����:");
		System.out.print(graph.toString());
		System.out.println();
		
		if (graph.isConnected()) {
			Prim prim = new Prim();
			Graph primGraph = prim.getGraph(graph);

			System.out.println("����:");
			System.out.print(primGraph.toString());
			System.out.println();
			
			Kruskal kruskal = new Kruskal();
			Graph kruskalGraph = kruskal.getGraph(graph);
			
			System.out.println("�������:");
			System.out.println(kruskalGraph.toString());
			System.out.println();	
		}
		else {
			System.out.println("���� �� �������");
		}
	}
	
}
