package TaskB4_Migration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MigrationTool {
    public static void inspectDatabase(Connection con) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();
        System.out.println("Database Product Name: " + meta.getDatabaseProductName());
        System.out.println("Database Product Version: " + meta.getDatabaseProductVersion());
        System.out.println("JDBC Driver Name: " + meta.getDriverName());
        System.out.println("JDBC Driver Version: " + meta.getDriverVersion());
    }

    public static void main(String[] args) {
        System.out.println("Multi-Campus Migration and Metadata Inspector ready.");
    }
}
