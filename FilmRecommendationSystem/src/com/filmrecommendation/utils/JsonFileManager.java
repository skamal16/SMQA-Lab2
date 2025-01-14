package com.filmrecommendation.utils;

import com.filmrecommendation.models.Film;
import com.filmrecommendation.models.User;
import com.filmrecommendation.models.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager {
	
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILMS_FILE = "data/films.json";
    private static final String USERS_FILE = "data/users.json";
    private static final String ADMINS_FILE = "data/admins.json";

    // Load films from JSON file
    public static List<Film> loadFilms() {
        return loadFromFile(FILMS_FILE, new TypeToken<ArrayList<Film>>() {}.getType());
    }

    // Save films to JSON file
    public static void saveFilms(List<Film> films) {
        saveToFile(FILMS_FILE, films);
    }

    // Load users from JSON file
    public static List<User> loadUsers() {
        return loadFromFile(USERS_FILE, new TypeToken<ArrayList<User>>() {}.getType());
    }

    // Save users to JSON file
    public static void saveUsers(List<User> users) {
        saveToFile(USERS_FILE, users);
    }

    // Load admins from JSON file
    public static List<Admin> loadAdmins() {
        return loadFromFile(ADMINS_FILE, new TypeToken<ArrayList<Admin>>() {}.getType());
    }

    // Save admins to JSON file
    public static void saveAdmins(List<Admin> admins) {
        saveToFile(ADMINS_FILE, admins);
    }

    // Generic method to load a list from a file
    private static <T> List<T> loadFromFile(String filePath, Type type) {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            return new ArrayList<>();
        }
    }

    // Generic method to save a list to a file
    private static <T> void saveToFile(String filePath, List<T> data) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
}

