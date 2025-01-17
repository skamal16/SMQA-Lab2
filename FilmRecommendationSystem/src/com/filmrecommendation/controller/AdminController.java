package com.filmrecommendation.controller;

import com.filmrecommendation.service.FilmService;
import com.filmrecommendation.service.UserService;
import com.filmrecommendation.service.AdminService;
import com.filmrecommendation.models.Admin;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;

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

public class AdminController {

    private Scanner scanner;
    private Admin loggedInAdmin;
    private FilmService filmService;
    private UserService userService;
    private AdminService adminService;

    public AdminController(AdminService adminService, FilmService filmService, UserService userService, Scanner scanner) {
        this.adminService = adminService;
        this.filmService = filmService;
        this.userService = userService;
        this.scanner = scanner;
    }

    /**
     * This function handles the admin login process.
     * It prompts the admin for their username and password, then authenticates them.
     * If successful, it displays the admin menu; otherwise, it shows an error message.
     */
    public void loginAdmin() {
    	System.out.println("===========================================================");
    	System.out.println("                   Enter Admin Credentials                 ");
    	System.out.println("===========================================================");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    	System.out.println("===========================================================");

        loggedInAdmin = adminService.authenticateAdmin(username, password);

        if (loggedInAdmin != null) {
            System.out.println("Login successful. Welcome, Admin " + loggedInAdmin.getUsername() + "!");
            showAdminMenu();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * This function displays the admin menu and allows the admin to choose various actions
     * such as listing, searching, updating, or deleting films and users.
     * The admin can also log out or exit the application.
     */
    public void showAdminMenu() {
        while (true) {
        	System.out.println("===========================================================");
        	System.out.println("                        Admin Menu                         ");
        	System.out.println("===========================================================");
            System.out.println("1. List all films");
            System.out.println("2. Search film");
            System.out.println("3. Create film");
            System.out.println("4. Update film");
            System.out.println("5. Delete film");
            System.out.println("6. List all users");
            System.out.println("7. Search user");
            System.out.println("8. Update user");
            System.out.println("9. Delete user");
            System.out.println("10. Logout");
            System.out.println("11. Exit");
        	System.out.println("===========================================================");
        	System.out.print("Please choose an option (1-11): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> listAllFilms();
                case 2 -> searchFilm();
                case 3 -> createFilm();
                case 4 -> updateFilm();
                case 5 -> deleteFilm();
                case 6 -> listAllUsers();
                case 7 -> searchUser();
                case 8 -> updateUser();
                case 9 -> deleteUser();
                case 10 -> logout();
                case 11 -> System.exit(0);
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * This function lists all films from the filmService.
     * It displays the title of each film. If there are no films, a message is shown indicating no films are available.
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
     * This function allows the admin to search for a film by its title.
     * It prompts the admin for the film title and displays the search results.
     * If no films match the title, a message indicating no films found is displayed.
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
     * This function allows the admin to create a new film by entering its title, genre, year, and rating.
     * The film is then added to the database via the adminService.
     */
    private void createFilm() {
    	System.out.println("===========================================================");
    	System.out.println("                        Create Film                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter film title: ");
        String title = scanner.nextLine();
        System.out.print("Enter film genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter film year: ");
        int year = scanner.nextInt();
        System.out.print("Enter film rating: ");
        double rating = scanner.nextDouble();
    	System.out.println("===========================================================");
        
        Film newFilm = new Film(title, genre, year, rating);
        adminService.addFilm(newFilm);
        System.out.println("Film added successfully!");
    }

    /**
     * This function allows the admin to update the details of an existing film.
     * The admin provides the title of the film, and if found, they can update its genre and year.
     */
    private void updateFilm() {
    	System.out.println("===========================================================");
    	System.out.println("                        Update Film                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter film title to update: ");
        String title = scanner.nextLine();
    	System.out.println("===========================================================");
        
        Film film = adminService.searchFilmByTitle(title);
        
        if (film != null) {
            System.out.print("Enter new genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter new year: ");
            int year = scanner.nextInt();
        	System.out.println("===========================================================");
            film.setGenre(genre);
            film.setYear(year);
            adminService.updateFilm(film);
            System.out.println("Film updated successfully!");
        } else {
            System.out.println("Film not found.");
        }
    }

    /**
     * This function allows the admin to delete a film based on its title.
     * If the film exists, it is deleted; otherwise, a message is shown indicating the film was not found.
     */
    private void deleteFilm() {
    	System.out.println("===========================================================");
    	System.out.println("                        Delete Film                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter film title to delete: ");
        String title = scanner.nextLine();
    	System.out.println("===========================================================");
        
        boolean isDeleted = adminService.deleteFilm(title);
        
        if (isDeleted) {
            System.out.println("Film deleted successfully.");
        } else {
            System.out.println("Film not found.");
        }
    }

    /**
     * This function lists all users from the adminService.
     * It displays information about each user. If no users are found, a message is displayed.
     */
    private void listAllUsers() {
        List<User> users = adminService.getAllUsers();
    	System.out.println("===========================================================");
    	System.out.println("                     List of All Users                     ");
    	System.out.println("===========================================================");
    	if (users.isEmpty()) {
            System.out.println("No user available.");
        } else {
        	users.forEach(user -> System.out.println(user));
        }
    }

    /**
     * This function allows the admin to search for a user by their username.
     * If the user exists, their details are displayed; otherwise, a message is shown indicating the user was not found.
     */
    private void searchUser() {
    	System.out.println("===========================================================");
        System.out.print("Enter username to search: ");
        String username = scanner.nextLine();
        
        User user = adminService.searchUserByUsername(username);
    	System.out.println("===========================================================");
    	System.out.println("                       Search Results                      ");
    	System.out.println("===========================================================");
        
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * This function allows the admin to update a user's password.
     * The admin provides the username of the user they want to update and sets a new password.
     */
    private void updateUser() {
    	System.out.println("===========================================================");
    	System.out.println("                        Update User                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine();
    	System.out.println("===========================================================");
    	
        User user = adminService.searchUserByUsername(username);
        
        if (user != null) {
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();
        	System.out.println("===========================================================");
            user.setPassword(password);
            adminService.updateUser(user);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * This function allows the admin to delete a user by their username.
     * If the user exists, they are deleted; otherwise, a message is shown indicating the user was not found.
     */
    private void deleteUser() {
    	System.out.println("===========================================================");
    	System.out.println("                        Delete User                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();
    	System.out.println("===========================================================");
    	
        boolean isDeleted = adminService.deleteUser(username);
        
        if (isDeleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    /**
     * This function handles the logout process.
     * It logs out the admin and displays a confirmation message.
     */
    private void logout() {
    	System.out.println("===========================================================");
        System.out.println("Logged out successfully.");
    	System.out.println("===========================================================");
    	return;
    }
    
}