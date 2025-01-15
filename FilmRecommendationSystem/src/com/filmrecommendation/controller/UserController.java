package com.filmrecommendation.controller;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.service.UserService;
import com.filmrecommendation.service.FilmService;

import java.util.List;
import java.util.Scanner;

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

    public boolean registerUser(String firstName, String lastName, String dob, String username, String password) {
        if (!userService.checkUsernameAvailability(username)) {
            return false;
        }

        User newUser = new User(firstName, lastName, dob, username, password);
        userService.addUser(newUser);
        currentUsername = username;
        return true;
    }

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

    private void logout() {
        currentUsername = null;
    	System.out.println("===========================================================");
        System.out.println("Logged out successfully.");
    	System.out.println("===========================================================");
    }
    
}