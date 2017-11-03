package graphs;

import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;

/**
 * The subclass of AbstractPlot used to create BarGraphs
 * @author Jordo
 *
 * @param <T1> x Axis
 * @param <T2> y Axis
 */
public class BarPlot<T1, T2> extends AbstractPlot<T1, T2> {
	

	/**
	 * The constructor for Barplots, uses the overriden initializing method
	 * to specify the type of XYChart to use
	 * @param xAxis defines the X Axis
	 * @param yAxis defines the Y Axis
	 */
	public BarPlot(Axis<T1> xAxis, Axis<T2> yAxis) {
		super(xAxis, yAxis);
	}

	/**
	 * Initializes a BarChart as the type of Chart to plot with
	 */
	@Override
	public void init() {
		plot = new BarChart<T1, T2>(xAxis, yAxis);
	}



}
