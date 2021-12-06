package wiut.id00010174.workouttracker.data.local.room.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.DailyData;

/**
 * Created by Farhod Tohirov on 01-December-2021, 17-20
 **/

@Dao
public interface DailyDao {

    @Query("SELECT * FROM daily_table")
    List<DailyData> getDailyData();

    @Insert(onConflict = REPLACE)
    void insertAll(List<DailyData> list);
}
