package dc3_4;

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

    private Stage mStage;
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

    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    private void draw() {
        double fontRatio = (double) Main.mProperty.getTimeFontSize().getSize() / Property.DEFAULT_FONT_SIZE;
        // Main.mProperty.setWindowWidth((int) (Property.DEFAULT_FRAME_WIDTH * fontRatio));
        // Main.mProperty.setWindowHeight((int) (Property.DEFAULT_FRAME_HEIGHT * fontRatio));
        int width = (int) (Property.DEFAULT_FRAME_WIDTH * fontRatio);
        int height = (int) (Property.DEFAULT_FRAME_HEIGHT * fontRatio);
        canvasMain.setWidth(width);
        canvasMain.setHeight(height);
        canvasMain.getScene().getWindow().setWidth(width);
        canvasMain.getScene().getWindow().setHeight(height);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("property_dialog.fxml"));
        GridPane pane = loader.load();
        PropertyDialogController dialogController = loader.getController();
        Scene scene = new Scene(pane, Property.DEFAULT_DIALOG_WIDTH, Property.DEFAULT_DIALOG_HEIGHT);
        Stage stage = new Stage();
        dialogController.setStage(stage);
        if (mStage != null) {
            stage.initOwner(mStage);
            if (Main.mProperty.getTimeFontSize().getName().equals(Property.SMALL_FONT_SIZE_NAME)) {
                stage.setX(mStage.getX() - Property.DEFAULT_DIALOG_WIDTH * 2.0 / 8);
                stage.setY(mStage.getY() - Property.DEFAULT_DIALOG_HEIGHT * 2.0 / 8);
                // 以下はここで処理させても利かない
                // pane.setPrefWidth(mStage.getWidth() * 3 / 4);
                // pane.setPrefHeight(mStage.getHeight() * 3 / 4);
            } else {
                stage.setX(mStage.getX() + mStage.getWidth() / 8);
                stage.setY(mStage.getY() + mStage.getHeight() / 8);
                // 以下はここで処理させても利かない
                // pane.setPrefWidth(Property.DEFAULT_DIALOG_WIDTH * 6 / 4);
                // pane.setPrefHeight(Property.DEFAULT_DIALOG_HEIGHT * 6 / 4);
            }
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }

}
