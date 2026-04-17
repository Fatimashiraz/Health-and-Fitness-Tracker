/**
 * Test class for Health and Fitness Tracker
 * Demonstrates the functionality of the application
 * 
 * @author Student
 * @version 1.0
 */
public class TestHealthTracker {
    
    public static void main(String[] args) {
        System.out.println("=== Health & Fitness Tracker - SDG 3 Test ===\n");
        
        // Test BMI calculations
        testBMICalculations();
        
        // Test health status assessments
        testHealthStatus();
        
        // Test data structures
        testDataStructures();
        
        System.out.println("\n=== Test Completed Successfully ===");
        System.out.println("Run 'java HealthFitnessTracker' to launch the GUI application");
    }
    
    /**
     * Test BMI calculation functionality
     */
    private static void testBMICalculations() {
        System.out.println("Testing BMI Calculations:");
        System.out.println("------------------------");
        
        // Test cases
        double[][] testCases = {
            {70.0, 170.0},  // Normal weight
            {90.0, 170.0},  // Overweight
            {50.0, 170.0},  // Underweight
            {100.0, 170.0}  // Obese
        };
        
        for (int i = 0; i < testCases.length; i++) {
            double weight = testCases[i][0];
            double height = testCases[i][1];
            double bmi = calculateBMI(weight, height);
            String status = getHealthStatus(bmi);
            
            System.out.printf("Weight: %.1f kg, Height: %.1f cm -> BMI: %.2f (%s)%n", 
                            weight, height, bmi, status);
        }
        System.out.println();
    }
    
    /**
     * Test health status assessment
     */
    private static void testHealthStatus() {
        System.out.println("Testing Health Status Assessment:");
        System.out.println("--------------------------------");
        
        double[] bmiValues = {16.0, 20.0, 27.0, 35.0};
        String[] expectedStatus = {"Underweight", "Normal Weight", "Overweight", "Obese"};
        
        for (int i = 0; i < bmiValues.length; i++) {
            String status = getHealthStatus(bmiValues[i]);
            System.out.printf("BMI %.1f -> %s (Expected: %s)%n", 
                            bmiValues[i], status, expectedStatus[i]);
        }
        System.out.println();
    }
    
    /**
     * Test data structures and classes
     */
    private static void testDataStructures() {
        System.out.println("Testing Data Structures:");
        System.out.println("----------------------");
        
        // Test HealthRecord
        HealthRecord healthRecord = new HealthRecord("2024-01-15", 70.0, 170.0, 25, 
                                                   "120/80", 24.2, "Normal Weight", "Feeling good today");
        System.out.println("Health Record created: " + healthRecord.getDate());
        System.out.println("BMI: " + healthRecord.getBmi());
        System.out.println("Status: " + healthRecord.getHealthStatus());
        
        // Test FitnessRecord
        FitnessRecord fitnessRecord = new FitnessRecord("2024-01-15", 8000, 400, 
                                                       "Walking", 60, "Morning walk in the park");
        System.out.println("Fitness Record created: " + fitnessRecord.getDate());
        System.out.println("Steps: " + fitnessRecord.getSteps());
        System.out.println("Calories: " + fitnessRecord.getCalories());
        
        // Test Goal
        Goal goal = new Goal(65.0, 10000, 500, "Lose 5kg and increase daily activity");
        System.out.println("Goal created:");
        System.out.println("Target Weight: " + goal.getTargetWeight() + " kg");
        System.out.println("Daily Steps Goal: " + goal.getDailyStepsGoal());
        System.out.println("Daily Calories Goal: " + goal.getDailyCaloriesGoal());
        System.out.println();
    }
    
    /**
     * Calculate BMI based on weight and height
     */
    private static double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);
    }
    
    /**
     * Determine health status based on BMI
     */
    private static String getHealthStatus(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 25) return "Normal Weight";
        else if (bmi < 30) return "Overweight";
        else return "Obese";
    }
}
