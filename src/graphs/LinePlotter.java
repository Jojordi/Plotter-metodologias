package graphs;

import javafx.scene.chart.NumberAxis;

/**
 * Subclass of AbstractPlotter, specializes the plotting style to
 * produce Line graphs.
 * @author Jordo
 *
 */
public class LinePlotter extends AbstractPlotter {

	/**
	 * Constructor
	 */
	public LinePlotter() {
	}
	
	/**
	 * Initializes the plot specifing that it will be a LinePlot
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected IPlot initPlot() {
		return new LinePlot<>(new NumberAxis(), new NumberAxis());
	}

}
