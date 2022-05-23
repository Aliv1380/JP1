
//создал класс для автоматической генерации данных для SQL таблиц но не доделал пока.

public class StudentProjectTest {
    public static void main(String[] args) {
        String a="";
        for (int c=1; c<8; c++){
            a = a + "("+c+",  010000)";
        }
        System.out.println(a);
    }
}
