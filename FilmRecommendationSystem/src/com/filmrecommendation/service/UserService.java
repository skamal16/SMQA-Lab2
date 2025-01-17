package com.filmrecommendation.service;

import com.filmrecommendation.models.User;
import com.filmrecommendation.utils.JsonFileManager;

import java.util.List;

/*
 * ===========================================================================================
 *                                 Film Recommendation System
 *                                MSc Advanced Computer Science
 *                                  University of Leicester
 * 
 *  Module: Software Measurement and Quality Assurance (CO7095)
 *  Last Modified: 17/01/2025
 * 
 *  This class is part of the group project for the Film Recommendation System.
 *  The project aims to provide personalized film recommendations using various algorithms 
 *  and user preferences. This system incorporates film data management, user authentication, 
 *  and an intuitive interface to enhance the overall user experience.
 * 
 * ===========================================================================================
 */

public class UserService {
	
    private List<User> users;

    public UserService() {
        users = JsonFileManager.loadUsers();
    }

    /**
     * Checks if the given username is available.
     * The method ensures that the username is not already taken by any existing user.
     * @param username the username to check for availability
     * @return true if the username is available, false if it is already taken
     */
    public boolean checkUsernameAvailability(String username) {
        return users.stream().noneMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Adds a new user to the system.
     * After the user is added to the list, the updated list of users is saved to the file.
     * @param user the user to be added
     */
    public void addUser(User user) {
        users.add(user);
        JsonFileManager.saveUsers(users);
    }

    /**
     * Authenticates a user based on the provided username and password.
     * If a match is found, the corresponding user is returned; otherwise, null is returned.
     * @param username the username to authenticate
     * @param password the password to authenticate
     * @return the authenticated user if the credentials are correct, null otherwise
     */
    public User authenticateUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the password of a user.
     * The user is found by their username, and if the username matches, the password is updated.
     * After updating, the list of users is saved to the file.
     * @param username the username whose password needs to be updated
     * @param newPassword the new password to be set
     * @return true if the password was successfully updated, false otherwise
     */
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

    /**
     * Retrieves all users from the system.
     * @return the list of all users
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Deletes a user based on their username.
     * The method checks if the user exists, and if so, removes them from the list.
     * The updated list is saved to the file after the user is removed.
     * @param username the username of the user to delete
     * @return true if the user was deleted, false otherwise
     */
    public boolean deleteUser(String username) {
        boolean removed = users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        if (removed) {
            JsonFileManager.saveUsers(users);
        }
        return removed;
    }
    
}