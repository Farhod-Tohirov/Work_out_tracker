package wiut.id00010174.workouttracker.data.local.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Farhod Tohirov on 01-December-2021, 17-18
 **/

@Entity(tableName = "daily_table")
public class DailyData {

    @PrimaryKey(autoGenerate = true)
    public  int id;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DailyData(int id, String time) {
        this.id = id;
        this.time = time;
    }
}
