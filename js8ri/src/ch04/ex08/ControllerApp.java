package ch04.ex08;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerApp implements Initializable {

    @FXML
    private TextField fieldTodo;
    @FXML
    private Button buttonRegister;
    @FXML
    private Button buttonRemove;
    @FXML
    private ListView<String> listTodo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonRegister.setOnAction((event) -> {
            String toDoText = fieldTodo.getText();
            if (toDoText.length() > 0) {
                listTodo.getItems().add(toDoText);
                fieldTodo.setText("");
                buttonRemove.setDisable(listTodo.getSelectionModel().getSelectedIndex() < 0);
            }
        });
        buttonRemove.setOnAction((event) -> {
            listTodo.getItems().remove(listTodo.getSelectionModel().getSelectedItem());
            buttonRemove.setDisable(listTodo.getSelectionModel().getSelectedIndex() < 0);
        });
        buttonRemove.setDisable(listTodo.getSelectionModel().getSelectedIndex() < 0);
    }

    @FXML
    public void handleMouseClick() {
        buttonRemove.setDisable(listTodo.getSelectionModel().getSelectedIndex() < 0);
    }

}
