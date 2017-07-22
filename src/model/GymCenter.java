package model;

import model.registry.ExerciseRegistry;
import model.registry.PlanRegistry;
import model.registry.UserRegistry;
import model.registry.WorkoutRegistry;

/**
 * Created by vitor on 04/07/2017.
 */
public class GymCenter {
    private static String name;
    private static String[] contactEmail;
    private static int[] contactPhoneNumber;
    private static String address;
    private static String timeTable;
    private static UserRegistry userRegistry = new UserRegistry();
    private static ExerciseRegistry exerciseRegistry = new ExerciseRegistry();
    private static WorkoutRegistry workoutRegistry = new WorkoutRegistry();
    private static PlanRegistry planRegistry = new PlanRegistry();

    public static ExerciseRegistry getExerciseRegistry() {
        return exerciseRegistry;
    }

    public static WorkoutRegistry getWorkoutRegistry() {
        return workoutRegistry;
    }

    public static PlanRegistry getPlanRegistry() {
        return planRegistry;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String n) {
        name = n;
    }

    public static String[] getContactEmail() {
        return contactEmail;
    }

    public static void setContactEmail(String[] email) {
        contactEmail = email;
    }

    public static int[] getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public static void setContactPhoneNumber(int[] PhoneNumber) {
        contactPhoneNumber = PhoneNumber;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String s) {
        address = s;
    }

    public static String getTimeTable() {
        return timeTable;
    }

    public static void setTimeTable(String time) {
        timeTable = time;
    }

    public static UserRegistry getUserRegistry() {
        return userRegistry;
    }

    public static void addUnconfirmedUser(User u) {
        userRegistry.addNonConfirmedUser(u);
    }

    public static boolean isUser(String username, String password) {
        return userRegistry.getUser(username, password);
    }

    public static void addExercise(Exercise e) {
        exerciseRegistry.addExercise(e);
    }
}
