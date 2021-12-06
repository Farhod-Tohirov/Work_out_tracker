package wiut.id00010174.workouttracker.presentation.viewmodels.home.prgram_add;

import androidx.lifecycle.LiveData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 18-07
 **/

public interface AddProgramViewModel {
    LiveData<Boolean> closeScreenLiveData();

    void saveProgram(String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes);
}
