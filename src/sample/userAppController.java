package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class userAppController implements Initializable{

    @FXML private Button exitBtn;
    @FXML private TableView<libraryDetails> baseData;
    @FXML private TableColumn<libraryDetails, String> authorBook;
    @FXML private TableColumn<libraryDetails, String> bookName;
    @FXML private TableColumn<libraryDetails, String> genreBook;
    @FXML private TableColumn<libraryDetails, Integer> idBook;
    @FXML private TableColumn<libraryDetails, String> usBook;
    @FXML private TableColumn<libraryDetails, Integer> year;
    @FXML private Label nameUser;

    @FXML
    private Button userLibrary;
    // ObservableList для хранения данных бд
    private ObservableList<libraryDetails> bookData = FXCollections.observableArrayList();
    private DbConnection dc;
    FXMLLoader loader = new FXMLLoader();

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        idBook.setCellValueFactory(new PropertyValueFactory<>("idbook"));
        usBook.setCellValueFactory(new PropertyValueFactory<>("user"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("namebook"));
        authorBook.setCellValueFactory(new PropertyValueFactory<>("authorbook"));
        genreBook.setCellValueFactory(new PropertyValueFactory<>("genrebook"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        // кнопка для перехода в библиотеку текущего пользователя
        userLibrary.setOnAction(event -> {
            userLibrary.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("userLibrary.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        });

        dc=new DbConnection();
        try {
            loadDataFromDataBase();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
    }
    // считывание и заполнение данных из бд в таблицу
    private void loadDataFromDataBase() throws SQLException, ClassNotFoundException {
        Connection conn = dc.Connect();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM basedata.library");

        while (rs.next()){
            int id = rs.getInt(1);
            String user = rs.getString(2);
            String nookname = rs.getString(3);
            String autorbook = rs.getString(4);
            String  genrebook= rs.getString(5);
            int year = rs.getInt(6);

            bookData.add(new libraryDetails(id, user, nookname, autorbook, genrebook, year));
        } // заполняем таблицу данными
        baseData.setItems(bookData);
    }
}
