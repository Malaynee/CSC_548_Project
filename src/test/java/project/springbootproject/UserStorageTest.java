package project.springbootproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.springbootproject.model.User;
import project.springbootproject.model.UserStorage;

public class UserStorageTest {

    @Test
    void testUserStorage() throws Exception {
        UserStorage store = new UserStorage();
        store.createUser(new User("testuser", "abc123"));
    }
}

