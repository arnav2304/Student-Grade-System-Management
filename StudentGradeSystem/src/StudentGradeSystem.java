import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class StudentGradeSystem extends JFrame {
    JTextField tfName, tfID, tfMarks1, tfMarks2, tfMarks3;
    JTextArea taResult;
    JButton btnAdd, btnView, btnClear;

    public StudentGradeSystem() {
        setTitle("Student Grade Management System");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("Name:");
        JLabel l2 = new JLabel("Student ID:");
        JLabel l3 = new JLabel("Marks1:");
        JLabel l4 = new JLabel("Marks2:");
        JLabel l5 = new JLabel("Marks3:");

        tfName = new JTextField();
        tfID = new JTextField();
        tfMarks1 = new JTextField();
        tfMarks2 = new JTextField();
        tfMarks3 = new JTextField();

        btnAdd = new JButton("Add Record");
        btnView = new JButton("View Records");
        btnClear = new JButton("Clear");

        taResult = new JTextArea();

        // Set bounds
        l1.setBounds(30, 30, 100, 25); tfName.setBounds(150, 30, 200, 25);
        l2.setBounds(30, 70, 100, 25); tfID.setBounds(150, 70, 200, 25);
        l3.setBounds(30, 110, 100, 25); tfMarks1.setBounds(150, 110, 200, 25);
        l4.setBounds(30, 150, 100, 25); tfMarks2.setBounds(150, 150, 200, 25);
        l5.setBounds(30, 190, 100, 25); tfMarks3.setBounds(150, 190, 200, 25);

        btnAdd.setBounds(30, 230, 120, 30);
        btnView.setBounds(170, 230, 120, 30);
        btnClear.setBounds(310, 230, 120, 30);
        taResult.setBounds(30, 280, 400, 150);

        add(l1); add(tfName);
        add(l2); add(tfID);
        add(l3); add(tfMarks1);
        add(l4); add(tfMarks2);
        add(l5); add(tfMarks3);
        add(btnAdd); add(btnView); add(btnClear);
        add(taResult);

        setVisible(true);

        // Add action listeners
        btnAdd.addActionListener(e -> addRecord());
        btnView.addActionListener(e -> viewRecords());
        btnClear.addActionListener(e -> taResult.setText(""));
    }

    void addRecord() {
        try {
            String name = tfName.getText();
            String id = tfID.getText();
            int m1 = Integer.parseInt(tfMarks1.getText());
            int m2 = Integer.parseInt(tfMarks2.getText());
            int m3 = Integer.parseInt(tfMarks3.getText());

            int total = m1 + m2 + m3;
            double percent = total / 3.0;
            String grade;

            if (percent >= 90) grade = "A+";
            else if (percent >= 75) grade = "A";
            else if (percent >= 60) grade = "B";
            else if (percent >= 45) grade = "C";
            else grade = "Fail";

            String record = id + "," + name + "," + m1 + "," + m2 + "," + m3 + "," + total + "," + percent + "," + grade;

            FileWriter fw = new FileWriter("student_records.txt", true);
            fw.write(record + "\n");
            fw.close();

            taResult.setText("Record Added Successfully:\n" + record);

            // Clear fields
            tfName.setText(""); tfID.setText("");
            tfMarks1.setText(""); tfMarks2.setText(""); tfMarks3.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    void viewRecords() {
        try {
            File file = new File("student_records.txt");
            if (!file.exists()) {
                taResult.setText("No records found.");
                return;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            taResult.setText("All Student Records:\n\n");
            while ((line = br.readLine()) != null) {
                taResult.append(line + "\n");
            }
            br.close();
        } catch (Exception ex) {
            taResult.setText("Error reading file.");
        }
    }

    public static void main(String[] args) {
        new StudentGradeSystem();
    }
}