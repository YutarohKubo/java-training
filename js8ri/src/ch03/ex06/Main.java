package ch03.ex06;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.BiFunction;

public class Main extends Application{

    private static final String CD = System.getProperty("user.dir");
    private static final String IMAGE_FILE_PATH = CD + "\\src\\ch03\\ex06\\ouchi.jpg";

    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y), arg));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();

        File file = new File(IMAGE_FILE_PATH);
        System.out.println("image path = " + IMAGE_FILE_PATH + " exist? = " + file.exists());
        Image img = new Image(file.toURI().toString());
        int imgWidth = (int) img.getWidth();
        int imgHeight = (int) img.getHeight();

        Image newImage
                = transform(img, (c, factor) -> c.deriveColor(0, 1, factor, 1), 2.0);

        ImageView imageView = new ImageView(newImage);

        Scene scene = new Scene(pane, imgWidth, imgHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXTest");
        primaryStage.setResizable(false);

        pane.getChildren().add(imageView);
        primaryStage.show();
    }
}
