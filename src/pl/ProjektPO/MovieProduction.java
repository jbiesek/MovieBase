package pl.ProjektPO;

public abstract class MovieProduction {
    protected String title;
    protected String director;
    protected String genre;
    protected String status;

    public MovieProduction(String title, String director, String genre, String status) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
