package com.dmitriikuzmin.service;

import com.dmitriikuzmin.model.User;

public interface UserService {
    User add(User user);

    User get(long id);

    User update(User user);

    User delete(long id);
}
