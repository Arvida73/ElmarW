import Controller.Controller;

import DataManagement.DataManager;
import GUI.Frame;
import InterFaces.IController;
import InterFaces.IDataManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		IController controller = new Controller();
		IDataManager dataManager = new DataManager("Guarantee.txt" , controller);
		Frame frame = new Frame(controller);
		
		frame.init(stage);
		controller.registerDisplay(frame.getPane());
		controller.registerManager(dataManager);
		dataManager.loadExistingData();
	}

}
