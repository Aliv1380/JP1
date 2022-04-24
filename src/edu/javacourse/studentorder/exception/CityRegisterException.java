package edu.javacourse.studentorder.exception;

public class CityRegisterException extends Exception {
    private String code;

//конструктор причины - сообщение с кодом, с причиной
    public CityRegisterException(String code, String message) {
        super(message);
        this.code = code;
    }
//конструктор причины - сообщение с кодом, с причиной и возвращаемым объектом
    public CityRegisterException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
