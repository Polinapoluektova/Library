package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
public class adminAppController extends toOpen{

    @FXML private TableView<libraryDetails> baseData;
    @FXML private TableColumn<libraryDetails, String> authorBook;
    @FXML private TableColumn<libraryDetails, String> bookName;
    @FXML private TableColumn<libraryDetails, String> genreBook;
    @FXML private TableColumn<libraryDetails, Integer> idBook;
    @FXML private TableColumn<libraryDetails, String> usBook;
    @FXML private TableColumn<libraryDetails, Integer> year;
    @FXML private TextField authorBookTF;
    @FXML private TextField bookNameTF;
    @FXML private TextField genreBookTF;
    @FXML private TextField usBookTF;
    @FXML private TextField yearTF;
    @FXML private Button addBookBtn;
    @FXML private Button deleteBtn;
    @FXML private Button exitBtn;
    @FXML private Button usersBtn;
    @FXML private Button usersbookBtn;

    FXMLLoader loader = new FXMLLoader();
    private ObservableList<libraryDetails> bookData = FXCollections.observableArrayList();
    public DbConnection dc;
    public void initialize() {
        // данные на заполнение каждого TableColumn (столбец)
        idBook.setCellValueFactory(new PropertyValueFactory<>("idbook"));
        usBook.setCellValueFactory(new PropertyValueFactory<>("user"));
        bookName.setCellValueFactory(new PropertyValueFactory<>("namebook"));
        authorBook.setCellValueFactory(new PropertyValueFactory<>("authorbook"));
        genreBook.setCellValueFactory(new PropertyValueFactory<>("genrebook"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));

        // обработчик кнопки для удаления книги
        deleteBtn.setOnAction(event -> {
            deleteBtn.getScene().getWindow().hide();
            baseData.getItems().removeAll(
                    baseData.getSelectionModel().getSelectedItems());
            handleDelete();
        });

        // обработчик кнопки для добавления новой книги
        addBookBtn.setOnAction(event -> {
            addBookBtn.getScene().getWindow().hide();
            String authorBook = authorBookTF.getText().trim();
            String bookName = bookNameTF.getText().trim();
            String genreBook = genreBookTF.getText().trim();
            String usBook = usBookTF.getText().trim();
            String year = yearTF.getText().trim();

            add(authorBook, bookName, genreBook, usBook, year);
        });

        // переход на форму пользователей и их персональных данных
        usersBtn.setOnAction(event -> {
            usersBtn.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("users.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        });
        // переход на форму пользователей и закрепленных за ними книгами
        usersbookBtn.setOnAction(event -> {
            usersbookBtn.getScene().getWindow().hide();
            loader.setLocation(getClass().getResource("usersBook.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        });
        // выход из приложения (на форму авторизации)
        exitBtn.setOnAction(event -> {
            exitBtn.getScene().getWindow().hide();
            toOpen open = new toOpen();
            loader.setLocation(getClass().getResource("sample.fxml"));
            open.op(loader);
        });

        // вызов метода для заполнения TableView из бд
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

    private void add(String authorBook, String bookName, String genreBook, String usBook, String year) {
        dataBaseHandler dbHandler = new dataBaseHandler();
        dbHandler.addBook(authorBook, bookName, genreBook, usBook, year);
    }

    private void handleDelete() {
        libraryDetails selectedItem = baseData.getSelectionModel().getSelectedItem();
        int id = selectedItem.getIdbook();
        String name = selectedItem.getNamebook();
        String author = selectedItem.getAuthorbook();
        String genre = selectedItem.getGenrebook();
        int year = selectedItem.getYear();
        String user = selectedItem.getUser();
        int selectedIndex = baseData.getSelectionModel().getSelectedIndex();
        dataBaseHandler dbHandler = new dataBaseHandler();

        if (selectedIndex >= 0) {
            baseData.getItems().remove(selectedIndex);
            dbHandler.deleteBook(id, name, author, genre, year, user);
        } else { // если ничего не выбрано
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
}
