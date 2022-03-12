package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class userLibraryController {
    @FXML
    private TableColumn<?, ?> authorBook;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<?> baseData;

    @FXML
    private TableColumn<?, ?> bookName;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> genreBook;

    @FXML
    private TableColumn<?, ?> idBook;

    @FXML
    private TextField search;

    @FXML
    private TableColumn<?, ?> usBook;

    @FXML
    private TableColumn<?, ?> year;

    public void initialize() {
        // (зарегистрироваться и зайти)
        backBtn.setOnAction(event -> {
            backBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userApp.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }
}

