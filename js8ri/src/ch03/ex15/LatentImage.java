package ch03.ex15;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

public class LatentImage {

    private final Object mLock = new Object();

    private Image in;
    private List<UnaryOperator<Color>> pendingOperations;

    private LatentImage(Image image){
        this.in = image;
        pendingOperations = new ArrayList<>();
    }

    static LatentImage from(Image image) {
        return new LatentImage(image);
    }

    LatentImage transform(UnaryOperator<Color> f) {
        pendingOperations.add(f);
        return this;
    }

    public Image toImage() {
        Color[][] colorTransformed = parallelTransform(ImageUtil.toColorArray(in));
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color c = colorTransformed[y][x];
                out.getPixelWriter().setColor(x, y, c);
            }
        }
        return out;
    }

    public Color[][] parallelTransform(Color[][] in) {
        int n = Runtime.getRuntime().availableProcessors();
        int height = in.length;
        int width = in[0].length;
        Color[][] out = new Color[height][width];
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < n; i++) {
                int fromY = i * height / n;
                int toY = (i + 1) * height / n;
                pool.submit(() -> {
                    for (int x = 0; x < width; x++) {
                        for (int y = fromY; y < toY; y++) {
                            for (UnaryOperator<Color> f : pendingOperations) {
                                synchronized (mLock) {
                                    out[y][x] = f.apply(in[y][x]);
                                }
                            }
                        }
                    }
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return out;
    }

}
