package pl.ProjektPO;

import java.io.*;
import java.util.Scanner;

public class MovieAdd {
    protected static void add() throws IOException, InterruptedException {
        System.out.println("-------------");
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        int choice;
        System.out.println("1 - film\n2 - serial");
        choice = scannerInt.nextInt();
        System.out.println("-------------");
        System.out.println("Podaj tytuł:");
        String title = scanner.nextLine();
        System.out.println("Podaj reżysera:");
        String director = scanner.nextLine();
        System.out.println("Podaj gatunek:");
        String genre = scanner.nextLine();
        System.out.println("Podaj rok produkcji:");
        int year=1900;
        try {
            year = numberCheck(1);
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
            return;
        }
        int episodes = 0;
        if (choice == 2){
            System.out.println("Podaj ilość odcinków:");
            try {
                episodes = numberCheck(2);
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Podaj status(1 - obejrzane/2 - w trakcie/3 - nieobejrzane/4 - do kupienia");
        int choicedStatus = scannerInt.nextInt();
        System.out.println("-------------");
        if(choice ==1) {
            if (choicedStatus == 1) addMovie(title, director, genre, year, "Obejrzane");
            if (choicedStatus == 2) addMovie(title, director, genre, year, "W trakcie");
            if (choicedStatus == 3) addMovie(title, director, genre, year, "Nieobejrzane");
            if (choicedStatus == 4) addMovie(title, director, genre, year,"Do kupienia");
        } else {
            if (choicedStatus == 1) addSeries(title, director, genre, year, episodes, "Obejrzane");
            if (choicedStatus == 2) addSeries(title, director, genre, year, episodes,"W trakcie");
            if (choicedStatus == 3) addSeries(title, director, genre, year, episodes, "Nieobejrzane");
            if (choicedStatus == 4) addSeries(title, director, genre, year, episodes,"Do kupienia");
        }
    }

    protected static int numberCheck(int type) throws InvalidNumberException {
        Scanner scanner = new Scanner(System.in);
        String numberStr = scanner.nextLine();
        String digits = "[0-9]+";
        if (!numberStr.matches(digits)){
            throw new InvalidNumberException();
        }
        int number = Integer.parseInt(numberStr);
        if (type==1) {
            if (number < 1888 || number > 2030) {
                throw new InvalidNumberException();
            }
        } else {
            if (number < 0 || number > 15762) {
                throw new InvalidNumberException();
            }
        }
        return number;
    }

    protected static void addMovie(String title, String director, String genre, int year, String status) throws IOException, InterruptedException {
        Movie movie = new Movie(title, director, genre, year, status);
        MoviesSeriesMap.moviesMapAdd(movie);
        filesMovie(movie);
        MoviesSeriesMap.reloadMoviesMap();
        Stats.statAddDel(title, 0, 1);
    }

    protected static void addSeries(String title, String director, String genre, int year, int episodes, String status) throws IOException, InterruptedException {
        Series series = new Series(title, director, genre, year, episodes, status);
        MoviesSeriesMap.seriesMapAdd(series);
        filesSeries(series);
        MoviesSeriesMap.reloadMoviesMap();
        Stats.statAddDel(title, 0,2);
    }

    protected static void filesMovie(Movie movie) throws FileNotFoundException, InterruptedException {
        Runnable runnable = () ->
        {
            System.out.print("Trwa zapis");
            for (int i = 0; i < 3; i++){
                System.out.print(".");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("");
            try {
                FileWriter file = new FileWriter("plik.txt", true);
                BufferedWriter out = new BufferedWriter(file);
                out.write("Movie" + "\n" +
                        movie.getTitle() + "\n" +
                        movie.getDirector() + "\n" +
                        movie.getGenre() + "\n" +
                        movie.getYear() + "\n" +
                        movie.getStatus() + "\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Pomyślnie dokonano zapisu!");
        };

        Thread thread = new Thread( runnable);
        thread.start();
        Thread.sleep(1000);
    }

    protected static void filesSeries(Series series) throws FileNotFoundException, InterruptedException {
        Runnable runnable = () ->
        {
            System.out.print("Trwa zapis");
            for (int i = 0; i < 3; i++){
                System.out.print(".");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try{
                FileWriter file = new FileWriter("plik.txt",true);
                BufferedWriter out = new BufferedWriter(file);
                out.write(series.getTitle() + "\n" +
                        series.getDirector() + "\n" +
                        series.getGenre() + "\n" +
                        series.getYear() + "\n" +
                        series.getEpisodes() + "\n" +
                        series.getStatus() + "\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Pomyślnie dokonano zapisu!");
        };

        Thread thread = new Thread( runnable);
        thread.start();
        Thread.sleep(1000);
    }
}
