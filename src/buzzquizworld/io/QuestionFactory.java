package buzzquizworld.io;

import buzzquizworld.model.category.Category;
import buzzquizworld.model.question.Answer;
import buzzquizworld.model.question.ImageQuestion;
import buzzquizworld.model.question.Question;
import buzzquizworld.model.question.TextQuestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * QuestionFactory class represents a factory that creates/loads questions
 * @author Dimitris
 */
public class QuestionFactory {

    /**
     *
     * @param filePath the path to the file from which the questions will be loaded
     * @return ArrayList of all the questions contained in the file
     * @throws IOException exception from file
     */
    public ArrayList<Question> loadQuestionsFromFile(String filePath) throws IOException {

        ArrayList<Question> questions = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();

        String line;
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        while ((line = csvReader.readLine()) != null) {
            String[] data = line.split(",");

            Category category = Category.values()[0];
            for (Category c : Category.values())
                if (c.toString().equals(data[0]))
                    category = c;

            String question = data[1];

            for (int i = 2; i <= 5; i++)
                answers.add(new Answer(data[i]));

            int correctAnswerIndex = 0;
            try{
                correctAnswerIndex = Integer.parseInt(data[6]);
            }catch (Exception ignored){ }

            Question q;
            if (data.length == 8){
                String imgPath = data[7];
                q = new ImageQuestion(category, question,new ArrayList<>(answers),correctAnswerIndex,imgPath);
            }
            else
                q = new TextQuestion(category, question,new ArrayList<>(answers),correctAnswerIndex);
            questions.add(q);
            answers.clear();
        }
        csvReader.close();

        return questions;
    }

}
