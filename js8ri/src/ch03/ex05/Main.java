package ch03.ex05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    private static final String CD = System.getProperty("user.dir");
    private static final String IMAGE_FILE_PATH = CD + "\\src\\ch03\\ex05\\ouchi.jpg";

    /** 縁の太さ */
    private static final int IMG_FLAME_STROKE_WIDTH = 10;

    private static Image transform(Image in, ColorTransformer transformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, transformer.apply(x, y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();

        File file = new File(IMAGE_FILE_PATH);
        System.out.println("image path = " + IMAGE_FILE_PATH + " exist? = " + file.exists());
        Image img = new Image(file.toURI().toString());
        int imgWidth = (int) img.getWidth();
        int imgHeight = (int) img.getHeight();

        Image newImage
                = transform(img, (x, y, colorAtXY) -> {
            if (x < IMG_FLAME_STROKE_WIDTH || x >= imgWidth - IMG_FLAME_STROKE_WIDTH ||
                    y < IMG_FLAME_STROKE_WIDTH || y >= imgHeight - IMG_FLAME_STROKE_WIDTH) {
                return Color.SILVER;
            }
            return colorAtXY;
        });

        ImageView imageView = new ImageView(newImage);

        Scene scene = new Scene(pane, imgWidth, imgHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXTest");
        primaryStage.setResizable(false);

        pane.getChildren().add(imageView);
        primaryStage.show();
    }
}
