package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.*;
import static sample.Main.fill;

public class usersController {
    @FXML private Button backBtn;
    @FXML private Button addUser;
    @FXML private TableView<ObservableList> baseData;
    @FXML private TextField firstnameTF;
    @FXML private TextField lastnameTF;
    @FXML private TextField loginTF;
    @FXML private TextField passwordTF;
    @FXML private TextField useridTF;

    @FXML
    void initialize() {
        try { // заполнение TableView из бд
            fill("SELECT * FROM users", baseData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        addUser.setOnAction(actionEvent -> {
            String login = loginTF.getText().trim();
            String password = passwordTF.getText().trim();
            String firstname = firstnameTF.getText().trim();
            String lastName = lastnameTF.getText().trim();

            add(login, password, firstname, lastName);
        });
    }

    // добавление нового читателя
    private static void add(String login, String password, String firstname, String lastName) {
        dataBaseHandler dbHandler = new dataBaseHandler();
        dbHandler.addUser(login, password, firstname, lastName);
    }
}
