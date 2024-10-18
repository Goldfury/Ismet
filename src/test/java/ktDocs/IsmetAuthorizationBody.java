package ktDocs;

public class IsmetAuthorizationBody {
    private String login;
    private String password;
    private String clientId;
    private String reCaptcha;
    private String lang;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getReCaptcha() {
        return reCaptcha;
    }

    public void setReCaptcha(String reCaptcha) {
        this.reCaptcha = reCaptcha;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public IsmetAuthorizationBody(String login, String password, String clientId, String reCaptcha, String lang) {
        this.login = login;
        this.password = password;
        this.clientId = clientId;
        this.reCaptcha = reCaptcha;
        this.lang = lang;
    }

    public IsmetAuthorizationBody(String login) {
        this.login = login;
    }
}
