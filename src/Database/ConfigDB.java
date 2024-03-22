package Database;

import java.sql.Connection;
import java.sql.*;

public class ConfigDB {
    public static Connection status = null;

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://bvty5ugol274hg1njfit-mysql.services.clever-cloud.com:3306/bvty5ugol274hg1njfit";
            String user = "uh3wurqmvg357ip5";
            String password = "cR9GyMlVBOX5RbJHMl1l";
            status = (Connection) DriverManager.getConnection(url, user, password);
            Statement statement = status.createStatement();
            System.out.println("all correct");
        } catch (ClassNotFoundException error) {
            System.out.println(error.getMessage());
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }

    public static void closeConnection (){
        try {
            if(status != null){
                status.close();
                System.out.println("Close connection.");
            }else{
                System.out.println("no connection rn");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
