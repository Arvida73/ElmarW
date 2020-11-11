package GUI;

import InterFaces.IController;
import InterFaces.IMyPanel;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Frame {

	private IController controller;
	private MyPanel myPanel;
	
	
	public Frame(IController controller) {
		this.controller = controller;
	}
	
	public void init(Stage stage) {
		myPanel = new MyPanel(this.controller);
		Scene scene = new Scene(myPanel);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.show();
	}
	
	public IMyPanel getPane() {
		return this.myPanel;
	}

}
