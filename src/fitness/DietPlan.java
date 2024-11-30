package fitness;

public class DietPlan {
    private double maintenanceCalories;

    public DietPlan(double maintenanceCalories) {
        this.maintenanceCalories = maintenanceCalories;
    }

    public String getAdjustedCalories(String goal) {
        double adjustedCalories = maintenanceCalories;
        double proteinPercentage = 0.3;
        double carbPercentage = 0.4;
        double fatPercentage = 0.3;

        switch (goal.toLowerCase()) {
            case "lose weight":
                adjustedCalories = maintenanceCalories - 500;
                break;
            case "gain weight":
                adjustedCalories = maintenanceCalories + 500;
                break;
            case "maintain weight":
            default:
                break;
        }

        double proteinCalories = adjustedCalories * proteinPercentage;
        double carbCalories = adjustedCalories * carbPercentage;
        double fatCalories = adjustedCalories * fatPercentage;

        return String.format(
            "<b>Your goal:</b> %s<br>" +
            "<b>Adjusted Calories:</b> %.0f kcal/day<br>" +
            "<b>Protein:</b> %.0f kcal/day (%.0f%%)<br>" +
            "<b>Carbs:</b> %.0f kcal/day (%.0f%%)<br>" +
            "<b>Fat:</b> %.0f kcal/day (%.0f%%)<br><br>" +
            "<b>Healthy Protein Options:</b> Beef, Chicken, Pork, Fish, Eggs<br>" +
            "<b>Healthy Carb Options:</b> Fruit, Brown/White Rice, Potatoes, Sourdough bread<br>" +
            "<b>Healthy Fat Options:</b> Avocados, Cheese, Olive Oil, Almonds, Walnuts",
            goal, adjustedCalories, proteinCalories, proteinPercentage * 100, 
            carbCalories, carbPercentage * 100, fatCalories, fatPercentage * 100
        );
    }
}
