package edu.javacourse.studentorder;

import edu.javacourse.studentorder.dao.DictionaryDaoImpl;
import edu.javacourse.studentorder.domain.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class SaveStudentOrder
{
    public static void main(String[] args) throws Exception {
        List<Street> d = new DictionaryDaoImpl().findStreets("sec");
        for (Street s:d ){
            System.out.println(s.getStreetName());
        }


//        StudentOrder so = new StudentOrder();
//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 100;
        System.out.println("отвтет studentOrder" );
        return answer;
    }

    //Имитация создания студенческой заявки, содержит данные студ.семьи
    public static StudentOrder buildStudentOrder(long id){
        StudentOrder so=new StudentOrder();

        so.setStudentOrderId(id);
        so.setMarriageCertificateId(""+(1000+id));
        so.setMarriageDate(LocalDate.of(2017,7,4));
        so.setMarriageOffice("Отдкл ЗАГС");


        Street street = new Street(1L, "first street"); //запись 1L означает 1 - число и L - long - тип)
        Address address = new Address("12121212",street,"30","б","26");

        Adult husband = new Adult("Алексей","Иванов","Степанович",LocalDate.of(1980,4,18));
        husband.setPassportSeria(""+(1000+id));
        husband.setPassportNumber(""+(100000+id));
        husband.setIssueDate(LocalDate.of(2017,3,11));
        husband.setIssueDepartment("Отдел Милиции");
        husband.setStudentID(""+(100000+id));
        husband.setAddress(address);

        Adult wife = new Adult("Светлана","Иванова","Сергеевна",LocalDate.of(1984,5,22));
        wife.setPassportSeria(""+(2000+id));
        wife.setPassportNumber(""+(200000+id));
        wife.setIssueDate(LocalDate.of(2015,2,3));
        wife.setIssueDepartment("Отдел Милиции");
        wife.setStudentID(""+(200000+id));
        wife.setAddress(address);

        Child child1 = new Child("Сергей","Иванов","Алексеевич",LocalDate.of(2011,3,3));
        child1.setCertificateNumber(""+(300000+id));
        child1.setIssueDate(LocalDate.of(2011,4,1));
        child1.setIssueDepartment("Отдел ЗАГС №"+id);
        child1.setAddress(address);

        Child child2 = new Child("Нина","Иванова","Алексеевна",LocalDate.of(2011,3,3));
        child2.setCertificateNumber(""+(400000+id));
        child2.setIssueDate(LocalDate.of(2011,4,1));
        child2.setIssueDepartment("Отдел ЗАГС №"+id);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);


        return so;
    }



}
