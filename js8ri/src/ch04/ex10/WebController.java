package ch04.ex10;

import javafx.beans.binding.Bindings;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class WebController implements Initializable {

    @FXML
    private Button buttonPrev;
    @FXML
    private Button buttonNext;
    @FXML
    private TextField fieldAddress;
    @FXML
    private Button buttonSearch;
    @FXML
    private WebView webView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load("https://www.google.co.jp/");
        webView.getEngine().getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                fieldAddress.setText(webView.getEngine().getLocation());

                WebEngine engine = webView.getEngine();
                WebHistory history = engine.getHistory();
                buttonPrev.setDisable(history.getCurrentIndex() == 0);
                buttonNext.setDisable(history.getCurrentIndex() == history.getEntries().size() - 1);
            }
        });
        buttonSearch.disableProperty().bind(Bindings.equal(fieldAddress.lengthProperty(), 0));
    }

    @FXML
    private void onClickSearch() {
        load(fieldAddress.getText());
    }

    @FXML
    private void onClickPrev() {
        WebEngine engine = webView.getEngine();
        WebHistory history = engine.getHistory();
        if (history.getCurrentIndex() > 0) {
            history.go(-1);
        }
    }

    @FXML
    private void onClickNext() {
        WebEngine engine = webView.getEngine();
        WebHistory history = engine.getHistory();
        if (history.getCurrentIndex() < history.getEntries().size() - 1) {
            history.go(1);
        }
    }

    private void load(String search) {
        if (!search.matches("^https?://.+")) {
            //httpじゃないならgoogle検索を実行
            search = "https://www.google.co.jp/search?q=" + search;
        }
        webView.getEngine().load(search);
    }
}
