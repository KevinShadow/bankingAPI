package net.javaguides.banking.exception;

import java.time.LocalDateTime;

public record ErrorDetails(
    LocalDateTime timestamp,
    String message,
    String deta,
    String errorCode) {

}
