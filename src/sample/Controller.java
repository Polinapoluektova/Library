package sample;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class Controller {

    @FXML private Label getError;
    @FXML private Button signUpButton;
    @FXML private Button enterBtn;
    @FXML private TextField login;
    @FXML private PasswordField password;
    @FXML private CheckBox user;
    @FXML private CheckBox admin;

    FXMLLoader loader = new FXMLLoader();
    @FXML
    void initialize() {
        enterBtn.setOnAction(event -> { // авторизация: считыване и проверка введенных данных
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
                getError.setText("Есть некорректно введенные данные");
            }
        });

        // переход на форму регистрации
        signUpButton.setOnAction(event -> {
            signUpButton.getScene().getWindow().hide();
            loader.setLocation(Controller.class.getResource("signUp.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        });
    }

    // авторизация
    public void loginUser(String loginText, String loginPassword) throws SQLException, IOException {
        dataBaseHandler dbHandler = new dataBaseHandler();
        String role = "";
        if(admin.isSelected()){
            role = "библиотекарь";
            user.setSelected(false);
        } else {
            role = "читатель";
        } //
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        int counter=0;
        try { // ищет совпадения в бд через цикл
            while (result.next()){
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } // определяет роль пользователя и открывает соовтетствующее окно приложения
        if ((counter>=1) && (role.equals("читатель"))) {
            loader.setLocation(getClass().getResource("userApp.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        }if ((counter>=1) && (role.equals("библиотекарь"))) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("adminApp.fxml"));
            toOpen open = new toOpen();
            open.op(loader);
        }if ((counter>=1) | (role.equals("библиотекарь"))) {
            getError.setText("Неверные данные, либо такого пользователя нет в системе");
        } else {
            getError.setText("Успешно");
        }
    }
}