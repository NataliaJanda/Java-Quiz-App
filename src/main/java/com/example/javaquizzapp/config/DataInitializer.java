package com.example.javaquizzapp.config;

import com.example.javaquizzapp.entity.Answer;
import com.example.javaquizzapp.entity.Question;
import com.example.javaquizzapp.entity.Student;
import com.example.javaquizzapp.repository.QuestionRepository;
import com.example.javaquizzapp.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository, StudentRepository studentRepository) {
        return args -> {
            Question question1 = (new Question(null, "W wyniku kompilacji kodu źródłowego Java otrzymano między innymi pliki Class$1.class, Class$2.class. " + "Dlaczego w nazwach plików klasowych po znaku $ są liczby 1 i 2:"));
            Answer answer11 = new Answer(null, "Ponieważ klasa zawierała dwie klasy wewnętrzne o nazwach 1 i 2 ", true, question1);
            Answer answer12 = new Answer(null, "Ponieważ klasa zawierała dwie anonimowe klasy wewnętrzne", false, question1);
            Answer answer13 = new Answer(null, "Ponieważ klasa zawierała dwie nazwane klasy abstrakcyjne", false, question1);
            Answer answer14 = new Answer(null, "Ponieważ klasa zawierała dwie nazwane klasy adaptacyjne", false, question1);
            question1.setAnswers(List.of(answer11, answer12, answer13, answer14));
            Question question2 = (new Question(null, "W języku Java rozkład komponentów (przycisków) dla biblioteki Swing w oknie aplikacji " + "jak na rysunku niżej realizuje klasa:"));
            Answer answer21 = new Answer(null, "FlowLayout", true, question2);
            Answer answer22 = new Answer(null, "GridLayout", false, question2);
            Answer answer23 = new Answer(null, "BorderLayout", false, question2);
            Answer answer24 = new Answer(null, "BoxLayout", false, question2);
            question2.setAnswers(List.of(answer21, answer22, answer23, answer24));
            Question question3 = (new Question(null, "Wykonanie poniższego programu spowoduje wyświetlenie:"));
            Answer answer31 = new Answer(null, "List:1List:2List:3" + "List:4List:5List:6", true, question3);
            Answer answer32 = new Answer(null, "List:123456", false, question3);
            Answer answer33 = new Answer(null, "error", false, question3);
            Answer answer34 = new Answer(null, " List:{123456}", false, question3);
            question3.setAnswers(List.of(answer31, answer32, answer33, answer34));
            Question question4 = (new Question(null, "W języku Java w klasie serwletów javax.servlet.http.HttpServlet " + "za obsługę żądań typu GET odpowiada metoda:"));
            Answer answer41 = new Answer(null, "processRequest()", true, question4);
            Answer answer42 = new Answer(null, "doGet()", false, question4);
            Answer answer43 = new Answer(null, "doPost()", false, question4);
            Answer answer44 = new Answer(null, "Język Java nie obsługuje żądań typu GET", false, question4);
            question4.setAnswers(List.of(answer41, answer42, answer43, answer44));
            Question question5 = (new Question(null, "Wykonanie poniższego kodu języka Java powoduje wyświetlenie na konsoli:"));
            Answer answer51 = new Answer(null, "AC", true, question5);
            Answer answer52 = new Answer(null, "BC", false, question5);
            Answer answer53 = new Answer(null, "BD", false, question5);
            Answer answer54 = new Answer(null, "AD", false, question5);
            question5.setAnswers(List.of(answer51, answer52, answer53, answer54));
            Question question6 = (new Question(null, "Która składnia dostępu do kolekcji ArrayList<String> myList = new ArrayList<String>(); " + "za pomocą pętli for jest poprawna:"));
            Answer answer61 = new Answer(null, "for (int i = 0; i < myList.size(); i++) { System.out.println(myList(i)); }", true, question6);
            Answer answer62 = new Answer(null, "for (String s : myList) { System.out.println(s); }", false, question6);
            Answer answer63 = new Answer(null, "for (int s : myList) { System.out.println(s); }", false, question6);
            Answer answer64 = new Answer(null, "for (int s = 0) { System.out.println(s); }", false, question6);
            question6.setAnswers(List.of(answer61, answer62, answer63, answer64));
            Question question7 = (new Question(null, "Wykonanie poniższego programu spowoduje wyświetlenie: "));
            Answer answer71 = new Answer(null, "List:1List:2List:3" + "List:4List:5List:6", true, question7);
            Answer answer72 = new Answer(null, "List:123456", false, question7);
            Answer answer73 = new Answer(null, "error", false, question7);
            Answer answer74 = new Answer(null, "List:{123456}", false, question7);
            question7.setAnswers(List.of(answer71, answer72, answer73, answer74));
            Question question8 = (new Question(null, "Wykonanie poniższego programu spowoduje wyświetlenie:"));
            Answer answer81 = new Answer(null, "123", true, question8);
            Answer answer82 = new Answer(null, "Programu nie można uruchomić, ponieważ nie da się skompilować", false, question8);
            Answer answer83 = new Answer(null, "123.0", false, question8);
            Answer answer84 = new Answer(null, "Program da się skompilować, a przy wykonaniu zgłosi wyjątek", false, question8);
            question8.setAnswers(List.of(answer81, answer82, answer83, answer84));
            Question question9 = (new Question(null, "Które zapisy referencji do metody i wyrażenia Lambda są równoważne ?"));
            Answer answer91 = new Answer(null, "System.out::println" + "x->System.out.println()", true, question9);
            Answer answer92 = new Answer(null, "JButton::new" + "() -> new JButton()", false, question9);
            Answer answer93 = new Answer(null, "int[]::new" + "x->new int[x]", false, question9);
            Answer answer94 = new Answer(null, "String::valueOf" +"x -> String.valueOf(x)", false, question9);
            question9.setAnswers(List.of(answer91, answer92, answer93, answer94));
            Question question10 = (new Question(null, "Głównym elementem pliku AndroidManifest.xml jest ?"));
            Answer answer101 = new Answer(null, "activity", true, question10);
            Answer answer102 = new Answer(null, "manifest", false, question10);
            Answer answer103 = new Answer(null, "application", false, question10);
            Answer answer104 = new Answer(null, "android", false, question10);
            question10.setAnswers(List.of(answer101, answer102, answer103, answer104));

            studentRepository.save(new Student(1L,"169548","Natalia", "Janda","root"));

            questionRepository.save(question1);
            questionRepository.save(question2);
            questionRepository.save(question3);
            questionRepository.save(question4);
            questionRepository.save(question5);
            questionRepository.save(question6);
            questionRepository.save(question7);
            questionRepository.save(question8);
            questionRepository.save(question9);
            questionRepository.save(question10);


        };
    }
}
