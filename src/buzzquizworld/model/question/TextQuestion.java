package buzzquizworld.model.question;


import buzzquizworld.model.category.Category;

import java.util.ArrayList;

/**
 * TextQuestion class represents a text question that extends Question class
 * @author Chris
 */
public class TextQuestion extends Question {

    /**
     * Constructor that creates an instance of a TextQuestion object.
     * Fields are set to the respective parameter values.
     * @param category the category of the question
     * @param question the name of the question
     * @param answerList the list with all the answers to the question
     * @param correctAnswer the index that points to the correct question from the answerList
     */
    public TextQuestion(Category category, String question, ArrayList<Answer> answerList, int correctAnswer) {
        super(category, question, answerList, correctAnswer);
    }
}
