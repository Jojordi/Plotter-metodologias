package graphs;

import javafx.scene.chart.NumberAxis;
/**
 * Subclass of Abstrat plotter, it specifies the plotter to produce
 * Scatter Graphs
 * @author Jordo
 *
 */
public class ScatterPlotter extends AbstractPlotter {

	/**
	 * Constructor
	 */
	public ScatterPlotter() {
	}

	/**
	 * Specifies that the chart to be used will be a ScatterChart
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected IPlot initPlot() {
		return new ScatterPlot<>(new NumberAxis(), new NumberAxis());
	}

}
