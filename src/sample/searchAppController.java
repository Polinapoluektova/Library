package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class searchAppController {

    @FXML
    private Button addUserBook;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField dataSearch;

    @FXML
    private Button back;

    @FXML
    private Label nameUser;

    @FXML
    private TableView<libraryDetails> baseData;

    @FXML
    private TableColumn<libraryDetails, String> authorBook;

    @FXML
    private TableColumn<libraryDetails, String> bookName;

    @FXML
    private TableColumn<libraryDetails, String> genreBook;

    @FXML
    private TableColumn<libraryDetails, Integer> idBook;

    @FXML
    private TableColumn<libraryDetails, String> usBook;

    @FXML
    private TableColumn<libraryDetails, Integer> year;


    private ObservableList<libraryDetails> bookList = FXCollections.observableArrayList();
    //private Integer selectedBook = 0;
    private DbConnection dc;
    }


