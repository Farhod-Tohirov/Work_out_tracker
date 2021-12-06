package wiut.id00010174.workouttracker.data.local.room.dao;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-37
 **/
@Dao
public interface ProgramDao {

    @Query("SELECT * FROM program_data")
    List<ProgramData> getAllPrograms();

    @Insert(onConflict = REPLACE)
    void createProgramData(ProgramData data);

    @Query("SELECT * FROM program_data WHERE id=:id")
    ProgramData getProgramData(int id);

    @Delete
    void deleteProgramData(ProgramData data);

    @Update(onConflict = REPLACE)
    void updateProgramData(ProgramData data);
}
