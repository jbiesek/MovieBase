package pl.ProjektPO;

import java.io.IOException;

public class Main {
    
    public static void main(String [] args) throws IOException, InterruptedException {
        MoviesSeriesMap.getMoviesSeriesFromFile();
        Menu.printMenu();
    }
}
