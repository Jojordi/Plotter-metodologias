package graphs;



import java.util.List;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
/**
 * As a subclass of AbstractPlotter, this class specializes the AbstractPlotter
 * class to produce Bar-Graph type Nodes
 * @author Jordo
 *
 */
public class BarPlotter extends AbstractPlotter {
	/**
	 * Constructor
	 */
	public BarPlotter(){
	}
	
	/**
	 * Initializes a Barplot type of plot, with a CategoryAxis for the x Axis 
	 * and a number Axis for the y Axis
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected IPlot initPlot() {
		return new BarPlot<>(new CategoryAxis(), new NumberAxis());
	}
	/**
	 * Reads the line in the format specific to barplot graphs, if the line 
	 * doesnt contain valid data it is discarded.
	 */
	@Override
	protected void addLine(String[] currentLine,List<SeriesVector> points) {
		try {
			for(int j=1; j<points.size()+1;j++){
					points.get(j-1).addNamed(currentLine[0], parseNumber(currentLine[j]));
				}
				}catch (Exception e) {}
	}
	
	/**
	 * Adds the series in the format specific to barplot types in the way that 
	 * it uses a CategoryAxis for the x Axis
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void plotSeries(int i,IPlot plot, List<String> series, List<SeriesVector> points) {
		plot.addSeries(points.get(i).getName(),points.get(i).getY(), series.get(i));
	}
	
	/**
	 * Initializes the CategoryVector to store points in the correct format
	 * for barplot types
	 */
	@Override
	protected SeriesVector initVector() {
		return new CategoryVector();
	}

}
