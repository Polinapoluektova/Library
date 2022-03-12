package sample;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private Label getError;
    @FXML
    private Button signUpButton;

    @FXML
    private Button enterBtn;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox user;

    @FXML
    private CheckBox admin;

    @FXML
    void initialize() {
        enterBtn.setOnAction(event -> {
            String loginText = login.getText().trim();
            String loginPassword = password.getText().trim();
            String regex = "/W*";
            boolean matchesOne = Pattern.matches(regex, loginText);
            boolean matchesTwo = Pattern.matches(regex, loginPassword);
            if (!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword);
                }
                catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            }else{
                getError.setText("Не все поля заполнены");}
            if ((matchesOne) | (matchesTwo)){
                getError.setText("Есть некорректно введенные данные ");
            }
        });

        //регистрация
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Controller.class.getResource("signUp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root_ = loader.getRoot();
            Stage stage_ = new Stage();
            stage_.setScene(new Scene(root_));
            stage_.showAndWait();
        });
    }

    public void setLogin(TextField login) {
        this.login = login;
    }

    public TextField getLogin() {
        return login;
    }

    public void loginUser(String loginText, String loginPassword) throws SQLException, IOException {  // принимает вводимые пользователем данные
        dataBaseHandler dbHandler = new dataBaseHandler();//  и ищет их в бд через цикл
        String role = "";
        if(admin.isSelected()){
            role = "библиотекарь";
            user.setSelected(false);
        } else {
            role = "читатель";
        }
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter=0;
        try {
            while (result.next()){
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if ((counter>=1) && (role.equals("читатель"))) {
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
        }
        if ((counter>=1) && (role.equals("библиотекарь"))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminApp.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        }
        else {
            getError.setText("Неверные данные, либо такого пользователя нет в системе");
        }
    }
}