package project.springbootproject.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import project.springbootproject.model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

/**
 * Manages user data storage in individual directories
 * Each user gets their own folder: users/username/user.json
 */
public class UserStorage {
    private final Path usersRoot = Paths.get("users");
    private final Gson gson;

    public UserStorage() { 
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        
        // Create users directory if it doesn't exist
        try {
            if (!Files.exists(usersRoot)) {
                Files.createDirectories(usersRoot);
            }
        } catch (IOException e) {
            System.err.println("Could not create users directory: " + e.getMessage());
        }
    }

    //check if user exists
    public boolean userExists(String username) {
        Path userFile = usersRoot.resolve(username).resolve("user.json");
        return Files.exists(userFile);
    }

    //create new user
    public void createUser(User user) throws IOException {
        String username = user.getUsername();

        if (userExists(username)) {
            throw new IllegalArgumentException("User already exists: " + username);
        }

        Path userDir = usersRoot.resolve(username);
        Files.createDirectories(userDir);

        Path userFile = userDir.resolve("user.json");

        try (FileWriter writer = new FileWriter(userFile.toFile())) {
            gson.toJson(user, writer);
        }
        System.out.println("User created successfully: " + username);
    }

    //load an existing user from storage
    public User loadUser(String username) throws IOException {
        Path userFile = usersRoot.resolve(username).resolve("user.json");

        if (!Files.exists(userFile)) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        try (FileReader reader = new FileReader(userFile.toFile())) {
            return gson.fromJson(reader, User.class);
        }
    }

    //authenticates a user login attempt
    public User authenticate(String username, String password) {
        try {
            User user = loadUser(username);
            if (user.checkPassword(password)) {
                return user; // Valid login
            }
        } catch (Exception e) {
            // User doesn't exist or file error
            System.err.println("Authentication failed: " + e.getMessage());
        }
        return null; // Invalid login
    }
}
