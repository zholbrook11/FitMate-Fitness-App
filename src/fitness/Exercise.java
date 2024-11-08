package fitness;

public class Exercise {
    public String name;
    public int sets;

    public Exercise() {
        // Default constructor
    }

    public Exercise(String name, int sets) {
        this.name = name;
        this.sets = sets;
    }

    public void setReps() {
        // Set reps with default
    }

    public void setReps(int reps) {
        // Set reps with specific number
    }

    private int calculateDuration() {
        // Calculate duration
        return 0;
    }
}
