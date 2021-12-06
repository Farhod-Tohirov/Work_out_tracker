package wiut.id00010174.workouttracker.domain.home.program;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.utils.helpers.CallbackHelper;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-28
 **/

public interface ProgramsUseCase {
    void getPrograms(CallbackHelper<List<ProgramData>> callBack);

    void saveProgramData(String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes, CallbackHelper<Boolean> callback);

    void getProgramData(int programId, CallbackHelper<ProgramData> callback);

    void updateProgramData(int id, String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes, CallbackHelper<Boolean> callback);

    void deleteProgramData(ProgramData data, CallbackHelper<Boolean> callback);
}
