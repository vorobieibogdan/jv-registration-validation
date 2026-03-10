package core.basesyntax.service;

import core.basesyntax.db.Storage;
import core.basesyntax.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceImplTest {

    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationServiceImpl();
        Storage.people.clear();
    }

    @Test
    void register_validUser_ok() {
        User user = new User();
        user.setLogin("validLogin");
        user.setPassword("password123");
        user.setAge(20);

        User registeredUser = registrationService.register(user);

        Assertions.assertEquals(user, registeredUser);
        Assertions.assertNotNull(registeredUser.getId());
    }

    @Test
    void register_nullUser_notOk() {
        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(null));
    }

    @Test
    void register_nullLogin_notOk() {
        User user = new User();
        user.setLogin(null);
        user.setPassword("password123");
        user.setAge(20);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_shortLogin_notOk() {
        User user = new User();
        user.setLogin("abc");
        user.setPassword("password123");
        user.setAge(20);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_nullPassword_notOk() {
        User user = new User();
        user.setLogin("validLogin");
        user.setPassword(null);
        user.setAge(20);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_shortPassword_notOk() {
        User user = new User();
        user.setLogin("validLogin");
        user.setPassword("123");
        user.setAge(20);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_nullAge_notOk() {
        User user = new User();
        user.setLogin("validLogin");
        user.setPassword("password123");
        user.setAge(null);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_underAge_notOk() {
        User user = new User();
        user.setLogin("validLogin");
        user.setPassword("password123");
        user.setAge(16);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user));
    }

    @Test
    void register_duplicateLogin_notOk() {
        User user1 = new User();
        user1.setLogin("duplicateLogin");
        user1.setPassword("password123");
        user1.setAge(20);

        registrationService.register(user1);

        User user2 = new User();
        user2.setLogin("duplicateLogin");
        user2.setPassword("password456");
        user2.setAge(25);

        Assertions.assertThrows(RuntimeException.class,
                () -> registrationService.register(user2));
    }
}



