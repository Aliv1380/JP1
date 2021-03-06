package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.register.AnswerCityRegisterItem;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;

/*
CityRegisterValidator - класс для проверки регистрации в городе
Мы для каждой заявки, проверяем всю семью.  Создаём обобщенный ответ, в виде отчета и начинаем проверять.
Проверяем мужа, проверяем жену, проверяем всех детей, для каждого вызывая checkPerson.
----------------------------------------------------------------------------------------
checkPerson - формирует довольно серьезный ответ.
В ответе:
Status - который говорит получен ли ответ вообще;
cityError - ошибка которая случилась;
Person  - персона, в рамках которой производилась данная проверка.

Если ошики не случается - возвращается персона со статусом.
Если ошибка случается - возвращается status в значении ERROR, и ошибка с кодом ошибки и сообщением.
Затем формируется ответ по резальтатам проверки персоны  (статус, персона и ошибка=null если все норм).
-----------------------------------------------------------------------------------------
 */

public class CityRegisterValidator {
    public static final String IN_CODE = "NO GRN";  //константа для варианта ответа по транспортному исключению

    public String hostName;
    public String login;
    public String password;

    //объявляем интерфейс, и после его настройки можем объявлять переменную с типом CityRegisterChecker
    //которая является интерфейсом.   Т.о. включается полиморфизм. Т.е. получаем ссылку на интерфейс, которая по фактя
    //является ссылкой на оба чекера (RealCityRegisterChecker и FakeCityRegisterChecker)
    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        //реальный объект пока создается только тут, но есть механизмы замены, их рассмотрим позже
        personChecker = new FakeCityRegisterChecker();
    }

    //несмотря на то что Adult и Child разные классы, они оба наследники Person, и для них можно вызвать checkPerson это пример полиморфизма
    public AnswerCityRegister checkCityRegister(StudentOrder so) {
        AnswerCityRegister ans = new AnswerCityRegister();
        ans.addItem(checkPerson(so.getHusband()));//hans - husband answer
        ans.addItem(checkPerson(so.getWife())); //wans - wife answer
//вариант цикла for each
        for (Child child : so.getChildren()) {
            ans.addItem(checkPerson(child));
        }
        return ans;
    }

    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status= null;
        AnswerCityRegisterItem.CityError error= null;


        try {
            CityRegisterResponse tmp =  personChecker.checkPerson(person);
            status = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;

        } catch (CityRegisterException ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(),ex.getMessage());
        } catch (TransportException ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE,ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE,ex.getMessage());
        }



        AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, person, error);
        return ans;
    }

}
