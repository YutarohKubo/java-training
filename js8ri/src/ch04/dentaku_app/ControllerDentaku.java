package ch04.dentaku_app;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDentaku implements Initializable {

    private StringBuilder resultBuilder = new StringBuilder();

    @FXML
    private TextField fieldResult;
    @FXML
    private Button buttonOne;
    @FXML
    private Button buttonTwo;
    @FXML
    private Button buttonThree;
    @FXML
    private Button buttonFour;
    @FXML
    private Button buttonFive;
    @FXML
    private Button buttonSix;
    @FXML
    private Button buttonSeven;
    @FXML
    private Button buttonEight;
    @FXML
    private Button buttonNine;
    @FXML
    private Button buttonZero;
    @FXML
    private Button buttonPeriod;
    @FXML
    private Button buttonEqual;
    @FXML
    private Button buttonDel;
    @FXML
    private Button buttonPlus;
    @FXML
    private Label labelResult;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addHandlerToNumButton(buttonOne);
        addHandlerToNumButton(buttonTwo);
        addHandlerToNumButton(buttonThree);
        addHandlerToNumButton(buttonFour);
        addHandlerToNumButton(buttonFive);
        addHandlerToNumButton(buttonSix);
        addHandlerToNumButton(buttonSeven);
        addHandlerToNumButton(buttonEight);
        addHandlerToNumButton(buttonNine);
        addHandlerToNumButton(buttonZero);
        addHandlerToNumButton(buttonPeriod);
        addHandlerToNumButton(buttonPlus);
        buttonDel.setOnAction((event) -> {
            removeText();
        });
        BooleanBinding booleanBinding = Bindings.lessThanOrEqual(fieldResult.lengthProperty(), 0);
        booleanBinding.addListener((observable) -> {
            System.out.println("aaaaa invalidate.");
        });
        booleanBinding.addListener((observable, newValue, oldValue) -> {
            System.out.println("aaaaa " + "oldValue = " + oldValue + " , newValue = " + newValue);
        });

        buttonDel.disableProperty().bind(Bindings.lessThanOrEqual(fieldResult.lengthProperty(), 0));
        StringProperty stringProperty = fieldResult.textProperty();
        /*stringProperty.addListener((observable) -> {
            System.out.println("invalidate.");
        });
        stringProperty.addListener(((observable, oldValue, newValue) -> {
            System.out.println("oldValue = " + oldValue + " , newValue = " + newValue);
        }));*/
        labelResult.textProperty().bind(stringProperty);
    }

    private void addHandlerToNumButton(Button numButton) {
        numButton.setOnAction((event) -> {
            addText(numButton.getText());
        });
    }

    private void addText(String text) {
        resultBuilder.append(text);
        fieldResult.setText(resultBuilder.toString());
    }

    private void removeText() {
        resultBuilder.delete(resultBuilder.length() - 1, resultBuilder.length());
        fieldResult.setText(resultBuilder.toString());
    }

}
