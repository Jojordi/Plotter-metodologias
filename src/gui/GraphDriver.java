package gui;

import java.io.File;
import java.util.Hashtable;

import graphs.GraphFileManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Class to be run, it controls all of the plotting process, from selecting and 
 * reading a file, to plotting it and displaying it using javaFX
 * @author Jordo
 *
 */
public class GraphDriver extends Application{

	private GraphFileManager manager = new GraphFileManager();
	private File currentFile = null;
	private Text statusBar = new Text(10,50,"Please select a file");
	private Hashtable<String, String> fileTypes = new Hashtable<String, String>();
    
	/**
	 * Start method 
	 */
	@Override public void start(Stage stage) {

        stage.setTitle("JPlot");

        BorderPane pane = new BorderPane();

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);

        FileChooser dataChooser = new FileChooser();
        dataChooser.getExtensionFilters().add(new ExtensionFilter("LinePlot files","*.tdata"));
        Button openFileButton = new Button("Choose Data File");

        openFileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{
				currentFile = dataChooser.showOpenDialog(stage);
				statusBar.setText("File selected: "+currentFile.getName());
				}catch(NullPointerException e){
					statusBar.setText("No file selected");
					currentFile=null;
				}
			}
		});

        Button drawButton = new Button("Draw");

        drawButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(currentFile!=null){
					try{
					pane.setCenter(manager.read(currentFile));
					}catch (Exception e){
						statusBar.setText(e.getMessage());
						currentFile=null;
					}
				}
				else{
					statusBar.setText("There is nothing to be drawn, please select a file!");
				}
			}
		});

        ObservableList<String> options =
        	    FXCollections.observableArrayList(
        	        "BarPlot", "LinePlot", "ScatterPlot"
        	    );
        ComboBox<String> comboBox = new ComboBox<>(options);
        comboBox.setValue("LinePlot");
        comboBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				initTable();
				manager.changeMode(comboBox.getValue());
				dataChooser.getExtensionFilters().clear();
				dataChooser.getExtensionFilters().add(new ExtensionFilter(comboBox.getValue()+" files","*."+fileTypes.get(comboBox.getValue())));
				if(!(manager.getExtension(currentFile)).equals(fileTypes.get(comboBox.getValue()))){
					statusBar.setText("Can't change plotting style for this type of file, please select another file");
					currentFile = null;
				}
				
			}
		});

        hbox.getChildren().addAll(new Label("Plot Type:"), comboBox, openFileButton, drawButton);
        pane.setTop(hbox);
        pane.setBottom(statusBar);

        Scene scene  = new Scene(pane,800,600);

        stage.setScene(scene);
        stage.show();
    }
	/**
	 * Run method
	 * @param args
	 */
	public static void main(String[] args) {
        launch(args);
    }
	
	/**
	 * Initializes the dicctionary of types of plots and their associated file 
	 * extensions
	 */
	private void initTable(){
		if(fileTypes.size()<2){
		fileTypes.put("BarPlot", "cdata");
		fileTypes.put("LinePlot", "tdata");
		fileTypes.put("ScatterPlot", "tdata");
		}
	}
}
