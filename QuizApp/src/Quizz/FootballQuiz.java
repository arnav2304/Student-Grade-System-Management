package Quizz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FootballQuiz extends JFrame implements ActionListener {
    String[] questions = {
        "1. Who won the FIFA World Cup 2022?",
        "2. Who is known as 'The King of Football'?",
        "3. Which country has won the most FIFA World Cups?",
        "4. What club does Lionel Messi currently play for (as of 2023)?",
        "5. Which country hosted the FIFA World Cup 2018?",
        "6. How many players are there in a football team (on field)?",
        "7. What does FIFA stand for?",
        "8. Which player has won the most Ballon d'Or awards?",
        "9. Which is the oldest football club in the world?",
        "10. What is the standard duration of a football match?"
    };

    String[][] options = {
        {"Argentina", "France", "Brazil", "Germany"},
        {"Messi", "Pele", "Maradona", "Cristiano Ronaldo"},
        {"Brazil", "Germany", "Italy", "Argentina"},
        {"Inter Miami", "Barcelona", "PSG", "Al-Nassr"},
        {"France", "Russia", "Brazil", "Qatar"},
        {"10", "11", "9", "12"},
        {"Football International Federation Association", 
         "Federation International Football Association", 
         "Football India Federation Association", 
         "Federation of Inter Football Association"},
        {"Cristiano Ronaldo", "Pele", "Lionel Messi", "Ronaldinho"},
        {"Sheffield FC", "Real Madrid", "Manchester United", "Barcelona"},
        {"90 minutes", "80 minutes", "100 minutes", "60 minutes"}
    };

    char[] answers = {'A','B','A','A','B','B','B','C','A','A'};

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel number_right = new JLabel();
    JLabel percentage = new JLabel();

    public FootballQuiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,600);
        frame.getContentPane().setBackground(new Color(240,248,255)); // light theme
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0,0,700,50);
        textfield.setBackground(new Color(224,255,255));
        textfield.setForeground(new Color(25,25,112));
        textfield.setFont(new Font("Arial", Font.BOLD, 30));
        textfield.setBorder(null);
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);
        textfield.setText("Football Quiz");

        textarea.setBounds(50,60,600,60);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(245,255,250));
        textarea.setForeground(new Color(0,0,0));
        textarea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textarea.setEditable(false);

        buttonA.setBounds(100,140,500,40);
        buttonB.setBounds(100,190,500,40);
        buttonC.setBounds(100,240,500,40);
        buttonD.setBounds(100,290,500,40);

        JButton[] buttons = {buttonA, buttonB, buttonC, buttonD};
        for (JButton button : buttons) {
            button.setFocusable(false);
            button.addActionListener(this);
            button.setBackground(new Color(230, 230, 250));
            button.setFont(new Font("Verdana", Font.BOLD, 16));
            frame.add(button);
        }

        answer_labelA.setBounds(105,140,500,40);
        answer_labelB.setBounds(105,190,500,40);
        answer_labelC.setBounds(105,240,500,40);
        answer_labelD.setBounds(105,290,500,40);

        number_right.setBounds(250,350,200,100);
        number_right.setFont(new Font("Arial", Font.BOLD, 25));
        number_right.setHorizontalAlignment(JLabel.CENTER);

        percentage.setBounds(250,400,200,100);
        percentage.setFont(new Font("Arial", Font.BOLD, 25));
        percentage.setHorizontalAlignment(JLabel.CENTER);

        frame.add(textfield);
        frame.add(textarea);
        frame.add(number_right);
        frame.add(percentage);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {
        if(index >= total_questions) {
            results();
        } else {
            textfield.setText("Question " + (index + 1));
            textarea.setText(questions[index]);
            buttonA.setText("A. " + options[index][0]);
            buttonB.setText("B. " + options[index][1]);
            buttonC.setText("C. " + options[index][2]);
            buttonD.setText("D. " + options[index][3]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource() == buttonA) guess = 'A';
        if(e.getSource() == buttonB) guess = 'B';
        if(e.getSource() == buttonC) guess = 'C';
        if(e.getSource() == buttonD) guess = 'D';

        answer = answers[index];
        if(guess == answer) {
            correct_guesses++;
        }

        Timer pause = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                index++;
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results() {
        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
        textarea.setVisible(false);

        int result = (int)((correct_guesses/(double)total_questions)*100);
        number_right.setText("Correct: " + correct_guesses + "/" + total_questions);
        percentage.setText("Score: " + result + "%");
    }

    public static void main(String[] args) {
        new FootballQuiz();
    }
}
