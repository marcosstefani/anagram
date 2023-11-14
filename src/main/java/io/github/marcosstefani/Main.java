package io.github.marcosstefani;

import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import static java.util.Objects.isNull;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnagramHelper anagramHelper = new AnagramHelper();
        int choice;

        do {
            System.out.println("Anagram Checker Menu:");
            System.out.println("1. Check if two texts are anagrams");
            System.out.println("2. Find all anagrams for a given string");
            System.out.println("0. Exit");
            System.out.print("Choose a number: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> System.out.println("Exiting...");
                case 1 -> {
                    anagramHelper.clear();
                    System.out.print("Enter the first text: ");
                    String firstText = scanner.nextLine();
                    anagramHelper.addText(firstText);
                    System.out.print("Enter the second text: ");
                    anagramHelper.addText(scanner.nextLine());
                    Set<String> anagrams = anagramHelper.findAnagrams(firstText);
                    System.out.println(anagrams.isEmpty() ? "The texts are not an anagram" : "Yes! are anagrams!");
                }
                case 2 -> {
                    anagramHelper.clear();
                    int nextChoice;
                    do {
                        System.out.println("Find all anagrams for a given string:");
                        System.out.println("1. Add text");
                        System.out.println("2. Select one String and find");
                        System.out.println("0. Exit");
                        System.out.print("Choose a number: ");

                        nextChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (nextChoice) {
                            case 0 -> {
                                System.out.println("Backing to main menu...");
                            }
                            case 1 -> {
                                System.out.print("Add a text: ");
                                anagramHelper.addText(scanner.nextLine());
                            }
                            case 2 -> {
                                System.out.println("Select the number of a String to verify:");
                                String[] textList = anagramHelper.getTextList();

                                if (isNull(textList) || textList.length == 0) {
                                    System.out.println("The list is empty");
                                }
                                else {
                                    for (int i = 1; i <= textList.length; i++) {
                                        System.out.println(i + ". " + textList[i -1]);
                                    }
                                    System.out.print("Choose a number: ");
                                    int textChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    System.out.println("Result: " + anagramHelper.findAnagrams(textList[textChoice -1]));
                                }
                            }
                            default -> System.out.println("Invalid choice");
                        }
                        System.out.println("");

                    } while (nextChoice != 0);
                }
                default -> System.out.println("Invalid choice");
            }
            System.out.println("");


        } while (choice != 0);

        scanner.close();
    }
}