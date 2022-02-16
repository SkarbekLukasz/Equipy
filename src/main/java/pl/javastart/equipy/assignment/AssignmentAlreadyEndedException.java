package pl.javastart.equipy.assignment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wskazane wypożyczenie zostało już zakończone.")
public class AssignmentAlreadyEndedException extends RuntimeException{
}
