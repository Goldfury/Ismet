package authorization;

import io.qameta.allure.Step;

import java.sql.*;

public class SmsCodeFetcher {

    private Connection connect() {
        String url = "jdbc:postgresql://10.77.20.29:5000/bpmn";
        String user = "bpmn";
        String password = "7KkjFpnCMF)?";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    @Step("Отправка последнего смс кода по номеру")
    public String getLastSmsCode(String phoneNumber) {
        String query = "SELECT code FROM bpmn.sms_codes WHERE phone_number = ? ORDER BY send_time DESC LIMIT 1";
        String lastCode = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, phoneNumber);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    lastCode = rs.getString("code");
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при извлечении последнего SMS-кода: " + e.getMessage());
        }

        return lastCode;
    }
}
