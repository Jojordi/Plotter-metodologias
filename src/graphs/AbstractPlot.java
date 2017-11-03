package graphs;

import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;
/**
 * AbstractPlot is the superclass of all the subtypes of two Axis graphs used 
 * in the plotting process. It manages the adding of info to the Chart that 
 * will be plotted
 * @author Jordo
 *
 * @param <T1> X Axis
 * @param <T2> Y Axis
 */
public abstract class AbstractPlot<T1, T2> implements IPlot<T1, T2> {
	
	protected Axis<T1> xAxis;
	protected Axis<T2> yAxis;
	protected XYChart<T1, T2> plot;

	/**
	 * The constructor for all the subtypes of plot, note that this constructor 
	 * requires especialization from each subclass of AbstractPlot as they 
	 * initialize the type of XYchart they use.
	 * @param xAxis Sets the X Axis
	 * @param yAxis Sets the Y Axis
	 */
	public AbstractPlot(Axis<T1> xAxis, Axis<T2> yAxis){
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.init();
	}
	
	/**
	 * Adds points to the Chart in the form of 2 lists with the x and y 
	 * coordinates and with a specified series name.
	 */
	public void addSeries(List<T1> xData, List<T2> yData, String seriesName){
		if(xData.size() != yData.size())
			throw new IllegalArgumentException("X and Y data must have the same size");
		XYChart.Series<T1, T2> series = new XYChart.Series<>();
		for(int i=0; i<xData.size(); i++){
			series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
		}
		series.setName(seriesName);
		plot.getData().add(series);
	}
	
	/**
	 * Chart initializing method, must be overriden
	 */
	public abstract void init();
	
	/**
	 * Sets the Chart title
	 */
	public void setTitle(String title){
		plot.setTitle(title);
	}
	
	/**
	 * Sets the Chart X Axis Label
	 */
	public void setXLabel(String xLabel){
		xAxis.setLabel(xLabel);
	}
	
	/**
	 * Sets the Chart Y Axis Label
	 */
	public void setYLabel(String yLabel){
		yAxis.setLabel(yLabel);
	}
	
	/**
	 * Returns the XYChart
	 */
	public XYChart<T1, T2> getPlot(){
		return plot;
	}

}
