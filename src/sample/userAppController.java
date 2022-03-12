package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import sample.Controller;

public class userAppController implements Initializable {
    @FXML
    private Button addUserBook;

    @FXML
    private Button searchBtn;

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

    @FXML
    private Label nameUser;

    @FXML
    private Button userLibrary;

    private ObservableList<libraryDetails> bookData = FXCollections.observableArrayList();

    private DbConnection dc;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idBook.setCellValueFactory(new PropertyValueFactory<>("idbook"));
        usBook.setCellValueFactory(new PropertyValueFactory<>("user"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("namebook"));
        authorBook.setCellValueFactory(new PropertyValueFactory<>("authorbook"));
        genreBook.setCellValueFactory(new PropertyValueFactory<>("genrebook"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        userLibrary.setOnAction(event -> {
            userLibrary.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("userLibrary.fxml"));

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

        dc=new DbConnection();
        try {
            loadDataFromDataBase();
        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }



        searchBtn.setOnAction(event -> {
            searchBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("searchApp.fxml"));

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
        }
        // заполняем таблицу данными
        baseData.setItems(bookData);
    }


    // передать имя пользователя
//    public void initData(String text){
//        nameUser.setLogin();
//    }
//
//    static public void openPersonalAreaWindow(String name, User user) throws IOException, SQLException{
//        FXMLLoader loader = new FXMLLoader(Main.class.getResource(name));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(loader.load()));
//
//        stage.show();
//    }
}
