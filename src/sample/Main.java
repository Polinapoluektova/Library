package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[]args) throws Exception{
        launch(args);

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            String password = "Mabe.131852";

        Connection con = DriverManager.getConnection(url, username, password);
        if (con != null) {
            System.out.println("Database Connected successfully");
        } else {
            System.out.println("Database Connection failed");
        }
    }


}
