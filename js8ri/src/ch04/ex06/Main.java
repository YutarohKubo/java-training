package ch04.ex06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        HBox childPane1 = new HBox();
        HBox childPane2 = new HBox();
        Button buttonTop = new Button("TOP");
        Button buttonBottom = new Button("BOTTOM");
        childPane1.getChildren().add(buttonTop);
        childPane1.setAlignment(Pos.CENTER);
        childPane2.getChildren().add(buttonBottom);
        childPane2.setAlignment(Pos.CENTER);
        pane.setTop(childPane1);
        pane.setLeft(new Button("LEFT"));
        pane.setCenter(new Button("CENTER"));
        pane.setRight(new Button("RIGHT"));
        pane.setBottom(childPane2);

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
