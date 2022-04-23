package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerCityRegister;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.CityRegisterCheckerResponse;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exeption.CityRegisterException;

import java.util.Iterator;

public class CityRegisterValidator {
    public String hostName;
    public String login;
    public String password;

    //объявляем интерфейс, и после его настройки можем объявлять переменную с типом CityRegisterChecker
    //которая является интерфейсом.   Т.о. включается полиморфизм. Т.е. получаем ссылку на интерфейс, которая по фактя
    //является ссылкой на оба чекера (RealCityRegisterChecker и FakeCityRegisterChecker)
    private CityRegisterChecker personChecker;

    public CityRegisterValidator(){
        //реальный объект пока создается только тут, но есть механизмы замены, их рассмотрим позже
        personChecker = new FakeCityRegisterChecker();
    }

    //несмотря на то что Adult и Child разные классы, они оба наследники Person, и для них можно вызвать checkPerson это пример полиморфизма
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        try {
            CityRegisterCheckerResponse hans = personChecker.checkPerson(so.getHusband()); //hans - husband answer
            CityRegisterCheckerResponse wans = personChecker.checkPerson(so.getWife()); //wans - wife answer

//вариант обхода списка через цикл со счетчиком
            for (int i = 0; i < so.getChildren().size(); i++){
                CityRegisterCheckerResponse cans = personChecker.checkPerson(so.getChildren().get(i)); //cans - child answer
            }
//вариант обхода списка - через итератор (это специалный объект, коорый при инициализации встает в начало списка
            for (Iterator<Child> it = so.getChildren().iterator(); it.hasNext();){
                Child child = it.next();
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
            }
//вариант цикла for each
            for (Child child : so.getChildren()){
                CityRegisterCheckerResponse cans = personChecker.checkPerson(child);
            }

        } catch (CityRegisterException ex) {
            ex.printStackTrace(System.out);
        }

        AnswerCityRegister ans = new AnswerCityRegister();
        return ans;
    }
}
