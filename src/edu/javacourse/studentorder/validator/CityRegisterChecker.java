package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exeption.CityRegisterException;

public interface CityRegisterChecker {
    CityRegisterCheckerResponse checkPerson (Person person) throws CityRegisterException;  //throws CityRegisterException - значит может вместо ответа вернуть исключение (ошибку)
}
