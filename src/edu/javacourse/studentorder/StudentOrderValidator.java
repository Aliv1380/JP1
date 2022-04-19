package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;
import mail.MailSander;

//стартовый класс
public class StudentOrderValidator {

    //т.к. СтудентОрдерВалидатор без самих валидаторов не нужен, прописываем инициализацию его переменных и методов
    //при поможи создания именованного конструктора
    private CityRegisterValidator cityRegisterVal;
    private WeddingValidator weddingVal;
    private ChildrenValidator childrenVal;
    private StudentValidator studentVal;
    private MailSander mailSander;

    //конструктор
    public StudentOrderValidator(){
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new WeddingValidator();
        childrenVal = new ChildrenValidator();
        studentVal = new StudentValidator();
        mailSander = new MailSander();
    }

    /*
    функция МЭЙН  - начало начал
     */
    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();
    }

    public void checkAll(){
        StudentOrder[] soArray = readStudentOrders();
//        for (int c = 0; c< soArray.length; c++){
//            System.out.println();
//            checkOneOrder(soArray[c]);
//        }
//        //Вариант аналогичной записи с циклом типа for each
//        System.out.println();
//        System.out.println("Вариант аналогичной записи с циклом типа for each");
        for (StudentOrder so : soArray){
            checkOneOrder(so);
        }
    }

    public StudentOrder[] readStudentOrders(){
        StudentOrder[] soArray = new StudentOrder[3];
        for (int c=0; c<soArray.length; c++){
            soArray[c] = SaveStudentOrder.buildStudentOrder(c);
        }
        return soArray;
    }

    public void checkOneOrder(StudentOrder so){
        AnswerCityRegister cityAnswer = checkCityRegister(so);
//        AnswerWedding wedAnswer = checkWedding(so);
//        AnswerChildren childAnswer = checkChildren(so);
//        AnswerStudent studentAnswer = checkStudent(so);
//        sendMail(so);
    }

/* checkCityRegisret - Городской реестр населения - это сервис который принимает данные
о персоне ФИО, и Адрес, и возвращает ответ:
1.- да или нет (зарегистрирован такой челове ко данному адресу или нет);
2.- постоянная регистрация или временная */
    public AnswerCityRegister checkCityRegister(StudentOrder so){
        return cityRegisterVal.checkCityRegister(so);
    }
    public AnswerWedding checkWedding(StudentOrder so){
        return weddingVal.checkWedding(so);
    }
    public AnswerChildren checkChildren(StudentOrder so){
        return childrenVal.checkChildren(so);
    }
    public AnswerStudent checkStudent(StudentOrder so){
        return studentVal.checkStudent(so);
    }

    public void sendMail (StudentOrder so){
        mailSander.sendMail(so);
    }
}
