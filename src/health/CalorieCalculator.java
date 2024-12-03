package health;

import user.UserProfile;

public class CalorieCalculator extends HealthMetrics {
    private int maintenanceCalories;
    private UserProfile userProfile;

    public CalorieCalculator(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    // men
    private static int calculateBMRForMen(int age, double weight, double height) {
        return (int) ((10 * weight) + (6.25 * height) - (5 * age) + 5);
    }

    // women
    private static int calculateBMRForWomen(int age, double weight, double height) {
        return (int) ((10 * weight) + (6.25 * height) - (5 * age) - 161);
    }

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

            maintenanceCalories = calculateTDEE(bmr, userProfile.getActivityLevel());
        }
    }

    public int getMaintenanceCalories() {
        return maintenanceCalories;
    }

    @Override
    public void calculate() {
        calculateCalories();
    }

    @Override
    public void display() {
        System.out.println("Maintenance Calories: " + maintenanceCalories + " kcal/day");
    }
}
