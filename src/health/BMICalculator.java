package health;

import user.UserProfile;

public class BMICalculator extends HealthMetrics {
    private double bmi;

    public BMICalculator() {
        // Default constructor
    }

    public BMICalculator(UserProfile userProfile) {
        // Constructor with user profile
    }

    public static double calculateBMI(double weight, double height) {
        // Calculate BMI
        return 0.0;
    }

    public static double calculateBMI(double weight, double height, int age) {
        // Calculate BMI with age
        return 0.0;
    }

    protected void displayBMI() {
        // Display BMI
    }

    @Override
    public void calculate() {
        // Perform BMI calculation
    }

    @Override
    public void display() {
        // Display BMI result or details
        System.out.println("Displaying BMI details...");
    }
}
