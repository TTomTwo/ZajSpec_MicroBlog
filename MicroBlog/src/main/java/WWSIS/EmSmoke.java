package WWSIS;

import org.springframework.context.support.ClassPathXmlApplicationContext;  // Importujemy klasę, która umożliwia załadowanie kontekstu aplikacji Spring z pliku XML.

public class EmSmoke {
    public static void main(String[] args) {
        // UWAGA: tu ładujemy webowe XML, więc trzeba wskazać ścieżkę jako zasób
        // Powyższy komentarz przypomina, że w przypadku aplikacji webowej musimy podać poprawną ścieżkę do pliku konfiguracyjnego.

        // Najprościej: skopiuj rootApplicationContext.xml do src/main/resources na chwilę,
        // albo odpal z testu JUnit (poniżej prościej).

        // System.out.println("OK"); – To jest tylko miejsce na testowanie, możemy wyświetlić coś na konsoli, aby sprawdzić, że aplikacja działa.

        // Przykład załadowania kontekstu Springa:
        // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("rootApplicationContext.xml");
        // context.close();  // Zawsze pamiętaj o zamknięciu kontekstu po zakończeniu jego używania, aby zasoby zostały zwolnione.

        System.out.println("OK");  // Ta linia jest testem, który po prostu wypisuje "OK" na konsolę.
    }
}