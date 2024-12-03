package health;

import user.UserProfile;

public class BMICalculator extends HealthMetrics {
    private double bmi;
    private UserProfile userProfile;

    public BMICalculator() {
    }

    public BMICalculator(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public static double calculateBMI(double weight, double height) {
        if (height <= 0) return 0.0;
        
        double heightInchesSquared = height * height;

        return 703 * (weight/heightInchesSquared);
    }
    

    public static double calculateBMI(double weight, double height, int age) {
        return calculateBMI(weight, height);
    }

    protected void displayBMI() {
        System.out.printf("BMI: %.2f\n", bmi);
    }

    @Override
    public void calculate() {
        if (userProfile != null) {
            bmi = calculateBMI(userProfile.getWeight(), userProfile.getHeight());
        }
    }

    @Override
    public void display() {
        displayBMI();
    }
}
