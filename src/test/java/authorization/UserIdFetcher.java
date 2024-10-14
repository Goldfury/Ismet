package authorization;

import io.qameta.allure.Step;

import java.sql.*;

public class UserIdFetcher {

    private final String url = "jdbc:postgresql://10.77.20.29:5000/bpmn";
    private final String user = "bpmn";
    private final String password = "7KkjFpnCMF)?";


    private Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Step("Находит id пользователя по email в схеме keycloak в таблице user_entity ")
    public String getUserIdByEmail(String email) {
        String query = "SELECT id FROM keycloak.user_entity WHERE email = ? ";
        String userId = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getString("id");
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при извлечении ID по email: " + e.getMessage());
        }

        return userId;
    }

}
