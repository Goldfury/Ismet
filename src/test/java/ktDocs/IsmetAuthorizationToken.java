package ktDocs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IsmetAuthorizationToken {
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}
