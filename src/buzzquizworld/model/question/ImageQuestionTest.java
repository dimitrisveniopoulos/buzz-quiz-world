package buzzquizworld.model.question;

import buzzquizworld.model.category.Category;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ImageQuestionTest extends  QuestionTest{

    @Test
    void getPath() {
        ArrayList<Answer> ans = new ArrayList<>();
        String path = "This is the path to image";
        ImageQuestion q = new ImageQuestion(Category.GEOGRAPHY,"question", ans,0,path);
        assertEquals(path, q.getPath());
    }

    @Override
    Question getObject() {
        ArrayList<Answer> ans = new ArrayList<>();
        ans.add(new Answer("answer 1"));
        ans.add(new Answer("answer 2 - correct"));
        ans.add(new Answer("answer 3"));
        ans.add(new Answer("answer 4"));
        String path = "This is the path to image";
        return new ImageQuestion(Category.GEOGRAPHY, "question",ans,1,path);
    }
}