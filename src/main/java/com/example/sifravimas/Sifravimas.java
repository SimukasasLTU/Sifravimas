package com.example.sifravimas;

import java.util.Scanner;

public class Sifravimas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Pasirinkti:");
            System.out.println("1. ASCII");
            System.out.println("2. Masyvas");
            System.out.println("3. Baigti");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleASCII(scanner);
                    break;
                case 2:
                    Masyvas(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Blogas pasirinkimas");
            }
        }

        scanner.close();
    }

    public static void handleASCII(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("Pasirinkti:");
            System.out.println("1. Sifruoti");
            System.out.println("2. Desifruoti");
            System.out.println("3. Grizti i pagrindini meniu");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Tekstas sifravimui:");
                    String messageToEncrypt = scanner.nextLine();
                    System.out.println("Poslinkis:");
                    int shiftEncrypt = scanner.nextInt();
                    String encryptedText = asc2Encrypt(messageToEncrypt, shiftEncrypt);
                    System.out.println("Uzsifruotas Tekstas: " + encryptedText);
                    break;
                case 2:
                    System.out.println("Simboliai desifruoti:");
                    String encryptedMessage = scanner.nextLine();
                    System.out.println("Poslinkis:");
                    int shiftDecrypt = scanner.nextInt();
                    String decryptedText = asc2Decrypt(encryptedMessage, shiftDecrypt);
                    System.out.println("Desifruotas tekstas: " + decryptedText);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Negeras pasirinkimas!");
            }
        }
    }

    public static String asc2Encrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            char encryptedChar = (char) (currentChar + shift);
            encryptedMessage.append(encryptedChar);
        }
        return encryptedMessage.toString();
    }

    public static String asc2Decrypt(String encryptedMessage, int shift) {
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < encryptedMessage.length(); i++) {
            char currentChar = encryptedMessage.charAt(i);
            char decryptedChar = (char) (currentChar - shift);
            decryptedMessage.append(decryptedChar);
        }
        return decryptedMessage.toString();
    }

    public static void Masyvas(Scanner userInputScanner) {
        boolean back = false;
        while (!back) {
            System.out.println("Masyvas:");
            System.out.println("1. Sifruoti");
            System.out.println("2. Desifruoti");
            System.out.println("3. Grizti i pagrindini meniu");
            int option = userInputScanner.nextInt();
            userInputScanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Tekstas sifravimui:");
                    String message = userInputScanner.nextLine();
                    System.out.println("Poslinkis:");
                    int shift = userInputScanner.nextInt();
                    String encryptedMessage = encrypt(message, shift);
                    System.out.println("Uzsifruotas Tekstas: " + encryptedMessage);
                    break;
                case 2:
                    System.out.println("Simboliai desifruoti:");
                    String encrypted = userInputScanner.nextLine();
                    System.out.println("Poslinkis:");
                    int decryptShift = userInputScanner.nextInt();
                    String decryptedMessage = decrypt(encrypted, decryptShift);
                    System.out.println("Desifruotas tekstas: " + decryptedMessage);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Blogas pasirinkimas");
            }
        }
    }

    public static String encrypt(String message, int shift) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] shiftedAlphabet = new char[36];
        //Abeceles paslinkimas
        for (int i = 0; i < 36; i++) {
            shiftedAlphabet[i] = alphabet[(i + shift) % 36];
        }

        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char encryptedChar = shiftedAlphabet[c - base];
                encryptedMessage.append(encryptedChar);
                //Jei skaicius pasislenkam iki skaiciu pridedami abeceles raidziu kieki
            } else if (Character.isDigit(c)) {
                char encryptedChar = shiftedAlphabet[c - '0' + 26];
                encryptedMessage.append(encryptedChar);
            } else {
                encryptedMessage.append(c);
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, int shift) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        char[] shiftedAlphabet = new char[36];
        for (int i = 0; i < 36; i++) {
            shiftedAlphabet[(i + shift) % 36] = alphabet[i];
        }

        StringBuilder decryptedMessage = new StringBuilder();
        for (char c : encryptedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char decryptedChar = shiftedAlphabet[c - base];
                decryptedMessage.append(decryptedChar);
            } else if (Character.isDigit(c)) {
                char decryptedChar = shiftedAlphabet[c - '0' + 26];
                decryptedMessage.append(decryptedChar);
            } else {
                decryptedMessage.append(c);
            }
        }
        return decryptedMessage.toString();
    }
}
