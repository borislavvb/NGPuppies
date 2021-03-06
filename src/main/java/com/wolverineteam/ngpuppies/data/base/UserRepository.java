package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.models.User;

import java.util.List;

public interface UserRepository {

    User loadUserByUsername(String username);

    User getById(int id);

    List<User> getAll();

    void update(User user);

    void create(User user);
}
