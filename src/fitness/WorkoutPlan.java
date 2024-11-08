package fitness;

import interfaces.Plan;

public class WorkoutPlan implements Plan {
    public String planType;
    public String duration;

    public WorkoutPlan() {
        // Default constructor
    }

    public WorkoutPlan(String planType) {
        this.planType = planType;
    }

    public void generatePlan() {
        // Generate workout plan
    }

    public void displayPlan() {
        // Display workout plan
    }
}
