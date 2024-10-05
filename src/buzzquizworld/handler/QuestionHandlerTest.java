package buzzquizworld.handler;

import buzzquizworld.model.category.Category;
import buzzquizworld.model.question.Question;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class QuestionHandlerTest {

    @Test
    void getQuestionsByCategory() {
        QuestionHandler qh = new QuestionHandler();
        ArrayList<Question> questions = qh.getQuestionsByCategory(Category.MUSIC);
        for (Question q : questions)
            assertEquals(q.getCategory(), Category.MUSIC);
    }

    @Test
    void pickRandomQuestion() {
        QuestionHandler qh = new QuestionHandler();
        Question q = qh.pickRandomQuestion();
        assertNotNull(q);
    }
}