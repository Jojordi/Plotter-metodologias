package graphs;

import javafx.scene.chart.Axis;
import javafx.scene.chart.ScatterChart;

/**
 * SubClass of AbstractPlot it specifies that the chart to be used will be a
 * Scatterchart
 * @author Jordo
 *
 * @param <T1> x Axis
 * @param <T2> y Axis
 */
public class ScatterPlot<T1, T2> extends AbstractPlot<T1, T2> {

	/**
	 * Constructor, it has to use the init method within this class
	 * @param xAxis  the type of x Axis
	 * @param yAxis  the type of y Axis
	 */
	public ScatterPlot(Axis<T1> xAxis, Axis<T2> yAxis) {
		super(xAxis, yAxis);
	}

	/**
	 * Initialization method that specifies that the chart to use will be a
	 * ScatterChart
	 */
	@Override
	public void init() {
		plot = new ScatterChart<T1, T2>(xAxis, yAxis);	
	}

}
