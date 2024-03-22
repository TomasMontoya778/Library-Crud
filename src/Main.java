import Controller.AuthorController;
import Controller.BookController;
import Database.ConfigDB;
import Entity.Author;
import Entity.Book;
import Model.AuthorModel;

import javax.swing.*;
import java.util.jar.JarEntry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String option;
        do {
            option = JOptionPane.showInputDialog(null, """
                    -------Library Menu-------
                    1. Insert Menu
                    2. List Menu
                    3. Delete Menu
                    4. Update Menu
                    5. Exit
                    """);
            switch (option) {
                case "1":
                    String optionInsert = "";
                    do {
                        optionInsert = JOptionPane.showInputDialog("""
                                -------Insert Menu-------
                                1. Insert Author
                                2. Insert book
                                3. Exit
                                """);
                        switch (optionInsert){
                            case "1":
                                AuthorController.insertAuthor();
                                break;
                            case "2":
                                BookController.insertBook();
                                break;
                        }
                    } while (!optionInsert.equals("3"));
                    break;
                case "2":
                    String optionList = "";
                    do {
                        optionList = JOptionPane.showInputDialog("""
                                -------List Menu-------
                                1. List Author.
                                2. List book.
                                3. List The author's Books by Id.
                                4. Exit
                                """);
                        switch (optionList){
                            case "1":
                                AuthorController.listAuthor();
                                break;
                            case "2":
                                BookController.listBook();
                                break;
                            case "3":
                                BookController.listAuthorBooks();
                                break;
                        }
                    }while (!optionList.equals("4"));
                    break;
                case "3":
                    String optionDelete = "";
                    do {
                        optionDelete = JOptionPane.showInputDialog("""
                                -------Delete Menu-------
                                1. Delete Author.
                                2. Delete book.
                                3. Exit
                                """);
                        switch (optionDelete){
                            case "1":
                                AuthorController.deleteAuthor();
                                break;
                            case "2":
                                BookController.deleteBook();
                                break;
                        }
                    }while (!optionDelete.equals("3"));
                    break;
                case "4":
                    String optionUpdate = "";
                    do {
                        optionUpdate = JOptionPane.showInputDialog("""
                                -------Update Menu-------
                                1. Update Author.
                                2. Update book.
                                3. Exit
                                """);
                        switch (optionUpdate){
                            case "1":
                                AuthorController.updateAuthor();
                                break;
                            case "2":
                                BookController.updateBooks();
                                break;
                        }
                    }while (!optionUpdate.equals("3"));
                    break;
            }
        } while (!option.equals("5"));
    }
}