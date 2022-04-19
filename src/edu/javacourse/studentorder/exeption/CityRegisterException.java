package edu.javacourse.studentorder.exeption;

public class CityRegisterException extends Exception {
    //просто сообщение
    public CityRegisterException() {
    }
//сообщение с причиной
    public CityRegisterException(String message) {
        super(message);
    }
//сообщение с причиной и возвращаемым объектом
    public CityRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
