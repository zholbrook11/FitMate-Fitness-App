package user;

import interfaces.Trackable;

public class GoalTracker implements Trackable {
    public int weightGoal;
    public int workoutGoal;

    public GoalTracker() {
        // Default constructor
    }

    public GoalTracker(int weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void updateGoal() {
        // Update goal logic
    }

    public void displayGoal() {
        // Display goal
    }

    public final void resetGoal() {
        // Reset goal to default
    }

    @Override
    public void calculateProgress() {
        // Track progress
    }

    @Override
    public void resetProgress() {
        // Reset progress
    }
}
