package sample;
import java.sql.*;

public class dataBaseHandler extends config{
    Connection dbconnection;
    public Connection getDbConnection()                                                    //метод подключения к базе данных (записывать В
            throws ClassNotFoundException, SQLException {                                  // нее, что-то считывать С нее)
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbconnection;
    }


    public void signUpUser(User user) {
        String insert = "INSERT INTO " + constants.USER_TABLE + "("+
                constants.USERS_FIRSTNAME + "," +
                constants.USERS_LASTNAME + "," +
                constants.USERS_LOGIN + "," +
                constants.USERS_PASSWORD + ")" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
            prst.setString(1, user.getNameOne());
            prst.setString(2, user.getNameTwo());
            prst.setString(3, user.getLogin());
            prst.setString(4, user.getPassword());
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ResultSet getUser(User user){                                             // регистрация
        ResultSet resSet = null;
        String select = "SELECT * FROM " +constants.USER_TABLE + " WHERE " +
                constants.USERS_LOGIN + "=? AND " + constants.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getPassword());

            resSet = prst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet loadDataFromDataBase(libraryDetails book) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + constants.LIBRARY_TABLE + " WHERE " +
                constants.ID_BOOK + "=? AND " + constants.USER_BOOK + "=? AND " +
                constants.BOOK_NAME + "=? AND " + constants.AUTHOR_BOOK +
                constants.GENRE_BOOK + constants.YEAR + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, String.valueOf(book.getIdbook()));
            prst.setString(2, String.valueOf(book.getUser()));
            prst.setString(3, String.valueOf(book.getNamebook()));
            prst.setString(4, String.valueOf(book.getAuthorbook()));
            prst.setString(5, String.valueOf(book.getGenrebook()));
            prst.setString(6, String.valueOf(book.getYear()));

            resSet = prst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }


    public ResultSet searchBook(libraryDetails book) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + constants.LIBRARY_TABLE + " WHERE " +
                constants.ID_BOOK + "=? AND " + constants.USER_BOOK + "=? AND " +
                constants.BOOK_NAME + "=? AND " + constants.AUTHOR_BOOK +
                constants.GENRE_BOOK + constants.YEAR + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, String.valueOf(book.getIdbook()));
            prst.setString(2, String.valueOf(book.getUser()));
            prst.setString(3, String.valueOf(book.getNamebook()));
            prst.setString(4, String.valueOf(book.getAuthorbook()));
            prst.setString(5, String.valueOf(book.getGenrebook()));
            prst.setString(6, String.valueOf(book.getYear()));

            resSet = prst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet addBook(libraryDetails book) {
        ResultSet resSet = null;
        String select = "INSERT * INTO " + constants.LIBRARY_TABLE + " WHERE " +
                constants.ID_BOOK + "=? AND " + constants.USER_BOOK + "=? AND " +
                constants.BOOK_NAME + "=? AND " + constants.AUTHOR_BOOK +
                constants.GENRE_BOOK + constants.YEAR + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, String.valueOf(book.getIdbook()));
            prst.setString(2, String.valueOf(book.getUser()));
            prst.setString(3, String.valueOf(book.getNamebook()));
            prst.setString(4, String.valueOf(book.getAuthorbook()));
            prst.setString(5, String.valueOf(book.getGenrebook()));
            prst.setString(6, String.valueOf(book.getYear()));

            resSet = prst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
