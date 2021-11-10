import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    SQLConnection connect = new SQLConnection();

    public String getAccountBalance(){
        String sql = "select balance from jbank.accounts order by balance asc;";
        try{PreparedStatement stmt = connect.getInstance().prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
            if(rs.next()){String bal = rs.getString("balance");
              return  bal;}

            }catch(java.sql.SQLException e){
                e.getMessage();

    }
        return " ";
    }





}
