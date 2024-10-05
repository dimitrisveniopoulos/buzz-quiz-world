package buzzquizworld.main;

import buzzquizworld.model.game.Game;
import buzzquizworld.model.player.Player;
import buzzquizworld.model.question.Answer;
import buzzquizworld.model.question.ImageQuestion;
import buzzquizworld.model.question.Question;
import buzzquizworld.model.round.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * GUIGameHandler is a class that handles the graphical user interface of the quiz
 * @author Dimitris
 */
public class GUIGameHandler {

    private final JFrame frame;
    private final MyJPanel panel;
    private final JLabel roundName;
    private final JLabel roundRules;
    private final JLabel scoreboard;
    private final JLabel categoryLabel;
    private final JLabel questionLabel;
    private final JLabel correctAnswerLabel;
    private ArrayList<JLabel> playerScores;
    private final JButton continueButton;
    private final ArrayList<JButton> answerButtons;
    private Game game;
    private int numberOfPlayers;

    /**
     * Constructor that creates an instance of a GUIGameHandler object
     */
    GUIGameHandler(){

        frame = new JFrame("Buzz Quizworld");
        frame.setSize(1200,1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new MyJPanel();
        frame.add(panel);

        roundName = createCustomLabel("",25,0,0,0,0);
        roundRules = createCustomLabel("",25,0,0,0,0);
        scoreboard = createCustomLabel("Scoreboard:",20,520,150,200,50);
        categoryLabel = createCustomLabel("",20,20,450,200,50);
        questionLabel = createCustomLabel("",20,20, 500,1000,50);
        correctAnswerLabel = createCustomLabel("", 20,20,550, 700,50);
        continueButton = createCustomButton("",20,420,700,300,50);
        continueButton.addActionListener(e -> playRound());
        answerButtons = new ArrayList<>();
        for (int i = 0 ; i < 4 ; i++){
            answerButtons.add(new JButton(""))  ;
            answerButtons.get(i).setFont(new Font("MS Sans Serif", Font.PLAIN, 20));
        }

        answerButtons.get(0).setBounds(20, 550, 300, 50);
        answerButtons.get(1).setBounds(370, 550, 300, 50);
        answerButtons.get(2).setBounds(20, 620, 300, 50);
        answerButtons.get(3).setBounds(370, 620, 300, 50);

        mainScreen();
    }

    /**
     * MyJPanel is a class that represents a modified JPanel, that contains two buffered images
     * MyJPanel class extends JPanel class
     */
    static class MyJPanel extends JPanel{

        BufferedImage img1;
        boolean img1IsVisible;
        int x1;
        int y1;

        BufferedImage img2;
        boolean img2IsVisible;
        int x2;
        int y2;

        /**
         * Constructor that creates an instance of a modified JPanel
         */
        MyJPanel(){
            setLayout(null);
            setFocusable(true);
            img1IsVisible = false;
            img2IsVisible = false;
        }

        /**
         * Method that sets the first buffered image
         * @param img1 the buffered image
         * @param x x-axis value
         * @param y y-axis value
         */
        public void setImage1(BufferedImage img1, int x, int y){
            this.img1 = img1;
            this.x1 = x;
            this.y1 = y;
            this.repaint();
        }

        /**
         * Method that sets the second buffered image
         * @param img2 the buffered image
         * @param x x-axis value
         * @param y y-axis value
         */
        public void setImage2(BufferedImage img2, int x, int y) {
            this.img2 = img2;
            this.x2 = x;
            this.y2 = y;
            this.repaint();
        }

        /**
         * Method that sets the visibility value of the first image
         * @param flag the value to which the visibility of the image is set to
         */
        public void setImg1Visible(boolean flag){ img1IsVisible = flag; }

        /**
         * Method that sets the visibility value of the second image
         * @param flag the value to which the visibility of the image is set to
         */
        public void setImg2Visible(boolean flag) { img2IsVisible = flag; }

        /**
         * Method that draws the images within the panel
         * @param g graphics
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if (img1IsVisible)
                g.drawImage(img1, x1, y1, 250, 250, this);
            if (img2IsVisible)
                g.drawImage(img2, x2, y2, 250, 250, this);
        }
    }

    /**
     * Methot that creates a customised JLabel
     * @param text the text of the label
     * @param fontSize the font size of the label
     * @param x the x-axis value
     * @param y the y-axis value
     * @param width the width of the label
     * @param height the height of the label
     * @return a customised JLabel
     */
    private JLabel createCustomLabel(String text, int fontSize, int x, int y, int width, int height){
        JLabel label = new JLabel(text);
        label.setFont(new Font("MS Sans Serif", Font.PLAIN, fontSize));
        label.setBounds(x, y, width, height);
        return label;
    }

    /**
     * Methot that creates a customised JButton
     * @param text the text of the button
     * @param fontSize the font size of the button
     * @param x the x-axis value
     * @param y the y-axis value
     * @param width the width of the button
     * @param height the height of the button
     * @return a customised JButton
     */
    private JButton createCustomButton(String text, int fontSize, int x, int y, int width, int height){
        JButton button = new JButton(text);
        button.setFont(new Font("MS Sans Serif", Font.PLAIN, fontSize));
        button.setBounds(x, y, width, height);
        return button;
    }

    /**
     * Method that creates the main screen of the game
     */
    private void mainScreen(){
        panel.removeAll();
        panel.repaint();

        panel.add(createCustomLabel("Buzz Quizworld", 35, 470, 0, 300,50));

        JButton newGameButton = createCustomButton("New Game",25,450,350,300,50);
        panel.add(newGameButton);

        JButton archiveButton = createCustomButton("Show Archive",25,450,450,300,50);
        panel.add(archiveButton);

        JButton exitGameButton = createCustomButton("Exit Game",25,450,550,300,50);
        panel.add(exitGameButton);

        panel.add(createCustomLabel("Created by Chris and Dimitris", 25, 450,900,400,50));

        newGameButton.addActionListener(e -> modeSelectionScreen());

        archiveButton.addActionListener(e -> showArchive());

        exitGameButton.addActionListener(e -> System.exit(0));

        panel.revalidate();
    }

    /**
     * Method that creates the user interface for the player archive
     */
    private void showArchive(){
        ArrayList<Player> playersArchive = Game.createPlayerArchive();
        int size = playersArchive.size();

        JFrame archiveFrame = new JFrame("Player Archive");
        archiveFrame.setSize(600,500);
        archiveFrame.setLocationRelativeTo(null);
        archiveFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        archiveFrame.setVisible(true);

        JPanel container = new JPanel();
        JScrollPane scrollPane = new JScrollPane(container);
        container.setPreferredSize(new Dimension(500, (size + 1) * 50));
        container.setLayout(null);

        container.add(createCustomLabel("Name:", 20,20,0,100,50));
        container.add(createCustomLabel("High Scores:",20,220,0,150,50));
        container.add(createCustomLabel("Wins:", 20,420,0,100,50));

        //add players from archive to container
        int y = 50;
        JLabel[] playerNames = new JLabel[size];
        JLabel[] playerHighScores = new JLabel[size];
        JLabel[] playerWins = new JLabel[size];
        for (int i = 0; i < size; i ++){
            Player player = playersArchive.get(i);
            playerNames[i] = createCustomLabel(player.getName(),20,20,y,300,50);
            container.add(playerNames[i]);
            playerHighScores[i] = createCustomLabel("" + player.getHighScore(),20,220,y, 100,50);
            container.add(playerHighScores[i]);
            playerWins[i] = createCustomLabel("" + player.getWins(),20,420,y,100,50);
            container.add(playerWins[i]);
            y += 50;
        }

        archiveFrame.getContentPane().add(scrollPane);
    }

    /**
     * Method that creates the mode selection screen
     */
    private void modeSelectionScreen(){
        panel.removeAll();
        panel.repaint();

        panel.add(createCustomLabel("Select number of players",25,450,300,300,100));

        JButton solo = createCustomButton("1",25,350,400,200,50);
        panel.add(solo);

        JButton duo = createCustomButton("2",25,650,400,200,50);
        panel.add(duo);

        panel.revalidate();

        solo.addActionListener(e -> {
            numberOfPlayers = 1;
            playerInfoScreen();
        });

        duo.addActionListener(e -> {
            numberOfPlayers = 2;
            playerInfoScreen();
        });
    }

    /**
     * Method that creates the screen that gets the player information
     */
    private void playerInfoScreen(){
        panel.removeAll();
        panel.repaint();

        panel.add(createCustomLabel("Player 1 control buttons: 1 2 3 4 or Mouse",25,350,100,500,50));
        if (numberOfPlayers == 2)
            panel.add(createCustomLabel("Player 2 control buttons: 1 2 3 4 (NUMPAD)",25,350,150,500,50));

        panel.add(createCustomLabel("Enter Player Information",25,450,250,300,100));
        panel.add(createCustomLabel("If you type a name that already exists in the archive, " +
                "your progress will continue", 25, 150,300,900,100));

        JTextField[] nameFields = new JTextField[numberOfPlayers];
        JLabel[] labels = new JLabel[numberOfPlayers];

        int y = 400;
        for (int i = 0; i < numberOfPlayers; i++){
            labels[i] = createCustomLabel("Player " + (i + 1),25,450,y,300,50);
            panel.add(labels[i]);

            nameFields[i] = new JTextField();
            nameFields[i].setVisible(true);
            nameFields[i].setFont(new Font("MS Sans Serif", Font.PLAIN, 25));
            nameFields[i].setBounds(550, y,200,50);
            int finalI = i;
            nameFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (nameFields[finalI].getText().length() >= 12 ) // limit to 12 characters
                        e.consume();
                }
            });
            panel.add(nameFields[i]);

            y += 100;
        }

