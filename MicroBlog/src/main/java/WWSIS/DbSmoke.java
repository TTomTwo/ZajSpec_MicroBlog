package WWSIS;

import java.sql.Connection;  // Importujemy klasę Connection do nawiązywania połączenia z bazą danych.
import java.sql.DriverManager;  // Importujemy klasę DriverManager do zarządzania połączeniami z bazą danych.
import java.sql.ResultSet;  // Importujemy klasę ResultSet do przechwytywania wyników zapytań SQL.
import java.sql.Statement;  // Importujemy klasę Statement do wykonywania zapytań SQL.

public class DbSmoke {
    public static void main(String[] args) throws Exception {
        // Ustawienie szczegółów połączenia z bazą danych
        String url = "jdbc:hsqldb:hsql://localhost:9001/testdb";  // URL bazy danych HSQLDB, wskazujący na lokalny serwer i bazę testową
        String user = "SA";  // Użytkownik bazy danych
        String pass = "";  // Hasło bazy danych (puste w tym przypadku)

        // Blok try-with-resources zapewnia automatyczne zamknięcie zasobów po zakończeniu pracy
        try (Connection c = DriverManager.getConnection(url, user, pass);  // Uzyskujemy połączenie z bazą danych
             Statement st = c.createStatement();  // Tworzymy obiekt Statement do wykonywania zapytań SQL
             ResultSet rs = st.executeQuery("SELECT 1 FROM (VALUES(1))")) {  // Wykonujemy zapytanie SQL, które zwraca wynik 1

            // Pętla while przechodzi przez wyniki zapytania
            while (rs.next()) {
                // Wypisujemy wynik zapytania
                System.out.println("DB OK -> " + rs.getInt(1));  // Wypisuje wartość z pierwszej kolumny wyniku zapytania (1)
            }
        }
    }
}