package kg.max.crud.dao;

import kg.max.crud.model.User;

import java.util.List;

public interface UserDAO {
    List<User> allUsers();

    void delete(User user);

    void edit(User user);

    User getById(long id);

    void add(User user);

    User findByUsername(String username);
}
