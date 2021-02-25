package lab_4;

import graphs.*;

public class Main {

	public static void main(String[] args) {
		GraphOut graphOut = new GraphOut();
		
		Graph graph = new Graph();
		graph.addEdge(new Vertex(0), new Vertex(2), 1);
		graph.addEdge(new Vertex(1), new Vertex(3), 3);
		graph.addEdge(new Vertex(0), new Vertex(1), 4);
		graph.addEdge(new Vertex(2), new Vertex(3), 5);
		graph.addEdge(new Vertex(2), new Vertex(1), 2);
		graph.addEdge(new Vertex(0), new Vertex(1), 0);

		graph.addEdge(new Vertex(3), new Vertex(4), 6);

		System.out.println("Исходный граф:");
		System.out.print(graphOut.out(graph));
		System.out.println();
		
		if (graph.isConnected()) {
			Prim prim = new Prim();
			Graph primGraph = prim.getGraph(graph);

			System.out.println("Прим:");
			System.out.print(graphOut.out(primGraph));
			System.out.println();
			
			Kruskal kruskal = new Kruskal();
			Graph kruskalGraph = kruskal.getGraph(graph);
			
			System.out.println("Краскал:");
			System.out.println(graphOut.out(kruskalGraph));
			System.out.println();	
		}
		else {
			System.out.println("Граф не связный");
		}
	}
	
}
