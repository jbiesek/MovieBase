package pl.ProjektPO;

public class InvalidNumberException extends Exception{

    public InvalidNumberException(){
        super("Wpisano błędne dane!");
    }

    public InvalidNumberException(String message) {
        super(message);
    }
}
