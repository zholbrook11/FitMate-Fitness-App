package user;

import interfaces.Trackable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GoalTracker implements Trackable {
    private UserProfile userProfile;
    private ObservableList<CheckBox> goalList;
    private ListView<CheckBox> goalListView;  

    public GoalTracker(UserProfile userProfile) {
        this.userProfile = userProfile;
        this.goalList = FXCollections.observableArrayList();
        this.goalListView = new ListView<>(goalList);  
    }

    public void addGoal(String goalText) {
        CheckBox goalCheckBox = new CheckBox(goalText);
        goalList.add(goalCheckBox);
    }

    public void clearGoals() {
        goalList.clear();
    }

    public void showGoalTracker(Stage primaryStage) {
        VBox vbox = new VBox(10);

        Button addGoalButton = new Button("Add Goal");
        addGoalButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Goal");
            dialog.setHeaderText("Enter your new fitness goal:");
            dialog.setContentText("Goal:");

            dialog.showAndWait().ifPresent(goalText -> {
                if (!goalText.isEmpty()) {
                    addGoal(goalText);
                }
            });
        });

        Button clearGoalsButton = new Button("Clear Goals");
        clearGoalsButton.setOnAction(event -> clearGoals());

        vbox.getChildren().addAll(goalListView, addGoalButton, clearGoalsButton);

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setTitle("Your Goals");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void calculateProgress() {
    }
    @Override
    public void resetProgress() {
        for (CheckBox goal : goalList) {
            goal.setSelected(false);
        }
    }
    
}
