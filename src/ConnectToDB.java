import java.sql.*;

public class ConnectToDB
{
    Connection con;
    public Connection con1(){
        return con;
    }
    // method to connect to database
    public void connect(){
        try{
            con = DriverManager.getConnection
                    // last two parameter are user name and password respectively
                            ("jdbc:mysql://localhost:3306/project", "nkhan", "nkhan123");
            System.out.println("connection established");

        }catch(SQLException e){
            System.out.println("No Connection");
        }
    }
    // Method to disconnect from database
    public void disconnect(){
        try{
            if(con != null){
                con.close();
                System.out.println("Connection closed");
            }
        }catch(SQLException e){
            System.out.println("unable to close Connection");
        }
    }
}
