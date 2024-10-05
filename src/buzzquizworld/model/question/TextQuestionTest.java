package buzzquizworld.model.question;

import buzzquizworld.model.category.Category;

import java.util.ArrayList;


class TextQuestionTest extends QuestionTest{

    @Override
    Question getObject() {
        ArrayList<Answer> ans = new ArrayList<>();
        ans.add(new Answer("answer 1"));
        ans.add(new Answer("answer 2 - correct"));
        ans.add(new Answer("answer 3"));
        ans.add(new Answer("answer 4"));
        return new TextQuestion(Category.GEOGRAPHY, "question",ans,1);
    }
}