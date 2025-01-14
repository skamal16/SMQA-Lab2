package com.filmrecommendation.controller;

import com.filmrecommendation.service.FilmService;
import com.filmrecommendation.models.Film;

import java.util.List;
import java.util.Scanner;

public class VisitorController {

    private Scanner scanner;
    private FilmService filmService;

    public VisitorController(FilmService filmService, Scanner scanner) {
        this.filmService = filmService;
        this.scanner = scanner;
    }

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
    
}
