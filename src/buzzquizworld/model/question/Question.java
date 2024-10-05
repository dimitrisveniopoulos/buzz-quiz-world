package buzzquizworld.model.question;


import buzzquizworld.model.category.Category;

import java.util.ArrayList;
import java.util.Random;

/**
 * Question is an abstract class that represents a question.
 * Contains fields for the question's category, the question's name and the possible answers.
 * @author Chris
 */
public abstract class Question {
    private Category category;
    private String question;
    private ArrayList<Answer> answerList;
    private int correctAnswerIndex;

    /**
     * Constructor that creates an instance of a Question object.
     * Fields are set to the respective parameter values.
     * @param category the category of the question
     * @param question the name of the question
     * @param answerList the list with all the answers to the question
     * @param correctAnswer the index that points to the correct question from the answerList
     */
    Question(Category category, String question, ArrayList<Answer> answerList, int correctAnswer){
        this.category = category;
        this.question = question;
        this.answerList = answerList;
        this.correctAnswerIndex = correctAnswer;
    }

    /**
     * @return question title
     */
    public String getQuestion() { return question; }

    /**
     * @return correct answer
     */
    public Answer getCorrectAnswer() { return answerList.get(correctAnswerIndex); }

    /**
     * @return index that points to the correct answer within the answer list.
     */
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }

    /**
     * @return all the answers to the question
     */
    public ArrayList<Answer> getAllAnswers() { return answerList; }

    /**
     * @return question's category
     */
    public Category getCategory() { return category; }

    /**
     * Method that shuffles the answers in the answer list
     */
    public void shuffleAnswers() {
        int numberOfAnswers = answerList.size();
        Answer correctAnswer = answerList.get(correctAnswerIndex);
        ArrayList<Answer> newAnswerList = new ArrayList<>();
        Random random = new Random();
        int i = 0;
        while (i < numberOfAnswers) {
            int randomIndex = random.nextInt(numberOfAnswers);
            if (!newAnswerList.contains(answerList.get(randomIndex))) {
                newAnswerList.add(answerList.get(randomIndex));
                i++;
            }
        }
        answerList = newAnswerList;
        correctAnswerIndex = answerList.indexOf(correctAnswer);
    }

}
