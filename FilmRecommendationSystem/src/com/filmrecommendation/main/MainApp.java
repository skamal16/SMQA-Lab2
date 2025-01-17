package com.filmrecommendation.main;

import com.filmrecommendation.controller.AdminController;
import com.filmrecommendation.controller.UserController;
import com.filmrecommendation.controller.VisitorController;
import com.filmrecommendation.service.AdminService;
import com.filmrecommendation.service.FilmService;
import com.filmrecommendation.service.UserService;
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

public class MainApp {
	
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point of the application. Initializes the necessary services and controllers.
     * Displays the main menu to the user and directs them to appropriate controllers based on their choice.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        FilmService filmService = new FilmService();
        UserService userService = new UserService();
        AdminService adminService = new AdminService();

        VisitorController visitorController = new VisitorController(filmService, scanner);
        UserController userController = new UserController(userService, filmService, scanner);
        AdminController adminController = new AdminController(adminService, filmService, userService, scanner);

        displayMainMenu(visitorController, userController, adminController);
    }

    /**
     * Displays the main menu with options to either enter as a visitor, login as a user, signup as a user,
     * login as an admin, or exit the application.
     * Based on the user's choice, it directs to the respective action (controller).
     * @param visitorController the controller handling visitor actions
     * @param userController the controller handling user actions
     * @param adminController the controller handling admin actions
     */
    private static void displayMainMenu(VisitorController visitorController, UserController userController, AdminController adminController) {
        while (true) {
        	System.out.println("===========================================================");
        	System.out.println("           Welcome to Film Recommendation System           ");
        	System.out.println("===========================================================");
        	System.out.println("                          Main Menu                        ");
        	System.out.println("===========================================================");
        	System.out.println("1. Enter as Visitor");
        	System.out.println("2. Login as User");
        	System.out.println("3. Signup as User");
        	System.out.println("4. Login as Admin");
        	System.out.println("5. Exit");
        	System.out.println("===========================================================");
        	System.out.print("Please choose an option (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    visitorController.showVisitorMenu();
                    break;
                case 2:
                    userController.loginUser();
                    break;
                case 3:
                    signUpUser(userController);
                    break;
                case 4:
                    adminController.loginAdmin();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles the user signup process by collecting necessary user information (first name, last name, 
     * date of birth, username, and password) and then registering the user.
     * If registration is successful, the user is logged in automatically and the user menu is displayed.
     * @param userController the controller handling user actions
     */
    private static void signUpUser(UserController userController) {
    	System.out.println("===========================================================");
    	System.out.println("                        User Signup                        ");
    	System.out.println("===========================================================");
        System.out.print("Enter First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
    	System.out.println("===========================================================");

        boolean isRegistered = userController.registerUser(firstName, lastName, dob, username, password);
        
        if (isRegistered) {
            System.out.println("Registration successful! You are now logged in.");
            userController.showUserMenu();
        } else {
            System.out.println("Registration failed. Username may already be taken.");
        }
    }
    
}