package buzzquizworld.io;

import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionFactoryTest {

    @Test
    void loadQuestionsFromFile() throws IOException {
        QuestionFactory qf = new QuestionFactory();
        ArrayList<Question> questions;
        questions = qf.loadQuestionsFromFile("questionsFile.csv");
        assertNotNull(questions);
    }
}