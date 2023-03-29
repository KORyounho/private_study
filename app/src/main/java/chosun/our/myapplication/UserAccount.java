package chosun.our.myapplication;

/*
 사용자 계정 정보 모듈 클레스
 */
public class UserAccount {
    private String IdToken; //고유 회원정보
    private String emailId; // 이메이 아이다
    private String password; // 패스워드


    public UserAccount() { }

    public String getIdToken() { return IdToken;}

    public void setIdToken(String IdToken) {this.IdToken = IdToken;}

    public String getEmail() { return emailId;}

    public void setEmailId(String emailId) { this.emailId = emailId;}

    public String getPassword(){return password;}
    public void setPassword(String password) {this.password = password;}

    public UserAccount(String IdToken, String emailId, String password)
    {
        this.IdToken = IdToken;
        this.emailId = emailId;
        this.password = password;
    }
    public String ToString(){
        return "User{" + "userName='" + IdToken + '\''+ ", email = '" + emailId + '\'' + '}';
    }
}
