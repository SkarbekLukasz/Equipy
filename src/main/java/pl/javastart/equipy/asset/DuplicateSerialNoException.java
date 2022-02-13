package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Istnieje już wyposażenie o takim numerze seryjnym")
public class DuplicateSerialNoException extends RuntimeException {
}
