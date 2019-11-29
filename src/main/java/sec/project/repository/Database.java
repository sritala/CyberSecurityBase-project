package sec.project.repository;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.h2.tools.RunScript;
import sec.project.domain.Login;

public class Database {  
    Connection connection = null;
    private String databaseAddress = "jdbc:h2:file:./database";
    
    public Database() {
        try {
           this.connection = DriverManager.getConnection(databaseAddress, "sa", "");
            RunScript.execute(connection, new FileReader("src/main/resources/sqlFiles/schema.sql"));
            RunScript.execute(connection, new FileReader("src/main/resources/sqlFiles/data.sql"));
        } catch (Throwable t) {
            System.err.println(t.getMessage());
        }
    }
    
    public Login getAccount(String username, String password) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT id, username, password FROM accounts WHERE username='" +username+ "' AND password='" +password+ "'");
        while (resultSet.next()) {
            return new Login(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"));
    }
        return null;
    }
}
   