package graphs;

import java.util.Vector;

public class Logger {
	private volatile static Logger instance ;
	
	public static Logger getInstance() {
		if (instance == null) {
			synchronized (Logger.class) {
				if (instance == null)
				instance = new Logger() ;
			}
		}
		return instance;
	}

	public void print(String arg) {
		System.out.println(arg);
		System.out.flush();
	}

	public void print(Vector<Vector<Edge>> arg) {
		for (Vector<Edge> es: arg) {
			for (Edge e: es) {
				System.out.println(e.toString() + "; ");
			}
			System.out.println();
		}
		System.out.flush();
	}
}
