package com.cloudframe.app.exception;
/**
 * The {@code RuntimeException} class is used to be thrown by the application
 * in case of any caught error.
 */
public class RuntimeException extends Exception {
    String message;
    String errorCode;
    int rc;
    Exception exception;
    public RuntimeException(String message) {
        this.message = message;
    }
    public static RuntimeException getException(String message, Exception exception) {
        RuntimeException variableName = new RuntimeException(message);
        variableName.exception = exception;
        return variableName;
    }
    public static RuntimeException getException(String message, String errorCode, Exception exception) {
        RuntimeException variableName = new RuntimeException(message);
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
