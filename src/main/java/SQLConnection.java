import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    private static final String url = "jdbc:postgresql://enterprise.cw9r6djgzdex.us-east-2.rds.amazonaws.com:5432/postgres?CurrentSchema=jbank";
    private static final String username = "postgres";
    private static final String password =  "master123";


    public Connection instance;

    public SQLConnection(){}



    public Connection getInstance(){
        if(instance == null){
            try {
                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection(url, username, password);
                System.out.println("connected success");

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }




}
