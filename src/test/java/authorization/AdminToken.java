package authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Здесь происходит десериализации получаем админ токен для удаления учетки
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminToken {
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

}