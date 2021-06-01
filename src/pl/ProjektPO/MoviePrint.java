package pl.ProjektPO;


import java.util.*;

public class MoviePrint {

    static MoviesSeriesMap movies = new MoviesSeriesMap();
    static HashMap<String, Movie> moviesMap = movies.getMovieMap();
    static HashMap moviesTitlesMap = new HashMap<>();
    static MoviesSeriesMap series = new MoviesSeriesMap();
    static HashMap<String, Series> seriesMap = movies.getSeriesMap();
    static HashMap seriesTitlesMap = new HashMap<>();

    public static void Choice(){
        System.out.println("-------------");
        System.out.println("1 - wyświetl tylko tytuły");
        System.out.println("2 - wyświetl wszystkie informacje");
        System.out.println("3 - wyświetl tylko filmy");
        System.out.println("4 - wyświetl tylko seriale");
        System.out.println("5 - wyświetl obejrzane");
        System.out.println("6 - wyświetl nieobejrzane");
        System.out.println("7 - wyświetl w trakcie");
        System.out.println("8 - wyświetl do kupienia");
        System.out.println("9 - wyświetl produkcje danego reżysera");
        System.out.println("10 - wyświetl produkcje z danego gatunku");
        System.out.println("11 - wyświetl reżyserów");
        System.out.println("12 - wyświetl gatunki");
        System.out.println("-------------");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice==1){
            printTitles();
        }
        else if(choice==2){
            printAllInfo();
        }
        else if(choice ==3){
            printMovies();
        }
        else if(choice ==4){
            printSeries();
        }
        else if(choice == 5){
            printWatched();
        }
        else if(choice == 6){
            printUnWatched();
        }
        else if(choice == 7){
            printWatching();
        }
        else if(choice == 8){
            printToBuy();
        }
        else if(choice == 9){
            System.out.println("-------------");
            System.out.println("Wybierz reżysera:");
            HashMap directorsHashMap = directorsHashMap();
            int j = 1;
            for (Object i : directorsHashMap.values()) {
                System.out.println(j+ " - " +i);
                j++;
            }
            System.out.println("-------------");
            Scanner scan = new Scanner(System.in);
            int dirChoice = scan.nextInt();
            printByDirector(directorsHashMap.get(dirChoice).toString());
        }
        else if(choice == 10){
            System.out.println("-------------");
            System.out.println("Wybierz gatunek:");
            HashMap genresHashMap = genresHashMap();
            int j = 1;
            for (Object i : genresHashMap.values()) {
                System.out.println(j+ " - " +i);
                j++;
            }
            System.out.println("-------------");
            Scanner scan = new Scanner(System.in);
            int genChoice = scan.nextInt();
            printByGenre(genresHashMap.get(genChoice).toString());
        }
        else if(choice == 11){
            printDirectors();
        }
        else if(choice == 12){
            printGenres();
        }
    }

    private static void printMovies(){
        Collection<Movie> movieCollection = moviesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            printingMovie(movie,i);
            i++;
        }
        System.out.println("-------------");
    }

    private static void printSeries(){
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Series series : seriesCollection){
            printingSeries(series,i);
            i++;
        }
        System.out.println("-------------");
    }

    private static void printTitles(){
        moviesTitlesMap = MoviesSeriesMap.getMoviesTitlesMap();
        seriesTitlesMap = MoviesSeriesMap.getSeriesTitlesMap();
        int j = 1;
        System.out.println("-------------");
        for (Object i : moviesTitlesMap.values()){
            System.out.println(j + " - " + i + " (film)");
            j++;
        }
        for (Object i : seriesTitlesMap.values()){
            System.out.println(j + " - " + i + " (serial)");
            j++;
        }
        System.out.println("-------------");
    }

    private static void printAllInfo(){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            printingMovie(movie,i);
            i++;
        }
        i=1;
        for (Series series : seriesCollection){
            printingSeries(series,i);
            i++;
        }
        System.out.println("-------------");
    }

    private static void printWatched(){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.status.equals("Obejrzane")) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.status.equals("Obejrzane")) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }
    private static void printUnWatched(){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.status.equals("Nieobejrzane")) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.status.equals("Nieobejrzane")) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }
    private static void printWatching(){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.status.equals("W trakcie")) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.status.equals("W trkcie")) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }
    private static void printToBuy(){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.status.equals("Do kupienia")) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.status.equals("Do kupienia")) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }

    private static void printByDirector(String director){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.director.equals(director)) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.director.equals(director)) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }

    private static void printByGenre(String genre){
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        int i =1;
        System.out.println("-------------");
        for (Movie movie : movieCollection){
            if (movie.genre.equals(genre)) {
                printingMovie(movie,i);
                i++;
            }
        }
        i=1;
        for (Series series : seriesCollection){
            if (series.genre.equals(genre)) {
                printingSeries(series,i);
                i++;
            }
        }
        System.out.println("-------------");
    }

    private static void printingMovie(Movie movie, int i){
        System.out.println("Film nr: " + i);
        System.out.println("Tytuł: " + movie.getTitle());
        System.out.println("Reżyser: " + movie.getDirector());
        System.out.println("Gatunek: " + movie.getGenre());
        System.out.println("Rok produkcji: " + movie.getYear());
        System.out.println("Status: " + movie.getStatus());
    }

    private static void printingSeries(Series series, int i){
        System.out.println("Serial nr: " + i);
        System.out.println("Tytuł: " + series.title);
        System.out.println("Reżyser: " + series.director);
        System.out.println("Gatunek: " + series.genre);
        System.out.println("Rok produkcji: " + series.year);
        System.out.println("Ilość odcinków: " + series.episodes);
        System.out.println("Status: " + series.status);
    }


    private static HashSet directorsHashSet() {
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        HashSet<String> directors = new HashSet<>();
        String director;
        for (Movie movie : movieCollection){
            director = movie.getDirector();
            directors.add(director);
        }
        for (Series series : seriesCollection){
            director = series.getDirector();
            directors.add(director);
        }
        return directors;
    }

    private static void printDirectors(){
        HashSet directors = directorsHashSet();
        int j = 1;
        System.out.println("-------------");
        for (Object i : directors){
            System.out.println("Reżyser nr " + j + ": " + i);
            j++;
        }
        System.out.println("-------------");
    }

    public static HashMap directorsHashMap(){
        HashSet directors = directorsHashSet();
        HashMap<Integer, String> directorsHashMap = new HashMap<>();
        int j = 1;
        for (Object i : directors){
            directorsHashMap.put(j, i.toString());
            j++;
        }
        return(directorsHashMap);
    }
    public static HashSet genresHashSet() {
        Collection<Movie> movieCollection = moviesMap.values();
        Collection<Series> seriesCollection = seriesMap.values();
        HashSet<String> genres = new HashSet<>();
        String genre;
        for (Movie movie : movieCollection){
            genre = movie.getGenre();
            genres.add(genre);
        }
        for (Series series : seriesCollection){
            genre = series.getGenre();
            genres.add(genre);
        }
        return genres;
    }

    private static void printGenres(){
        HashSet genres = genresHashSet();
        int j = 1;
        System.out.println("-------------");
        for (Object i : genres){
            System.out.println("Gatunek nr " + j + ": " + i);
            j++;
        }
        System.out.println("-------------");
    }

    private static HashMap genresHashMap(){
        HashSet genres = genresHashSet();
        HashMap<Integer, String> genresHashMap = new HashMap<>();
        int j = 1;
        for (Object i : genres){
            genresHashMap.put(j, i.toString());
            j++;
        }
        return(genresHashMap);
    }

}
