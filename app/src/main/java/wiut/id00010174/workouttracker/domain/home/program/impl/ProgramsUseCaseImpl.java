package wiut.id00010174.workouttracker.domain.home.program.impl;

import java.util.List;

import javax.inject.Inject;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.data.repository.UseDataRepository;
import wiut.id00010174.workouttracker.domain.home.program.ProgramsUseCase;
import wiut.id00010174.workouttracker.utils.helpers.CallbackHelper;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-28
 **/

public class ProgramsUseCaseImpl implements ProgramsUseCase {

    UseDataRepository repository;

    @Inject
    public ProgramsUseCaseImpl(UseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getPrograms(CallbackHelper<List<ProgramData>> callBack) {
        repository.getPrograms(callBack);
    }

    @Override
    public void saveProgramData(String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes, CallbackHelper<Boolean> callback) {
        repository.createProgram(new ProgramData(title, startedTime, finishedTime, numberOfTimes, experience, additionalNotes), callback);
    }

    @Override
    public void getProgramData(int programId, CallbackHelper<ProgramData> callback) {
        repository.getProgramData(programId, callback);
    }

    @Override
    public void updateProgramData(int id, String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes, CallbackHelper<Boolean> callback) {
        ProgramData data = new ProgramData(title, startedTime, finishedTime, numberOfTimes, experience, additionalNotes);
        data.setId(id);
        repository.updateProgram(data, callback);
    }

    @Override
    public void deleteProgramData(ProgramData data, CallbackHelper<Boolean> callback) {
        repository.deleteProgram(data, callback);
    }
}
