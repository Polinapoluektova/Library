package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.ResourceBundle;

public class userLibraryController {
    @FXML private TableColumn<libraryDetails, String> authorBook;
    @FXML private Button backBtn;
    @FXML private TableView<libraryDetails> baseData;
    @FXML private TableColumn<libraryDetails, String> bookName;
    @FXML private Button deleteBtn;
    @FXML private TableColumn<libraryDetails, String> genreBook;
    @FXML private TableColumn<libraryDetails, Integer> idBook;
    @FXML private TableColumn<libraryDetails, String> usBook;
    @FXML private TableColumn<libraryDetails, Integer> year;

    public ObservableList<libraryDetails> bookListUser = FXCollections.observableArrayList();
    private DbConnection dc;
    FXMLLoader loader = new FXMLLoader();
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // кнопка выхода из системы
        backBtn.setOnAction(event -> {
            backBtn.getScene().getWindow().hide();
            loader.setLocation(Controller.class.getResource("userApp.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        });
    }
}



