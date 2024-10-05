package buzzquizworld.handler;

import buzzquizworld.io.QuestionFactory;
import buzzquizworld.model.category.Category;
import buzzquizworld.model.question.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * QustionHandler class is used to access and handle all the game's questions.
 * Contains an ArrayList with all the questions, a HashMap with all the questions mapped to their respective category
 * and an ArrayList with the index of every question that has already been asked.
 * @author Chris
 */
public class QuestionHandler {

    private ArrayList<Question> allQuestions;
    private HashMap<Category, ArrayList<Question>> questionsByCategory;
    private ArrayList<Integer> questionsAsked = new ArrayList<>();

    /**
     * Constructor that creates an instance of a QuestionHandler object.
     */
    public QuestionHandler() {
        allQuestions = new ArrayList<>();
        try{
            allQuestions = new QuestionFactory().loadQuestionsFromFile("questionsFile.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionsByCategory = new HashMap<>();
        categorizeQuestions();
    }

    /**
     * Getter for all the questions of a particular category.
     * @param type the type of the category
     * @return ArrayList of Question objects that includes all the question of the given category.
     */
    public ArrayList<Question> getQuestionsByCategory(Category type){ return  questionsByCategory.get(type); }

    /**
     * Method that categorizes all the questions.
     */
    private void categorizeQuestions(){

        for(Category type : Category.values())
            questionsByCategory.put(type, new ArrayList<>());

        for (Question question : allQuestions){
            Category type = question.getCategory();
            if (!questionsByCategory.get(type).contains(question))
                questionsByCategory.get(type).add(question);
        }
    }

    /**
     * Method that selects a random question from the question list, that has not already been asked.
     * @return random Question object
     */
    public Question pickRandomQuestion(){
        if (questionsAsked.size() == allQuestions.size())
            return null;

        Random random = new Random();
        int index;

        do
            index = random.nextInt(allQuestions.size());
        while(questionsAsked.contains(index));

        questionsAsked.add(index);
        Question randomQuestion = allQuestions.get(index);
        randomQuestion.shuffleAnswers();
        return randomQuestion;
    }

}


