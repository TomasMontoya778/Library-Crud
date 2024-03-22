package Model;

import Database.CRUD;
import Database.ConfigDB;
import Entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) obj;
        try {
            String sql = """
                    INSERT INTO authors (name, nacionality) VALUES(?, ?);
                    """;
            PreparedStatement objPrepared = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            objPrepared.setString(1, objAuthor.getName());
            objPrepared.setString(2, objAuthor.getNacionality());

            objPrepared.execute();
            ResultSet result = objPrepared.getGeneratedKeys();
            while (result.next()) {
                objAuthor.setIdAuthor(result.getInt(1));
            }
            JOptionPane.showMessageDialog(null, "The Author was insert successful.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public List<Object> readAll() {
        List<Object> listAuthor = new ArrayList<>();
        Connection objConnection = ConfigDB.openConnection();
        try {
            String sqlList = """
                    SELECT * FROM authors;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlList);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()) {
                Author objAuthor = new Author();
                objAuthor.setIdAuthor(resultSet.getInt("id"));
                objAuthor.setName(resultSet.getString("name"));
                objAuthor.setNacionality(resultSet.getString("nacionality"));
                listAuthor.add(objAuthor);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listAuthor;
    }

    @Override
    public boolean update(Object obj) {
        boolean flag = false;
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = (Author) obj;
        try {
            String sqlUpdate = """
                    UPDATE authors SET name = ?, nacionality = ? WHERE id = ?;
                    """;
            PreparedStatement prepared = objConnection.prepareStatement(sqlUpdate);
            prepared.setString(1, objAuthor.getName());
            prepared.setString(2, objAuthor.getNacionality());
            prepared.setInt(3, objAuthor.getIdAuthor());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "The Author was Update successly.\n" + objAuthor);
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
        Author objAuthor = (Author) obj;
        try {
            String slqDelete = """
                    DELETE FROM authors WHERE id = ?;
                    """;
            PreparedStatement prepared = objconnection.prepareStatement(slqDelete);
            prepared.setInt(1, objAuthor.getIdAuthor());
            int totalAffectedRows = prepared.executeUpdate();
            if (totalAffectedRows > 0) {
                flag = true;
                JOptionPane.showMessageDialog(null, "The Author was eliminate successly");
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
    public Author findById(int id){
        Connection objConnetion = ConfigDB.openConnection();
        Author objAuthor = null;
        try {
            String sqlFind ="SELECT * FROM authors WHERE id = ?;";
            PreparedStatement prepared = objConnetion.prepareStatement(sqlFind);
            prepared.setInt(1, id);
            ResultSet resultSet = prepared.executeQuery();
            while (resultSet.next()){
                objAuthor = new Author();
                objAuthor.setIdAuthor(resultSet.getInt("id"));
                objAuthor.setName(resultSet.getString("name"));
                objAuthor.setNacionality(resultSet.getString("nacionality"));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            ConfigDB.closeConnection();
        }
        return objAuthor;
    }

}
