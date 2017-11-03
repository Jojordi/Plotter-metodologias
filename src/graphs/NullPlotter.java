package graphs;

import javafx.scene.Node;

public class NullPlotter implements Plotter {

	@Override
	public Node makePlot(String[] data) {
		return null;
	}

}
