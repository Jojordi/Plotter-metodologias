package graphs;

import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;

/**
 * The subclass of AbstractPlot used to create LineGraphs
 * @author Jordo
 *
 * @param <T1> x Axis
 * @param <T2> y Axis
 */
public class LinePlot<T1, T2> extends AbstractPlot<T1, T2> {

	/**
	 * The constructor for Lineplots, uses the overriden initializing method
	 * to specify the type of XYChart to use
	 * @param xAxis defines the X Axis
	 * @param yAxis defines the Y Axis
	 */
	public LinePlot(Axis<T1> xAxis, Axis<T2> yAxis) {
		super(xAxis, yAxis);
	}
	/**
	 * Initializes a LineChart as the type of Chart to plot with
	 */
	@Override
	public void init(){
		plot = new LineChart<T1, T2>(xAxis, yAxis);
	}

}
