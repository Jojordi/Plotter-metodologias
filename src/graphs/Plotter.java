package graphs;

import javafx.scene.Node;

/**
 * The comon interface of all plotters, it has only one method, used by the 
 * callers or users of this interface to produce the different kinds of plots
 * @author Jordo
 *
 */
public interface Plotter {
	Node makePlot(String[] data);
}
