package com.filmrecommendation.controller;

import com.filmrecommendation.service.FilmService;
import com.filmrecommendation.service.UserService;
import com.filmrecommendation.service.AdminService;
import com.filmrecommendation.models.Admin;
import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;

import java.util.List;
import java.util.Scanner;

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
    
    private void logout() {
    	System.out.println("===========================================================");
        System.out.println("Logged out successfully.");
    	System.out.println("===========================================================");
    	return;
    }
    
}