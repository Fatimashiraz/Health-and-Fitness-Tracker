import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Health and Fitness Tracker Application
 * SDG 3: Good Health and Well-being
 * 
 * This application helps users track their health metrics, set fitness goals,
 * and maintain a healthy lifestyle to promote good health and well-being.
 * 
 * @author Student
 * @version 1.0
 */
public class HealthFitnessTracker extends JFrame {
    
    // GUI Components
    private JTabbedPane tabbedPane;
    private JPanel dashboardPanel, healthPanel, fitnessPanel, goalsPanel, reportsPanel;
    private JPanel statsPanel; // Quick stats panel for dashboard
    
    // Health tracking components
    private JTextField weightField, heightField, ageField, bloodPressureField;
    private JTextArea healthNotesArea;
    private JLabel bmiLabel, healthStatusLabel;
    
    // Fitness tracking components
    private JTextField stepsField, caloriesField, exerciseField, durationField;
    private JTextArea fitnessNotesArea;
    private JLabel dailyGoalLabel, weeklyProgressLabel;
    
    // Goals components
    private JTextField goalWeightField, goalStepsField, goalCaloriesField;
    private JTextArea goalsNotesArea;
    private JLabel goalProgressLabel;
    
    // Dashboard stats labels
    private JLabel bmiStatusLabel, stepsLabel, caloriesLabel, dashboardGoalProgressLabel, healthScoreLabel, weeklyAvgLabel;
    
    // Reports components
    private JTextArea reportsArea;
    
    // Data storage
    private ArrayList<HealthRecord> healthRecords;
    private ArrayList<FitnessRecord> fitnessRecords;
    private ArrayList<Goal> goals;
    
    // File paths for data persistence
    private static final String HEALTH_FILE = "health_records.txt";
    private static final String FITNESS_FILE = "fitness_records.txt";
    private static final String GOALS_FILE = "goals.txt";
    
