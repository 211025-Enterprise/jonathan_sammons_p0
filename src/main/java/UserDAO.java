

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO implements Dao<user> {

SQLConnection q = new SQLConnection();

PreparedStatement stmt = null;

    @Override
    public void create(user User) {
        //creates a username and password row in the database
        try{ String sql = "insert into jbank.users (username, passwords) values (?,?)";
            stmt = q.getInstance().prepareStatement(sql);
            stmt.setString(1, User.getUsername());
            stmt.setString(2,User.getPassword());
            stmt.executeUpdate();
            //creates a bank account initialized to 0.00
            sql = "insert into jbank.accounts (balance) values (?)";
           PreparedStatement stm = q.getInstance().prepareStatement(sql);
           stm.setDouble(1, 0);
           stm.executeUpdate();


        }catch(java.sql.SQLException o){
                System.out.println(o.getMessage());

            }

    }



}
