package pl.ProjektPO;

public class Series extends MovieProduction{
    protected int episodes;
    protected int year;

    public Series(String title, String director, String genre, int year, int episodes, String status) {
        super(title, director, genre, status);
        this.year = year;
        this.episodes = episodes;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
