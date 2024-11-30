package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.stage.Stage;

import user.UserProfile;
import user.GoalTracker;

import health.BMICalculator;
import health.CalorieCalculator;

import java.util.Optional;

import fitness.DietPlan;
import fitness.ExercisePlan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainAppFX extends Application {
    private UserProfile userProfile;
    private BMICalculator bmiCalculator;
    private CalorieCalculator calorieCalculator;
    private GoalTracker goalTracker;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FitMate");

        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-image: url('/resources/FitMateBackground2.png'); " +
                "-fx-background-size: cover; " +
                "-fx-background-repeat: no-repeat;");
        mainLayout.setPadding(new Insets(50, 50, 50, 50));

        MenuBar menuBar = createMenuBar();
        mainLayout.setTop(menuBar);

        Label welcomeLabel = new Label("FitMate");
        welcomeLabel.getStyleClass().add("welcome-label");

        VBox welcomeBox = new VBox(welcomeLabel);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setPadding(new Insets(10));

        Button userProfileBtn = new Button("View/Update User Profile");
        userProfileBtn.setId("userProfileBtn");
        userProfileBtn.setMaxWidth(Double.MAX_VALUE);

        HBox userProfileBox = new HBox();
        userProfileBox.setAlignment(Pos.CENTER);
        userProfileBox.setPadding(new Insets(10));
        userProfileBox.getChildren().add(userProfileBtn);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(20);
        buttonGrid.setVgap(20);
        buttonGrid.setPadding(new Insets(20, 0, 0, 0));

        Button bmiBtn = new Button("Calculate your BMI");
        bmiBtn.setId("bmiBtn");
        bmiBtn.setMaxWidth(Double.MAX_VALUE);

        Button calorieBtn = new Button("Your Maintenance Calories");
        calorieBtn.setId("calorieBtn");
        calorieBtn.setMaxWidth(Double.MAX_VALUE);

        Button workoutPlanBtn = new Button("Generate Workout Plan");
        workoutPlanBtn.setId("workoutPlanBtn");
        workoutPlanBtn.setMaxWidth(Double.MAX_VALUE);

        Button dietPlanBtn = new Button("Generate Diet Plan");
        dietPlanBtn.setId("dietPlanBtn");
        dietPlanBtn.setMaxWidth(Double.MAX_VALUE);

        Button exerciseListBtn = new Button("View Exercises");
        exerciseListBtn.setId("exerciseListBtn");
        exerciseListBtn.setMaxWidth(Double.MAX_VALUE);

        Button goalTrackerBtn = new Button("Your Goals");
        goalTrackerBtn.setId("goalTrackerBtn");
        goalTrackerBtn.setMaxWidth(Double.MAX_VALUE);

        buttonGrid.add(bmiBtn, 0, 0);
        buttonGrid.add(calorieBtn, 1, 0);
        buttonGrid.add(workoutPlanBtn, 0, 1);
        buttonGrid.add(dietPlanBtn, 1, 1);
        buttonGrid.add(exerciseListBtn, 0, 2);
        buttonGrid.add(goalTrackerBtn, 1, 2);
        buttonGrid.setAlignment(Pos.CENTER);

        VBox centerLayout = new VBox(10);
        centerLayout.setAlignment(Pos.TOP_CENTER);
        centerLayout.setPadding(new Insets(20, 50, 50, 50));
        centerLayout.getChildren().addAll(userProfileBox, buttonGrid);

        mainLayout.setCenter(welcomeBox);
        mainLayout.setBottom(centerLayout);

        Scene scene = new Scene(mainLayout, 1400, 800);

        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();

        userProfileBtn.setOnAction(e -> {
            if (userProfile == null) {
                showUserProfileDialog();
            } else {
                showUserProfileDetails();
            }
        });
        
        bmiBtn.setOnAction(e -> calculateBMI());
        calorieBtn.setOnAction(e -> calculateCalories());
        workoutPlanBtn.setOnAction(e -> generateWorkoutPlan());
        dietPlanBtn.setOnAction(e -> generateDietPlan());
        exerciseListBtn.setOnAction(e -> showExerciseListDialog());
        goalTrackerBtn.setOnAction(e -> trackGoalsDialog());
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void showUserProfileDialog() {
        Dialog<UserProfile> dialog = new Dialog<>();
        dialog.setTitle("User Profile");
        dialog.setHeaderText("Enter your user information:");
    
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);
    
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
    
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        TextField weightField = new TextField();
        weightField.setPromptText("Weight (lb)");
        TextField heightField = new TextField();
        heightField.setPromptText("Height (inches)");
    
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female");
        genderComboBox.setPromptText("Gender");
    
        ComboBox<String> activityLevelComboBox = new ComboBox<>();
        activityLevelComboBox.getItems().addAll(
            "Sedentary (little to no exercise)",
            "Lightly Active (light exercise/sports 1-3 days/week)",
            "Moderately Active (moderate exercise/sports 3-5 days/week)",
            "Very Active (hard exercise/sports 6-7 days/week)",
            "Super Active (very hard exercise/sports & physical job)"
        );
        activityLevelComboBox.setPromptText("Activity Level");
    
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Age:"), 0, 1);
        grid.add(ageField, 1, 1);
        grid.add(new Label("Weight:"), 0, 2);
        grid.add(weightField, 1, 2);
        grid.add(new Label("Height:"), 0, 3);
        grid.add(heightField, 1, 3);
        grid.add(new Label("Gender:"), 0, 4);
        grid.add(genderComboBox, 1, 4);
        grid.add(new Label("Activity Level:"), 0, 5);
        grid.add(activityLevelComboBox, 1, 5);
    
        dialog.getDialogPane().setContent(grid);
    
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                String gender = genderComboBox.getValue(); 
                String activityLevel = activityLevelComboBox.getValue();
    
                userProfile = new UserProfile(name, age);
                userProfile.setWeight(weight);
                userProfile.setHeight(height);
                userProfile.setGender(gender);
                userProfile.setActivityLevel(activityLevel);
    
                bmiCalculator = new BMICalculator(userProfile);
                calorieCalculator = new CalorieCalculator(userProfile);
    
                return userProfile;
            }
            return null;
        });
    
        dialog.showAndWait();
    }

    private void showUserProfileDetails() {
        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-font-weight: bold;");
        Label nameValue = new Label(userProfile.getName());
    
        Label ageLabel = new Label("Age:");
        ageLabel.setStyle("-fx-font-weight: bold;");
        Label ageValue = new Label(String.valueOf(userProfile.getAge()));
    
        Label weightLabel = new Label("Weight:");
        weightLabel.setStyle("-fx-font-weight: bold;");
        Label weightValue = new Label(userProfile.getWeight() + " lbs");
    
        Label heightLabel = new Label("Height:");
        heightLabel.setStyle("-fx-font-weight: bold;");
        Label heightValue = new Label(userProfile.getHeight() + " inches");
    
        Label genderLabel = new Label("Gender:");
        genderLabel.setStyle("-fx-font-weight: bold;");
        Label genderValue = new Label(userProfile.getGender());
    
        VBox profileDetailsVBox = new VBox(10);
        profileDetailsVBox.getChildren().addAll(nameLabel, nameValue,
                                                ageLabel, ageValue,
                                                weightLabel, weightValue,
                                                heightLabel, heightValue,
                                                genderLabel, genderValue);
    
        Alert profileDialog = new Alert(Alert.AlertType.INFORMATION);
        profileDialog.setTitle("User Profile");
        profileDialog.setHeaderText("Your Profile Details");
        profileDialog.getDialogPane().setContent(profileDetailsVBox);
    
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        profileDialog.getButtonTypes().setAll(closeButton);
    
        profileDialog.showAndWait();
    }

    private void calculateBMI() {
        if (userProfile == null) {
            showAlert("User Profile Missing", "Please set up your user profile first.");
            return;
        }
    
        bmiCalculator.calculate();
        String bmiText = "Your BMI: " + String.format("%.2f", bmiCalculator.calculateBMI(userProfile.getWeight(), userProfile.getHeight()));
    
        Alert bmiDialog = new Alert(Alert.AlertType.INFORMATION);
        bmiDialog.setTitle("BMI Calculator");
        bmiDialog.setHeaderText(null);
    
        Label bmiLabel = new Label(bmiText);
        
        bmiLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
    
        bmiDialog.getDialogPane().setContent(bmiLabel);

        bmiDialog.getDialogPane().setMinWidth(400); 
        bmiDialog.getDialogPane().setMinHeight(200);
    
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        bmiDialog.getButtonTypes().setAll(closeButton);
    
        bmiDialog.showAndWait();
    }

    private void calculateCalories() {
        if (userProfile == null) {
            showAlert("User Profile Missing", "Please set up your user profile first.");
            return;
        }
        
        calorieCalculator.calculateCalories();
        
        String calorieText = "Your Maintenance Calories: " + calorieCalculator.getMaintenanceCalories() + " kcal/day";
        
        Alert calorieDialog = new Alert(Alert.AlertType.INFORMATION);
        calorieDialog.setTitle("Calorie Calculator");
        calorieDialog.setHeaderText(null);
    
        Label calorieLabel = new Label(calorieText);
        calorieLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");
    
        calorieDialog.getDialogPane().setContent(calorieLabel);
    
        calorieDialog.getDialogPane().setMinWidth(400);
        calorieDialog.getDialogPane().setMinHeight(200);
    
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        calorieDialog.getButtonTypes().setAll(closeButton);
    
        calorieDialog.showAndWait();
    }

    private void generateWorkoutPlan() {
        if (userProfile == null) {
            showAlert("User Profile Missing", "Please set up your user profile first.");
            return;
        }

        Alert goalDialog = new Alert(Alert.AlertType.CONFIRMATION);
        goalDialog.setTitle("Workout Goal");
        goalDialog.setHeaderText("What is your main fitness goal?");
        goalDialog.setContentText("Choose your goal:");
    
        ButtonType muscleGainBtn = new ButtonType("Put on Muscle");
        ButtonType weightLossBtn = new ButtonType("Lose Weight");
    
        goalDialog.getButtonTypes().setAll(muscleGainBtn, weightLossBtn, ButtonType.CANCEL);
    
        Optional<ButtonType> result = goalDialog.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.CANCEL) {
            return;
        }
    
        String goal = result.get() == muscleGainBtn ? "Muscle Gain" : "Weight Loss";
        String[] workoutPlan = ExercisePlan.generateWeeklyWorkout(goal);
    
        Alert workoutDialog = new Alert(Alert.AlertType.INFORMATION);
        workoutDialog.setTitle("Workout Plan");
        workoutDialog.setHeaderText("Your Weekly Workout Plan");
    
        GridPane workoutGrid = new GridPane();
        workoutGrid.setHgap(10);
        workoutGrid.setVgap(10);
        workoutGrid.setPadding(new Insets(20));
    
        workoutGrid.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #ccc; -fx-border-width: 1px;");
    
        for (int i = 0; i < workoutPlan.length; i++) {
            Label dayLabel = new Label("Day " + (i + 1));
            dayLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            
            Label workoutLabel = new Label(workoutPlan[i]);
            workoutLabel.setWrapText(true);
            workoutLabel.setStyle("-fx-font-size: 12px;");
    
            workoutGrid.add(dayLabel, 0, i);
            workoutGrid.add(workoutLabel, 1, i);
        }
    
        workoutDialog.getDialogPane().setContent(workoutGrid);
    
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        workoutDialog.getButtonTypes().setAll(closeButton);
    
        workoutDialog.showAndWait();
    }   

    private void generateDietPlan() {
        if (userProfile == null) {
            showAlert("User Profile Missing", "Please set up your user profile first.");
            return;
        }

        calorieCalculator.calculateCalories();
    
        Alert goalDialog = new Alert(Alert.AlertType.CONFIRMATION);
        goalDialog.setTitle("Diet Goal");
        goalDialog.setHeaderText("Select Your Goal:");
        goalDialog.setContentText("");
    
        ButtonType loseWeightBtn = new ButtonType("Lose Weight");
        ButtonType maintainWeightBtn = new ButtonType("Maintain Weight");
        ButtonType gainWeightBtn = new ButtonType("Gain Weight");
    
        goalDialog.getButtonTypes().setAll(loseWeightBtn, maintainWeightBtn, gainWeightBtn, ButtonType.CANCEL);
    
        Optional<ButtonType> result = goalDialog.showAndWait();
        if (result.isEmpty() || result.get() == ButtonType.CANCEL) {
            return;
        }
    
        String goal = result.get() == loseWeightBtn ? "Lose Weight" :
                      result.get() == maintainWeightBtn ? "Maintain Weight" : "Gain Weight";
    
        DietPlan dietPlan = new DietPlan(calorieCalculator.getMaintenanceCalories());
        String dietInfo = dietPlan.getAdjustedCalories(goal);
    
        Stage dietStage = new Stage();
        dietStage.setTitle("Your Diet Plan");
    
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(dietInfo, "text/html");
    
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dietStage.close());
        
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(webView, closeButton);
    
        Scene scene = new Scene(vbox, 400, 400);
        dietStage.setScene(scene);
        dietStage.show();
    }

    private void showExerciseListDialog() {
        if (userProfile == null) {
            showAlert("User Profile Missing", "Please set up your user profile first.");
            return;
        }
        
        ObservableList<String> exercises = FXCollections.observableArrayList(
            "CHEST:", "Bench Press", "Dumbell flys", "Push-ups", 
            "BACK:", "Pull-ups", "Dumbell Row", "Lat pulldown",
            "ARMS:", "Tricep pushdown", "Bicep curl", 
            "LEGS:", "Squats", "Leg extension", "Hamstring curl"
        );

    ListView<String> exerciseListView = new ListView<>(exercises);

    exerciseListView.setCellFactory(lv -> new ListCell<String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (item.endsWith(":")) {
                    Text boldText = new Text(item);
                    boldText.setStyle("-fx-font-weight: bold;");
                    setGraphic(new TextFlow(boldText));
                    setText(null);
                } else {
                    setText(item);
                    setGraphic(null);
                }
            }
        }
    });

    Alert exerciseDialog = new Alert(Alert.AlertType.INFORMATION);
    exerciseDialog.setTitle("Exercise List");
    exerciseDialog.setHeaderText("Effective Exercises");
    exerciseDialog.getDialogPane().setContent(exerciseListView);
    exerciseDialog.showAndWait();
   }

    private List<String> loadGoalsFromFile() {
    List<String> goals = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader("goals.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            goals.add(line);
        }
    } catch (IOException e) {
        System.out.println("No saved goals found.");
    }
    return goals;
   }

   private void saveGoalsToFile(List<String> goals) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("goals.txt"))) {
        for (String goal : goals) {
            writer.write(goal);
            writer.newLine();
        }
    } catch (IOException e) {
        showAlert("Error", "Failed to save goals.");
    }
    }

   private void trackGoalsDialog() {
    if (userProfile == null) {
        showAlert("User Profile Missing", "Please set up your user profile first.");
        return;
    }
    if (goalTracker == null) {
        goalTracker = new GoalTracker(userProfile);
    }

    List<String> savedGoals = loadGoalsFromFile();

    Dialog<Void> goalDialog = new Dialog<>();
    goalDialog.setTitle("Goal Tracker");
    goalDialog.setHeaderText("Set and Track Your Fitness Goals");

    ButtonType closeButtonType = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
    goalDialog.getDialogPane().getButtonTypes().addAll(closeButtonType);

    VBox goalLayout = new VBox(10);
    goalLayout.setPadding(new Insets(20));

    ObservableList<CheckBox> goalItems = FXCollections.observableArrayList();
    ListView<CheckBox> goalListView = new ListView<>(goalItems);
    goalListView.setPrefHeight(200);

    for (String goal : savedGoals) {
        goalItems.add(new CheckBox(goal));
    }

    TextField goalInput = new TextField();
    goalInput.setPromptText("Enter your new goal");

    Button addGoalButton = new Button("Add Goal");
    Button clearGoalsButton = new Button("Clear All Goals");

    addGoalButton.setOnAction(e -> {
        String newGoal = goalInput.getText().trim();
        if (!newGoal.isEmpty()) {
            CheckBox goalCheckBox = new CheckBox(newGoal);
            goalItems.add(goalCheckBox);
            goalInput.clear();
            saveGoalsToFile(getGoalTexts(goalItems));
        }
    });
    clearGoalsButton.setOnAction(e -> {
        goalItems.clear();
        saveGoalsToFile(getGoalTexts(goalItems)); 
    });

    HBox inputLayout = new HBox(10, goalInput, addGoalButton, clearGoalsButton);
    inputLayout.setAlignment(Pos.CENTER);

    goalLayout.getChildren().addAll(new Label("Your Goals:"), goalListView, inputLayout);

    goalDialog.getDialogPane().setContent(goalLayout);

    goalDialog.showAndWait();
    }

    private List<String> getGoalTexts(ObservableList<CheckBox> goalItems) {
        List<String> goalTexts = new ArrayList<>();
        for (CheckBox goalCheckBox : goalItems) {
            goalTexts.add(goalCheckBox.getText());
        }
        return goalTexts;
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("FitMate");
        alert.setContentText("This is a prototype beginner-friendly fitness application to help users towards their fitness goals. Features include BMI and Calorie calculations, Workout/Diet plan generation, Optimal exercises, and Goal tracking.");
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
