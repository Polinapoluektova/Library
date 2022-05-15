package sample;
import java.sql.*;

public class dataBaseHandler extends config { // класс с запросами к бд
    Connection dbconnection;
    ResultSet result = null;

    public Connection getDbConnection()                     //метод подключения к базе данных (записывать В
            throws ClassNotFoundException, SQLException {   // нее, что-то считывать С нее)
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbconnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);
        return dbconnection;
    }
    // регистрация
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + constants.USER_TABLE + "(" +
                constants.USERS_FIRSTNAME + "," +
                constants.USERS_LASTNAME + "," +
                constants.USERS_LOGIN + "," +
                constants.USERS_PASSWORD + ")" +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(insert);
            prst.setString(1, User.getNameOne());
            prst.setString(2, User.getNameTwo());
            prst.setString(3, User.getLogin());
            prst.setString(4, User.getPassword());
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // авторизация
    public ResultSet getUser(User user) {                                             // регистрация
        ResultSet resSet = null;
        String select = "SELECT * FROM " + constants.USER_TABLE + " WHERE " +
                constants.USERS_LOGIN + "=? AND " + constants.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prst = getDbConnection().prepareStatement(select);
            prst.setString(1, User.getLogin());
            prst.setString(2, User.getPassword());

            resSet = prst.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
    //берет данные о книгах из бд
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet querry(String querry) {
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(querry,
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = preparedStatement.executeQuery(querry);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    //добавление пользователя
    public void addUser(String firstname, String lastname, String login, String password) {
        String request = "INSERT INTO users (firstname, lastname, login, password, idusers)VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, lastname);
            preparedStatement.setString(4, login);
            preparedStatement.setString(5, password);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // добавление книги
    public void addBook(String authorBook, String bookName, String genreBook, String usBook, String year) {
        String request = "INSERT INTO library (idbook, user, bookname, authorbook, genrebook, year)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, usBook);
            preparedStatement.setString(3, bookName);
            preparedStatement.setString(4, authorBook);
            preparedStatement.setString(5, genreBook);
            preparedStatement.setString(6, year);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    // удаление книги
    public void deleteBook(int id, String name, String author, String genre, int year, String user) {
        String request = "DELETE FROM library (idbook, user, bookname, authorbook, genrebook, year)VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(request);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, user);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, author);
            preparedStatement.setString(5, genre);
            preparedStatement.setInt(6, year);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}