        JButton beginQuiz = createCustomButton("Begin Quiz",25,500,y,200,50);
        panel.add(beginQuiz);

        beginQuiz.addActionListener(e -> {
            ArrayList<Player> players = new ArrayList<>();
            for (int i = 0; i < numberOfPlayers; i++)
                players.add(new Player(nameFields[i].getText()));

            game = new Game();
            game.setPlayers(players);
            playGame();
        });

        panel.revalidate();
    }

    /**
     * Method that creates the end game screen with the final results
     */
    private void endGameScreen(){
        panel.removeAll();
        panel.repaint();

        game.endGame();

        panel.add(createCustomLabel("Thanks for playing Buzz Quizworld", 35, 320, 0, 600,50));
        panel.add(createCustomLabel("Name:", 25,250,200,200,50));
        panel.add(createCustomLabel("Final Scores:",25,450,200,200,50));
        panel.add(createCustomLabel("High Scores:",25,650,200,200,50));
        panel.add(createCustomLabel("Wins:", 25,850,200,200,50));

        int y = 250;
        for (int i = 0; i < numberOfPlayers; i ++){
            Player player = game.getPlayers().get(i);
            panel.add(createCustomLabel(player.getName(),25,250,y,300,50));
            panel.add(createCustomLabel("" + player.getScore(),25,450,y, 400,50));
            panel.add(createCustomLabel("" + player.getHighScore(),25,650,y, 400,50));
            panel.add(createCustomLabel("" + player.getWins(),25,850,y,300,50));
            y += 50;
        }

        JButton mainMenuButton = createCustomButton("Main Menu",25,450,500,300,50);
        panel.add(mainMenuButton);

        JButton exitGameButton = createCustomButton("Exit Game",25,450,600,300,50);
        panel.add(exitGameButton);

        JLabel creditsLabel = createCustomLabel("Created by Chris and Dimitris", 25, 450,900,400,50);
        panel.add(creditsLabel);

        mainMenuButton.addActionListener(e -> mainScreen());

        exitGameButton.addActionListener(e -> System.exit(0));

        panel.revalidate();
    }

    /**
     * Method that initiates the game
     */
    private void playGame(){
        panel.setImg1Visible(false);
        panel.setImg2Visible(false);
        if (game.getRoundsRemaining() > 0) {
            game.createNewRound();
            playRound();
        }
        else
            endGameScreen();
    }

    /**
     * Method that initiates the round screen
     */
    private  void playRound() {
        panel.removeAll();
        panel.repaint();
        for (KeyListener kl : panel.getKeyListeners())
            panel.removeKeyListener(kl);

        Round round = game.getRound();
        if (round.getType() == RoundType.THERMOMETER)
            thermometerRoundScreen();
        else if (round.getQuestionsRemaining() > 0)
            roundTemplate();
        else
            playGame();
    }

    /**
     * Method that creates the basic components for the round screens
     */
    private void roundTemplate(){
        panel.add(roundRules);
        panel.add(roundName);
        panel.add(scoreboard);
        panel.add(categoryLabel);
        panel.add(questionLabel);
        panel.add(correctAnswerLabel);
        panel.add(continueButton);

        playerScores = new ArrayList<>();
        int axisY = 200;
        for (Player player : game.getPlayers()){
            playerScores.add(createCustomLabel(player.getName() + " " + player.getScore(),
                    20,520,axisY,400,50));
            axisY += 50;
        }

        for (JButton answerButton: answerButtons) {
            panel.add(answerButton);
            for (ActionListener al :answerButton.getActionListeners())
                answerButton.removeActionListener(al);
        }

        for (JLabel score : playerScores)
            panel.add(score);

        Round round = game.getRound();
        round.play();

        Question question = round.getCurrentQuestion();

        ArrayList<Answer> answers = question.getAllAnswers();
        for (int i = 0; i < 4; i++) {
            answerButtons.get(i).setVisible(true);
            answerButtons.get(i).setText(answers.get(i).getAnswer());
        }

        categoryLabel.setText("Category: " + question.getCategory().toString());
        questionLabel.setText("Question: " + question.getQuestion());
        correctAnswerLabel.setText("The correct answer is " + question.getCorrectAnswer().getAnswer());
        correctAnswerLabel.setVisible(false);

        continueButton.setText("Next Question");
        if (round.getQuestionsRemaining() == 0) {
            continueButton.setText("Next Round");
            if (game.getRoundsRemaining() == 0)
                continueButton.setText("End Game");
        }
        continueButton.setVisible(false);

        switch (round.getType()) {
            case CORRECT_ANSWER:
                correctAnswerRoundScreen();
                break;
            case BETTING:
                bettingRoundScreen();
                break;
            case QUICK_ANSWER:
                quickAnswerRoundScreen();
                break;
            case STOP_THE_TIMER:
                stopTheTimerRoundScreen();
                break;
        }

        panel.revalidate();
        panel.requestFocusInWindow();
    }

    /**
     * Class that represents a simple Key Listener
     */
    class simpleKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    answerButtons.get(0).setActionCommand("0");
                    answerButtons.get(0).doClick();
                    break;

                case KeyEvent.VK_2:
                    answerButtons.get(1).setActionCommand("0");
                    answerButtons.get(1).doClick();
                    break;

                case KeyEvent.VK_3:
                    answerButtons.get(2).setActionCommand("0");
                    answerButtons.get(2).doClick();
                    break;

                case KeyEvent.VK_4:
                    answerButtons.get(3).setActionCommand("0");
                    answerButtons.get(3).doClick();
                    break;

                case KeyEvent.VK_NUMPAD1:
                    answerButtons.get(0).setActionCommand("1");
                    answerButtons.get(0).doClick();
                    break;

                case KeyEvent.VK_NUMPAD2:
                    answerButtons.get(1).setActionCommand("1");
                    answerButtons.get(1).doClick();
                    break;

                case KeyEvent.VK_NUMPAD3:
                    answerButtons.get(2).setActionCommand("1");
                    answerButtons.get(2).doClick();
                    break;

                case KeyEvent.VK_NUMPAD4:
                    answerButtons.get(3).setActionCommand("1");
                    answerButtons.get(3).doClick();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    /**
     * Method that creates and handles the correct answer round screen
     */
    private void correctAnswerRoundScreen(){

        roundName.setText("You are playing the Correct Answer round! (" + game.getRoundsPlayed()
                + " out of " + game.getNumberOfRounds() + ")");
        roundName.setBounds(290,0, 700,50);

        roundRules.setText("For each correct answer you get 1000 points!");
        roundRules.setBounds(340, 50,600,50);

        CorrectAnswerRound round = (CorrectAnswerRound) game.getRound();

        Question question = round.getCurrentQuestion();
        String path = "images/quiz1.jpg";
        if (question instanceof ImageQuestion)
            path = ((ImageQuestion) question).getPath();

        try{
            BufferedImage image = ImageIO.read(new File(path));
            panel.setImage1(image, 800,450);
            panel.setImg1Visible(true);
        }catch (IOException ex){
            ex.printStackTrace();
        }


        //setting up answer button functionalities
        for (int i = 0; i < answerButtons.size(); i ++){
            int answer = i;
            answerButtons.get(i).addActionListener(e -> {
                panel.requestFocusInWindow();
                int playerIndex;
                try {
                    playerIndex = Integer.parseInt(e.getActionCommand());
                    if (playerIndex < 0 || playerIndex >= numberOfPlayers)
                        playerIndex = 0;
                } catch (Exception exc) {
                    playerIndex = 0;
                }
                Player player = game.getPlayers().get(playerIndex);
                round.playerAnswer(player, answer);
                if (round.everyoneHasAnswered()) {
                    correctAnswerLabel.setVisible(true);
                    for (int j = 0; j < numberOfPlayers; j++) {
                        player = game.getPlayers().get(j);
                        playerScores.get(j).setText(player.getName() + " " + player.getScore());
                    }
                    for (JButton ansButton : answerButtons)
                        ansButton.setVisible(false);
                    continueButton.setVisible(true);
                }
            });
        }

        panel.addKeyListener(new simpleKeyListener());

    }

    /**
     * Method that creates and handles the quick answer round screen
     */
    private void quickAnswerRoundScreen(){

        roundName.setText("You are playing the Quick Answer round! (" + game.getRoundsPlayed()
                + " out of " + game.getNumberOfRounds() + ")");
        roundName.setBounds(290,0, 700,50);

        roundRules.setText("The first to answer correctly gets 1000 points and the second gets 500!");
        roundRules.setBounds(200, 50,800,50);

        QuickAnswerRound round = (QuickAnswerRound) game.getRound();

        Question question = round.getCurrentQuestion();
        String path = "images/quiz1.jpg";
        if (question instanceof ImageQuestion)
            path = ((ImageQuestion) question).getPath();

        try{
            BufferedImage image = ImageIO.read(new File(path));
            panel.setImage1(image, 800,450);
            panel.setImg1Visible(true);
        }catch (IOException ex){
            ex.printStackTrace();
        }


        //setting up answer button functionalities
        for (int i = 0; i < answerButtons.size(); i ++){
            int answer = i;
            answerButtons.get(i).addActionListener(e -> {
                panel.requestFocusInWindow();
                int playerIndex;
                try {
                    playerIndex = Integer.parseInt(e.getActionCommand());
                    if (playerIndex < 0 || playerIndex >= numberOfPlayers)
                        playerIndex = 0;
                } catch (Exception exc) {
                    playerIndex = 0;
                }
                Player player = game.getPlayers().get(playerIndex);
                round.playerAnswer(player, answer);
                if (round.everyoneHasAnswered()) {
                    correctAnswerLabel.setVisible(true);
                    for (int j = 0; j < numberOfPlayers; j++) {
                        player = game.getPlayers().get(j);
                        playerScores.get(j).setText(player.getName() + " " + player.getScore());
                    }
                    for (JButton ansButton : answerButtons)
                        ansButton.setVisible(false);
                    continueButton.setVisible(true);
                }
            });
        }

        panel.addKeyListener(new simpleKeyListener());
    }

    /**
     * Countdown class represents a countdown timer for the quiz
     */
    class Countdown extends JFrame{

        private int ms;
        private final JLabel countdown;
        private Timer timer;

        Countdown(){
            ms = 5000;
            countdown = createCustomLabel("Time Left: " + ms, 20,20,400,200,50);
            panel.add(countdown);
            timer = new Timer(0, e -> {
                ms--;
                countdown.setText("Time Left: " + ms);
                if (ms == 0)
                    timer.stop();
            });
            timer.start();
        }
    }

    /**
     * Method that creates and handles the stop-the-timer round screen
     */
    private void stopTheTimerRoundScreen(){

        roundName.setText("You are playing the Stop-The-Timer round! (" + game.getRoundsPlayed() + " out of "
                + game.getNumberOfRounds() + ")");
        roundName.setBounds(290,0, 700,50);

        roundRules.setText("Correct answer gives you points equal to 0.2 times the milliseconds left");
        roundRules.setBounds(200,50,800,50);

        StopTheTimerRound round = (StopTheTimerRound) game.getRound();

        Question question = round.getCurrentQuestion();
        String path = "images/quiz1.jpg";
        if (question instanceof ImageQuestion)
            path = ((ImageQuestion) question).getPath();

        try{
            BufferedImage image = ImageIO.read(new File(path));
            panel.setImage1(image, 800,450);
            panel.setImg1Visible(true);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Countdown countdownTimer = new Countdown();


        //setting up answer button functionalities
        for (int i = 0; i < 4; i++) {
            int answer = i;
            answerButtons.get(i).addActionListener(e -> {
                panel.requestFocusInWindow();
                int playerIndex;
                try{
                    playerIndex = Integer.parseInt(e.getActionCommand());
                    if (playerIndex < 0 || playerIndex >= numberOfPlayers)
                        playerIndex = 0;
                }catch (Exception exc){
                    playerIndex = 0;
                }
                Player player = game.getPlayers().get(playerIndex);
                if (!round.getPlayersWhoHaveAnswered().contains(player)){
                    String text = playerScores.get(playerIndex).getText() + " (answered at " + countdownTimer.ms + " ms)";
                    playerScores.get(playerIndex).setText(text);
                }
                round.playerAnswer(player, answer, countdownTimer.ms);
                if (round.everyoneHasAnswered()){
                    countdownTimer.timer.stop();
                    correctAnswerLabel.setVisible(true);
                    for (int j = 0; j < numberOfPlayers; j++){
                        player = game.getPlayers().get(j);
                        playerScores.get(j).setText(player.getName() + " " + player.getScore() +" (answered at "
                                + round.getPlayerTimeStamp(j) + " ms)");
                    }
                    for (int j = 0; j < 4; j++)
                        answerButtons.get(j).setVisible(false);
                    continueButton.setVisible(true);
                }
            });
        }

        panel.addKeyListener(new simpleKeyListener());
    }

    /**
     * Method that creates and handles the betting round screen
     */
    private void bettingRoundScreen(){

        roundName.setText("You are playing the Betting round! (" + game.getRoundsPlayed() + " out of " +
                    game.getNumberOfRounds() + ")");
        roundName.setBounds(340,0,700,50);

        roundRules.setText("Bet your points and risk of winning or losing them!");
        roundRules.setBounds(310,50,600,50);

        BettingRound round = (BettingRound) game.getRound();

        Question question = round.getCurrentQuestion();
        try{
            BufferedImage image = ImageIO.read(new File("images/quiz1.jpg"));
            panel.setImage1(image, 800,450);
            panel.setImg1Visible(true);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        questionLabel.setVisible(false);

        //setting up answer button functionalities
        for (int i = 0; i < 4; i++) {
            answerButtons.get(i).setVisible(false);
            answerButtons.get(i).setEnabled(false);
            int answer = i;
            answerButtons.get(i).addActionListener(e -> {
                panel.requestFocusInWindow();
                int playerIndex;
                try{
                    playerIndex = Integer.parseInt(e.getActionCommand());
                    if (playerIndex < 0 || playerIndex >= numberOfPlayers)
                        playerIndex = 0;
                }catch (Exception exc){
                    playerIndex = 0;
                }
                Player player = game.getPlayers().get(playerIndex);
                round.playerAnswer(player, answer,round.getBet(player));
                if (round.everyoneHasAnswered()){
                    for (int j = 0; j < numberOfPlayers; j++){
                        player = game.getPlayers().get(j);
                        playerScores.get(j).setText(player.getName() + " " + player.getScore()+ " (bet " +
                                round.getBet(player) + ")");
                    }
                    for (int j = 0; j < 4; j++)
                        answerButtons.get(j).setVisible(false);
                    correctAnswerLabel.setVisible(true);
                    continueButton.setVisible(true);
                }
            });
        }

        JLabel betMessage = createCustomLabel("Select your bets",20,240,450,200,50);
        panel.add(betMessage);

        //setting up bet buttons
        JButton[] betButtons = new JButton[4];
        int betAmount = 250;
        int x = 400;
        for (int i = 0; i < 4; i++){
            betButtons[i] = createCustomButton(Integer.toString(betAmount),20,x,450,80,50);
            int finalBetAmount = betAmount;
            betButtons[i].addActionListener(e -> {
                panel.requestFocusInWindow();
                int playerIndex;
                try{
                    playerIndex = Integer.parseInt(e.getActionCommand());
                    if (playerIndex < 0 || playerIndex >= numberOfPlayers)
                        playerIndex = 0;
                }catch (Exception exc){
                    playerIndex = 0;
                }
                Player player = game.getPlayers().get(playerIndex);
                round.setBet(player, finalBetAmount);
                playerScores.get(playerIndex).setText(player.getName() + " " + player.getScore() + " (bet " +
                        round.getBet(player) + ")");
                if (round.everyoneHasBet()){
                    betMessage.setVisible(false);
                    for (int j = 0; j < 4; j++){
                        betButtons[j].setVisible(false);
                        betButtons[j].setEnabled(false);
                        answerButtons.get(j).setVisible(true);
                        answerButtons.get(j).setEnabled(true);
                    }
                    questionLabel.setVisible(true);
                    if (question instanceof ImageQuestion){
                        try{
                            BufferedImage image = ImageIO.read(new File(((ImageQuestion) question).getPath()));
                            panel.setImage1(image, 800,450);
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                }
            });
            panel.add(betButtons[i]);
            betAmount += 250;
            x += 100;
        }

        panel.addKeyListener(new simpleKeyListener());
        //add bet buttons key listener
        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_1:
                        if (betButtons[0].isEnabled()){
                            betButtons[0].setActionCommand("0");
                            betButtons[0].doClick();
                        }
                        break;

                    case KeyEvent.VK_2:
                        if (betButtons[1].isEnabled()){
                            betButtons[1].setActionCommand("0");
                            betButtons[1].doClick();
                        }
                        break;

                    case KeyEvent.VK_3:
                        if (betButtons[2].isEnabled()){
                            betButtons[2].setActionCommand("0");
                            betButtons[2].doClick();
                        }
                        break;

                    case KeyEvent.VK_4:
                        if (betButtons[3].isEnabled()){
                            betButtons[3].setActionCommand("0");
                            betButtons[3].doClick();
                        }
                        break;

                    case KeyEvent.VK_NUMPAD1:
                        if (betButtons[0].isEnabled()){
                            betButtons[0].setActionCommand("1");
                            betButtons[0].doClick();
                        }
                        break;

                    case KeyEvent.VK_NUMPAD2:
                        if (betButtons[1].isEnabled()){
                            betButtons[1].setActionCommand("1");
                            betButtons[1].doClick();
                        }
                        break;

                    case KeyEvent.VK_NUMPAD3:
                        if (betButtons[2].isEnabled()){
                            betButtons[2].setActionCommand("1");
                            betButtons[2].doClick();
                        }
                        break;

                    case KeyEvent.VK_NUMPAD4:
                        if (betButtons[3].isEnabled()){
                            betButtons[3].setActionCommand("1");
                            betButtons[3].doClick();
                        }
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    /**
     * Method that creates and handles the thermometer round screen
     */
    private void thermometerRoundScreen(){

        Thermometer round = (Thermometer) game.getRound();
        round.play();

        roundName.setText("You are playing the Thermometer round! (" + game.getRoundsPlayed()
                + " out of " + game.getNumberOfRounds() + ")");
        roundName.setBounds(300,0, 700,50);
        panel.add(roundName);

        roundRules.setText("The first to answer 5 out of maximum 10 questions correctly gets 5000 points!");
        roundRules.setBounds(150,50,900,50);
        panel.add(roundRules);

        panel.add(scoreboard);

        playerScores = new ArrayList<>();
        int axisY = 200;
        for (Player player : game.getPlayers()){
            playerScores.add(createCustomLabel(player.getName() + " " + player.getScore(),
                    20,520,axisY,400,50));
            axisY += 50;
        }

        for (JLabel score : playerScores)
            panel.add(score);


        JButton continueButton = createCustomButton("Next Round",20,420,900,300,50);
        if (game.getRoundsRemaining() == 0)
            continueButton.setText("End Game");
        continueButton.setVisible(false);
        continueButton.addActionListener( e -> playGame());
        panel.add(continueButton);


        Question question = round.getQuestionPoll().get(0);
        String path1 = "images/quiz1.jpg";
        String path2 = "images/quiz2.jpg";
        if (question instanceof ImageQuestion){
            path1 = ((ImageQuestion) question).getPath();
            path2 = ((ImageQuestion) question).getPath();
        }
        try{
            BufferedImage image1 = ImageIO.read(new File(path1));
            panel.setImage1(image1, 800,350);
            panel.setImg1Visible(true);
            BufferedImage image2 = ImageIO.read(new File(path2));
            panel.setImage2(image2, 800,650);
            panel.setImg2Visible(true);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        JLabel[] playerLabel = new JLabel[numberOfPlayers];
        JLabel[] categoryLabel = new JLabel[numberOfPlayers];
        JLabel[] questionLabel = new JLabel[numberOfPlayers];
        axisY = 300;
        for (int i = 0 ; i < numberOfPlayers ; i ++){
            Player player = game.getPlayers().get(i);
            playerLabel[i] = createCustomLabel("Player: " + player.getName(),20,20,axisY,200,50);
            panel.add(playerLabel[i]);
            categoryLabel[i] = createCustomLabel("Category: " + question.getCategory().toString(),
                    20,20,axisY + 50,200,50);
            panel.add(categoryLabel[i]);
            questionLabel[i] = createCustomLabel("Question: " + question.getQuestion(),
                    20,20,axisY + 100,1000,50);
            panel.add(questionLabel[i]);
            axisY += 300;
        }



        //setting up answer button functionalities for both players
        axisY = 450;
        HashMap<Player, ArrayList<JButton>> buttonSet = new HashMap<>();
        for (Player player : game.getPlayers()){
            ArrayList<JButton> ansButtons = new ArrayList<>();
            buttonSet.put(player, ansButtons);
            for (int i = 0 ; i < 4 ; i++){
                int answer = i;
                ansButtons.add(new JButton(question.getAllAnswers().get(i).getAnswer()));
                ansButtons.get(i).setFont(new Font("MS Sans Serif", Font.PLAIN, 20));
                panel.add(ansButtons.get(i));
                ansButtons.get(i).addActionListener(e -> {
                    panel.requestFocusInWindow();
                    int playerIndex = game.getPlayers().indexOf(player);
                    round.playerAnswer(player, answer);
                    playerScores.get(playerIndex).setText(player.getName() + " " + player.getScore() + " (" +
                            round.getCorrectAnswersByPlayer(player) + " out of " +
                            round.getTotalQuestionsAskedToPlayer(player) + ")");
                    if (!round.playerIsDone(player)) {
                        Question newQuestion = round.getNextQuestionForPlayer(player);
                        categoryLabel[playerIndex].setText("Category: " + newQuestion.getCategory().toString());
                        questionLabel[playerIndex].setText("Question: " + newQuestion.getQuestion());
                        for (int j = 0 ; j < ansButtons.size(); j ++)
                            ansButtons.get(j).setText(newQuestion.getAllAnswers().get(j).getAnswer());
                        String imgPath1 = "images/quiz1.jpg";
                        String imgPath2 = "images/quiz2.jpg";
                        if (newQuestion instanceof ImageQuestion){
                            if (playerIndex == 0)
                                imgPath1 = ((ImageQuestion) newQuestion).getPath();
                            else
                                imgPath2 = ((ImageQuestion) newQuestion).getPath();
                        }
                        try{
                            if (playerIndex == 0){
                                BufferedImage image1 = ImageIO.read(new File(imgPath1));
                                panel.setImage1(image1, 800,350);
                            }
                            else{
                                BufferedImage image2 = ImageIO.read(new File(imgPath2));
                                panel.setImage2(image2, 800,650);
                            }
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                    else if (round.roundIsDone()){
                        continueButton.setVisible(true);
                    }
                });
            }
            ansButtons.get(0).setBounds(20, axisY, 300, 50);
            ansButtons.get(1).setBounds(370, axisY, 300, 50);
            ansButtons.get(2).setBounds(20, axisY + 80, 300, 50);
            ansButtons.get(3).setBounds(370, axisY + 80, 300, 50);
            axisY += 300;
        }

        //add key listener for the button sets for both players
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                ArrayList<JButton> buttonList1 = buttonSet.get(game.getPlayers().get(0));
                ArrayList<JButton> buttonList2 = buttonSet.get(game.getPlayers().get(1));
                switch (key){
                    case KeyEvent.VK_1:
                        buttonList1.get(0).doClick();
                        break;

                    case KeyEvent.VK_2:
                        buttonList1.get(1).doClick();
                        break;

                    case KeyEvent.VK_3:
                        buttonList1.get(2).doClick();
                        break;

                    case KeyEvent.VK_4:
                        buttonList1.get(3).doClick();
                        break;

                    case KeyEvent.VK_NUMPAD1:
                        buttonList2.get(0).doClick();
                        break;

                    case KeyEvent.VK_NUMPAD2:
                        buttonList2.get(1).doClick();
                        break;

                    case KeyEvent.VK_NUMPAD3:
                        buttonList2.get(2).doClick();
                        break;

                    case KeyEvent.VK_NUMPAD4:
                        buttonList2.get(3).doClick();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

}
