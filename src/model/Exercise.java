package model;

/**
 * Created by vitor on 18/07/2017.
 */
public class Exercise {
    private String name;
    private float rating;
    private String muscleTarget;
    private String type;
    private String equipment;
    private String instruction;

    public Exercise(String name, float rating, String muscleTarget, String type, String equipment, String instruction) {
        this.name = name;
        this.rating = rating;
        this.muscleTarget = muscleTarget;
        this.type = type;
        this.equipment = equipment;
        this.instruction = instruction;
    }

    public Exercise() {

    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", muscleTarget='" + muscleTarget + '\'' +
                ", type='" + type + '\'' +
                ", equipment='" + equipment + '\'' +
                ", instruction='" + instruction + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMuscleTarget() {
        return muscleTarget;
    }

    public void setMuscleTarget(String muscleTarget) {
        this.muscleTarget = muscleTarget;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
