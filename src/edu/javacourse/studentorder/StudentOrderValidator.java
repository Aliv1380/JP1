package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.domain.register.AnswerCityRegister;
import edu.javacourse.studentorder.validator.ChildrenValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;
import edu.javacourse.studentorder.validator.StudentValidator;
import edu.javacourse.studentorder.validator.WeddingValidator;
import mail.MailSander;

import java.util.LinkedList;
import java.util.List;

//стартовый класс. В его функции main запускается проверка всего checkAll.
//checkAll (здесь).  Считывает функцикей readStudentOrders массив заявок и для каждой запускает checkOneOrder
//readStudentOrders = создает массив и в каждый элемент массива записывает студенческие заявки с помощью SaveStudentOrder.buildStudentOrder (это другой класс - SaveStudentOrder)
//checkOneOrder (здесь). - запускает checkCityRegister

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
        List<StudentOrder> soList = readStudentOrders();
//Вариант записи с циклом типа for each
        for (StudentOrder so : soList){
            checkOneOrder(so);
        }
    }

    public List<StudentOrder> readStudentOrders(){
        List<StudentOrder> soList = new LinkedList<>();
        for (int c=0; c<5; c++){
            StudentOrder so = SaveStudentOrder.buildStudentOrder(c);
            soList.add(so);
        }
        return soList;
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
