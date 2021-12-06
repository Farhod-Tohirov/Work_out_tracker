package wiut.id00010174.workouttracker.presentation.viewmodels.home.prgram_add.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.domain.home.program.ProgramsUseCase;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.prgram_add.AddProgramViewModel;

/**
 * Created by Farhod Tohirov on 06-December-2021, 18-07
 **/
@HiltViewModel
public class AddProgramViewModelImpl extends ViewModel implements AddProgramViewModel {

    private final ProgramsUseCase useCase;
    private final MutableLiveData<Boolean> closeScreen = new MutableLiveData<>();
    private final MutableLiveData<List<String>> experienceList = new MutableLiveData<>();

    @Inject
    public AddProgramViewModelImpl(ProgramsUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public LiveData<Boolean> closeScreenLiveData() {
        return closeScreen;
    }

    @Override
    public void saveProgram(String title, String startedTime, String finishedTime, int numberOfTimes, String experience, String additionalNotes) {
        useCase.saveProgramData(title, startedTime, finishedTime, numberOfTimes, experience, additionalNotes, closeScreen::setValue);
    }
}
