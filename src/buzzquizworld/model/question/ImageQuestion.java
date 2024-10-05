package buzzquizworld.model.question;

import buzzquizworld.model.category.Category;

import java.util.ArrayList;

public class ImageQuestion extends Question {

    private String path;
    /**
     * Constructor that creates an instance of an ImageQuestion object.
     * Fields are set to the respective parameter values.
     *
     * @param category      the category of the question
     * @param question      the name of the question
     * @param answerList    the list with all the answers to the question
     * @param correctAnswer the index that points to the correct question from the answerList
     * @param path          the path to the image
     */
    public ImageQuestion(Category category, String question, ArrayList<Answer> answerList, int correctAnswer, String path) {
        super(category, question, answerList, correctAnswer);
        this.path = path;
    }

    public String getPath() { return path; }
}
