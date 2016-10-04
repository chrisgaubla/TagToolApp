package tagtool;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class TextPane extends GridPane {

	private TextArea sourceText;
	private GridPane codeZone;
	private TextArea tag;
	public Button addButton;
	private Font font;

	public TextPane() {
		super();

		sourceText = new TextArea("This box will show the text.");
		
		
		
		
		codeZone = new GridPane();
		tag = new TextArea("Enter code here");
		addButton = new Button("Add Code");
		font = new Font("arial", 18);

		sourceText.setFont(font);
		sourceText.setWrapText(true);
		sourceText.setEditable(false);
		sourceText.prefHeightProperty().bind(heightProperty());
		sourceText.prefWidthProperty().bind(widthProperty());

		tag.setFont(font);
		tag.setEditable(true);

		addButton.prefHeight(40);
		addButton.prefWidthProperty().bind(widthProperty());

		codeZone.getChildren().add(addButton);
		codeZone.setPrefHeight(40);
		codeZone.prefWidthProperty().bind(widthProperty());

		tag.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (tag.getText().equals("Enter code here")) {
					tag.clear();
				}
			}

		});

		GridPane.setColumnIndex(sourceText, 0);
		GridPane.setColumnSpan(sourceText, 2);
		GridPane.setRowIndex(sourceText, 0);

		GridPane.setColumnIndex(tag, 0);
		GridPane.setColumnSpan(tag, 1);
		GridPane.setRowIndex(tag, 0);

		GridPane.setColumnIndex(addButton, 1);
		GridPane.setColumnSpan(addButton, 1);
		GridPane.setRowIndex(addButton, 0);

		GridPane.setColumnIndex(codeZone, 0);
		GridPane.setRowIndex(codeZone, 1);

		codeZone.getChildren().add(tag);
		getChildren().add(codeZone);
		getChildren().add(sourceText);

	}

	public void update(LoadedJsonfx loadedjson) {
		sourceText.setText((String) loadedjson.getJson().get("text"));
	}

	public String getTagCode() {
		return tag.getText();
	}

	public String getSelection() {
		return sourceText.getSelectedText();
	}

	public int getBegin() {
		return sourceText.getSelection().getStart();

	}

	public int getEnd() {
		return sourceText.getSelection().getEnd();
	}

	public TextArea getSourceText() {
		return sourceText;
	}

}
