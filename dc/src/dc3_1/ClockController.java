package dc3_1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ClockController implements Initializable {

    private LocalDateTime currentTime;
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    private GraphicsContext g;

    @FXML
    private Canvas canvasMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = canvasMain.getGraphicsContext2D();
        Thread t = new Thread(() -> {
            while (true) {
                currentTime = LocalDateTime.now();
                try {
                    draw();
                } catch (Exception e) {
                    // 何もしない
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void draw() {
        g.clearRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        g.setFill(AppStyle.BLACK);
        g.fillRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        int fontSize = 48;
        Font font = Font.font("TimesRoman", FontWeight.NORMAL, fontSize);
        String currentDateStr = currentTime.format(DateTimeFormatter.ofPattern(dateFormat));
        String currentTimeStr = currentTime.format(DateTimeFormatter.ofPattern(timeFormat));
        Text textDate = new Text(currentDateStr);
        textDate.setFont(font);
        Text textTime = new Text(currentTimeStr);
        textTime.setFont(font);
        final double textDateWidth = textDate.getLayoutBounds().getWidth();
        final double textDateHeight = textDate.getLayoutBounds().getHeight();
        final double textTimeWidth = textTime.getLayoutBounds().getWidth();
        final double textTimeHeight = textTime.getLayoutBounds().getHeight();
        double textCenterX = canvasMain.getWidth() / 2.0;
        double textCenterY = canvasMain.getHeight() / 2.0;
        g.setFont(font);
        g.setFill(AppStyle.VERT_PRAIRIE);
        g.fillText(currentDateStr, textCenterX - textDateWidth / 2.0, textCenterY - fontSize - textDateHeight / 2.0);
        g.fillText(currentTimeStr, textCenterX - textTimeWidth / 2.0, textCenterY + fontSize - textTimeHeight / 2.0);
    }

}
