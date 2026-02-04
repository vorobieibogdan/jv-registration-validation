package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RuntimeException("User is null");
        }

        if (user.getLogin() == null || user.getLogin().length() < 6) {
            throw new RuntimeException("Login is invalid");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new RuntimeException("Password is invalid");
        }

        if (user.getAge() == null || user.getAge() < 18) {
            throw new RuntimeException("Age is invalid");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new RuntimeException("User already exists");
        }

        return storageDao.add(user);
    }
}

