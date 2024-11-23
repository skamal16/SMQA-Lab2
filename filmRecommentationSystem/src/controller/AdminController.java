package controller;

import java.util.Scanner;

import models.Film;
import service.FilmService;

public class AdminController {
	
	private Scanner scanner;
	private FilmService filmService;
	
	public AdminController(Scanner scanner, FilmService filmService){
		this.scanner = scanner;
		this.filmService = filmService;
	}

	public void AddFilm() {
        	System.out.print("Title of Film: ");
            
            String title = scanner.next();
            scanner.nextLine();
            
            filmService.addFilm(new Film(title));
            
            System.out.println("Film: '" + title + "' added.");
	}

	public void RemoveFilm() {
		System.out.print("Title of Film: ");
        
        String title = scanner.next();
        scanner.nextLine();
        
        if(filmService.deleteFilm(title))
        	System.out.println("Film: '" + title + "' deleted.");
        else
        	System.out.println("Film: '" + title + "' not found.");
	}

	public void ShowFilms() {
		for(Film film : filmService.getAllFilms()){
			System.out.println(film.toString());
		}
	}

}
