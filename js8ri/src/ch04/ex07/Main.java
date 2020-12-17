package ch04.ex07;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * ex6に対して、TopのHBoxとLeftのButtonに対して、境界を設定した.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        HBox childPane1 = new HBox();
        HBox childPane2 = new HBox();
        Button buttonTop = new Button("TOP");
        buttonTop.setAlignment(Pos.CENTER);
        Button buttonBottom = new Button("BOTTOM");
        buttonBottom.setAlignment(Pos.CENTER);
        childPane1.getChildren().add(buttonTop);
        childPane1.setAlignment(Pos.CENTER);
        // 境界を設定
        childPane1.setBorder(new Border(new BorderStroke(Color.BLACK ,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(3)
        )));
        childPane2.getChildren().add(buttonBottom);
        childPane2.setAlignment(Pos.CENTER);
        pane.setTop(childPane1);
        Button buttonLeft = new Button("LEFT");
        // 境界を設定
        buttonLeft.setBorder(new Border(new BorderStroke(Color.BLACK ,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(3)
        )));
        pane.setLeft(buttonLeft);
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
