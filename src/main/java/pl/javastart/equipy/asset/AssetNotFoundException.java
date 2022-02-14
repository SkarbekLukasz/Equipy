package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Urządzenie o podanym ID nie istnieje w bazie")
public class AssetNotFoundException extends RuntimeException{
}
