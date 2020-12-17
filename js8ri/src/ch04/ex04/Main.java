package ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        Circle circle = new Circle(100);
        root.setCenter(circle);
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        ObservableValue<Number> diffSceneWidthHeight = Bindings.subtract(scene.widthProperty(), scene.heightProperty());
        diffSceneWidthHeight.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() < 0) {
                circle.radiusProperty().bind(Bindings.divide(scene.widthProperty(), 2));
            } else {
                circle.radiusProperty().bind(Bindings.divide(scene.heightProperty(), 2));
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
