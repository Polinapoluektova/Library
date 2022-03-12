package sample;
import sample.AddressBook;

import java.util.ArrayList;

public class collectionAddressBook implements AddressBook{
    private ArrayList<libraryDetails> bookList = new ArrayList<libraryDetails>();

    @Override
    public void add(libraryDetails book) {
        bookList.add(book);
    }

    @Override
    public void upDate(libraryDetails book) {
        //bookList.update(book);
    }

    @Override
    public void delete(libraryDetails book) {
        bookList.remove(book);
    }

    public ArrayList<libraryDetails> getBookList(){
        return bookList;
    }
}
