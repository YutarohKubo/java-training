package dc3_2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    private BorderPane parentPane;
    @FXML
    private Canvas canvasMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = canvasMain.getGraphicsContext2D();
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            currentTime = LocalDateTime.now();
            draw();
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void draw() {
        double fontRatio = (double) Main.mProperty.getTimeFontSize().getSize() / Property.DEFAULT_FONT_SIZE;
        Main.mProperty.setWindowWidth((int) (Property.DEFAULT_FRAME_WIDTH * fontRatio));
        Main.mProperty.setWindowHeight((int) (Property.DEFAULT_FRAME_HEIGHT * fontRatio));
        canvasMain.setWidth(Main.mProperty.getWindowWidth());
        canvasMain.setHeight(Main.mProperty.getWindowHeight());
        canvasMain.getScene().getWindow().setWidth(Main.mProperty.getWindowWidth());
        canvasMain.getScene().getWindow().setHeight(Main.mProperty.getWindowHeight());
        g.clearRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        g.setFill(Main.mProperty.getBackgroundColor().getColor());
        g.fillRect(0, 0, canvasMain.getWidth(), canvasMain.getHeight());
        Font font = Main.mProperty.getTimeFont();
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
        g.setFill(Main.mProperty.getTimeColor().getColor());
        g.fillText(currentDateStr, textCenterX - textDateWidth / 2.0, textCenterY - font.getSize() - textDateHeight / 2.0);
        g.fillText(currentTimeStr, textCenterX - textTimeWidth / 2.0, textCenterY + font.getSize() - textTimeHeight / 2.0);
    }

    @FXML
    private void onClickMenuSetting(ActionEvent event) throws IOException {
        GridPane pane = FXMLLoader.load(getClass().getResource("property_dialog.fxml"));
        Scene scene = new Scene(pane, Property.DEFAULT_DIALOG_WIDTH, Property.DEFAULT_DIALOG_HEIGHT);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
