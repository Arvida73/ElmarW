package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitlePanel extends HBox {
	
	private final String titleName = "Kolor czerwony, gwaracja doobiegła końca. Zółty kolor ,gwarancja minie w tym miesiącu";
	
	private Label company;
	
	public TitlePanel(int width , int height) {
		this.setPrefSize(width, height);
		
		this.company = new Label(titleName);
		this.company.setPrefSize(width, height);
		this.company.setFont(Font.font("Serif", FontWeight.BOLD, 30));
		this.company.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(this.company);
		
	}
	
	
}
