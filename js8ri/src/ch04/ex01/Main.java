package ch04.ex01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Label label = new Label();
        label.setFont(new Font(100));
        TextField textField = new TextField();
        label.textProperty().bind(textField.textProperty());
        textField.setText("Hello Fx");

        Scene scene = new Scene(pane);
        pane.setTop(textField);
        pane.setCenter(label);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello");
        primaryStage.show();
    }
}
