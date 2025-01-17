package com.filmrecommendation.controller;

import com.filmrecommendation.service.FilmService;
import com.filmrecommendation.models.Film;

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

public class VisitorController {

    private Scanner scanner;
    private FilmService filmService;

    public VisitorController(FilmService filmService, Scanner scanner) {
        this.filmService = filmService;
        this.scanner = scanner;
    }

    /**
     * Displays the visitor menu with options to list all films, search for a film,
     * go back to the previous menu, or exit the application.
     * Continuously prompts the user for an action until they choose to exit or go back.
     */
    public void showVisitorMenu() {
        while (true) {
        	System.out.println("===========================================================");
        	System.out.println("                        Visitor Menu                       ");
        	System.out.println("===========================================================");
            System.out.println("1. List all films");
            System.out.println("2. Search Film");
            System.out.println("3. Back");
            System.out.println("4. Exit");
        	System.out.println("===========================================================");
        	System.out.print("Please choose an option (1-4): ");
            
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
                    return;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Lists all the films available in the system.
     * If no films are available, it will display a message indicating that no films are present.
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
    
}