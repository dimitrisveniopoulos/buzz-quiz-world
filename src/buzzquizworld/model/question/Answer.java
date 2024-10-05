package buzzquizworld.model.question;

/**
 * Answer class represents an answer to a question
 * @author Chris
 */
public class Answer {
    private String answer;

    /**
     * Contructor that creates an instance of an Answer object.
     * @param answer the answer as a String
     */
    public Answer(String answer) { this.answer = answer; }

    /**
     * @return answer as String
     */
    public String getAnswer() {
        return answer;
    }

}
