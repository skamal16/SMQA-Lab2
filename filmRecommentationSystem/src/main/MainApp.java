package main;

import java.util.Scanner;

import controller.AdminController;
import service.FilmService;

public class MainApp {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		FilmService filmService = new FilmService();
		AdminController adminController = new AdminController(scanner, filmService);
		displayMainMenu(adminController);
	}
	
	private static void displayMainMenu(AdminController adminController) {
        while (true) {
        	System.out.println("1. Add Film");
        	System.out.println("2. Remove Film");
        	System.out.println("3. Show Films");
        	System.out.println("4. Exit");
        	System.out.print("Please choose an option (1-4): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminController.AddFilm();
                    break;
                case 2:
                    adminController.RemoveFilm();
                    break;
                case 3:
                    adminController.ShowFilms();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
