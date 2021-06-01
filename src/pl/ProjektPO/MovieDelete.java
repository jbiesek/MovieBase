package pl.ProjektPO;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class MovieDelete {

    static HashMap moviesTitlesMap = new HashMap<>();
    static HashMap seriesTitlesMap = new HashMap<>();

    public static void userChoice() throws IOException {
        System.out.println("-------------");
        System.out.println("Który film/serial chcesz usunąć: ");
        moviesTitlesMap = MoviesSeriesMap.getMoviesTitlesMap();
        seriesTitlesMap = MoviesSeriesMap.getSeriesTitlesMap();
        int j = 0;
        for (Object i : moviesTitlesMap.values()){
            j++;
            System.out.println(j + " - " + i + " (film)");
        }
        int k = 0;
        for (Object i : seriesTitlesMap.values()){
            k++;
            System.out.println(k + j + " - " + i + " (serial)");
        }
        System.out.println("-------------");
        Scanner scanner = new Scanner(System.in);
        int dirChoice = scanner.nextInt();
        String titleDel ="";
        if (dirChoice <= j) {
            titleDel = moviesTitlesMap.get(dirChoice).toString();
        } else if (dirChoice > j) {
            dirChoice = dirChoice - j;
            titleDel = seriesTitlesMap.get(dirChoice).toString();
        }
        deleteMovieSeries(titleDel);
    }

    protected static void deleteMovieSeries(String title) throws IOException {
        File file = new File("plik.txt");
        File tempFile = new File("plikTemp.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        Scanner scanner = new Scanner(file);
        String line1, line2, line3, line4, line5, line6;
        int type=0;
        while(scanner.hasNextLine()) {
            line1 = scanner.nextLine();
            line2 = scanner.nextLine();
            line3 = scanner.nextLine();
            line4 = scanner.nextLine();
            line5 = scanner.nextLine();
            line6 = scanner.nextLine();
            if (line1.equals("Movie") && line2.equals(title)){
                line1 = "";
                line2 = "";
                line3 = "";
                line4 = "";
                line5 = "";
                line6 = "";
                type = 1;
            } else if (line1.equals(title)) {
                line1 = "";
                line2 = "";
                line3 = "";
                line4 = "";
                line5 = "";
                line6 = "";
                type = 2;
            } else {
                writer.write(line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n");
            }
        }
        writer.close();
        scanner.close();
        boolean s = tempFile.renameTo(file);
        MoviesSeriesMap.reloadMoviesMap();
        Stats.statAddDel(title, 1, type);
    }
}
