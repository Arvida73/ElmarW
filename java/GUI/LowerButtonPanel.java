package GUI;



import java.time.LocalDate;

import InterFaces.IController;
import InterFaces.ICompany;
import Company.Company;
import Company.CompanyData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LowerButtonPanel extends HBox{
	
	private final String monthButton = "Szukaj w miesiącu";
	private final String yearButton = "Szukaj w roku";
	private final String allButton = "Szukaj";
	
	private final int labelHeight = 50;

	private final Font paneFont = Font.font("Serif", 20);
	
	private Button monthButtons;
	private Button yearButtons;
	private Button allButtons;

	private VBox searchButtonPanel;
	
	private int paneWidth;
	private int paneHeight;
	
	private IController controller;
	
	public LowerButtonPanel(IController controller , int width , int height) {
		this.paneWidth = width;
		this.paneHeight = height;
		
		this.controller = controller;
		this.setPrefSize(this.paneWidth, this.paneHeight);
		
		initProperties();
		setInsidePanes();
		setButtons();
		
		this.getChildren().addAll(this.searchButtonPanel);
	}

	private void initProperties() {

		monthButtons = new Button(monthButton);
		monthButtons.setPrefSize(this.paneWidth, labelHeight);
		monthButtons.setAlignment(Pos.CENTER);
		monthButtons.setFont(paneFont);
		monthButtons.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");
		
		yearButtons = new Button(yearButton);
		yearButtons.setPrefSize(this.paneWidth, labelHeight);
		yearButtons.setAlignment(Pos.CENTER);
		yearButtons.setFont(paneFont);
		yearButtons.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");
		
		allButtons = new Button(allButton);
		allButtons.setPrefSize(this.paneWidth, labelHeight);
		allButtons.setAlignment(Pos.CENTER);
		allButtons.setFont(paneFont);
		allButtons.setStyle("-fx-background-color : WHITE;\n -fx-border-color: BLACK ");

		searchButtonPanel = new VBox();
		searchButtonPanel.setPrefSize(this.paneWidth , this.paneHeight);
		
	}
	

	private void setInsidePanes() {
		
		searchButtonPanel.getChildren().addAll(allButtons, yearButtons, monthButtons);
		searchButtonPanel.setSpacing(100);
		searchButtonPanel.setPadding(new Insets(50, 0 , 50, 0));
		searchButtonPanel.setStyle("-fx-border-color: BLACK");
	}
	
	
	private void setButtons() {
		//TODO implement button
		monthButtons.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//
					LocalDate date = LocalDate.now();
					ICompany iCompany = new Company(null, date, 1);
					CompanyData companyData = new CompanyData(iCompany, false, false, true, false , false);
					controller.notify(companyData);
					
			}
		});
		
		yearButtons.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
//
					LocalDate date = LocalDate.now();
					ICompany iCompany = new Company(null, date, 1);
					CompanyData companyData = new CompanyData(iCompany, false, false, true, false , true);
					controller.notify(companyData);
				
			}
		});
		
		allButtons.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

					LocalDate date = LocalDate.now();
					ICompany iCompany = new Company(null, date, 1);
					CompanyData companyData = new CompanyData(iCompany, false, false, true, true , false);
					controller.notify(companyData);

		
			}
		});
		
		
	}
	
//	private int checkYearValidity() {
//		if(yearButtons.getText().isEmpty()) {
//			return -1;
//		}
//		try {
//			int year = Integer.valueOf(yearButtons.getText().trim());
//			return year;
//
//		}catch(NumberFormatException e) {
//			return -1;
//		}
//	}
//
//	private int checkMonthValidity() {
//		if(monthButtons.getText().isEmpty()) {
//			return -1;
//		}
//		try {
//			int month = Integer.valueOf(monthButtons.getText().trim());
//			if(month < 1 || month > 12) {
//				return -1;
//			}
//			return month;
//		}catch(NumberFormatException e) {
//			return -1;
//		}
//	}

}
