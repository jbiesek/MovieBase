package pl.ProjektPO;

public class Movie extends MovieProduction {
    protected int year;

    public Movie(String title, String director, String genre, int year, String status) {
        super(title,director,genre,status);
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
