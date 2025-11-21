package project.springbootproject.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User {
    private String username;
    private String passHash;


    public User(String username, String password){
        this.username = username;
        this.passHash = hashPassword(password);
    }

    // No-arg constructor for Gson
    public User() {
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassHash(){
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    // Verify login attempt
    public boolean checkPassword(String attempt) {
        return this.passHash.equals(hashPassword(attempt));
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password", e);
        }
    }
}
