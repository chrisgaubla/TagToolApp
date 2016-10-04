package tagtool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TagToolApp extends Application {

    private MenuBar menubar;
    private Menu options;
    private MenuItem loadJsonMenu;
    private MenuItem saveJsonMenu;

    private FileChooser fileChooser;
    private GridPane root;
    private TextPane text;
    private TagPane tags;

    private LoadedJsonfx loadedjson;

    @Override
    public void start(Stage primaryStage) throws Exception {

        fileChooser = new FileChooser();

        loadedjson = null;

        menubar = new MenuBar();
        options = new Menu("Options");
        loadJsonMenu = new MenuItem("Load JSON");
        saveJsonMenu = new MenuItem("Save JSON");

        options.getItems().addAll(loadJsonMenu, saveJsonMenu);
        menubar.getMenus().add(options);

        loadJsonMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loadJson(fileChooser.showOpenDialog(primaryStage));
            }
        });

        saveJsonMenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                saveJson(fileChooser.showSaveDialog(primaryStage));
            }
        });

        root = new GridPane();
        root.setGridLinesVisible(true);

        text = new TextPane();
        tags = new TagPane();

        root.getChildren().add(menubar);
        root.getChildren().add(text);
        root.getChildren().add(tags);

        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        GridPane.setRowIndex(menubar, 0);
        GridPane.setRowIndex(text, 1);
        GridPane.setRowIndex(tags, 2);
        Scene scene = new Scene(root, 800, 900);

        primaryStage.setTitle("Text Tagger Tool");
        primaryStage.setScene(scene);

        primaryStage.show();

        text.prefHeightProperty().bind(scene.heightProperty());
        text.prefWidthProperty().bind(scene.widthProperty());

        tags.prefHeightProperty().bind(scene.heightProperty());
        tags.prefWidthProperty().bind(scene.widthProperty());

        // event handling and filters

        text.addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                loadedjson.addTag(text.getSelection(), text.getTagCode(), text.getBegin(), text.getEnd());
                tags.update(loadedjson);
            }

        });

        tags.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("update");
                tags.update(loadedjson);
                System.out.println("done");
            }
        });
        
        text.getSourceText().textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(final ObservableValue<? extends String> observable, final String oldValue,
					final String newValue) {
					loadedjson.updateTagsFromText( oldValue,  newValue);
					
			}
		});
		

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadJson(File file) {
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());
            loadedjson = new LoadedJsonfx(file);
            update();
        }
    }

    private void saveJson(File file) {
        if (file != null) {
            fileChooser.setInitialDirectory(file.getParentFile());

            try {
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                writer.write(loadedjson.getJson().toJSONString());
                writer.close();

            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void update() {
        tags.update(loadedjson);
        text.update(loadedjson);
    }

}
