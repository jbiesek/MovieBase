package pl.ProjektPO;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void printMenu() throws IOException, InterruptedException {
        while (true) {
            System.out.println("1 - dodanie filmu/serialu");
            System.out.println("2 - usunięcie filmu/serialu");
            System.out.println("3 - modyfikacja filmu/serialu");
            System.out.println("4 - pokaż filmy/seriale");
            System.out.println("5 - statystyki");
            System.out.println("6 - wyjście z programu");
            Scanner scanner = new Scanner(System.in);
            int choice;
            choice = scanner.nextInt();
            userChoice(choice);
        }
    }

    private static void userChoice(int choice) throws IOException, InterruptedException {
        if(choice == 1){
            MovieAdd.add();
        }
        else if(choice == 2){
            MovieDelete.userChoice();
        }
        else if(choice == 3){
            MovieModify.userChoiceMovieSeries();
        }
        else if(choice == 4){
            MoviePrint.Choice();
        }
        else if(choice == 5){
            Stats.userChoice();
        }
        else if(choice == 6){
            System.exit(0);
        }
    }
}
