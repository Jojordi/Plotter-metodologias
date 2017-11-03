package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;

import graphs.CategoryVector;
import graphs.GraphFileManager;
import graphs.LinePlotter;
import graphs.BarPlotter;
import graphs.NullPlotter;
import graphs.ScatterPlotter;
import graphs.SeriesVector;
import graphs.XYVector;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Test extends Application{

	private SeriesVector genericVector;
	private XYVector pointVector;
	private CategoryVector barVector;

	private LinePlotter linePlotter;
	private BarPlotter barPlotter;
	private ScatterPlotter scatterPlotter;
	private NullPlotter nullPlotter;
	private String[] testData = new String[]{"Title;xLabel;yLabel;Series1","1, 1, 0"};
	private String[] testDataScatter = new String[]{"Title;xLabel;yLabel;Series1","1, 1, 0"};
	private Node testNodeLine;
	private Node testNodeBar;
	private Node testNodeScatter;
	private Node testNoNode;
	
	private File testFileA;
	private File testFileB;
	private File testFileC;
	
	private GraphFileManager graphFM;

	
	
	@BeforeClass
	public static void initJFX() {
	  Thread t = new Thread("JavaFX Init Thread") {
	    public void run() {
	      Application.launch(Test.class);
	    }
	  };
	  t.setDaemon(true);
	  t.start();
	}
	
	@Before
	public void setUp(){

		genericVector = new XYVector();
		pointVector = new XYVector();
		barVector = new CategoryVector();
		nullPlotter = new NullPlotter();
		pointVector.addXY(3, 4);
		pointVector.addNamed("s",3);
		barVector.addXY(3, 4);
		barVector.addNamed("s", 3);

		linePlotter = new LinePlotter();
		barPlotter = new BarPlotter();
		scatterPlotter = new ScatterPlotter();
		testNodeLine = linePlotter.makePlot(testData);
		testNodeBar = barPlotter.makePlot(testData);
		testNodeScatter = scatterPlotter.makePlot(testDataScatter);
		testNoNode = nullPlotter.makePlot(testData);

		testFileA = new File("/Tarea3/src/graphs/CDA.cdata");
		testFileB = new File("/Tarea3/src/graphs/TDA.tdata");
		testFileC= new File(testFileA.getPath());

		graphFM = new GraphFileManager();
		
	}
	
	@org.junit.Test
	public void fullTest(){
		//
		assertNotNull(genericVector);
		assertNotNull(pointVector);
		assertNotNull(barVector);
		assertNull(testNoNode);
		assertNotEquals(genericVector,pointVector);
		assertFalse(pointVector.getY().size()==2);
		assertFalse(barVector.getY().size()==2);
		assertNull(pointVector.getName());
		assertNull(barVector.getX());
		assertTrue(pointVector.getX().size()==1);
		assertTrue(barVector.getName().size()==1);
		
		assertNotNull(linePlotter);
		assertNotNull(barPlotter);
		assertNotNull(scatterPlotter);
		assertNotNull(nullPlotter);
		assertNotNull(testNodeLine);
		assertNotNull(testNodeBar);
		assertNotNull(testNodeScatter);
		
		assertNotNull(testFileA);
		assertNotNull(testFileB);
		assertEquals("CDA.cdata",testFileA.getName());
		assertEquals("TDA.tdata",testFileB.getName());
		assertEquals("CDA.cdata",testFileC.getName());
		
		assertEquals(graphFM.getExtension(testFileA),"cdata");
		assertEquals(graphFM.getExtension(testFileB),"tdata");
		assertEquals(graphFM.getExtension(testFileC),"cdata");
		assertEquals(graphFM.getExtension(null),"");
		graphFM.changeMode("BarPlot");
		assertEquals(graphFM.getPlotter().getClass(),new BarPlotter().getClass());
		graphFM.changeMode("");
		assertEquals(graphFM.getPlotter().getClass(),new ScatterPlotter().getClass());
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	}

}