package health;

import user.UserProfile;

public class BMICalculator extends HealthMetrics {
    private double bmi;
    private UserProfile userProfile;

    public BMICalculator() {
        // Default constructor
    }

    public BMICalculator(UserProfile userProfile) {
        // Constructor with user profile
        this.userProfile = userProfile;
    }

    public static double calculateBMI(double weight, double height) {
        if (height <= 0) return 0.0;
        
        double heightInchesSquared = height * height;

        return 703 * (weight/heightInchesSquared);
    }
    

    public static double calculateBMI(double weight, double height, int age) {
        // Simplified BMI calculation ignoring age for demonstration
        return calculateBMI(weight, height);
    }

    protected void displayBMI() {
        System.out.printf("BMI: %.2f\n", bmi);
    }

    @Override
    public void calculate() {
        // Perform BMI calculation using user profile data
        if (userProfile != null) {
            bmi = calculateBMI(userProfile.getWeight(), userProfile.getHeight());
        }
    }

    @Override
    public void display() {
        // Display BMI result
        displayBMI();
    }
}
