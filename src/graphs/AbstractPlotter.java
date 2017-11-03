package graphs;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;


/**
 * AbstractPlotter is the superclass of all the subtypes of plotters that 
 * specialize the way the data is interpreted.
 * @author Jordo
 *
 */
public abstract class AbstractPlotter implements Plotter {
	
	/**
	 * Default constructor, doesnt have parameters since this class shoulde be
	 * viewed as a Factory type of class.
	 */
	public AbstractPlotter(){
	}
	
	/**
	 * makePlot is the Node making method that each subclass specializes 
	 * according to their need, as each different type of plot requires
	 * different reading methods and types of XYCharts.
	 * <p>
	 * It should be noted that this method can either succesfully return a 
	 * javaFX Node or throw an IndexOutOfBoundsException, wich means that the
	 * format of the file was not valid or that there was missing info.
	 * <p>
	 * This method recieves an array of strings that correspond to lines in a 
	 * file. After recieveing such data it begins to initialize all of the plot
	 * aspects, sets the title, the axis labels, initializes the series list
	 * and adds the points to the XYChart using the interface IPlot.
	 * <p>
	 * Supresses the warning of RawTypes since IPlot does not have defined type
	 * parameters
	 */
	@SuppressWarnings("rawtypes")
	public Node makePlot(String[] data){
		try{
		IPlot plot = this.initPlot();
		String[] header = data[0].split(";");
		plot.setTitle(header[0]);
		plot.setXLabel(header[1]);
		plot.setYLabel(header[2]);
		List<String> series = new ArrayList<String>();
		for(int i=3; i<header.length; i++){
			series.add(header[i]);
		}
		List<SeriesVector> points = this.initData(data,series.size());
		return this.addInfo(plot,series,points).getPlot();
		}catch(IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException("There was a problem with the format of file, please select another");
		}
	}
	
	/**
	 * Transforms the lines of data into a list that contains the info of
	 * each point to be plotted in the form of a List that contains the lists
	 * of all points of a specified serie.
	 * @param data The String array of data to get the points from
	 * @param size The amount of series
	 * @return a  List of vectors that corresponds to all points of the same 
	 * series
	 */
	protected List<SeriesVector> initData(String[] data, int size){
		List<SeriesVector> points =initSeries(size);
		String[] currentLine;
		for(int i=1; i<data.length;i++){
			currentLine=data[i].split(", ");
			this.addLine(currentLine,points);
			}
		return points;
	}

	/**
	 * Used to verify the correctness of all the lines to be added into the 
	 * plot, it can discard any line that presents a proble at the time of 
	 * reading it.
	 * <p>
	 * This method is overriden if the way to read each line changes.
	 * @param currentLine A line of the lines array to be added
	 * @param points The list of all the points already added
	 */
	protected void addLine(String[] currentLine,List<SeriesVector> points) {
		try{
		points.get(Integer.parseInt(currentLine[2])).addXY(parseNumber(currentLine[0]), parseNumber(currentLine[1]));
		}catch(Exception e){}
	}

	/**
	 * The initialization of the list of series-lists that each point belongs
	 * to.
	 * @param size the amount of different series
	 * @return a list of blank lists that will be used to store the series.
	 */
	protected List<SeriesVector> initSeries(int size) {
		List<SeriesVector> listOfSeries = new ArrayList<SeriesVector>();
		for(int i=0; i<size;i++){
			SeriesVector newSerie = this.initVector();
			listOfSeries.add(newSerie);
		}
		return listOfSeries;
	}

	/**
	 * Acts as the wrapper for adding points into the XYChart to be 
	 * plotted using the IPlot interface.
	 * @param plot the especific plot to add points to
	 * @param series the list of series
	 * @param points the list of Vectors that hold the x and y coordinates.
	 * @return an IPlot type object that already holds the valid points
	 */
	@SuppressWarnings("rawtypes")
	protected IPlot addInfo(IPlot plot, List<String> series, List<SeriesVector> points) {
		for(int i=0;i<series.size();i++){
			plotSeries(i,plot,series,points);
		}
		return plot;
	}
	
	/**
	 * Adds an especific point to the plot in the IPlot type object, this point
	 * corresponds to the point specified in the line number i.
	 * <p>
	 * This method can be overriden since different plotters could read data in
	 * a different way
	 * @param i the index of the point
	 * @param plot the plot that the point will be added to
	 * @param series the series to wich the point belongs to
	 * @param points the list that contains the vector that holds the point's x
	 * and y values
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void plotSeries(int i, IPlot plot, List<String> series, List<SeriesVector> points) {
		plot.addSeries(points.get(i).getX(),points.get(i).getY(),series.get(i));
	}
	
	/**
	 * Initializes the list of specific types of points that the ploter will use.
	 * <p>
	 * It uses an XYVector type of SeriesVector to create a list of points with 
	 * numeric values in both x and y Axes.
	 * <p>
	 * It uses a CategoryVector type of SeriesVector to create a List of points
	 * with a string value on its x Axis and a numeric value on its y Axis.
	 * @return
	 */
	protected SeriesVector initVector() {
		return new XYVector();
	}
	
	/**
	 * IPlot object initializing method, left to be overriden by subclasses
	 * since it needs to be specialized.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected abstract IPlot initPlot();
	
	/**
	 * Method used for converting strings into numbers, can throw a parse exception
	 * @param number the string to be parsed into a number
	 * @return a Number
	 * @throws ParseException in the case that the number couldnt be 
	 * interpreted as such
	 */
	protected Number parseNumber(String number) throws ParseException{
			return NumberFormat.getInstance().parse(number);
	}
}
