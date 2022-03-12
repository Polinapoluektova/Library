package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class libraryDetails {
    private Integer idbook;
    private String user;
    private String namebook;
    private String authorbook;
    private String genrebook;
    private int year;

    public libraryDetails(Integer idbook, String user, String namebook, String authorbook, String genrebook, int year) {
        this.idbook = idbook;
        this.user = user;
        this.namebook = namebook;
        this.authorbook = authorbook;
        this.genrebook = genrebook;
        this.year = year;
    }

    // getters and setters

    public Integer getIdbook() {
        return idbook;
    }

    public void setIdbook(Integer idbook) {
        this.idbook = idbook;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getNamebook() {
        return namebook;
    }

    public void setNamebook(String namebook) {
        this.namebook = namebook;
    }

    public String getAuthorbook() {
        return authorbook;
    }

    public void setAuthorbook(String authorbook) {
        this.authorbook = authorbook;
    }

    public String getGenrebook() {
        return genrebook;
    }

    public void setGenrebook(String genrebook) {
        this.genrebook = genrebook;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }





}
