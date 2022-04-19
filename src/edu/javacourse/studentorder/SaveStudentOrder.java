package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.Address;
import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.StudentOrder;

import java.time.LocalDate;

public class SaveStudentOrder
{
    public static void main(String[] args) {

//        StudentOrder so = new StudentOrder();
//        long ans = saveStudentOrder(so);
//        System.out.println(ans);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 100;
        System.out.println("отвтет studentOrder" );
        return answer;
    }

    //Имитация создания студенческой заявки
    public static StudentOrder buildStudentOrder(long id){
        StudentOrder so=new StudentOrder();

        so.setStudentOrderId(id);
        so.setMarriageCertificateId(""+(1000+id));
        so.setMarriageDate(LocalDate.of(2017,7,4));
        so.setMarriageOffice("Отдкл ЗАГС");

        Address address = new Address("12121212","Дубравная","30","б","26");

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

        Child child = new Child("Сергей","Иванов","Алексеевич",LocalDate.of(2011,3,3));
        child.setCertificateNumber(""+(300000+id));
        child.setIssueDate(LocalDate.of(2011,4,1));
        child.setIssueDepartment("Отдел ЗАГС №"+id);
        child.setAddress(address);

        so.setHusband(husband);
        so.setWife(wife);
        so.setChild(child);


        return so;
    }



}
