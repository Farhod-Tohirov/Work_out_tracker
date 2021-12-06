package wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program;

import androidx.lifecycle.LiveData;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-15
 **/

public interface ProgramViewModel {
    LiveData<List<ProgramData>> programsLiveData();

    void getPrograms();

    void deleteProgram(ProgramData data);
}
