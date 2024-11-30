package fitness;

public class ExercisePlan {
    public String intensity;

    public ExercisePlan() {
        this.intensity = "Moderate";
    }

    public ExercisePlan(String intensity) {
        this.intensity = intensity;
    }

    public static String[] generateWeeklyWorkout(String goal) {
        if ("Muscle Gain".equals(goal)) {
            return new String[]{
                "Chest & Triceps:  Bench Press, Dumbbell Flys, Tricep Pushdowns",
                "Back & Biceps:  Pull-Ups, Dumbbell Rows, Bicep Curls",
                "Legs & Abs:  Squats, Leg Press, Crunches",
                "Rest or Light Cardio:  30-minute walk or light jog",
                "Shoulders & Arms:  Shoulder Press, Lateral Raises, Hammer Curls",
                "Full Body:  Deadlifts, Pull-Ups, Push-Ups, Plank",
                "Rest or Light Cardio:  Yoga, stretching, or a light run"
            };
        } else {
            return new String[]{
                "Cardio & Legs:  30-minute Run/Bike, Squats, Lunges",
                "Upper Body & Cardio:  Push-Ups, Pull-Ups, Jump Rope",
                "Core & Cardio:  Plank, Crunches, 20-minute cycling",
                "Rest or Light Activity:  Brisk walk, Stretching",
                "Full Body & Cardio:  Circuit training with bodyweight exercises",
                "Cardio & Abs:  30-minute moderate run, Crunches",
                "Rest or Light Cardio:  Running, Biking, or Yoga"
            };
        }
    }
}
