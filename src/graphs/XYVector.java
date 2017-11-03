package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to create a vector of points that hold two numeric values, for 
 * coordinates x and y.
 * @author Jordo
 *
 */
public class XYVector implements SeriesVector {
	private List<Number> x;
	private List<Number> y;
	/**
	 * Constructor, sets the list of x and y to hold numeric values
	 */
	public XYVector(){
		this.x= new ArrayList<Number>();
		this.y= new ArrayList<Number>();
	}
	
	/**
	 * Adds a point in the form of two Lists, one for its x values and one for
	 * its y values
	 */
	public void addXY(Number xN, Number yN){
		x.add(xN);
		y.add(yN);
	}
	
	/**
	 * returns the x atribute of this list
	 */
	public List<Number> getX(){
		return x;
	}
	
	/**
	 * returns the y atribute of this list
	 */
	public List<Number> getY(){
		return y;
	}

	/**
	 * doesnt return anyhing since this method should not be instanciated while
	 * using this class
	 */
	@Override
	public List<String> getName() {
		return null;
	}

	/**
	 * Doesnt do anything since it should not be instanciated while using this 
	 * class, to get a valid instance of this method use CategoryVector
	 */
	@Override
	public void addNamed(String x, Number y) {}

}
