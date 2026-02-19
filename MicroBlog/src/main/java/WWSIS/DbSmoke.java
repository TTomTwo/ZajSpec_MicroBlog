package WWSIS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbSmoke {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:hsqldb:hsql://localhost:9001/testdb";
        String user = "SA";
        String pass = "";

        try (Connection c = DriverManager.getConnection(url, user, pass);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT 1 FROM (VALUES(1))")) {

            while (rs.next()) {
                System.out.println("DB OK -> " + rs.getInt(1));
            }
        }
    }
}
