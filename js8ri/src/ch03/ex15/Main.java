package ch03.ex15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private static final String CD = System.getProperty("user.dir");
    private static final String IMAGE_FILE_PATH = CD + "\\src\\ch03\\ex15\\ouchi.jpg";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();

        File file = new File(IMAGE_FILE_PATH);
        Image img = new Image(file.toURI().toString());

        Image finalImage = LatentImage.from(img).transform(Color::brighter).transform(Color::grayscale).toImage();

        ImageView imageView = new ImageView(finalImage);

        Scene scene = new Scene(pane, img.getWidth(), img.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXTest");
        primaryStage.setResizable(false);

        pane.getChildren().add(imageView);
        primaryStage.show();
    }

}
