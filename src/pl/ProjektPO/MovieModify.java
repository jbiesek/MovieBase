package pl.ProjektPO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class MovieModify {

    static HashMap moviesTitlesMap = new HashMap<>();
    static HashMap seriesTitlesMap = new HashMap<>();

    public static void userChoiceMovieSeries() throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Który film/serial chcesz zmodyfikować?");
        moviesTitlesMap = MoviesSeriesMap.getMoviesTitlesMap();
        seriesTitlesMap = MoviesSeriesMap.getSeriesTitlesMap();
        int j = 0;
        for (Object i : moviesTitlesMap.values()) {
            j++;
            System.out.println(j + " - " + i + " (film)");
        }
        int k = 0;
        for (Object i : seriesTitlesMap.values()) {
            k++;
            System.out.println(j + k + " - " + i + " (serial)");
        }
        System.out.println("-------------");
        Scanner scanner = new Scanner(System.in);
        int dirChoice = scanner.nextInt();
        String titleMod;
        if (dirChoice <= j) {
            titleMod = moviesTitlesMap.get(dirChoice).toString();
        } else if (dirChoice > j){
            dirChoice = dirChoice - j;
            titleMod = seriesTitlesMap.get(dirChoice).toString();
        } else return;

        userChoice(titleMod);
    }

    private static void userChoice(String titleMod) throws IOException, InterruptedException {
        File file = new File("plik.txt");
        Scanner scanner = new Scanner(file);
        String line1, line2, line3, line4, line5, line6;
        while(scanner.hasNextLine()){
            line1 = scanner.nextLine();
            line2 = scanner.nextLine();
            line3 = scanner.nextLine();
            line4 = scanner.nextLine();
            line5 = scanner.nextLine();
            line6 = scanner.nextLine();
            if (line1.equals("Movie") && line2.equals(titleMod)){
                String yearStr = line5.strip();
                int year = Integer.parseInt(yearStr);
                Movie movie = new Movie(line2, line3, line4, year, line6);
                System.out.println("-------------");
                System.out.println("Co chcesz zmodyfikować?\n1 - tytuł\n2 - reżyser\n3 - gatunek\n4 - rok produkcji\n5 - status");
                scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 1){
                    changeMovieSeriesTitle(movie, null, 1);
                    return;
                } else if (choice == 2){
                    changeMovieSeriesDirector(movie, null,1);
                    return;
                } else if (choice ==3) {
                    changeMovieSeriesGenre(movie, null,1);
                    return;
                } else if (choice ==4){
                    changeMovieSeriesYear(movie, null, 1);
                    return;
                } else if (choice ==5){
                    changeMovieSeriesStatus(movie,null,1);
                    return;
                }
            } else if(line1.equals(titleMod)){
                String yearStr = line4.strip();
                String episodesStr = line5.strip();
                int year = Integer.parseInt(yearStr);
                int episodes = Integer.parseInt(episodesStr);
                Series series = new Series(line1, line2, line3, year, episodes, line6);
                System.out.println("-------------");
                System.out.println("Co chcesz zmodyfikować?\n1 - tytuł\n2 - reżyser\n3 - gatunek\n4 - rok produkcji\n5 - ilość odcinków\n6 - status");
                scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                if (choice == 1){
                    changeMovieSeriesTitle(null,series,2);
                    return;
                } else if (choice == 2){
                    changeMovieSeriesDirector(null, series,2);
                    return;
                } else if (choice ==3) {
                   changeMovieSeriesGenre(null, series,2);
                    return;
                } else if (choice ==4){
                    changeMovieSeriesYear(null, series,2);
                    return;
                } else if (choice ==5){
                    changeSeriesEpisodes(series);
                    return;
                } else if (choice ==6){
                    changeMovieSeriesStatus(null, series,2);
                    return;
                }
            }
        }
    }

    private static void changeMovieSeriesTitle(Movie movie, Series series, int type) throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Wpisz nowy tytuł");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("-------------");
        if (type == 1) {
            String titleToDel = movie.title;
            String director = movie.director;
            String genre = movie.genre;
            int year = movie.year;
            String status = movie.status;
            MovieAdd.addMovie(title, director, genre, year, status);
            MovieDelete.deleteMovieSeries(titleToDel);
            Stats.statModify(1, titleToDel, title, titleToDel, "statsModify.txt", 1);
        } else {
            String titleToDel = series.title;
            String director = series.director;
            String genre = series.genre;
            int year = series.year;
            int episodes = series.episodes;
            String status = series.status;
            MovieAdd.addSeries(title, director, genre, year, episodes, status);
            MovieDelete.deleteMovieSeries(titleToDel);
            Stats.statModify(1, titleToDel, title, titleToDel, "statsModify.txt", 2);
        }
    }

    private static void changeMovieSeriesDirector(Movie movie, Series series, int type) throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Wpisz nowego reżysera");
        Scanner scanner = new Scanner(System.in);
        String director = scanner.nextLine();
        System.out.println("-------------");
        if (type == 1) {
            String title = movie.title;
            String directorToDel = movie.director;
            String genre = movie.genre;
            int year = movie.year;
            String status = movie.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addMovie(title, director, genre, year, status);
            Stats.statModify(2, directorToDel, director, title, "statsModify.txt", 1);
        }else {
            String title = series.title;
            String directorToDel = series.director;
            String genre = series.genre;
            int year = series.year;
            int episodes = series.episodes;
            String status = series.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addSeries(title, director, genre, year, episodes, status);
            Stats.statModify(2, directorToDel, director, title, "statsModify.txt", 2);
        }
    }

    private static void changeMovieSeriesGenre(Movie movie, Series series, int type) throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Wpisz nowy gatunek");
        Scanner scanner = new Scanner(System.in);
        String genre = scanner.nextLine();
        System.out.println("-------------");
        if (type == 1) {
            String title = movie.title;
            String director = movie.director;
            String genreToDel = movie.genre;
            int year = movie.year;
            String status = movie.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addMovie(title, director, genre, year, status);
            Stats.statModify(3, genreToDel, genre, title, "statsModify.txt", 1);
        } else {
            String title = series.title;
            String director = series.director;
            String genreToDel = series.genre;
            int year = series.year;
            int episodes = series.episodes;
            String status = series.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addSeries(title, director, genre, year, episodes, status);
            Stats.statModify(3, genreToDel, genre, title, "statsModify.txt", 2);
        }
    }

    private static void changeMovieSeriesYear(Movie movie, Series series, int type) throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Wpisz nowy rok produkcji");
        int year = 1900;
        try {
            year = MovieAdd.numberCheck(1);
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("-------------");
        if (type == 1) {
            String title = movie.title;
            String director = movie.director;
            String genre = movie.genre;
            int yearToDel = movie.year;
            String status = movie.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addMovie(title, director, genre, year, status);
            Stats.statModifyInt(1, yearToDel, year, title, "statsModify.txt", 1);
        } else {
            String title = series.title;
            String director = series.director;
            String genre = series.genre;
            int yearToDel = series.year;
            int episodes = series.episodes;
            String status = series.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addSeries(title, director, genre, year, episodes, status);
            Stats.statModifyInt(1, yearToDel, year, title, "statsModify.txt", 2);
        }
    }

    private static void changeMovieSeriesStatus(Movie movie, Series series, int type) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------");
        System.out.println("Podaj status(1 - obejrzane/2 - w trakcie/3 - nieobejrzane/4 - do kupienia");
        int choicedStatus = scanner.nextInt();
        System.out.println("-------------");
        if (type == 1) {
            String status = "";
            if (choicedStatus == 1) status = "Obejrzane";
            if (choicedStatus == 2) status = "W trakcie";
            if (choicedStatus == 3) status = "Nieobejrzane";
            if (choicedStatus == 4) status = "Do kupienia";
            String title = movie.title;
            String director = movie.director;
            String genre = movie.genre;
            int year = movie.year;
            String statusToDel = movie.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addMovie(title, director, genre, year, status);
            Stats.statModify(6, statusToDel, status, title, "statsModify.txt", 1);
        } else {
            String status = "";
            if (choicedStatus == 1) status = "Obejrzane";
            if (choicedStatus == 2) status = "W trakcie";
            if (choicedStatus == 3) status = "Nieobejrzane";
            if (choicedStatus == 4) status = "Do kupienia";
            String title = series.title;
            String director = series.director;
            String genre = series.genre;
            int year = series.year;
            int episodes = series.episodes;
            String statusToDel = series.status;
            MovieDelete.deleteMovieSeries(title);
            MovieAdd.addSeries(title, director, genre, year, episodes, status);
            Stats.statModify(6, statusToDel, status, title, "statsModify.txt", 2);
        }
    }

    private static void changeSeriesEpisodes(Series series) throws IOException, InterruptedException {
        System.out.println("-------------");
        System.out.println("Wpisz nową ilość odcinków");
        int episodes = 1;
        try {
            episodes = MovieAdd.numberCheck(2);
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("-------------");
        String title = series.title;
        String director = series.director;
        String genre = series.genre;
        int year = series.year;
        int episodesToDel = series.episodes;
        String status = series.status;
        MovieDelete.deleteMovieSeries(title);
        MovieAdd.addSeries(title, director, genre, year, episodes, status);
        Stats.statModifyInt(2, episodesToDel, episodes, title, "statsModify.txt", 2);
    }
}
