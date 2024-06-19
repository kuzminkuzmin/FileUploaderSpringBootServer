package com.dmitriikuzmin.service;

import com.dmitriikuzmin.model.User;
import com.dmitriikuzmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User already exists");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User does not exist")
        );
    }

    @Override
    public User update(User user) {
        try {
            User base = this.get(user.getId());
            base.setUsername(user.getUsername());
            base.setPassword(user.getPassword());
            base.setFio(user.getFio());
            return this.userRepository.save(base);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("User name already exists");
        }
    }

    @Override
    public User delete(long id) {
        User user = this.get(id);
        this.userRepository.delete(user);
        return user;
    }
}
