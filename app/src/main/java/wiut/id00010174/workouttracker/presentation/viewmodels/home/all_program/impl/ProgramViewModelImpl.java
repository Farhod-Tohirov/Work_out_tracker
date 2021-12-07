package wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.domain.home.program.ProgramsUseCase;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program.ProgramViewModel;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-15
 **/
@HiltViewModel
public class ProgramViewModelImpl extends ViewModel implements ProgramViewModel {

    private final ProgramsUseCase useCase;
    private final MutableLiveData<List<ProgramData>> programs = new MutableLiveData<>();

    @Inject
    public ProgramViewModelImpl(ProgramsUseCase useCase) {
        this.useCase = useCase;
        getPrograms();
    }

    @Override
    public LiveData<List<ProgramData>> programsLiveData() {
        return programs;
    }

    @Override
    public void getPrograms() {
        programs.setValue(Collections.emptyList());
        useCase.getPrograms(programs::setValue);
    }

    @Override
    public void deleteProgram(ProgramData data) {
        useCase.deleteProgramData(data, deleted -> useCase.getPrograms(programs::setValue));
    }
}
