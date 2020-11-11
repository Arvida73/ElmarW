package GUI;

import InterFaces.ICompany;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DisplayPanel extends VBox {

	private final String nameLabel = "Nazwa firmy";
	private final String startDateLabel = "Rozpoczęcie";
	private final String guaranteeLabel = "Gwarancja w (Msc)";
	private final String expiredDateLabel = "Zakończenie";
	
	private final double nameLabelWidth = 0.35;
	private final double startDateLabelWidth = 0.2;
	private final double guaranteeLabelWidth = 0.25;
	private final double expiredDateLabelWidth = 0.18;
	
	
	
	private final int labelHeight = 50;
	private final int companyPanelHeight = 40;
	
	private int panelWidth;
	private int panelHeight;

	
	private final Font labelFont = Font.font("Serif", 25);
	
	private Label nameL;
	private Label startDateL;
	private Label guaranteeL;
	private Label expireDateL;
	
	private ScrollPane scrollPanel;
	private VBox companyShowPanel;
	private int numOfCompany;

	
	public DisplayPanel(int width , int height) {
		this.panelWidth = width;
		this.panelHeight = height;
		
		numOfCompany = 0;
		
		this.setPrefSize(panelWidth, panelHeight);
		
		initProperties();
		initHeadLines();
		
		scrollPanel = new ScrollPane();
		scrollPanel.setPrefSize(this.panelWidth, this.panelHeight);
		scrollPanel.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPanel.setContent(this.companyShowPanel);
		scrollPanel.setStyle("-fx-background: azure;\n -fx-border-color: black;\n -fx-fit-to-height: false");
		
		this.getChildren().addAll(scrollPanel);
		
	}
	
	
	
	private void initProperties() {
		
		this.nameL = new Label(nameLabel);
		this.nameL.setPrefSize(this.panelWidth * nameLabelWidth, labelHeight);
		this.nameL.setAlignment(Pos.CENTER);
		this.nameL.setFont(labelFont);
		this.nameL.setStyle("-fx-border-color: BLACK");	
		
		
		this.startDateL = new Label(startDateLabel);
		this.startDateL.setPrefSize(this.panelWidth * startDateLabelWidth, labelHeight);
		this.startDateL.setAlignment(Pos.CENTER);
		this.startDateL.setFont(labelFont);
		this.startDateL.setStyle("-fx-border-color: BLACK");


		this.guaranteeL = new Label(guaranteeLabel);
		this.guaranteeL.setPrefSize(this.panelWidth * guaranteeLabelWidth, labelHeight);
		this.guaranteeL.setAlignment(Pos.CENTER);
		this.guaranteeL.setFont(labelFont);
		this.guaranteeL.setStyle("-fx-border-color: BLACK");


		this.expireDateL = new Label(expiredDateLabel);
		this.expireDateL.setPrefSize(this.panelWidth * expiredDateLabelWidth, labelHeight);
		this.expireDateL.setAlignment(Pos.CENTER);
		this.expireDateL.setFont(labelFont);
		this.expireDateL.setStyle("-fx-border-color: BLACK");

		
		this.companyShowPanel = new VBox();
		this.companyShowPanel.setPrefSize(this.panelWidth, this.panelHeight - labelHeight);
		this.companyShowPanel.setSpacing(2);
		
		
	}




	private void initHeadLines() {
		HBox headlines = new HBox();
		headlines.setPrefSize(this.panelWidth, labelHeight);
		headlines.getChildren().addAll(expireDateL, guaranteeL, startDateL, nameL );
		headlines.setStyle(" -fx-border-color: BLACK");
		
		this.getChildren().add(headlines);
	}
	
	
	public void addCompany(ICompany company) {
		if(company != null) {
			numOfCompany++;
			if(numOfCompany * companyPanelHeight > companyShowPanel.getPrefHeight()) {
				companyShowPanel.setPrefHeight(numOfCompany * companyPanelHeight);
			}
			
			companyShowPanel.getChildren().add( new CompanyPanel(company ,this.panelWidth, companyPanelHeight));
		}

	}

	public void cleanCompany() {
		companyShowPanel.getChildren().clear();
		numOfCompany = 0;
	}
	
}

	