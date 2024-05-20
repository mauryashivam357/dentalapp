package com.example.navigation_drawer.ResponseData;

// VerificationResponse.java
public class VerificationResponse {
    private boolean success;
    private String message;
    private String authToken;
    private UserInfo userInfo;

    // Empty constructor (required for Gson deserialization)
    public VerificationResponse() {
    }

    // Parameterized constructor
    public VerificationResponse(boolean success, String message, String authToken, UserInfo userInfo) {
        this.success = success;
        this.message = message;
        this.authToken = authToken;
        this.userInfo = userInfo;
    }

    // Getter and setter methods...
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfo {
        private int userId;
        private String username;
        private String email;
        private String mobile;

        // Empty constructor (required for Gson deserialization)
        public UserInfo() {
        }

        // Parameterized constructor
        public UserInfo(int userId, String username, String email, String mobile) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.mobile = mobile;
        }

        // Getter and setter methods...
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
