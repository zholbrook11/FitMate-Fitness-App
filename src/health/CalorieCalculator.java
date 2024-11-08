package health;

import user.UserProfile;

public class CalorieCalculator extends HealthMetrics {
    public CalorieCalculator() {
        // Default constructor
    }

    public CalorieCalculator(UserProfile userProfile) {
        // Constructor with user profile
    }

    public static int calculateCalories(int age, double weight, double height) {
        // Calculate maintenance calories
        return 0;
    }

    public static int calculateCalories(String activityLevel, int age) {
        // Overloaded method to calculate calories with activity level
        return 0;
    }

    protected void displayCalories() {
        // Display calculated calories
    }

    @Override
    public void calculate() {
        // Perform calorie calculation
    }

    @Override
    public void display() {
        // Display calorie calculation result or details
        System.out.println("Displaying calorie details...");
    }
}
