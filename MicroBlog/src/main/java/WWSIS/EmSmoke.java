package WWSIS;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmSmoke {
    public static void main(String[] args) {
        // UWAGA: tu ładujemy webowe XML, więc trzeba wskazać ścieżkę jako zasób
        // Najprościej: skopiuj rootApplicationContext.xml do src/main/resources na chwilę,
        // albo odpal z testu JUnit (poniżej prościej).
        System.out.println("OK");
    }
}
