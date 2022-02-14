package pl.javastart.equipy.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nie znaleziono użytkowika o podanym id")
public class UserNotFoundException extends RuntimeException {
}
