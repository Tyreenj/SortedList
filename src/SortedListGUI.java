import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI application for the SortedList class
 * Allows users to add elements and search for them
 */
public class SortedListGUI extends JFrame {
    private SortedList sortedList;
    private JTextField inputField;
    private JTextArea displayArea;
    private JButton addButton;
    private JButton searchButton;
    private JButton clearButton;
    private JButton showListButton;
    private JLabel statusLabel;

    public SortedListGUI() {
        sortedList = new SortedList();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Sorted List Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel inputLabel = new JLabel("Enter String:");
        inputField = new JTextField(20);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        addButton = new JButton("Add to List");
        searchButton = new JButton("Search");
        showListButton = new JButton("Show List");
        clearButton = new JButton("Clear All");

        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showListButton);
        buttonPanel.add(clearButton);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);

        topPanel.add(inputPanel, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel displayLabel = new JLabel("Operations Log:");
        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        centerPanel.add(displayLabel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        statusLabel = new JLabel("Ready. Enter strings to add to the sorted list.");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));
        bottomPanel.add(statusLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addElement());
        searchButton.addActionListener(e -> searchElement());
        showListButton.addActionListener(e -> showList());
        clearButton.addActionListener(e -> clearAll());

        inputField.addActionListener(e -> addElement());

        inputField.requestFocusInWindow();

        pack();
        setLocationRelativeTo(null);
    }

    private void addElement() {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            statusLabel.setText("Error: Cannot add empty string");
            JOptionPane.showMessageDialog(this,
                    "Please enter a non-empty string",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String result = sortedList.add(input);
        appendToDisplay("ADD: " + result);
        statusLabel.setText("Added: " + input);
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    private void searchElement() {
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            statusLabel.setText("Error: Cannot search for empty string");
            JOptionPane.showMessageDialog(this,
                    "Please enter a string to search",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (sortedList.isEmpty()) {
            statusLabel.setText("Error: List is empty");
            JOptionPane.showMessageDialog(this,
                    "The list is empty. Add elements first.",
                    "List Empty",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String result = sortedList.search(input);
        appendToDisplay("SEARCH: " + result);
        statusLabel.setText("Search completed");
        inputField.setText("");
        inputField.requestFocusInWindow();
    }

    private void showList() {
        appendToDisplay("\n" + sortedList.displayList());
        statusLabel.setText("Displayed list (" + sortedList.size() + " elements)");
    }

    private void clearAll() {
        int response = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear all elements?",
                "Confirm Clear",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            sortedList.clear();
            displayArea.setText("");
            statusLabel.setText("List cleared");
            inputField.setText("");
            appendToDisplay("═══ LIST CLEARED ═══\n");
        }
    }

    private void appendToDisplay(String text) {
        displayArea.append(text + "\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            SortedListGUI gui = new SortedListGUI();
            gui.setVisible(true);
        });
    }
}