package dc3_4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PropertyDialogController implements Initializable {

    @FXML
    private ComboBox<String> fontChoice;

    @FXML
    private ComboBox<AppFontSizeData> fontSizeChoice;

    @FXML
    private ComboBox<AppColorData> timeColorChoice;

    @FXML
    private ComboBox<AppColorData> backgroundColorChoice;

    private Stage mStage;
    private ObservableList<String> fontComboItems;
    private ObservableList<AppFontSizeData> fontSizeComboItems;
    private ObservableList<AppColorData> timeColorComboItems;
    private ObservableList<AppColorData> backgroundColorItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initFontChoice();
        initFontSizeChoice();
        initTimeColorChoice();
        initBackgroundChoice();
    }

    public void setProperty(Property property) {
        Main.mProperty = property;
    }

    /**
     * 本プロパティダイアログのステージをセットする
     *
     * @param mStage ステージ
     */
    public void setStage(Stage mStage) {
        this.mStage = mStage;
    }

    private void initFontChoice() {
        fontComboItems = FXCollections.observableArrayList();
        fontComboItems.addAll(Property.fontFamilies);
        fontChoice.setItems(fontComboItems);
        fontChoice.setValue(Main.mProperty.getTimeFontFamily());
    }

    private void initFontSizeChoice() {
        fontSizeComboItems = FXCollections.observableArrayList();
        fontSizeComboItems.addAll(Property.getAppFontSize());
        fontSizeChoice.setItems(fontSizeComboItems);
        fontSizeChoice.setCellFactory(param -> new AppFontSizeCell());
        fontSizeChoice.setValue(Main.mProperty.getTimeFontSize());
    }

    private void initTimeColorChoice() {
        timeColorComboItems = FXCollections.observableArrayList();
        timeColorComboItems.addAll(Arrays.asList(Property.getAppColors()));
        timeColorChoice.setItems(timeColorComboItems);
        timeColorChoice.setCellFactory(param -> new AppColorChoiceCell());
        timeColorChoice.setValue(Main.mProperty.getTimeColor());
    }

    private void initBackgroundChoice() {
        backgroundColorItems = FXCollections.observableArrayList();
        backgroundColorItems.addAll(Arrays.asList(Property.getAppColors()));
        backgroundColorChoice.setItems(backgroundColorItems);
        backgroundColorChoice.setCellFactory(param -> new AppColorChoiceCell());
        backgroundColorChoice.setValue(Main.mProperty.getBackgroundColor());
    }

    @FXML
    private void onClickOk(ActionEvent event) {
        Main.mProperty.setTimeFont(fontChoice.getValue());
        Main.mProperty.setTimeFontSize(fontSizeChoice.getValue().getName());
        Main.mProperty.setTimeColor(timeColorChoice.getValue().getName());
        Main.mProperty.setBackgroundColor(backgroundColorChoice.getValue());
        Main.mProperty.saveProperty();
        fontChoice.getScene().getWindow().hide();
    }

    @FXML
    private void onClickCancel(ActionEvent event) {
        if (mStage != null) {
            mStage.hide();
        }
    }

}
