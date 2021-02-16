package dc3_3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
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
    private ContextMenu mMenu;

    @FXML
    private BorderPane parentPane;
    @FXML
    private Canvas canvasMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        g = canvasMain.getGraphicsContext2D();
        createPopupMenu();
        parentPane.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            if (mMenu.isShowing()) {
                mMenu.hide();
            }
        });
        parentPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                mMenu.show(parentPane, e.getScreenX(), e.getScreenY());
            }
        });
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

    private void createPopupMenu() {
        mMenu = new ContextMenu();
        Menu menuSettingTimeColor = new Menu("Time color");
        Menu menuSettingBackgroundColor = new Menu("Background color");
        Menu menuSettingFont = new Menu("Font");
        Menu menuSettingFontSize = new Menu("Font size");
        MenuItem menuItemCloseApp = new MenuItem("Close");
        menuItemCloseApp.setOnAction(e -> System.exit(0));
        final AppColorData[] colors = Property.getAppColors();
        CustomMenuItem[] menuItemTimeColors = new CustomMenuItem[colors.length];
        CustomMenuItem[] menuItemBackgroundColors = new CustomMenuItem[colors.length];
        for (int i = 0; i < colors.length; i++) {
            AppColorData color = colors[i];
            menuItemTimeColors[i] = new CustomMenuItem(new ColorMenuPane(color));
            menuItemTimeColors[i].setOnAction(e -> {
                Main.mProperty.setTimeColor(color.getName());
            });
            menuItemBackgroundColors[i] = new CustomMenuItem(new ColorMenuPane(color));
            menuItemBackgroundColors[i].setOnAction(e -> {
                Main.mProperty.setBackgroundColor(color.getName());
            });
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        MenuItem[] menuItemFontNames = new MenuItem[fontNames.length];
        for (int i = 0; i < fontNames.length; i++) {
            String fontName = fontNames[i];
            menuItemFontNames[i] = new MenuItem(fontName);
            menuItemFontNames[i].setOnAction(e -> {
                Main.mProperty.setTimeFont(fontName);
            });
        }
        final AppFontSizeData[] fontSizeData = Property.getAppFontSize();
        MenuItem[] menuItemFontSizes = new MenuItem[fontSizeData.length];
        for (int i = 0; i < fontSizeData.length; i++) {
            AppFontSizeData fontSize = fontSizeData[i];
            menuItemFontSizes[i] = new MenuItem(fontSize.getName());
            menuItemFontSizes[i].setOnAction(e -> {
                Main.mProperty.setTimeFontSize(fontSize.getName());
            });
        }
        menuSettingTimeColor.getItems().addAll(menuItemTimeColors);
        menuSettingBackgroundColor.getItems().addAll(menuItemBackgroundColors);
        menuSettingFont.getItems().addAll(menuItemFontNames);
        menuSettingFontSize.getItems().addAll(menuItemFontSizes);
        mMenu.getItems().addAll(menuSettingTimeColor, menuSettingBackgroundColor, menuSettingFont, menuSettingFontSize, menuItemCloseApp);
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
