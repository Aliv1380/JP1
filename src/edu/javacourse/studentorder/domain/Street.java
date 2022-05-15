package edu.javacourse.studentorder.domain;

public class Street {
    private Long streetCode;
    private String streetName;

    //конструктор без параметров (чтоы м.б. создать улицу и уже потом инициализировать поля
    public Street() {
    }

    //конструктор с параметрами
    public Street(Long streetCode, String streetName) {
        this.streetCode = streetCode;
        this.streetName = streetName;
    }

    //сеттеры и геттеры
    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
