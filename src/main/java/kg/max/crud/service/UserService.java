package kg.max.crud.service;

import kg.max.crud.model.User;

import java.util.List;

public interface UserService {
    List<User> allUsers();

    void delete(User user);

    void edit(User user);

    User getById(long id);

    void add(User user);

    String getUserPasswordById(long id);
}
