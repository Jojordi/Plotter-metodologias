package graphs;
import java.io.*;
import java.util.*;

import javafx.scene.Node;

/**
 * Class tasked with the management of file interpretation.
 * @author Jordo
 *
 */
public class GraphFileManager {
	
	private BufferedReader br;
	private Plotter currentPlotter;
	private Hashtable<String, Plotter> plotters = new Hashtable<String,Plotter>();
	
	/**
	 * Default constructor, the default plotter is a LinePlotter
	 */
	public GraphFileManager(){
		currentPlotter = new LinePlotter();
		plotters.put("LinePlot", currentPlotter);
	}
	
	/**
	 * Reads a file and calls the plotter to interpret the data returning a 
	 * Node to be added to the pane
	 * @param file File to be Interpreted
	 * @return Node to be added to the center pane.
	 * @throws IndexOutOfBoundsException recieved from MakePlot to be handled 
	 * by the GraphFileManager class displaying an error message in the status
	 * bar 
	 * @throws NullPointerException recieved from MakePlot, to be recieved by
	 * GraphFileManager and displayed in the status bar
	 */
	public Node read(File file) throws IndexOutOfBoundsException, NullPointerException{
		try{
			br = new BufferedReader(new FileReader(file));
			String line;
			List<String> lineList = new ArrayList<String>();
			while((line = br.readLine()) != null){
				lineList.add(line);
			}
			br.close();
			String[] data = lineList.toArray(new String[0]);
			return currentPlotter.makePlot(data);
		}catch(IOException e){}
		return null;
	}
	
	
	/**
	 * Changes the plotter to the specified one on value
	 * @param value Name of the plotter to change to
	 */
	public void changeMode(String value) {
		Plotter plotter = plotters.get(value);
		if(plotter!=null){
			this.currentPlotter=plotter;
		}
		else{
			plotter = createPlotter(value);
			plotters.put(value,plotter);
			this.currentPlotter=plotter;
		}
	}

	/**
	 * Creates a plotter if is not already present in the dicctionary of 
	 * plotters
	 * @param Value plotter to create
	 * @return The type of plotter to use
	 */
	private Plotter createPlotter(String value) {
		if(value.equals("BarPlot")){
			return new BarPlotter();
		}
		else{
			return new ScatterPlotter();
		}
	}

	/**
	 * Gets the extension of a file
	 * @param file file to get extension from
	 * @return A string that corresponds to the extension
	 */
	public String getExtension(File file) {
		if(file==null)return "";
		String extension = "";
		int i = file.getName().lastIndexOf('.');
		if (i > 0) {
		    extension = file.getName().substring(i+1);
		}
		return extension;
	}
	
	public Plotter getPlotter(){
		return currentPlotter;
	}
	
	
	
}
