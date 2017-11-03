package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Vector wich holds points with a string value for the x Axis
 * and a numeric value for the y Axis
 * @author Jordo
 *
 */
public class CategoryVector implements SeriesVector {
	private List<String> x;
	private List<Number> y;
	
	/**
	 * Default constructor, initializes the lists of x and y 
	 * coordinates for each point
	 */
	public CategoryVector(){
		this.x= new ArrayList<String>();
		this.y= new ArrayList<Number>();
	}
	
	/** 
	 * Adds the xN,yN point to the object in the way that the xN value is 
	 * added to the x list in the same index as the yN value is added to the
	 * y list.
	 */
	public void addNamed(String xN, Number yN){
		x.add(xN);
		y.add(yN);
	}
	
	/**
	 * returns the list of coordinates x
	 */
	public List<String> getName(){
		return x;
	}
	
	/**
	 * returns the list of values of the y coordinate
	 */
	public List<Number> getY(){
		return y;
	}

	/**
	 * Doesnt return anything since this method is not meant to be used by this
	 * class
	 */
	@Override
	public List<Number> getX() {
		return null;
	}

	/**
	 * Doesnt do anything since every point added to the lists must have the
	 * same type of values
	 */
	@Override
	public void addXY(Number x, Number y){}

}
