package health;

import user.UserProfile;

public class CalorieCalculator extends HealthMetrics {
    private int maintenanceCalories;
    private UserProfile userProfile;

    public CalorieCalculator(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    // Calculate BMR for men using Mifflin-St Jeor Equation
    private static int calculateBMRForMen(int age, double weight, double height) {
        return (int) ((10 * weight) + (6.25 * height) - (5 * age) + 5);
    }

    // Calculate BMR for women using Mifflin-St Jeor Equation
    private static int calculateBMRForWomen(int age, double weight, double height) {
        return (int) ((10 * weight) + (6.25 * height) - (5 * age) - 161);
    }

    // Calculate TDEE based on BMR and activity level
    private static int calculateTDEE(int bmr, String activityLevel) {
        double activityFactor;
        switch (activityLevel.toLowerCase()) {
            case "sedentary":
                activityFactor = 1.2;
                break;
            case "lightly active":
                activityFactor = 1.375;
                break;
            case "moderately active":
                activityFactor = 1.55;
                break;
            case "very active":
                activityFactor = 1.725;
                break;
            case "super active":
                activityFactor = 1.9;
                break;
            default:
                activityFactor = 1.2;
                break;
        }
        return (int) (bmr * activityFactor);
    }

    // Calculate maintenance calories using the user profile
    public void calculateCalories() {
        if (userProfile != null) {
            double weightInPounds = userProfile.getWeight();
            double heightInInches = userProfile.getHeight();
            int bmr;

            if (userProfile.getGender().equalsIgnoreCase("male")) {
                bmr = calculateBMRForMen(userProfile.getAge(), weightInPounds, heightInInches);
            } else {
                bmr = calculateBMRForWomen(userProfile.getAge(), weightInPounds, heightInInches);
            }

            // Calculate TDEE using the activity level from the user profile
            maintenanceCalories = calculateTDEE(bmr, userProfile.getActivityLevel());
        }
    }

    // Method to get the calculated maintenance calories
    public int getMaintenanceCalories() {
        return maintenanceCalories;
    }

    @Override
    public void calculate() {
        calculateCalories(); // Perform calorie calculation using user profile data
    }

    @Override
    public void display() {
        System.out.println("Maintenance Calories: " + maintenanceCalories + " kcal/day");
    }
}
