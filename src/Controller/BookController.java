package Controller;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Author;
import Entity.Book;
import Model.AuthorModel;
import Model.BookModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class BookController {
    public static void insertBook(){
        BookModel objBookModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthorString = AuthorController.listAuthorString();
        try {
            int idAuthorToAdd = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the author id to add his/her Book.\n"+listAuthorString));
            Author objAuthor = objAuthorModel.findById(idAuthorToAdd);
            if (objAuthor == null) {
                JOptionPane.showMessageDialog(null, "The Author wasn't find");
            }else {
                Book objBook = new Book();
                String title = JOptionPane.showInputDialog("Insert the title book.");
                String year = JOptionPane.showInputDialog("Insert the published year of the book. (YYYY/MM/DD)");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Insert the book price."));
                objBook.setTitle(title);
                objBook.setYear(year);
                objBook.setPrice(price);
                objBook.setAuthor(objAuthor);
                objBook = (Book) objBookModel.insert(objBook);
                JOptionPane.showMessageDialog(null, objBook.toString());
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid author ID.");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
        }
    }

    public static void listAuthorBooks(){
        BookModel objBookModel = new BookModel();
        AuthorModel objAuthorModel = new AuthorModel();
        String listAuthorString = AuthorController.listAuthorString();
        try {
            int authorId = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the author id to list all his/her books\n"+listAuthorString));
            Author objAuthor = objAuthorModel.findById(authorId);
            if (objAuthor == null) {
                JOptionPane.showMessageDialog(null, "The Author wasn't find");
            }else {
                String text = "----Author's books----\n";
                List<Book> books = objBookModel.listAuthorsBooksById(authorId);
                for (Book temp : books){
                    text += temp.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, text);
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Please enter a valid author ID.");
        }
    }
    public static void listBook() {
        BookModel objBookModel = new BookModel();
        String text = "----Book List----\n";
        for (Object temp : objBookModel.readAll()) {
            Book objBook = (Book) temp;
            text += objBook.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, text);
    }

    public static void deleteBook() {
        BookModel objBookModel = new BookModel();
        int idToDelete = Integer.parseInt(JOptionPane.showInputDialog("Insert the book Id to be eliminate\n" + listBookString()));
        Book objBook = objBookModel.findById(idToDelete);
        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "The book wasn't find");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to delete that book?\n" + objBook);
            if (confirm == 0) {
                objBookModel.delete(objBook);
            }
        }
    }
    public static String listBookString() {
        BookModel objBookModel = new BookModel();
        String text = "-----Book List-----\n";
        for (Object temp : objBookModel.readAll()) {
            Book objBook = (Book) temp;
            text += objBook.toString();
        }

        return text;
    }

    public static void updateBooks(){
        BookModel objBookModel = new BookModel();
        int idToUpdate = Integer.parseInt(JOptionPane.showInputDialog(null, "Insert the book Id to be Update\n" + listBookString()));
        Book objBook = objBookModel.findById(idToUpdate);
        if (objBook == null) {
            JOptionPane.showMessageDialog(null, "The book wasn't find");
        }else{
            String option = "";
            do {
                option = JOptionPane.showInputDialog(null, """
                        1. Update Title.
                        2. Update Year.
                        3. Update Price.
                        4. Update All.
                        5. Cancel.
                        """);
                switch (option) {
                    case "1":
                        String title = JOptionPane.showInputDialog(null, "Insert the new Book title.");
                        objBook.setTitle(title);
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Book?\n" + objBook);
                        if (confirm == 0) {
                            objBookModel.update(objBook);
                        }
                        break;
                    case "2":
                        String year = JOptionPane.showInputDialog(null, "Insert the new Book year (YYYY/MM/DD)");
                        objBook.setYear(year);
                        int confirm2 = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Book?\n" + objBook);
                        if (confirm2 == 0) {
                            objBookModel.update(objBook);
                        }
                        break;
                    case "3":
                        double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert the new Book price"));
                        objBook.setPrice(price);
                        int confirm4 = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Book?\n" + objBook);
                        if (confirm4 == 0) {
                            objBookModel.update(objBook);
                        }
                        break;
                    case "4":
                        String title2 = JOptionPane.showInputDialog(null, "Insert the new Book title.");
                        String year2 = JOptionPane.showInputDialog(null, "Insert the new Book year (YYYY/MM/DD)");
                        double price2 = Double.parseDouble(JOptionPane.showInputDialog(null, "Insert the new Book price"));
                        objBook.setTitle(title2);
                        objBook.setYear(year2);
                        objBook.setPrice(price2);
                        int confirm3 = JOptionPane.showConfirmDialog(null, "Are you sure what do you want to update that Book?\n" + objBook);
                        if (confirm3 == 0) {
                            objBookModel.update(objBook);
                        }
                        break;
                }
            }while (!option.equals("5"));
        }
    }
}
