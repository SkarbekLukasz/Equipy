package pl.javastart.equipy.assignment;

public class AssetInUseException extends RuntimeException{
    public AssetInUseException(String message) {
        super(message);
    }
}
