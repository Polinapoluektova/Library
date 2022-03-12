package sample;

import java.io.IOException;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signUpCantroller {

    @FXML
    private CheckBox user;

    @FXML
    private CheckBox admin;

    @FXML
    private Button SingUpButtton;

    @FXML
    private TextField SingUpLastName;

    @FXML
    private TextField SingUpName;

    @FXML
    private TextField loginField;

    @FXML
    private TextField password_field;


    public void initialize() {
        // (зарегистрироваться и зайти)
        SingUpButtton.setOnAction(event -> {
            SingUpButtton.getScene().getWindow().hide();
            signUpNewUser();

            String registerOneName = SingUpName.getText().trim();
            String registerLastName = SingUpLastName.getText().trim();
            String registerText = loginField.getText().trim();
            String registerPassword = password_field.getText().trim();

            String regex = "/W*";
            boolean matchesOne = Pattern.matches(regex, registerText);
            boolean matchesTwo = Pattern.matches(regex, registerPassword);
            boolean matchesThird = Pattern.matches(regex, registerOneName);
            boolean matchesFour = Pattern.matches(regex, registerLastName);

            if (!registerOneName.equals("") && !registerLastName.equals("")&&
                    !registerText.equals("") && !registerPassword.equals("") )
                signUpNewUser(registerOneName, registerLastName, registerText, registerPassword);
            else
                System.out.println("Не все поля заполнены");
            if ((matchesOne) | (matchesTwo) | (matchesThird) | (matchesFour)) {
                System.out.println("Есть некорректно введенные данные" );}
        });
    }

    private void signUpNewUser() {
    }

    private void signUpNewUser(String registerOneName, String registerLastName,
                               String registerText, String registerPassword) {

        dataBaseHandler dbHandler = new dataBaseHandler();

        String firstName=SingUpName.getText();
        String lastName=SingUpLastName.getText();
        String login=loginField.getText();
        String password=password_field.getText();

        String role = "";
        if (user.isSelected()){
            role = "читатель";
        } else {
            role = "библиотекарь";
        }

        User user = new User(firstName, lastName, login, password, role);
        dbHandler.signUpUser(user);

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
}