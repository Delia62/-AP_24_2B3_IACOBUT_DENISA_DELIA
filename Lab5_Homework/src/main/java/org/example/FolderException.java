package org.example;

public class FolderException extends Exception {



    public FolderException(String message) {
        super(message);
    }

    public FolderException(String message, Throwable cause) {
        super(message, cause);
    }

    public FolderException(Throwable cause) {
        super(cause);
    }
}
