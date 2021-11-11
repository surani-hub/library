package in.library.application.service;

import in.library.application.entity.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);

    public List<User> findAllUsers();
}
