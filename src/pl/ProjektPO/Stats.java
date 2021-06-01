package pl.ProjektPO;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class Stats {

    protected static void statAddDel(String title, int action, int type) throws IOException {
        if (action == 0) {
            statAdd(title, "statsAddDel.txt", type, 1);
        }
        if (action == 1) {
            statAdd(title, "statsAddDel.txt", type, 2);
        }
    }

    protected static void statAdd(String title, String fileTxt, int type, int action) throws IOException {
        FileWriter file = new FileWriter(fileTxt, true);
        BufferedWriter out = new BufferedWriter(file);
        Date date = new Date();
        String dateStr = date.toString();
        if (action == 1) {
            if (type == 1) {
                out.write(dateStr + "\n");
                out.write("Dodano nowy film: " + title + "\n");
            } else {
                out.write(dateStr + "\n");
                out.write("Dodano nowy serial: " + title + "\n");
            }
        } else {
            if (type == 1) {
                out.write(dateStr + "\n");
                out.write("Usunięto film: " + title + "\n");
            } else {
                out.write(dateStr + "\n");
                out.write("Usunięto serial: " + title + "\n");
            }
        }
        out.close();
        file.close();
    }


    protected static void statModify(int action, String prevAttr, String attr, String title, String fileTxt, int type) throws IOException {
        FileWriter file = new FileWriter(fileTxt, true);
        BufferedWriter out = new BufferedWriter(file);
        Date date = new Date();
        out.write(date + "\n");
        if (type == 1) out.write("Zmodyfikowano film: " + title + "\n");
        if (type == 2) out.write("Zmodyfikowano serial: " + title + "\n");
        if (action == 1) out.write("tytuł: " + prevAttr + " -> " + attr + "\n");
        if (action == 2) out.write("reżyser: " + prevAttr + " -> " + attr + "\n");
        if (action == 3) out.write("gatunek: " + prevAttr + " -> " + attr + "\n");
        if (action == 6) out.write("status: " + prevAttr + " -> " + attr + "\n");
        out.close();
        file.close();
        DeleteTrashStats(date, "statsAddDel.txt");
    }

    protected static void statModifyInt(int action, int prevAttr, int attr, String title, String fileTxt, int type) throws IOException {
        FileWriter file = new FileWriter(fileTxt, true);
        BufferedWriter out = new BufferedWriter(file);
        Date date = new Date();
        out.write(date + "\n");
        if (type == 1) out.write("Zmodyfikowano film: " + title + "\n");
        if (type == 2) out.write("Zmodyfikowano serial: " + title + "\n");
        if (action == 1) out.write("rok produkcji: " + prevAttr + " -> " + attr + "\n");
        if (action == 2) out.write("ilość odcinków: " + prevAttr + " -> " + attr + "\n");
        out.close();
        file.close();
        DeleteTrashStats(date, "statsAddDel.txt");
    }

    public static void userChoice() throws IOException {
        System.out.println("-------------");
        System.out.println("1 - historia dodawania i usuwania filmów/seriali");
        System.out.println("2 - historia dodawania filmów/seriali");
        System.out.println("3 - historia usuwania filmów/seriali");
        System.out.println("4 - historia modyfikacji filmów/seriali");
        System.out.println("5 - historia modyfikacji filmów");
        System.out.println("6 - historia modyfikacji seriali");
        System.out.println("7 - historia modyfikacji tytułów filmów/seriali");
        System.out.println("8 - historia modyfikacji reżyserów filmów/seriali");
        System.out.println("9 - historia modyfikacji gatunków filmów/seriali");
        System.out.println("10 - historia modyfikacji lat produkcji filmów/seriali");
        System.out.println("11 - historia modyfikacji ilości odcinków serialu");
        System.out.println("12 - historia modyfikacji statusów filmów/seriali");
        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
        if (choice == 1) printAddDelHistory(2);
        if (choice == 2) printAddDelHistory(0);
        if (choice == 3) printAddDelHistory(1);
        if (choice == 4) printModifyHistory(0,0);
        if (choice == 5) printModifyHistory(0,1);
        if (choice == 6) printModifyHistory(0,2);
        if (choice == 7) printModifyHistory(1,0);
        if (choice == 8) printModifyHistory(2,0);
        if (choice == 9) printModifyHistory(3,0);
        if (choice == 10) printModifyHistory(4,0);
        if (choice == 11) printModifyHistory(5,0);
        if (choice == 12) printModifyHistory(6,0);
    }

    protected static void DeleteTrashStats(Date date, String fileTxt) throws IOException {
        File file = new File(fileTxt);
        File tempFile = new File("statsTemp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        Scanner scanner = new Scanner(file);
        String line1 = "";
        String line2 = "";
        String line3 = "";
        String line4 = "";
        String dateStr = date.toString();
        boolean a = false;
        line1 = scanner.nextLine();
        line2 = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line3 = scanner.nextLine();
            line4 = scanner.nextLine();
            if (line3.equals(dateStr) && !a) {
                a = true;
            } else {
                writer.write(line1 + "\n" + line2 + "\n");
                line1 = line3;
                line2 = line4;
            }
        }
        writer.close();
        scanner.close();
        boolean s = tempFile.renameTo(file);
    }

    private static void printAddDelHistory(int add_del) throws FileNotFoundException {
        File file = new File("statsAddDel.txt");
        Scanner scanner = new Scanner(file);
        String line;
        String lineNext;
        System.out.println("-------------");
        if (add_del != 2) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineNext = scanner.nextLine();
                String[] words = lineNext.split(" ");
                if (words[0].equals("Dodano") && add_del == 0) {
                    System.out.println(line);
                    System.out.println(lineNext);
                }
                if (words[0].equals("Usunięto") && add_del == 1) {
                    System.out.println(line);
                    System.out.println(lineNext);
                }
            }
        } else {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
            }
        }
        System.out.println("-------------");
    }

    private static void printModifyHistory(int choice, int type) throws FileNotFoundException {
        File file = new File("statsModify.txt");
        Scanner scanner = new Scanner(file);
        String line;
        String lineNext;
        String lineMoreNext = "";
        System.out.println("-------------");
        if (type == 0) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineNext = scanner.nextLine();
                String[] words = lineNext.split(" ");
                if (words[0].equals("Zmodyfikowano")) {
                    lineMoreNext = scanner.nextLine();
                }
                if (words[0].equals("Zmodyfikowano") && choice == 0) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                String[] wordsNext = lineMoreNext.split(" ");
                if (words[0].equals("Zmodyfikowano") && choice == 1 && wordsNext[0].equals("tytuł:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                if (words[0].equals("Zmodyfikowano") && choice == 2 && wordsNext[0].equals("reżyser:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                if (words[0].equals("Zmodyfikowano") && choice == 3 && wordsNext[0].equals("gatunek:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                if (words[0].equals("Zmodyfikowano") && choice == 4 && wordsNext[0].equals("rok")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                if (words[0].equals("Zmodyfikowano") && choice == 5 && wordsNext[0].equals("ilość")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
                if (words[0].equals("Zmodyfikowano") && choice == 6 && wordsNext[0].equals("status:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
            }
        } else if (type == 1) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineNext = scanner.nextLine();
                String[] words = lineNext.split(" ");
                if (words[0].equals("Zmodyfikowano")) {
                    lineMoreNext = scanner.nextLine();
                }
                if (words[0].equals("Zmodyfikowano") && words[1].equals("film:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
            }
        } else {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                lineNext = scanner.nextLine();
                String[] words = lineNext.split(" ");
                if (words[0].equals("Zmodyfikowano")) {
                    lineMoreNext = scanner.nextLine();
                }
                if (words[0].equals("Zmodyfikowano") && words[1].equals("serial:")) {
                    System.out.println(line);
                    System.out.println(lineNext);
                    System.out.println(lineMoreNext);
                }
            }
            System.out.println("-------------");
        }

    }
}
