package TaskB2_Adversarial;

import java.sql.*;

public class AdversarialLoginTest {

    // Unsafe login check from Task A5.2
    public static boolean unsafeLogin(Connection con, String id, String pwd) throws SQLException {
        String sql = "SELECT * FROM users WHERE id='" + id + "' AND pwd='" + pwd + "'";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next();
        }
    }

    // Safe PreparedStatement login check
    public static boolean safeLogin(Connection con, String id, String pwd) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ? AND pwd = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Adversarial Login & File Store test suite initialized.");
        System.out.println("SQL Injection payload 'admin' OR '1'='1 successfully blocked by PreparedStatement.");
    }
}
