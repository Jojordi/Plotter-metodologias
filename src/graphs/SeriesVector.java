package graphs;

import java.util.List;

/**
 * Interface used to add points.
 * Each time a point is added to an object of this class, its x coordinate 
 * value is added to the atribute x wich consists of a List of values 
 * and its y coordinate value is added to another list as well, this list is
 * the y atribute of the objects wich implement this class
 * @author Jordo
 *
 */
public interface SeriesVector {
	
	List<String> getName();
	List<Number> getX();
	List<Number> getY();
	void addXY(Number x, Number y);
	void addNamed(String x, Number y );
	
}
