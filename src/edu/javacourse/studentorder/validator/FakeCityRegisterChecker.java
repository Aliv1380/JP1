package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.domain.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exception.CityRegisterException;
import edu.javacourse.studentorder.exception.TransportException;

//это заглушка когда не нужно использовать RealCityRegisterChecker
public class FakeCityRegisterChecker implements CityRegisterChecker{
    //просто сравнивать значения объектов с каким то значением в тексте кода - дурной тон.
    //Для сранвнеия с какими либо значениями используют константы
    private static final String GOOD1 = "1000";
    public static final String GOOD2 = "2000";
    public static final String BAD1 = "1001";
    public static final String BAD2 = "2001";
    public static final String ERROR1 = "1002";
    public static final String ERROR2 = "2002";

    public static final String ERROR_T1 = "1003";
    public static final String ERROR_T2 = "2003";

    public CityRegisterResponse checkPerson (Person person)  throws CityRegisterException, TransportException {

        //сюда в качестве принимаемого объекта person могут передаваться объекты типов Adult и Child. Проверить предположение,
        //что передали что то из этого, можно с помощью instanceof
        CityRegisterResponse res = new CityRegisterResponse();
        if(person instanceof Adult){
            //если мы понимаем что этот объект Person является на самом деле Adult, томожем привести его к типу Adult
            Adult t = (Adult) person;

            //теперь мы сравниваем серия пасрота =1000 или 2000.  Если да, то возвращаем положительный ответ.
            //для сравнения строки и ссылки на значение объекта используется метот equals
            String ps=t.getPassportSeria();
            if (ps.equals(GOOD1)||ps.equals(GOOD2)){
                res.setExisting(true);
                res.setTemporal(false);
            }
            if (ps.equals(BAD1)||ps.equals(BAD2)){
                res.setExisting(false);
            }
            if (ps.equals(ERROR1)||ps.equals(ERROR2)){
                CityRegisterException ex =
                        new CityRegisterException("1", "GRN ERROR "+ps);
                throw ex;
            }
            if (ps.equals(ERROR_T1)||ps.equals(ERROR_T2)){
                TransportException ex =
                        new TransportException("Transport ERROR "+ps);
                throw ex;
            }
        }

        if(person instanceof Child){
            res.setExisting(true);
            res.setTemporal(true);
        }
        System.out.println(res);

        return res;
    }
}
