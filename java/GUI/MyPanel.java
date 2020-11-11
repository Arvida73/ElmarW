package GUI;

import InterFaces.IController;
import InterFaces.ICompany;
import InterFaces.IMyPanel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MyPanel extends VBox implements IMyPanel {

	private final int width = 1366;
	private final int height = 768;
	
	private final double widthButton = 1.0/3.0;
	private final double heightButton = 3.0/5.0;
	
	private final int titleHeight = 50;
	
	private TitlePanel titlePanel;
	
	private HBox displayUpperLower;
	
	private VBox upperAndLowerButton;
	private LowerButtonPanel lowerButtonPanel;
	private UpperButtonPanel upperButtonPanel;
	
	private DisplayPanel displayPanel;

	private IController controller;
	
	public MyPanel(IController controller) {
		this.controller = controller;
		
		this.setPrefSize(width, height);
		this.setStyle("-fx-background-color: azure");
		
		initProperties();
		setInsidePanes();
		

		
		this.getChildren().addAll(titlePanel, displayUpperLower);
		
	}
	

	private void initProperties() {
	
		this.titlePanel = new TitlePanel(width, titleHeight);
		this.titlePanel.setStyle("-fx-border-color: BLACK");
		
		this.displayPanel = new DisplayPanel((int) (width * (1.0 - widthButton)), height - titleHeight);
		this.displayPanel.setStyle("-fx-border-color: BLACK");
		
		this.upperButtonPanel = new UpperButtonPanel(controller, (int) (width * widthButton), (int) ((height - titleHeight) * (heightButton)));
		this.upperButtonPanel.setStyle("-fx-border-color: BLACK");
		
		this.lowerButtonPanel = new LowerButtonPanel(this.controller , (int) (width * widthButton), (int) ((height - titleHeight) * (1.0 - heightButton)));
		this.lowerButtonPanel.setStyle("-fx-border-color: BLACK");
		
		this.upperAndLowerButton = new VBox();
		this.upperAndLowerButton.setPrefSize(width * widthButton, height - titleHeight);
		
		this.displayUpperLower = new HBox();
		this.displayUpperLower.setPrefSize(width,  height - titleHeight);
		

	}

	private void setInsidePanes() {
		
		this.upperAndLowerButton.getChildren().addAll(this.upperButtonPanel, this.lowerButtonPanel);
		this.displayUpperLower.getChildren().addAll(this.displayPanel, this.upperAndLowerButton);
	}

	@Override
	public void notify(ICompany company) {
		displayPanel.addCompany(company);
	}

	@Override
	public void notify(boolean b) {
		if(b) {
			displayPanel.cleanCompany();
			
		}
	}

}
