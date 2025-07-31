package Quizz;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGUI extends JFrame implements ActionListener {

    String[] questions = {
        "What is the capital of India?",
        "Which language is used for Android development?",
        "Which company developed Java?",
        "What is the extension of Java files?",
        "Which keyword is used to create a class?"
    };

    String[][] options = {
        {"Mumbai", "New Delhi", "Kolkata", "Chennai"},
        {"Python", "Swift", "Java", "Kotlin"},
        {"Microsoft", "Google", "Sun Microsystems", "Oracle"},
        {".js", ".java", ".class", ".py"},
        {"function", "def", "create", "class"}
    };

    int[] answers = {1, 3, 2, 1, 3};

    JLabel questionLabel;
    JRadioButton[] choices = new JRadioButton[4];
    ButtonGroup group;
    JButton nextButton;

    int current = 0;
    int score = 0;

    public QuizGUI() {
        setTitle("🧠 Creative Java Quiz App");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(250, 250, 250));

        // Heading panel
        JLabel header = new JLabel("✨ Welcome to the Java Quiz ✨", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setForeground(new Color(60, 60, 120));
        header.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        // Question area
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 245, 255));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        questionLabel.setForeground(Color.DARK_GRAY);
        centerPanel.add(questionLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            choices[i] = new JRadioButton();
            choices[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            choices[i].setBackground(new Color(240, 245, 255));
            choices[i].setFocusPainted(false);
            group.add(choices[i]);
            centerPanel.add(choices[i]);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        add(centerPanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(250, 250, 250));

        nextButton = new JButton("Next ➡");
        nextButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nextButton.setBackground(new Color(60, 120, 200));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.addActionListener(this);

        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        group.clearSelection();
        questionLabel.setText("Q" + (current + 1) + ": " + questions[current]);
        for (int i = 0; i < 4; i++) {
            choices[i].setText(options[current][i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (choices[i].isSelected()) {
                selected = i;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "⚠ Please select an answer.", "No Option Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selected == answers[current]) {
            score++;
        }

        current++;
        if (current < questions.length) {
            loadQuestion();
        } else {
            showResult();
        }
    }

    void showResult() {
        getContentPane().removeAll();
        repaint();
        setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel("🎉 Your Score: " + score + "/" + questions.length, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        resultLabel.setForeground(new Color(50, 150, 80));
        resultLabel.setBorder(BorderFactory.createEmptyBorder(40, 10, 20, 10));
        add(resultLabel, BorderLayout.CENTER);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        exitButton.setBackground(new Color(200, 0, 0));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        exitButton.addActionListener(e -> System.exit(0));

        JPanel southPanel = new JPanel();
        southPanel.setBackground(new Color(250, 250, 250));
        southPanel.add(exitButton);

        add(southPanel, BorderLayout.SOUTH);

        revalidate();
    }

    public static void main(String[] args) {
        // Set system look and feel for better design
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        new QuizGUI();
    }
}