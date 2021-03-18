package dc3_4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Property mProperty = new Property();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mProperty.loadProperty();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        BorderPane root = loader.load();
        ClockController clockController = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setOnCloseRequest((event) -> {
            mProperty.setWindowX((int) primaryStage.getX());
            mProperty.setWindowY((int) primaryStage.getY());
            mProperty.setWindowWidth((int) primaryStage.getWidth());
            mProperty.setWindowHeight((int) primaryStage.getHeight());
            mProperty.saveProperty();
            System.exit(0);
        });
        clockController.setStage(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setX(mProperty.getWindowX());
        primaryStage.setY(mProperty.getWindowY());
        primaryStage.setWidth(mProperty.getWindowWidth());
        primaryStage.setHeight(mProperty.getWindowHeight());
        primaryStage.show();
    }
}
