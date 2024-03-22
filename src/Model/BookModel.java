package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Author;
import Entity.Book;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class BookModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        try {
            String sqlInsert = """
                    INSERT INTO books (title, year, price, idAuthor) VALUES (?, ?, ?, ?);
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepared.setString(1, objBook.getTitle());
            prepared.setString(2, objBook.getYear());
            prepared.setDouble(3, objBook.getPrice());
            prepared.setInt(4, objBook.getAuthor().getIdAuthor());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows == 1) {
                ResultSet resultSet = prepared.getGeneratedKeys();
                if (resultSet.next()) {
                    objBook.setIdBook(resultSet.getInt(1));
                }
                JOptionPane.showMessageDialog(null, "The Book was insert successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Something isn't good.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return objBook;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listBooks = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = """
                    SELECT * FROM books;
                                        
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Book objbook = new Book();
                objbook.setIdBook(resultSet.getInt("id"));
                objbook.setTitle(resultSet.getString("title"));
                objbook.setYear(resultSet.getString("year"));
                objbook.setPrice(resultSet.getDouble("price"));
                listBooks.add(objbook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listBooks;
    }

    public List<Book> listAuthorsBooksById(int authorId) {

        List<Book> listBookAuthor = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = "SELECT * FROM books " +
                    "INNER JOIN authors ON books.idAuthor = authors.id " +
                    "WHERE books.idAuthor = ?";
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            prepared.setInt(1, authorId);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Book objBook = new Book();
                objBook.setIdBook(resultSet.getInt("id"));
                objBook.setTitle(resultSet.getString("title"));
                objBook.setYear(resultSet.getString("year"));
                objBook.setPrice(resultSet.getDouble("price"));
                Author objAuthor = new Author();
                objAuthor.setIdAuthor(resultSet.getInt("idAuthor"));
                objAuthor.setName(resultSet.getString("name"));
                objAuthor.setNacionality(resultSet.getString("nacionality"));

                objBook.setAuthor(objAuthor);
                listBookAuthor.add(objBook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listBookAuthor;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = false;
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        try {
            String sqlUpdate = """
                    UPDATE books SET title = ?, year = ?, price = ? WHERE id = ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlUpdate);
            prepared.setString(1, objBook.getTitle());
            prepared.setString(2, objBook.getYear());
            prepared.setDouble(3, objBook.getPrice());
            prepared.setInt(4, objBook.getIdBook());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "The book was Update successly.\n" + objBook);
            }else {
                JOptionPane.showMessageDialog(null, "Something isn't good...");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }

    @Override
    public boolean delete(Object obj) {
        boolean flag = false;
        Connection objconnection = ConfigDB.openConnection();
        Book objBook = (Book) obj;
        try {
            String slqDelete = """
                    DELETE FROM books WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objBook.getIdBook());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "The book was eliminate successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Something isn't good...");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return flag;
    }
    public Book findById(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Book objBook = null;
        try {
            String sqlFind ="SELECT * FROM books WHERE id = ?;";
            PreparedStatement prepared = objConnetion.prepareStatement(sqlFind);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objBook = new Book();
                objBook.setIdBook(resultSet.getInt("id"));
                objBook.setTitle(resultSet.getString("title"));
                objBook.setYear(resultSet.getString("year"));
                objBook.setPrice(resultSet.getDouble("price"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objBook;
    }
}

