package ru.nik66.cache;

public class OptimisticException extends RuntimeException {

    public OptimisticException() {
    }

    public OptimisticException(String message) {
        super(message);
    }

}