    /**
     * Constructor - Initializes the application
     */
    public HealthFitnessTracker() {
        healthRecords = new ArrayList<>();
        fitnessRecords = new ArrayList<>();
        goals = new ArrayList<>();
        
        loadData();
        setupGUI();
        setupEventHandlers();
        
        setTitle("Health & Fitness Tracker - SDG 3: Good Health & Well-being");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    /**
     * Sets up the main GUI components
     */
    private void setupGUI() {
        setLayout(new BorderLayout());
        
        // Create header
        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        
        // Create main content area
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Create tabs
        dashboardPanel = createDashboardPanel();
        healthPanel = createHealthPanel();
        fitnessPanel = createFitnessPanel();
        goalsPanel = createGoalsPanel();
        reportsPanel = createReportsPanel();
        
        tabbedPane.addTab("Dashboard", new ImageIcon(), dashboardPanel, "Overview of health and fitness data");
        tabbedPane.addTab("Goals", new ImageIcon(), goalsPanel, "Set and monitor goals");
        tabbedPane.addTab("Health Tracking", new ImageIcon(), healthPanel, "Track health metrics");
        tabbedPane.addTab("Fitness Tracking", new ImageIcon(), fitnessPanel, "Track fitness activities");
        tabbedPane.addTab("Reports", new ImageIcon(), reportsPanel, "View detailed reports");
        
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    /**
     * Creates the header panel with title and SDG information
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 139, 34)); // Forest green
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel("Health & Fitness Tracker");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel sdgLabel = new JLabel("SDG 3: Good Health and Well-being");
        sdgLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        sdgLabel.setForeground(Color.WHITE);
        
        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(sdgLabel, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Creates the dashboard panel with overview information
     */
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBorder(BorderFactory.createTitledBorder("Welcome to Your Health Journey"));
        
        JTextArea welcomeText = new JTextArea();
        welcomeText.setText(
            "Welcome to your Health & Fitness Tracker!\n\n" +
            "This application is designed to help you achieve SDG 3: Good Health and Well-being " +
            "by tracking your health metrics and fitness activities.\n\n" +
            "Key Features:\n" +
            "• Track your weight, BMI, and vital signs\n" +
            "• Monitor daily steps and calorie burn\n" +
            "• Set and track fitness goals\n" +
            "• Generate health reports\n" +
            "• Maintain a healthy lifestyle\n\n" +
            "Start by entering your health data in the Health Tracking tab " +
            "and set your fitness goals in the Goals tab."
        );
        welcomeText.setEditable(false);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeText.setLineWrap(true);
        welcomeText.setWrapStyleWord(true);
        welcomeText.setBackground(panel.getBackground());
        
        welcomePanel.add(welcomeText, BorderLayout.CENTER);
        
            // Quick stats panel
    statsPanel = createQuickStatsPanel();
    
    panel.add(welcomePanel, BorderLayout.CENTER);
    panel.add(statsPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Creates quick statistics panel
     */
    private JPanel createQuickStatsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Quick Statistics"));
        
        // BMI Status
        bmiStatusLabel = new JLabel("BMI Status: Normal");
        bmiStatusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bmiStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bmiStatusLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Daily Steps
        stepsLabel = new JLabel("Today's Steps: 0");
        stepsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        stepsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stepsLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Calories Burned
        caloriesLabel = new JLabel("Calories Burned: 0");
        caloriesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        caloriesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        caloriesLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Goal Progress
        dashboardGoalProgressLabel = new JLabel("Goal Progress: 0%");
        dashboardGoalProgressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dashboardGoalProgressLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dashboardGoalProgressLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Health Score
        healthScoreLabel = new JLabel("Health Score: Good");
        healthScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        healthScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        healthScoreLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        // Weekly Average
        weeklyAvgLabel = new JLabel("Weekly Avg Steps: 0");
        weeklyAvgLabel.setFont(new Font("Arial", Font.BOLD, 16));
        weeklyAvgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weeklyAvgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        panel.add(bmiStatusLabel);
        panel.add(stepsLabel);
        panel.add(caloriesLabel);
        panel.add(dashboardGoalProgressLabel);
        panel.add(healthScoreLabel);
        panel.add(weeklyAvgLabel);
        
        return panel;
    }
    
    /**
     * Creates the health tracking panel
     */
    private JPanel createHealthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Health Metrics Input"));
        
        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);
        
        inputPanel.add(new JLabel("Height (cm):"));
        heightField = new JTextField();
        inputPanel.add(heightField);
        
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        
        inputPanel.add(new JLabel("Blood Pressure (mmHg):"));
        bloodPressureField = new JTextField();
        inputPanel.add(bloodPressureField);
        
        inputPanel.add(new JLabel("BMI:"));
        bmiLabel = new JLabel("Calculate after entering weight and height");
        bmiLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bmiLabel.setForeground(Color.BLUE);
        inputPanel.add(bmiLabel);
        
        inputPanel.add(new JLabel("Health Status:"));
        healthStatusLabel = new JLabel("Enter data to see status");
        healthStatusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        healthStatusLabel.setForeground(Color.ORANGE);
        inputPanel.add(healthStatusLabel);
        
        // Notes panel
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Health Notes"));
        
        healthNotesArea = new JTextArea(5, 30);
        healthNotesArea.setLineWrap(true);
        healthNotesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(healthNotesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate BMI");
        JButton saveButton = new JButton("Save Health Record");
        JButton viewRecordsButton = new JButton("View Records");
        JButton clearButton = new JButton("Clear");
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(viewRecordsButton);
        buttonPanel.add(clearButton);
        
        // Add components to main panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(notesPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the fitness tracking panel
     */
    private JPanel createFitnessPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Fitness Activity Input"));
        
        inputPanel.add(new JLabel("Daily Steps:"));
        stepsField = new JTextField();
        stepsField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { updateDailyGoalProgress(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateDailyGoalProgress(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateDailyGoalProgress(); }
        });
        inputPanel.add(stepsField);
        
        inputPanel.add(new JLabel("Calories Burned (Auto-calculated):"));
        caloriesField = new JTextField();
        caloriesField.setEditable(false);
        caloriesField.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(caloriesField);
        
        inputPanel.add(new JLabel("Exercise Type:"));
        exerciseField = new JTextField();
        inputPanel.add(exerciseField);
        
        inputPanel.add(new JLabel("Duration (minutes):"));
        durationField = new JTextField();
        inputPanel.add(durationField);
        
        inputPanel.add(new JLabel("Daily Goal Progress:"));
        dailyGoalLabel = new JLabel("Enter steps to see progress");
        dailyGoalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dailyGoalLabel.setForeground(Color.BLUE);
        inputPanel.add(dailyGoalLabel);
        
        // Notes panel
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Fitness Notes"));
        
        fitnessNotesArea = new JTextArea(5, 30);
        fitnessNotesArea.setLineWrap(true);
        fitnessNotesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(fitnessNotesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Fitness Record");
        JButton viewRecordsButton = new JButton("View Records");
        JButton clearButton = new JButton("Clear");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(viewRecordsButton);
        buttonPanel.add(clearButton);
        
        // Add components to main panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(notesPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the goals panel
     */
    private JPanel createGoalsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Set Your Goals"));
        
        inputPanel.add(new JLabel("Target Weight (kg):"));
        goalWeightField = new JTextField();
        inputPanel.add(goalWeightField);
        
        inputPanel.add(new JLabel("Daily Steps Goal:"));
        goalStepsField = new JTextField();
        inputPanel.add(goalStepsField);
        
        inputPanel.add(new JLabel("Daily Calories Goal:"));
        goalCaloriesField = new JTextField();
        inputPanel.add(goalCaloriesField);
        
        inputPanel.add(new JLabel("Goal Progress:"));
        goalProgressLabel = new JLabel("Set goals to see progress");
        goalProgressLabel.setForeground(Color.BLUE);
        inputPanel.add(goalProgressLabel);
        
        // Notes panel
        JPanel notesPanel = new JPanel();
        notesPanel.setLayout(new BorderLayout());
        notesPanel.setBorder(BorderFactory.createTitledBorder("Goal Notes"));
        
        goalsNotesArea = new JTextArea(5, 30);
        goalsNotesArea.setLineWrap(true);
        goalsNotesArea.setWrapStyleWord(true);
        JScrollPane notesScrollPane = new JScrollPane(goalsNotesArea);
        notesPanel.add(notesScrollPane, BorderLayout.CENTER);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Goals");
        JButton clearButton = new JButton("Clear");
        
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        
        // Add components to main panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(notesPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Creates the reports panel
     */
    private JPanel createReportsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Welcome message for reports
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBorder(BorderFactory.createTitledBorder("Health & Fitness Reports"));
        
        JTextArea welcomeText = new JTextArea();
        welcomeText.setText(
            "Welcome to the Reports Section!\n\n" +
            "Here you can generate comprehensive health and fitness reports " +
            "to track your progress and maintain your health journey.\n\n" +
            "Available Reports:\n" +
            "• Health Report: Complete overview of your health metrics and trends\n" +
            "• Export Report: Save detailed reports to files for future reference\n\n" +
            "Features:\n" +
            "• BMI tracking and health status analysis\n" +
            "• Fitness activity summaries and progress\n" +
            "• Goal achievement tracking\n" +
            "• SDG 3 alignment documentation\n\n" +
            "Click 'Generate Health Report' to view your current data summary, " +
            "or 'Export Report' to save a detailed report to a file."
        );
        welcomeText.setEditable(false);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeText.setLineWrap(true);
        welcomeText.setWrapStyleWord(true);
        welcomeText.setBackground(panel.getBackground());
        
        welcomePanel.add(welcomeText, BorderLayout.CENTER);
        
        // Reports text area
        reportsArea = new JTextArea();
        reportsArea.setEditable(false);
        reportsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        reportsArea.setText("Click 'Generate Health Report' to view your data summary here...");
        JScrollPane scrollPane = new JScrollPane(reportsArea);
        scrollPane.setPreferredSize(new Dimension(600, 300));
        
        // Buttons panel
        JPanel buttonPanel = new JPanel();
        JButton generateReportButton = new JButton("Generate Health Report");
        JButton exportButton = new JButton("Export Report");
        JButton clearButton = new JButton("Clear Report");
        
        buttonPanel.add(generateReportButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(clearButton);
        
        // Add components to main panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(welcomePanel, BorderLayout.NORTH);
        topPanel.add(scrollPane, BorderLayout.CENTER);
        
        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Sets up event handlers for buttons and components
     */
    private void setupEventHandlers() {
        // Get all buttons from panels and add action listeners
        addActionListenersToHealthPanel();
        addActionListenersToFitnessPanel();
        addActionListenersToGoalsPanel();
        addActionListenersToReportsPanel();
    }
    
    /**
     * Adds action listeners to health panel buttons
     */
    private void addActionListenersToHealthPanel() {
        // Find buttons in health panel
        Component[] components = healthPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] subComponents = ((JPanel) comp).getComponents();
                for (Component subComp : subComponents) {
                    if (subComp instanceof JPanel) {
                        Component[] buttonComponents = ((JPanel) subComp).getComponents();
                        for (Component buttonComp : buttonComponents) {
                            if (buttonComp instanceof JButton) {
                                JButton button = (JButton) buttonComp;
                                if (button.getText().equals("Calculate BMI")) {
                                    button.addActionListener(e -> calculateBMI());
                                } else if (button.getText().equals("Save Health Record")) {
                                    button.addActionListener(e -> saveHealthRecord());
                                } else if (button.getText().equals("View Records")) {
                                    button.addActionListener(e -> viewHealthRecords());
                                } else if (button.getText().equals("Clear")) {
                                    button.addActionListener(e -> clearHealthFields());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Adds action listeners to fitness panel buttons
     */
    private void addActionListenersToFitnessPanel() {
        // Find buttons in fitness panel
        Component[] components = fitnessPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] subComponents = ((JPanel) comp).getComponents();
                for (Component subComp : subComponents) {
                    if (subComp instanceof JPanel) {
                        Component[] buttonComponents = ((JPanel) subComp).getComponents();
                        for (Component buttonComp : buttonComponents) {
                            if (buttonComp instanceof JButton) {
                                JButton button = (JButton) buttonComp;
                                if (button.getText().equals("Save Fitness Record")) {
                                    button.addActionListener(e -> saveFitnessRecord());
                                } else if (button.getText().equals("View Records")) {
                                    button.addActionListener(e -> viewFitnessRecords());
                                } else if (button.getText().equals("Clear")) {
                                    button.addActionListener(e -> clearFitnessFields());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Adds action listeners to goals panel buttons
     */
    private void addActionListenersToGoalsPanel() {
        // Find buttons in goals panel
        Component[] components = goalsPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] subComponents = ((JPanel) comp).getComponents();
                for (Component subComp : subComponents) {
                    if (subComp instanceof JPanel) {
                        Component[] buttonComponents = ((JPanel) subComp).getComponents();
                        for (Component buttonComp : buttonComponents) {
                            if (buttonComp instanceof JButton) {
                                JButton button = (JButton) buttonComp;
                                if (button.getText().equals("Save Goals")) {
                                    button.addActionListener(e -> saveGoals());
                                } else if (button.getText().equals("Clear")) {
                                    button.addActionListener(e -> clearGoalFields());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Adds action listeners to reports panel buttons
     */
    private void addActionListenersToReportsPanel() {
        // Find buttons in reports panel
        Component[] components = reportsPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                Component[] buttonComponents = ((JPanel) comp).getComponents();
                for (Component buttonComp : buttonComponents) {
                    if (buttonComp instanceof JButton) {
                        JButton button = (JButton) buttonComp;
                        if (button.getText().equals("Generate Health Report")) {
                            button.addActionListener(e -> generateHealthReport());
                        } else if (button.getText().equals("Export Report")) {
                            button.addActionListener(e -> exportReport());
                        } else if (button.getText().equals("Clear Report")) {
                            button.addActionListener(e -> clearReport());
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Calculates BMI based on weight and height
     */
    private double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    
    /**
     * Determines health status based on BMI
     */
    private String getHealthStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal Weight";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
    
    /**
     * Views all health records in a dialog
     */
    private void viewHealthRecords() {
        if (healthRecords.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No health records found. Please save some records first.", 
                "No Records", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder records = new StringBuilder();
        records.append("=== HEALTH RECORDS ===\n\n");
        
        for (int i = 0; i < healthRecords.size(); i++) {
            HealthRecord record = healthRecords.get(i);
            records.append("Record #").append(i + 1).append(" (").append(record.getDate()).append(")\n");
            records.append("Weight: ").append(record.getWeight()).append(" kg\n");
            records.append("Height: ").append(record.getHeight()).append(" cm\n");
            records.append("BMI: ").append(String.format("%.2f", record.getBmi())).append("\n");
            records.append("Health Status: ").append(record.getHealthStatus()).append("\n");
            records.append("Age: ").append(record.getAge()).append("\n");
            records.append("Blood Pressure: ").append(record.getBloodPressure()).append("\n");
            records.append("Notes: ").append(record.getNotes()).append("\n");
            records.append("----------------------------------------\n\n");
        }
        
        JTextArea recordsArea = new JTextArea(records.toString());
        recordsArea.setEditable(false);
        recordsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(recordsArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Health Records", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Calculates BMI and updates the display
     */
    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            
            double bmi = calculateBMI(weight, height);
            String status = getHealthStatus(bmi);
            
            bmiLabel.setText(String.format("BMI: %.2f", bmi));
            healthStatusLabel.setText("Health Status: " + status);
            
            // Update colors based on status
            if (status.equals("Normal Weight")) {
                healthStatusLabel.setForeground(new Color(0, 128, 0)); // Green
            } else if (status.equals("Overweight") || status.equals("Obese")) {
                healthStatusLabel.setForeground(Color.RED);
            } else {
                healthStatusLabel.setForeground(Color.ORANGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for weight and height.", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Views all fitness records in a dialog
     */
    private void viewFitnessRecords() {
        if (fitnessRecords.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No fitness records found. Please save some records first.", 
                "No Records", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        StringBuilder records = new StringBuilder();
        records.append("=== FITNESS RECORDS ===\n\n");
        
        for (int i = 0; i < fitnessRecords.size(); i++) {
            FitnessRecord record = fitnessRecords.get(i);
            records.append("Record #").append(i + 1).append(" (").append(record.getDate()).append(")\n");
            records.append("Steps: ").append(record.getSteps()).append("\n");
            records.append("Calories Burned: ").append(record.getCalories()).append("\n");
            records.append("Exercise Type: ").append(record.getExerciseType()).append("\n");
            records.append("Duration: ").append(record.getDuration()).append(" minutes\n");
            records.append("Notes: ").append(record.getNotes()).append("\n");
            records.append("----------------------------------------\n\n");
        }
        
        JTextArea recordsArea = new JTextArea(records.toString());
        recordsArea.setEditable(false);
        recordsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(recordsArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));
        
        JOptionPane.showMessageDialog(this, scrollPane, "Fitness Records", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Saves health record to the list and file
     */
    private void saveHealthRecord() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String bloodPressure = bloodPressureField.getText();
            String notes = healthNotesArea.getText();
            
            double bmi = calculateBMI(weight, height);
            String healthStatus = getHealthStatus(bmi);
            
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            HealthRecord record = new HealthRecord(currentDate, weight, height, age, 
                                                 bloodPressure, bmi, healthStatus, notes);
            healthRecords.add(record);
            
            saveHealthData();
            
            updateDashboardStats();
            
            JOptionPane.showMessageDialog(this, 
                "Health record saved successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for all fields.", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clears all health input fields
     */
    private void clearHealthFields() {
        weightField.setText("");
        heightField.setText("");
        ageField.setText("");
        bloodPressureField.setText("");
        healthNotesArea.setText("");
        bmiLabel.setText("Calculate after entering weight and height");
        healthStatusLabel.setText("Enter data to see status");
        healthStatusLabel.setForeground(Color.ORANGE);
    }
    
    /**
     * Calculates calories burned based on steps
     */
    private int calculateCaloriesFromSteps(int steps) {
        // Average calorie burn per step (varies by person, using conservative estimate)
        // 1 step ≈ 0.04 calories for average person
        return (int)(steps * 0.04);
    }
    
    /**
     * Updates calories and goal progress based on entered steps
     */
    private void updateDailyGoalProgress() {
        try {
            if (!stepsField.getText().isEmpty()) {
                int steps = Integer.parseInt(stepsField.getText());
                
                // Calculate calories automatically
                int calories = calculateCaloriesFromSteps(steps);
                caloriesField.setText(String.valueOf(calories));
                
                if (!goals.isEmpty()) {
                    Goal goal = goals.get(0);
                    // Calculate progress based on calories instead of steps
                    int progress = (int)((double)calories / goal.getDailyCaloriesGoal() * 100);
                    dailyGoalLabel.setText("Daily Goal Progress: " + progress + "%");
                    
                    // Color code based on progress
                    if (progress >= 100) {
                        dailyGoalLabel.setForeground(new Color(0, 128, 0)); // Green
                    } else if (progress >= 75) {
                        dailyGoalLabel.setForeground(Color.ORANGE);
                    } else {
                        dailyGoalLabel.setForeground(Color.RED);
                    }
                } else {
                    dailyGoalLabel.setText("Daily Goal Progress: Set goals first");
                    dailyGoalLabel.setForeground(Color.BLUE);
                }
            } else {
                caloriesField.setText("");
                dailyGoalLabel.setText("Daily Goal Progress: Enter steps to see progress");
                dailyGoalLabel.setForeground(Color.BLUE);
            }
        } catch (NumberFormatException e) {
            // Ignore if steps field is not a valid number
        }
    }
    
    /**
     * Saves fitness record to the list and file
     */
    private void saveFitnessRecord() {
        try {
            int steps = Integer.parseInt(stepsField.getText());
            // Use calculated calories instead of manual input
            int calories = calculateCaloriesFromSteps(steps);
            String exerciseType = exerciseField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String notes = fitnessNotesArea.getText();
            
            String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            FitnessRecord record = new FitnessRecord(currentDate, steps, calories, 
                                                   exerciseType, duration, notes);
            fitnessRecords.add(record);
            
            saveFitnessData();
            
            updateDashboardStats();
            
            JOptionPane.showMessageDialog(this, 
                "Fitness record saved successfully! Calories calculated automatically from steps.", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for steps and duration.", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clears all fitness input fields
     */
    private void clearFitnessFields() {
        stepsField.setText("");
        caloriesField.setText("");
        exerciseField.setText("");
        durationField.setText("");
        fitnessNotesArea.setText("");
        dailyGoalLabel.setText("Enter steps to see progress");
        dailyGoalLabel.setForeground(Color.BLUE);
    }
    
    /**
     * Saves goals to the list and file
     */
    private void saveGoals() {
        try {
            double targetWeight = Double.parseDouble(goalWeightField.getText());
            int dailyStepsGoal = Integer.parseInt(goalStepsField.getText());
            int dailyCaloriesGoal = Integer.parseInt(goalCaloriesField.getText());
            String notes = goalsNotesArea.getText();
            
            Goal goal = new Goal(targetWeight, dailyStepsGoal, dailyCaloriesGoal, notes);
            goals.clear(); // Only keep one goal at a time
            goals.add(goal);
            
            saveGoalData();
            
            JOptionPane.showMessageDialog(this, 
                "Goals saved successfully!", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers for all goal fields.", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Clears all goal input fields
     */
    private void clearGoalFields() {
        goalWeightField.setText("");
        goalStepsField.setText("");
        goalCaloriesField.setText("");
        goalsNotesArea.setText("");
        goalProgressLabel.setText("Set goals to see progress");
    }
    
    /**
     * Generates a comprehensive health report
     */
    private void generateHealthReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== HEALTH & FITNESS TRACKER REPORT ===\n");
        report.append("Generated on: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n\n");
        
        // Health Records Summary
        report.append("HEALTH RECORDS SUMMARY:\n");
        report.append("========================\n");
        if (healthRecords.isEmpty()) {
            report.append("No health records found.\n");
        } else {
            report.append("Total records: ").append(healthRecords.size()).append("\n");
            HealthRecord latest = healthRecords.get(healthRecords.size() - 1);
            report.append("Latest BMI: ").append(String.format("%.2f", latest.getBmi())).append(" (").append(latest.getHealthStatus()).append(")\n");
            report.append("Latest Weight: ").append(latest.getWeight()).append(" kg\n");
        }
        
        report.append("\nFITNESS RECORDS SUMMARY:\n");
        report.append("========================\n");
        if (fitnessRecords.isEmpty()) {
            report.append("No fitness records found.\n");
        } else {
            report.append("Total records: ").append(fitnessRecords.size()).append("\n");
            int totalSteps = 0;
            int totalCalories = 0;
            for (FitnessRecord record : fitnessRecords) {
                totalSteps += record.getSteps();
                totalCalories += record.getCalories();
            }
            report.append("Total steps tracked: ").append(totalSteps).append("\n");
            report.append("Total calories burned: ").append(totalCalories).append("\n");
            report.append("Average daily steps: ").append(totalSteps / fitnessRecords.size()).append("\n");
        }
        
        report.append("\nGOALS SUMMARY:\n");
        report.append("==============\n");
        if (goals.isEmpty()) {
            report.append("No goals set.\n");
        } else {
            Goal goal = goals.get(0);
            report.append("Target Weight: ").append(goal.getTargetWeight()).append(" kg\n");
            report.append("Daily Steps Goal: ").append(goal.getDailyStepsGoal()).append("\n");
            report.append("Daily Calories Goal: ").append(goal.getDailyCaloriesGoal()).append("\n");
        }
        
        report.append("\nSDG 3: GOOD HEALTH AND WELL-BEING\n");
        report.append("==================================\n");
        report.append("This application contributes to SDG 3 by:\n");
        report.append("- Promoting healthy lifestyle monitoring\n");
        report.append("- Encouraging regular physical activity\n");
        report.append("- Providing health awareness through BMI tracking\n");
        report.append("- Supporting preventive healthcare practices\n");
        
        // Display report in the reports area
        reportsArea.setText(report.toString());
        
        // Also show a confirmation dialog
        JOptionPane.showMessageDialog(this, 
            "Health report generated successfully! View the report in the text area above.", 
            "Report Generated", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Updates dashboard statistics
     */
    private void updateDashboardStats() {
        // Update BMI Status
        if (!healthRecords.isEmpty()) {
            HealthRecord latest = healthRecords.get(healthRecords.size() - 1);
            bmiStatusLabel.setText("BMI Status: " + latest.getHealthStatus());
        }
        
        // Update Today's Steps and Calories
        if (!fitnessRecords.isEmpty()) {
            FitnessRecord latest = fitnessRecords.get(fitnessRecords.size() - 1);
            stepsLabel.setText("Today's Steps: " + latest.getSteps());
            caloriesLabel.setText("Calories Burned: " + latest.getCalories());
            
            // Calculate goal progress based on calories
            if (!goals.isEmpty()) {
                Goal goal = goals.get(0);
                int caloriesProgress = (int)((double)latest.getCalories() / goal.getDailyCaloriesGoal() * 100);
                dashboardGoalProgressLabel.setText("Goal Progress: " + caloriesProgress + "%");
            } else {
                dashboardGoalProgressLabel.setText("Goal Progress: Set goals first");
            }
        }
        
        // Calculate weekly average steps
        if (!fitnessRecords.isEmpty()) {
            int totalSteps = 0;
            int count = Math.min(7, fitnessRecords.size());
            for (int i = fitnessRecords.size() - count; i < fitnessRecords.size(); i++) {
                totalSteps += fitnessRecords.get(i).getSteps();
            }
            int avgSteps = totalSteps / count;
            weeklyAvgLabel.setText("Weekly Avg Steps: " + avgSteps);
        }
        
        // Update health score
        if (!healthRecords.isEmpty()) {
            HealthRecord latest = healthRecords.get(healthRecords.size() - 1);
            String status = latest.getHealthStatus();
            if (status.equals("Normal Weight")) {
                healthScoreLabel.setText("Health Score: Excellent");
                healthScoreLabel.setForeground(new Color(0, 128, 0));
            } else if (status.equals("Overweight")) {
                healthScoreLabel.setText("Health Score: Good");
                healthScoreLabel.setForeground(Color.ORANGE);
            } else if (status.equals("Obese")) {
                healthScoreLabel.setText("Health Score: Needs Improvement");
                healthScoreLabel.setForeground(Color.RED);
            } else {
                healthScoreLabel.setText("Health Score: Fair");
                healthScoreLabel.setForeground(Color.ORANGE);
            }
        }
    }
    
    /**
     * Clears the report display
     */
    private void clearReport() {
        reportsArea.setText("Click 'Generate Health Report' to view your data summary here...");
    }
    
    /**
     * Exports report to a file
     */
    private void exportReport() {
        try {
            String fileName = "health_report_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
            File file = new File(fileName);
            
            StringBuilder report = new StringBuilder();
            report.append("=== HEALTH & FITNESS TRACKER REPORT ===\n");
            report.append("Generated on: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n\n");
            
            // Add all health records
            report.append("DETAILED HEALTH RECORDS:\n");
            report.append("========================\n");
            if (healthRecords.isEmpty()) {
                report.append("No health records found.\n");
            } else {
                for (HealthRecord record : healthRecords) {
                    report.append("Date: ").append(record.getDate()).append("\n");
                    report.append("Weight: ").append(record.getWeight()).append(" kg\n");
                    report.append("Height: ").append(record.getHeight()).append(" cm\n");
                    report.append("Age: ").append(record.getAge()).append("\n");
                    report.append("Blood Pressure: ").append(record.getBloodPressure()).append("\n");
                    report.append("BMI: ").append(String.format("%.2f", record.getBmi())).append("\n");
                    report.append("Health Status: ").append(record.getHealthStatus()).append("\n");
                    report.append("Notes: ").append(record.getNotes()).append("\n");
                    report.append("----------------------------------------\n\n");
                }
            }
            
            // Add all fitness records
            report.append("DETAILED FITNESS RECORDS:\n");
            report.append("=========================\n");
            if (fitnessRecords.isEmpty()) {
                report.append("No fitness records found.\n");
            } else {
                for (FitnessRecord record : fitnessRecords) {
                    report.append("Date: ").append(record.getDate()).append("\n");
                    report.append("Steps: ").append(record.getSteps()).append("\n");
                    report.append("Calories Burned: ").append(record.getCalories()).append("\n");
                    report.append("Exercise Type: ").append(record.getExerciseType()).append("\n");
                    report.append("Duration: ").append(record.getDuration()).append(" minutes\n");
                    report.append("Notes: ").append(record.getNotes()).append("\n");
                    report.append("----------------------------------------\n\n");
                }
            }
            
            // Add goals
            report.append("CURRENT GOALS:\n");
            report.append("==============\n");
            if (goals.isEmpty()) {
                report.append("No goals set.\n");
            } else {
                Goal goal = goals.get(0);
                report.append("Target Weight: ").append(goal.getTargetWeight()).append(" kg\n");
                report.append("Daily Steps Goal: ").append(goal.getDailyStepsGoal()).append("\n");
                report.append("Daily Calories Goal: ").append(goal.getDailyCaloriesGoal()).append("\n");
                report.append("Goal Notes: ").append(goal.getNotes()).append("\n");
            }
            
            // Add summary statistics
            report.append("\nSUMMARY STATISTICS:\n");
            report.append("==================\n");
            if (!healthRecords.isEmpty()) {
                HealthRecord latestHealth = healthRecords.get(healthRecords.size() - 1);
                report.append("Latest BMI: ").append(String.format("%.2f", latestHealth.getBmi())).append(" (").append(latestHealth.getHealthStatus()).append(")\n");
                report.append("Latest Weight: ").append(latestHealth.getWeight()).append(" kg\n");
            }
            
            if (!fitnessRecords.isEmpty()) {
                int totalSteps = 0;
                int totalCalories = 0;
                for (FitnessRecord record : fitnessRecords) {
                    totalSteps += record.getSteps();
                    totalCalories += record.getCalories();
                }
                report.append("Total Steps Tracked: ").append(totalSteps).append("\n");
                report.append("Total Calories Burned: ").append(totalCalories).append("\n");
                report.append("Average Daily Steps: ").append(totalSteps / fitnessRecords.size()).append("\n");
            }
            
            // Add SDG 3 information
            report.append("\nSDG 3: GOOD HEALTH AND WELL-BEING\n");
            report.append("==================================\n");
            report.append("This application contributes to SDG 3 by:\n");
            report.append("- Promoting healthy lifestyle monitoring\n");
            report.append("- Encouraging regular physical activity\n");
            report.append("- Providing health awareness through BMI tracking\n");
            report.append("- Supporting preventive healthcare practices\n");
            report.append("- Empowering individuals to take control of their health\n");
            
            // Write to file
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.write(report.toString());
            }
            
            JOptionPane.showMessageDialog(this, 
                "Complete report exported to: " + fileName, 
                "Export Success", JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "Error exporting report: " + e.getMessage(), 
                "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Loads data from files
     */
    private void loadData() {
        loadHealthData();
        loadFitnessData();
        loadGoalData();
    }
    
    /**
     * Loads health data from file
     */
    private void loadHealthData() {
        try {
            File file = new File(HEALTH_FILE);
            if (file.exists()) {
                // Implementation for loading health data
            }
        } catch (Exception e) {
            System.err.println("Error loading health data: " + e.getMessage());
        }
    }
    
    /**
     * Loads fitness data from file
     */
    private void loadFitnessData() {
        try {
            File file = new File(FITNESS_FILE);
            if (file.exists()) {
                // Implementation for loading fitness data
            }
        } catch (Exception e) {
            System.err.println("Error loading fitness data: " + e.getMessage());
        }
    }
    
    /**
     * Loads goal data from file
     */
    private void loadGoalData() {
        try {
            File file = new File(GOALS_FILE);
            if (file.exists()) {
                // Implementation for loading goal data
            }
        } catch (Exception e) {
            System.err.println("Error loading goal data: " + e.getMessage());
        }
    }
    
    /**
     * Saves health data to file
     */
    private void saveHealthData() {
        try {
            // Implementation for saving health data
        } catch (Exception e) {
            System.err.println("Error saving health data: " + e.getMessage());
        }
    }
    
    /**
     * Saves fitness data to file
     */
    private void saveFitnessData() {
        try {
            // Implementation for saving fitness data
        } catch (Exception e) {
            System.err.println("Error saving fitness data: " + e.getMessage());
        }
    }
    
    /**
     * Saves goal data to file
     */
    private void saveGoalData() {
        try {
            // Implementation for saving goal data
        } catch (Exception e) {
            System.err.println("Error saving goal data: " + e.getMessage());
        }
    }
    
    /**
     * Main method to run the application
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            HealthFitnessTracker app = new HealthFitnessTracker();
            app.setVisible(true);
        });
    }
}

/**
 * Health Record class to store health data
 */
class HealthRecord {
    private String date;
    private double weight;
    private double height;
    private int age;
    private String bloodPressure;
    private double bmi;
    private String healthStatus;
    private String notes;
    
    public HealthRecord(String date, double weight, double height, int age, 
                       String bloodPressure, double bmi, String healthStatus, String notes) {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.bloodPressure = bloodPressure;
        this.bmi = bmi;
        this.healthStatus = healthStatus;
        this.notes = notes;
    }
    
    // Getters and setters
    public String getDate() { return date; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getAge() { return age; }
    public String getBloodPressure() { return bloodPressure; }
    public double getBmi() { return bmi; }
    public String getHealthStatus() { return healthStatus; }
    public String getNotes() { return notes; }
}

/**
 * Fitness Record class to store fitness data
 */
class FitnessRecord {
    private String date;
    private int steps;
    private int calories;
    private String exerciseType;
    private int duration;
    private String notes;
    
    public FitnessRecord(String date, int steps, int calories, 
                        String exerciseType, int duration, String notes) {
        this.date = date;
        this.steps = steps;
        this.calories = calories;
        this.exerciseType = exerciseType;
        this.duration = duration;
        this.notes = notes;
    }
    
    // Getters and setters
    public String getDate() { return date; }
    public int getSteps() { return steps; }
    public int getCalories() { return calories; }
    public String getExerciseType() { return exerciseType; }
    public int getDuration() { return duration; }
    public String getNotes() { return notes; }
}

/**
 * Goal class to store user goals
 */
class Goal {
    private double targetWeight;
    private int dailyStepsGoal;
    private int dailyCaloriesGoal;
    private String notes;
    
    public Goal(double targetWeight, int dailyStepsGoal, int dailyCaloriesGoal, String notes) {
        this.targetWeight = targetWeight;
        this.dailyStepsGoal = dailyStepsGoal;
        this.dailyCaloriesGoal = dailyCaloriesGoal;
        this.notes = notes;
    }
    
    // Getters and setters
    public double getTargetWeight() { return targetWeight; }
    public int getDailyStepsGoal() { return dailyStepsGoal; }
    public int getDailyCaloriesGoal() { return dailyCaloriesGoal; }
    public String getNotes() { return notes; }
}
