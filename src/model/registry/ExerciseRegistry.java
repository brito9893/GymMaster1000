package model.registry;

import model.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitor on 18/07/2017.
 */
public class ExerciseRegistry {
    private List<Exercise> exerciseList;

    public ExerciseRegistry() {
        this.exerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise e) {
        exerciseList.add(e);
    }

    public void removeExercise(Exercise e) {
        exerciseList.remove(e);
    }
}
