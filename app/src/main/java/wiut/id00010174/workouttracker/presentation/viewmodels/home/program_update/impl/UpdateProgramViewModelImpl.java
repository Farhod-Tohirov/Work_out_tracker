package wiut.id00010174.workouttracker.presentation.viewmodels.home.program_update.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.domain.home.program.ProgramsUseCase;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.program_update.UpdateProgramViewModel;

/**
 * Created by Farhod Tohirov on 06-December-2021, 18-07
 **/
@HiltViewModel
public class UpdateProgramViewModelImpl extends ViewModel implements UpdateProgramViewModel {

    private final ProgramsUseCase useCase;
    private final MutableLiveData<Boolean> closeScreen = new MutableLiveData<>();
    private final MutableLiveData<List<String>> experienceList = new MutableLiveData<>();
    private final MutableLiveData<ProgramData> programData = new MutableLiveData<>();

    @Inject
    public UpdateProgramViewModelImpl(ProgramsUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public LiveData<ProgramData> programDataLiveData() {
        return programData;
    }

    @Override
    public LiveData<Boolean> closeScreenLiveData() {
        return closeScreen;
    }

    @Override
    public void getProgramdata(int programId) {
        useCase.getProgramData(programId, programData::setValue);
    }

    @Override
    public void updateProgram(int programId, String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes) {
        useCase.updateProgramData(programId, title, startedTime, finishedTime, numberOfTimes, experience, additionalNotes, closeScreen::setValue);
    }
}
