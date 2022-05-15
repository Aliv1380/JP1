package edu.javacourse.studentorder.domain;

public class Address {
    private String postCode; //Индекс
    private Street street;//Улица
    private String building;//Дом
    private String extension;//Корпус
    private String appartment;//Квартира

    public Address(String postCode, Street street, String building, String extension, String appartment) {
        this.postCode = postCode; //индекс
        this.street = street; // улица
        this.building = building; // дом
        this.extension = extension; //индекс
        this.appartment = appartment; //квартира
    }

    public String getPostCode() {        return postCode;    }

    public void setPostCode(String postCode) {        this.postCode = postCode;    }

    public Street getStreet() {        return street;    }

    public void setStreet(Street street) {        this.street = street;    }

    public String getBuilding() {        return building;    }

    public void setBuilding(String building) {        this.building = building;    }

    public String getExtension() {        return extension;    }

    public void setExtension(String extension) {        this.extension = extension;    }

    public String getAppartment() {        return appartment;    }

    public void setAppartment(String appartment) {        this.appartment = appartment;    }
}
