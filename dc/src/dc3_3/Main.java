package dc3_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Property mProperty = new Property();
    private double dragStartX;
    private double dragStartY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root);
        scene.setOnMousePressed(e -> {
            switch (e.getButton()) {
                case PRIMARY:
                    dragStartX = e.getSceneX();
                    dragStartY = e.getSceneY();
                    break;

                case SECONDARY:

                    break;
            }
        });
        scene.setOnMouseDragged(e -> {
            switch (e.getButton()) {
                case PRIMARY:
                    primaryStage.setX(e.getScreenX() - dragStartX);
                    primaryStage.setY(e.getScreenY() - dragStartY);
                    break;

                case SECONDARY:

                    break;
            }
        });
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
