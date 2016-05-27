package com.james;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, ArrayList<Country>> countryMap = new HashMap<>();

        File f = new File("countries.txt");
        Scanner cScanner = new Scanner(f);
        while (cScanner.hasNextLine()) {
            String line = cScanner.nextLine();
            String[] column = line.split("\\|");
            Country country = new Country(column[1], column[0]);
            String firstLetter = String.valueOf(country.name.charAt(0));
            if(!countryMap.containsKey(firstLetter)) {
                countryMap.put(firstLetter, new ArrayList<>());
            }
            ArrayList<Country> arr = countryMap.get(firstLetter);
            arr.add(country);
        }
        System.out.println(countryMap.toString());

        String firstLetter = getLetter();
        ArrayList<Country> newList = countryMap.get(firstLetter);
        System.out.println(newList.toString());

        saveFile(firstLetter, countryMap);

    }

    public static String getLetter() {
        Scanner newScanner = new Scanner(System.in);
        System.out.println("Please enter first letter of country");
        String inputLetter = newScanner.nextLine().toLowerCase();

        if (inputLetter.length() == 1 && inputLetter.matches("[a-zA-Z]+")) {
            return inputLetter;
        } else {
            System.out.println("Invalid input");
            return getLetter();
        }
    }

    public static void saveFile(String getLetter, HashMap<String, ArrayList<Country>> countryMap) throws IOException {

        File f = new File(getLetter + "_countries.txt");
        FileWriter fw = new FileWriter(f);
        fw.write(countryMap.get(getLetter).toString());
        fw.close();
    }
}
