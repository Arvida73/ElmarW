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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UpperButtonPanel extends HBox {
	
	private final double dataInputWidth = 2.0/3.0;
	
	private final String nameLabel = "Firma :";
	private final String startDateLabel = "Rozpoczęcie : ";
	private final String guaranteeLabel = "Gwaranacja w MSC :";

	private final Font panelFont = Font.font("Serif", 20);
	private final int labelHeight = 50;

	private final String createButton = "Stwórz";
	private final String deleteButton = "Skasuj";
	private final String refreshButton = "Odśwież";
	
	private Button createButtonPanel;
	private Button deleteButtonPanel;
	private Button updateButtonPanel;
	
	private Label nameL;
	private Label dateL;
	private Label guaranteeL;
	
	private TextField nameText;
	private TextField startDateText;
	private TextField guaranteeText;
	
	private int paneWidth;
	private int paneHeight;
	
	private VBox dataInputPane;
	private VBox buttonsPane;
	
	private IController controller;
	
	public UpperButtonPanel(IController controller , int width , int height) {
		this.controller = controller;
		this.setPrefSize(width, height);
		this.paneWidth = width;
		this.paneHeight = height;
		
		initProperties();
		setSizesToComponents();
		setDataInputPane();
		setButtonPane();
		addListenerToButtons();
		
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(this.dataInputPane , this.buttonsPane);
		
	}
	


	private void initProperties() {
		nameL = new Label(nameLabel);
		nameL.setFont(panelFont);
	
		dateL = new Label(startDateLabel);
		dateL.setFont(panelFont);
			
		guaranteeL = new Label(guaranteeLabel);
		guaranteeL.setFont(panelFont);
		
		nameText = new TextField();
		nameText.setFont(panelFont);
		
		startDateText =  new TextField();
		startDateText.setFont(panelFont);
		
		guaranteeText = new TextField();
		guaranteeText.setFont(panelFont);
		
		createButtonPanel = new Button(createButton);
		createButtonPanel.setFont(panelFont);
		
		deleteButtonPanel = new Button(deleteButton);
		deleteButtonPanel.setFont(panelFont);
		
		updateButtonPanel = new Button(refreshButton);
		updateButtonPanel.setFont(panelFont);
		
		dataInputPane = new VBox();
		dataInputPane.setPrefSize(this.paneWidth * dataInputWidth, this.paneHeight);
		
		buttonsPane = new VBox();
		buttonsPane.setPrefSize(this.paneHeight * (1.0 - dataInputWidth), this.paneHeight);
		
		
	}
	
	private void setSizesToComponents() {
		createButtonPanel.setPrefSize(this.paneWidth * (1.0 - dataInputWidth) , labelHeight);
		createButtonPanel.setStyle("-fx-background-color: white;\n -fx-border-color: black");
	
		deleteButtonPanel.setPrefSize(this.paneWidth * (1.0 - dataInputWidth), labelHeight);
		deleteButtonPanel.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		updateButtonPanel.setPrefSize(this.paneWidth * (1.0 - dataInputWidth), labelHeight);
		updateButtonPanel.setStyle("-fx-background-color: white;\n -fx-border-color: black");
		
		nameL.setPrefSize(this.paneWidth/3, labelHeight);
		dateL.setPrefSize(this.paneWidth*2/3, labelHeight);
		guaranteeL.setPrefSize(this.paneWidth*2/3, labelHeight);
			
	
	}
	
	private void setDataInputPane() {
		HBox nameBox = new HBox();
		nameBox.setPrefSize(this.paneWidth * dataInputWidth, this.paneHeight/3);
		nameBox.getChildren().addAll(this.nameL , this.nameText);
		
		HBox dateBox = new HBox();
		dateBox.setPrefSize(this.paneWidth * dataInputWidth, this.paneHeight/3);
		dateBox.getChildren().addAll(this.dateL , this.startDateText);
		
		HBox warrantyBox = new HBox();
		warrantyBox.setPrefSize(this.paneWidth * dataInputWidth, this.paneHeight/3);
		warrantyBox.getChildren().addAll(this.guaranteeL, this.guaranteeText);
		
		this.dataInputPane.getChildren().addAll(nameBox , dateBox ,warrantyBox);
		this.dataInputPane.setPadding(new Insets(0, 5, 0, 0));
	}
	
	
	private void setButtonPane() {
		this.buttonsPane.getChildren().addAll(this.createButtonPanel, this.deleteButtonPanel, this.updateButtonPanel);
		this.buttonsPane.setSpacing(90);
		this.buttonsPane.setStyle("-fx-border-color: BLACK");
	}
	

	

	private void addListenerToButtons() {
		this.createButtonPanel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CompanyData cd = checkData();
				if(cd != null) {
					controller.notify(cd);
				}
			}
		});
		
		this.deleteButtonPanel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CompanyData cd = checkData();
				if(cd != null) {
					cd.setCreat(false);
					controller.notify(cd);
				}
				
			}
		});
		
		this.updateButtonPanel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				CompanyData companyData = new CompanyData(null, false, true, false, false , false);
				controller.notify(companyData);
				
			}
		});

	}
	
	private CompanyData checkData() {
		int guarantee = 0;
		boolean validInput = true;
		if(nameText.getText().isEmpty()) {
			nameText.setText("Uzupełnij");
			validInput = false; 
		}
		
		if(startDateText.getText().isEmpty()) {
			startDateText.setText("Uzupełnij");
			validInput = false;
		}
		if(guaranteeText.getText().isEmpty()){
			guaranteeText.setText("Uzupełnij");
			validInput = false;
		}
		if(validInput) {
			try {
				if(!isValidDate(startDateText.getText().trim())) {
					validInput = false;
				}
				guarantee = Integer.valueOf(guaranteeText.getText().trim());
			}
			catch(NumberFormatException e) {
					validInput = false;
			}
			
			if(validInput) {
				String subDay = startDateText.getText().substring(0, 2);
				String subMonth = startDateText.getText().substring(3, 5);
				String subYear = startDateText.getText().substring(6, 10);
				int day = Integer.valueOf(subDay);
				int month = Integer.valueOf(subMonth);
				int year = Integer.valueOf(subYear);
				
				LocalDate date = LocalDate.of(year, month, day);
				ICompany product = new Company(nameText.getText().trim(), date , guarantee);
				return new CompanyData(product, true, false, false, false , false);
			}
			
		}
		else {
			return null;
		}
		return null;
		
	}	
	
	private boolean isValidDate(String str) {
		if(str.length() != 10) {
			return false;
		}
		if(str.charAt(2) != '.' || str.charAt(5) != '.' ) {
			return false;
		}
		
		for(int i = 0 ; i < 2 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		for(int i = 3 ; i < 5 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		for(int i = 6 ; i < 10 ; i++) {
			char currentChar = str.charAt(i);
			if(currentChar < '0' || currentChar > '9') {
				return false;
			}
		}
		
		return true;
		
	}

	
	
}


