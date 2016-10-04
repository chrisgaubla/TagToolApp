package tagtool;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class TagPane extends ScrollPane {

	private GridPane tags;

	public TagPane() {
		super();

		tags = new GridPane();

		tags.prefHeightProperty().bind(heightProperty());
		tags.prefWidthProperty().bind(widthProperty().multiply(0.97));
		setContent(tags);

	}

	public void update(LoadedJsonfx loadedjson) {
		JSONArray array = loadedjson.getTags();

		tags.getChildren().clear();
		for (int i = 0; i < array.size(); i++) {
			TagItem item = new TagItem((JSONObject) array.get(i));
			item.prefHeightProperty().bind(heightProperty().multiply(0.1));
			item.prefWidthProperty().bind(widthProperty());			
			
			item.getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					loadedjson.removeTag(item.getSelection(), item.getTagCode(), item.getBegin(), item.getEnd());
				}

			});

			tags.getChildren().add(item);
			GridPane.setRowIndex(item, i);

		}

	}

	public GridPane getTagsPane() {
		return tags;
	}

}
