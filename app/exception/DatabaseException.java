package com.cloudframe.app.exception;
/**
 * The {@code DatabaseException} class is used to be thrown by the application
 * in case of any caught error.
 */
public class DatabaseException extends Exception {
    String message;
    String errorCode;
    int rc;
    Exception exception;
    public DatabaseException(String message) {
        this.message = message;
    }
    public static DatabaseException getException(String message, Exception exception) {
        DatabaseException variableName = new DatabaseException(message);
        variableName.exception = exception;
        return variableName;
    }
    public static DatabaseException getException(String message, String errorCode, Exception exception) {
        DatabaseException variableName = new DatabaseException(message);
        variableName.exception = exception;
        variableName.errorCode = errorCode;
        return variableName;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Exception getException() {
        return exception;
    }
    public void setException(Exception exception) {
        this.exception = exception;
    }
     public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }
     public void setRc(int rc) {
        this.rc = rc;
    }
    public int getRc() {
        return rc;
    }
    @Override
    public String getMessage() {
        if (message != null) {
            return message;
        }
        return super.getMessage();
    }
}
