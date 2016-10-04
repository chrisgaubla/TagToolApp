package tagtool;

import org.json.simple.JSONObject;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TagItem extends GridPane{
	
	private String tagCode;
	private String selection;
	private Label selectionLabel;
	private int begin;
	private int end;
	
	private Font font;
	private Label tagLabel;
	private Label beginLabel;
	private Label endLabel;
	private Button deleteButton;

	public TagItem(JSONObject obj) {
		this.font = new Font("arial", 18);
		
		this.tagCode = (String) obj.get("tagCode");
		this.begin =  (int) obj.get("begin");
		this.end=  (int) obj.get("end");
		this.selection = (String) obj.get("selection");
		this.selectionLabel = new Label(selection);
		this.tagLabel = new Label(tagCode);
		this.endLabel = new Label(String.valueOf(end));
		this.beginLabel = new Label(String.valueOf(begin));
		this.deleteButton = new Button("Delete");
		
		
		tagLabel.setFont(font);
		beginLabel.setFont(font);
		endLabel.setFont(font);
		selectionLabel.setFont(font);
		
		
		setGridLinesVisible(true);
		
		tagLabel.prefHeightProperty().bind(heightProperty());
		tagLabel.prefWidthProperty().bind(widthProperty());
		beginLabel.prefHeightProperty().bind(heightProperty());
		beginLabel.prefWidthProperty().bind(widthProperty());
		endLabel.prefHeightProperty().bind(heightProperty());
		endLabel.prefWidthProperty().bind(widthProperty());
		selectionLabel.prefHeightProperty().bind(heightProperty());
		selectionLabel.prefWidthProperty().bind(widthProperty());
		
		deleteButton.prefHeightProperty().bind(heightProperty());
		deleteButton.prefWidthProperty().bind(widthProperty());
		
		GridPane.setColumnIndex(tagLabel, 0);
		GridPane.setColumnIndex(beginLabel, 1);
		GridPane.setColumnIndex(endLabel, 2);
		GridPane.setColumnIndex(selectionLabel, 3);
		GridPane.setColumnIndex(deleteButton, 4);

		getChildren().add(tagLabel);
		getChildren().add(beginLabel);
		getChildren().add(endLabel);
		getChildren().add(selectionLabel);
		getChildren().add(deleteButton);
	}

	public String getTagCode() {
		return tagCode;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}
	public String getSelection(){
		return selection;
	}
	
	public Button getDeleteButton(){
		return deleteButton;
	}
	

}
