package ch04.ex09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane, 600, 400);

        Circle circleSun = new Circle(50);
        Circle circleEarth = new Circle(20);
        circleSun.setFill(Color.RED);
        circleEarth.setFill(Color.BLUE);
        Ellipse earthOrbit = new Ellipse();
        circleSun.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        circleSun.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2));
        earthOrbit.centerXProperty().bind(circleSun.centerXProperty());
        earthOrbit.centerYProperty().bind(circleSun.centerYProperty());
        earthOrbit.setRadiusX(200);
        earthOrbit.setRadiusY(100);
        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setNode(circleEarth);
        pathTransition1.setPath(earthOrbit);
        pathTransition1.setDuration(Duration.millis(1000));
        pathTransition1.setInterpolator(Interpolator.LINEAR);
        pathTransition1.setCycleCount(PathTransition.INDEFINITE);

        pane.getChildren().add(circleSun);
        pane.getChildren().add(circleEarth);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        pathTransition1.play();
    }
}
