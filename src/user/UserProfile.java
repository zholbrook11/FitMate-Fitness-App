package user;

public class UserProfile {
    public String name;
    public int age;
    private double weight;
    private double height;
    private String gender;
    private String activityLevel;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserProfile(String name, int age, String gender, String activityLevel) {
        this(name, age); 
        this.gender = gender;
        this.activityLevel = activityLevel;
    }

    public UserProfile(String name, int age, double weight, double height, String gender, String activityLevel) {
        this(name, age, gender, activityLevel); 
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void displayProfile() {
        System.out.println("\nUser Profile:");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Height: " + height + " m");
        if (gender != null) {
            System.out.println("Gender: " + gender);
        }
        if (activityLevel != null) {
            System.out.println("Activity Level: " + activityLevel);
        }
    }

    public final void resetProfile() {
        this.weight = 0.0;
        this.height = 0.0;
        this.gender = null;
        this.activityLevel = null;
        System.out.println("Profile has been reset.");
    }
}
