package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UsersBookController {
    @FXML private Button addBtn;
    @FXML private Button backBtn;
    @FXML private TableView<usersBook> baseData;
    @FXML private TableColumn<usersBook, String> dateofissueColun;
    @FXML private Button deleteBtn;
    @FXML private TableColumn<usersBook, String> firstnameColumn;
    @FXML private TableColumn<usersBook, Integer> idColumn;
    @FXML private TableColumn<usersBook, String> lastnameColumn;
    @FXML private TableColumn<usersBook, String> nameBookColumn;
    @FXML private TableColumn<usersBook, String> returndateColumn;

    private ObservableList<libraryDetails> bookData = FXCollections.observableArrayList();
    private DbConnection dc;
    FXMLLoader loader = new FXMLLoader();
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameBookColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateofissueColun.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        returndateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        // кнопка для перехода на предыдущую форму администратора
        backBtn.setOnAction(event -> {
            backBtn.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("adminApp.fxml"));
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
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM basedata.pinnedbooks");

        while (rs.next()){
            Integer id = rs.getInt(1);
            String bookname = rs.getString(2);
            String firstname = rs.getString(3);
            String  secondname= rs.getString(4);
            String  begin= rs.getString(5);
            String returnbook = rs.getString(6);

            bookData.add(new usersBook(id, bookname, firstname, secondname, begin, returnbook));
        } // заполняем таблицу данными
        baseData.setItems(bookData);
    }
}