package com.example.nolfi;

/**
 * 사용자 계정 정보 모델 클래스
 */

public class UserAccount {

    private String idToken;    // Firebase Uid (고유 토큰 정보)
    private String emailId;    // 이메일 아이디
    private String password;   // 비밀번호

    // private Boolean status;    // store vs customer.
    private String nickname;
    private String address;
    private String category;
    private String getWhose;

    // public Boolean getStatus() { return status; }
    // public void setStatus(Boolean status) { this.status = status; }


    public UserAccount() {}

    public String getGetWhose() {
        return getWhose;
    }

    public void setGetWhose(String getWhose) {
        this.getWhose = getWhose;
    }


    public String getIdToken() {
        return idToken;
    }
    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;  }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}