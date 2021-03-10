package graph_classes;

public class Vertex implements Comparable<Vertex> {
	private int number = 0;

	public Vertex(int num) {
		number = num;
	}

	public int number() {
		return number;
	}

	@Override
	public int compareTo(Vertex vertex) {
		if (number == vertex.number()) {
			return 0;
		}
		return number < vertex.number() ? -1 : 1;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Vertex))
			return false;
		Vertex vertexesObj = (Vertex) o;
		return number == vertexesObj.number();
	}
	
	@Override
	public String toString() {
		return "" + number;
	}
}
