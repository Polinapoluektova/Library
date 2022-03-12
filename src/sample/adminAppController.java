package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.userAppController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminAppController {

    @FXML
    private TableView<libraryDetails> baseData;

    @FXML
    private TableColumn<libraryDetails, String> authorBook;

    @FXML
    private TableColumn<libraryDetails, String> bookName;

    @FXML
    private TableColumn<libraryDetails, String> genreBook;

    @FXML
    private TableColumn<libraryDetails, String> idBook;

    @FXML
    private TableColumn<libraryDetails, String> usBook;

    @FXML
    private TableColumn<libraryDetails, String> year;

    @FXML
    private Button addBookBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TextField search;

    @FXML
    private Button usersBtn;

    private DbConnection dc;


    void initialize() {
        addBookBtn.setOnAction(event -> {
            addBookBtn.getScene().getWindow().hide();
            //addBook();
        });
    }

    /*public void initData(libraryDetails detBook) {
        idBook.appendText(detBook.getIdbook());
        this.user = user; 
        phoneField.appendText(user.getPhone()); }

    @FXML
    void search(ActionEvent event) {

        if (tfSearch.getText().equals("")){
            JOptionPane.showMessageDialog(null," Enter firstname please !!!");
        }else{
            if(checkName()){
                search();

            }else{
                JOptionPane.showMessageDialog(null,"firstname does not exist");
            }}



    public String addBook(String namebook, String authorbook, String genrebook, int year) throws SQLException, ClassNotFoundException {
        Connection conn = dc.Connect();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM basedata.library");

        if ( book!=0){ //выбрана книга
            
        }
    }*/

}
