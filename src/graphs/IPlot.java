package graphs;

import java.util.List;

import javafx.scene.Node;
/**
 * IPlot is the Interface used to produce diferent kinds of plots, be it a 
 * LinePlot, a BarPlot or a ScatterPlot.
 * @author Jordo
 *
 * @param <T1> X Axis
 * @param <T2> Y Axis
 */
public interface IPlot<T1, T2> {
	public void addSeries(List<T1> xData, List<T2> yData, String seriesName);
	public void setTitle(String string);
	public void setXLabel(String string);
	public void setYLabel(String string);
	public Node getPlot();
}
