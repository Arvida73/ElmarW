package GUI;

import java.time.LocalDate;

import InterFaces.ICompany;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CompanyPanel extends HBox {
	
	
	private final double nameWidth = 0.35;
	private final double startDateWidth = 0.2;
	private final double expireDateWidth = 0.18;
	private final double guaranteeWidth = 0.25;
	
	
	private final Font labelFont = Font.font("Serif", 20);

	
	private Label companyNameValue;
	private Label companyStartDateValue;
	private Label companyExpireDateValue;
	private Label companyGuaranteeValue;


	public CompanyPanel(ICompany company , int width , int height) {
		this.setPrefSize(width , height);
		
		companyNameValue = new Label(" - " + company.getName());
		companyNameValue.setPrefSize(nameWidth * width , height);
		companyNameValue.setAlignment(Pos.CENTER);
		companyNameValue.setFont(labelFont);
		companyNameValue.setStyle("-fx-border-color: BLACK");
		
		
		companyStartDateValue = new Label(company.toStringStartDate());
		companyStartDateValue.setPrefSize(startDateWidth * width , height);
		companyStartDateValue.setAlignment(Pos.CENTER);
		companyStartDateValue.setFont(labelFont);
		companyStartDateValue.setStyle("-fx-border-color: BLACK");
		
		

		companyExpireDateValue = new Label(company.toStringExpireDate());
		companyExpireDateValue.setPrefSize(expireDateWidth * width , height);
		companyExpireDateValue.setAlignment(Pos.CENTER);
		companyExpireDateValue.setFont(labelFont);
		companyExpireDateValue.setStyle("-fx-border-color: BLACK");
		
		
		if(company.getExpireDate().compareTo(LocalDate.now()) <= 0 ) {
			companyExpireDateValue.setStyle("-fx-border-color: BLACK;\n -fx-background-color : red");
		}
		else {
			int month = company.getExpireDate().getMonthValue();
			int year = company.getExpireDate().getYear();
			
			int nowMonth = LocalDate.now().getMonthValue();
			int nowYear = LocalDate.now().getYear();
			
			if(month == nowMonth && year == nowYear) {
				companyExpireDateValue.setStyle("-fx-border-color: BLACK;\n -fx-background-color : yellow");
			}
		}

		
		companyGuaranteeValue = new Label(String.valueOf(company.getDurationMonths()));
		companyGuaranteeValue.setPrefSize(guaranteeWidth * width , height);
		companyGuaranteeValue.setAlignment(Pos.CENTER);
		companyGuaranteeValue.setFont(labelFont);
		companyGuaranteeValue.setStyle("-fx-border-color: BLACK");
		
		
		this.getChildren().addAll(companyExpireDateValue, companyGuaranteeValue, companyStartDateValue, companyNameValue);
			
	}
	

	
}
