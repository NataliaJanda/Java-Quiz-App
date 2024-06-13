package com.example.javaquizzapp.config;

import com.example.javaquizzapp.entity.Answer;
import com.example.javaquizzapp.entity.Question;
import com.example.javaquizzapp.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(QuestionRepository questionRepository) {
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

//            questionRepository.save(new Question(null, "Która składnia dostępu do kolekcji ArrayList<String> myList = new ArrayList<String>(); " + "za pomocą pętli for jest poprawna:"));
//            questionRepository.save(new Question(null, "Wykonanie poniższego programu spowoduje wyświetlenie: "));
//            questionRepository.save(new Question(null, "Które zapisy referencji do metody i wyrażenia Lambda są równoważne ?"));
//            questionRepository.save(new Question(null, "Wykonanie poniższego programu spowoduje wyświetlenie:"));

            questionRepository.save(question1);
            questionRepository.save(question2);
            questionRepository.save(question3);
            questionRepository.save(question4);
            questionRepository.save(question5);

        };
    }
}
