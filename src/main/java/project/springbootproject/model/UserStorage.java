package project.springbootproject.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springbootproject.model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

public class UserStorage {
    private final Path usersRoot = Paths.get("users");
    private final Gson gson;

    public UserStorageService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
    }

    //lead an existing user
    public User loadUser(String username) throws IOException {
        Path userFile = usersRoot.resolve(username).resolve("user.json");

        if (!Files.exists(userFile)) {
            throw new IllegalArgumentException("User not found: " + username);
        }

        try (FileReader reader = new FileReader(userFile.toFile())) {
            return gson.fromJson(reader, User.class);
        }
    }

    //authenticate user
    public User authenticate(String username, String password) {
        try {
            User user = loadUser(username);

            if (user.checkPassword(password)) {
                return user; // valid login
            }

        } catch (Exception ignored) {}

        return null; // invalid login
    }

    public static void main(String[] args) {
        UserStorage service = new UserStorage();
        service.createUser(new User("testuser", "abc123"));
    }

}
