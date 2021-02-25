package graphs;

public class Edge implements Comparable<Edge> {
	private int weight;
	private Pair <Vertex, Vertex> vertexPair;

	Edge(int w, Vertex v1, Vertex v2) {
		weight = w;
		vertexPair = new Pair<Vertex, Vertex>(v1, v2);
	}

	public int weight() {
		return weight;
	}

	public Pair <Vertex, Vertex> vertexes() {
		return vertexPair;
	}

	public int compareVertexes(Vertex v1, Vertex v2) {
		Pair <Vertex, Vertex> vertexis = new Pair<Vertex, Vertex>(v1, v2);
		if (vertexPair == vertexis) {
			return 0;
		}
		return vertexPair.hashCode() < vertexis.hashCode() ? -1 : 1;
	}

	public boolean hasVertex(Vertex v) {
		return (vertexPair.first().equals(v)
		|| vertexPair.last().equals(v));
	}
	
	public Vertex adjacentVertex(Vertex v) {
		if (vertexPair.first().equals(v)) {
			return vertexPair.last();
		}
		else {
			return vertexPair.first();
		}
	}
	
	@Override
	public int compareTo(Edge edge) {
		if (weight == edge.weight) {
			return 0;
		}
		return weight < edge.weight ? -1 : 1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Edge)) {
			return false;
		}
		Edge edgeObj = (Edge) o;
		return (this.weight == edgeObj.weight()) && 
				this.vertexPair.equals(edgeObj.vertexes());
	}
	
	@Override
	public String toString() {
		return "{ " + vertexPair.first().number() + " <---> " + 
				vertexPair.last().number() + ": " + weight + " }";
	}
}
