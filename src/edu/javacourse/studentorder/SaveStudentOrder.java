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
        List<Street> d = new DictionaryDaoImpl().findStreets("str");
        for (Street s:d ){
            System.out.println(s.getStreetName());
        }

        List<PassportOffice> po = new DictionaryDaoImpl().findPassportOffices("010020000000");
        for (PassportOffice p:po ){
            System.out.println(p.getOfficeName());
        }
        List<RegisterOffice> ro = new DictionaryDaoImpl().findRegisterOffices("010010000000");
        for (RegisterOffice r:ro ){
            System.out.println(r.getGetOfficeName());
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

        RegisterOffice ro = new RegisterOffice(1L, "","");
        so.setMarriageOffice(ro);


        Street street = new Street(1L, "first street"); //запись 1L означает 1 - число и L - long - тип)
        Address address = new Address("12121212",street,"30","б","26");

        Adult husband = new Adult("Алексей","Иванов","Степанович",LocalDate.of(1980,4,18));
        husband.setPassportSeria(""+(1000+id));
        husband.setPassportNumber(""+(100000+id));
        husband.setIssueDate(LocalDate.of(2017,3,11));

        PassportOffice po1=new PassportOffice(1L,"","");
        husband.setIssueDepartment(po1);
        husband.setStudentID(""+(100000+id));
        husband.setAddress(address);

        Adult wife = new Adult("Светлана","Иванова","Сергеевна",LocalDate.of(1984,5,22));
        wife.setPassportSeria(""+(2000+id));
        wife.setPassportNumber(""+(200000+id));
        wife.setIssueDate(LocalDate.of(2015,2,3));

        PassportOffice po2=new PassportOffice(2L,"","");
        wife.setIssueDepartment(po2);
        wife.setStudentID(""+(200000+id));
        wife.setAddress(address);

        Child child1 = new Child("Сергей","Иванов","Алексеевич",LocalDate.of(2011,3,3));
        child1.setCertificateNumber(""+(300000+id));
        child1.setIssueDate(LocalDate.of(2011,4,1));

        RegisterOffice ro2 = new RegisterOffice(2L, "","");
        child1.setIssueDepartment(ro2);
        child1.setAddress(address);

        Child child2 = new Child("Нина","Иванова","Алексеевна",LocalDate.of(2011,3,3));
        child2.setCertificateNumber(""+(400000+id));
        child2.setIssueDate(LocalDate.of(2011,4,1));

        RegisterOffice ro3 = new RegisterOffice(3l,"","");
        child2.setIssueDepartment(ro3);
        child2.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.addChild(child1);
        so.addChild(child2);


        return so;
    }



}
