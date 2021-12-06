package wiut.id00010174.workouttracker.presentation.viewmodels.home.program_update;

import androidx.lifecycle.LiveData;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 18-07
 **/

public interface UpdateProgramViewModel {
    LiveData<ProgramData> programDataLiveData();

    LiveData<Boolean> closeScreenLiveData();

    void getProgramdata(int programId);

    void updateProgram(int programId, String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes);
}
