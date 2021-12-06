package wiut.id00010174.workouttracker.data.local.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-22
 **/
@Entity(tableName = "program_data")
public class ProgramData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String startedTime;
    private String finishedTime;
    private int numberOfTimes;
    private String experience;
    private String additionalNotes;

    public ProgramData(String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes) {
        this.title = title;
        this.startedTime = startedTime;
        this.finishedTime = finishedTime;
        this.numberOfTimes = numberOfTimes;
        this.experience = experience;
        this.additionalNotes = additionalNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(String startedTime) {
        this.startedTime = startedTime;
    }

    public String getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(String finishedTime) {
        this.finishedTime = finishedTime;
    }

    public int getNumberOfTimes() {
        return numberOfTimes;
    }

    public void setNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }
}
