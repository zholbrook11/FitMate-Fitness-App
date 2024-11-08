package user;

public class UserProfile {
    public String name;
    public int age;
    private double weight;
    private double height;
    protected String gender;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserProfile(String name, int age, String gender) {
        this(name, age);
        this.gender = gender;
    }

    public void updateProfile() {
        // Update profile logic
    }

    public void displayProfile() {
        // Display profile details
    }

    public final void resetProfile() {
        // Reset profile to default
    }
}
