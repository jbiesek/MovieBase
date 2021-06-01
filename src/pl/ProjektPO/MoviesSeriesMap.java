package pl.ProjektPO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MoviesSeriesMap {
    static HashMap<String, Movie> moviesMap = new HashMap<>();

    static HashMap<String, Series> seriesMap = new HashMap<>();

    public HashMap<String, Movie> getMovieMap() {
        return moviesMap;
    }

    public HashMap<String, Series> getSeriesMap() {
        return seriesMap;
    }

    public static void moviesMapAdd(Movie movie){
        moviesMap.put(movie.title.toString(), movie);
    }

    public static void seriesMapAdd(Series series){
        seriesMap.put(series.title.toString(), series);
    }

    static void getMoviesSeriesFromFile() throws FileNotFoundException {
        File file = new File("plik.txt");
        Scanner scanner = new Scanner(file);
        String title, director, genre,yearStr, episodesStr, status;
        int year,episodes;
        while(scanner.hasNextLine()){
            String movieOrSeries = scanner.nextLine();
            if(movieOrSeries.equals("Movie")) {
                title = scanner.nextLine();
                director = scanner.nextLine();
                genre = scanner.nextLine();
                yearStr = scanner.nextLine();
                yearStr = yearStr.strip();
                year = Integer.parseInt(yearStr);
                status = scanner.nextLine();
                //status = scanner.nextLine();
                Movie movie = new Movie(title, director, genre, year, status);
                moviesMapAdd(movie);
            } else {
                if(scanner.hasNextLine()) {
                    director = scanner.nextLine();
                    genre = scanner.nextLine();
                    yearStr= scanner.nextLine();
                    yearStr = yearStr.strip();
                    year = Integer.parseInt(yearStr);
                    episodesStr = scanner.nextLine();
                    episodesStr = episodesStr.strip();
                    episodes = Integer.parseInt(episodesStr);
                    status = scanner.nextLine();
                    //status = scanner.nextLine();
                    Series series = new Series(movieOrSeries, director, genre, year, episodes, status);
                    seriesMapAdd(series);
                }
            }
        }
        scanner.close();
    }

    static void reloadMoviesMap() throws FileNotFoundException {
        moviesMap.clear();
        seriesMap.clear();
        getMoviesSeriesFromFile();
    }

    static HashMap getMoviesTitlesMap() {
        HashMap<Integer, String> moviesTitles = new HashMap<>();
        int j =1;
        for (Object i : moviesMap.keySet()) {
            moviesTitles.put(j, i.toString());
            j++;
        }
        return moviesTitles;
    }

    static HashMap getSeriesTitlesMap() {
        HashMap<Integer, String> seriesTitles = new HashMap<>();
        int j =1;
        for (Object i : seriesMap.keySet()) {
            seriesTitles.put(j, i.toString());
            j++;
        }
        return seriesTitles;
    }

}

