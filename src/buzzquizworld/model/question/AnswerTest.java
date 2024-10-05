package buzzquizworld.model.question;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void getAnswer() {
        Answer answer = new Answer("Answer blabla");
        assertEquals("Answer blabla", answer.getAnswer());
    }
}