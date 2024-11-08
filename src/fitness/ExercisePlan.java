package fitness;

public class ExercisePlan extends WorkoutPlan {
    public String intensity;

    public ExercisePlan() {
        // Default constructor
    }

    public ExercisePlan(String intensity) {
        this.intensity = intensity;
    }

    @Override
    public void generatePlan() {
        // Generate exercise plan
    }

    @Override
    public void displayPlan() {
        // Display exercise plan
    }
}
