package buzzquizworld.model.question;

import buzzquizworld.model.category.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

abstract class QuestionTest {

    abstract Question getObject();

    @Test
    void getQuestion() {
        Question q = getObject();
        assertEquals("question",q.getQuestion());
    }

    @Test
    void getCorrectAnswer() {
        Question q = getObject();
        assertEquals(q.getCorrectAnswer().getAnswer(), "answer 2 - correct");
    }

    @Test
    void getCorrectAnswerIndex() {
        Question q = getObject();
        assertEquals(1, q.getCorrectAnswerIndex());
    }

    @Test
    void getAllAnswers() {
        Question q = getObject();
        ArrayList<Answer> answers = new ArrayList<>();
        answers.add(new Answer("answer 1"));
        answers.add(new Answer("answer 2 - correct"));
        answers.add(new Answer("answer 3"));
        answers.add(new Answer("answer 4"));

        assertNotNull(q.getAllAnswers());

        for (int i = 0; i < 4; i ++)
            assertEquals(answers.get(i).getAnswer(), q.getAllAnswers().get(i).getAnswer());
    }

    @Test
    void getCategory() {
        Question q = getObject();
        assertEquals(Category.GEOGRAPHY, q.getCategory());
    }

    @Test
    void shuffleAnswers() {
        Question q = getObject();

        ArrayList<Answer> beforeShuffleList = q.getAllAnswers();
        int beforeShuffleIndex = q.getCorrectAnswerIndex();
        String beforeShuffleCorrectAnswer = beforeShuffleList.get(beforeShuffleIndex).getAnswer();

        q.shuffleAnswers();
        ArrayList<Answer> afterShuffleList = q.getAllAnswers();
        int afterShuffleIndex = q.getCorrectAnswerIndex();
        String afterShuffleCorrectAnswer = afterShuffleList.get(afterShuffleIndex).getAnswer();

        assertEquals(beforeShuffleCorrectAnswer, afterShuffleCorrectAnswer);
    }
}