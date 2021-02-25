package graphs;

public class Pair<F, L> {

	private final F frst;
	private final L lst;

	public Pair(F first, L last) {
		frst = first;
		lst = last;
	}
	
	public F first() {
		return frst;
	}

	public L last() {
		return lst;
	}
	
	@Override
	public int hashCode() {
		return frst.hashCode() ^ lst.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) {
			return false;
		}
		Pair pairo = (Pair) o;
		return frst.equals(pairo.first()) && lst.equals(pairo.last());
	}

}
