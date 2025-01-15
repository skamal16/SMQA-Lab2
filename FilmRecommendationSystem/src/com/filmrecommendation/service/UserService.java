package com.filmrecommendation.service;

import com.filmrecommendation.models.User;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.List;

public class UserService {
	
    private List<User> users;

    public UserService() {
        users = JsonFileManager.loadUsers();
    }

    public boolean checkUsernameAvailability(String username) {
        return users.stream().noneMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public void addUser(User user) {
        users.add(user);
        JsonFileManager.saveUsers(users);
    }

    public User authenticateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean updateUserPassword(String username, String newPassword) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                user.setPassword(newPassword);
                JsonFileManager.saveUsers(users);
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public boolean deleteUser(String username) {
        boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        if (removed) {
            JsonFileManager.saveUsers(users);
        }
        return removed;
    }
    
}