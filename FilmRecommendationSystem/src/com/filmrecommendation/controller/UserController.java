package com.filmrecommendation.controller;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.service.UserService;
import com.filmrecommendation.service.FilmService;

import java.util.List;
import java.util.Scanner;

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

public class UserController {
	
    private UserService userService;
    private FilmService filmService;
    private Scanner scanner;
    private String currentUsername;

    public UserController(UserService userService, FilmService filmService, Scanner scanner) {
        this.userService = userService;
        this.filmService = filmService;
        this.scanner = scanner;
        this.currentUsername = null;
    }

    /**
     * Registers a new user if the username is available.
     * 
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param dob The user's date of birth.
     * @param username The user's desired username.
     * @param password The user's password.
     * @return true if the user is successfully registered, false if the username is already taken.
     */
    public boolean registerUser(String firstName, String lastName, String dob, String username, String password) {
        if (!userService.checkUsernameAvailability(username)) {
            return false;
        }

        User newUser = new User(firstName, lastName, dob, username, password);
        userService.addUser(newUser);
        currentUsername = username;
        return true;
    }

    /**
     * Prompts the user to enter their credentials and logs them in if the credentials are valid.
     * If login is successful, the user is directed to the user menu.
     */
    public void loginUser() {
    	System.out.println("===========================================================");
    	System.out.println("                   Enter User Credentials                  ");
    	System.out.println("===========================================================");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
    	System.out.println("===========================================================");

        User user = userService.authenticateUser(username, password);
        if (user != null) {
            currentUsername = username;
            System.out.println("Login successful!");
            showUserMenu();
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    /**
     * Displays the user menu with options such as viewing films, searching films,
     * changing password, logging out, and exiting the application.
     * Continuously prompts the user for action until they log out or exit.
     */
    public void showUserMenu() {
        while (currentUsername != null) {
        	System.out.println("===========================================================");
        	System.out.println("                         User Menu                         ");
        	System.out.println("===========================================================");
            System.out.println("1. List all films");
            System.out.println("2. Search film");
            System.out.println("3. Change Password");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
        	System.out.println("===========================================================");
        	System.out.print("Please choose an option (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                	listAllFilms();
                    break;
                case 2:
                    searchFilm();
                    break;
                case 3:
                    changePassword();
                    break;
                case 4:
                    logout();
                    return;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Lists all the films available in the system.
     * If no films are available, it will display a message indicating so.
     */
    private void listAllFilms() {
        List<Film> films = filmService.getAllFilms();
    	System.out.println("===========================================================");
    	System.out.println("                     List of All Films                     ");
    	System.out.println("===========================================================");
        if (films.isEmpty()) {
            System.out.println("No films available.");
        } else {
            films.forEach(film -> System.out.println(film.getTitle()));
        }
    }

    /**
     * Prompts the user to enter a film title and searches for films that match the title.
     * Displays the search results or an appropriate message if no results are found.
     */
    private void searchFilm() {
    	System.out.println("===========================================================");
        System.out.print("Enter film title to search: ");
        String title = scanner.nextLine();
        
        List<Film> results = filmService.searchFilms(title);
    	System.out.println("===========================================================");
    	System.out.println("                       Search Results                      ");
    	System.out.println("===========================================================");
        if (results.isEmpty()) {
            System.out.println("No films found with title: " + title);
        } else {
            results.forEach(film -> System.out.println(film.getTitle()));
        }
    }

    /**
     * Prompts the user to enter a new password and updates the user's password.
     * Provides feedback on whether the password change was successful.
     */
    private void changePassword() {
    	System.out.println("===========================================================");
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        boolean isChanged = userService.updateUserPassword(currentUsername, newPassword);
        if (isChanged) {
            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Password change failed.");
        }
    }

    /**
     * Logs the current user out of the application and returns to the login state.
     */
    private void logout() {
        currentUsername = null;
    	System.out.println("===========================================================");
        System.out.println("Logged out successfully.");
    	System.out.println("===========================================================");
    }
    
}