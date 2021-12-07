package wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private final ExecutorService executors = Executors.newSingleThreadExecutor();
    private List<ProgramData> allProgramsList;

    @Inject
    public ProgramViewModelImpl(ProgramsUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public LiveData<List<ProgramData>> programsLiveData() {
        return programs;
    }

    @Override
    public void getPrograms() {
        useCase.getPrograms(programDataList -> {
            programs.setValue(programDataList);
            allProgramsList = new ArrayList<>(programDataList);
        });
    }

    @Override
    public void deleteProgram(ProgramData data) {
        useCase.deleteProgramData(data, deleted -> useCase.getPrograms(programs::setValue));
    }

    @Override
    public void filterPrograms(CharSequence constraint) {
        if (allProgramsList == null) return;
        executors.execute(() -> {
            List<ProgramData> filteredList = new ArrayList<>();
            if (constraint == null || constraint.toString().isEmpty()) {
                filteredList.addAll(allProgramsList);
            } else {
                String letter = constraint.toString().toLowerCase(Locale.ROOT).trim();
                for (ProgramData program : allProgramsList) {
                    boolean t = program.getTitle().toLowerCase(Locale.ROOT).contains(letter)
                            || (program.getAdditionalNotes() != null && program.getAdditionalNotes().toLowerCase(Locale.ROOT).contains(letter))
                            || program.getStartedTime().toLowerCase(Locale.ROOT).contains(letter)
                            || program.getFinishedTime().toLowerCase(Locale.ROOT).contains(letter)
                            || program.getExperience().toLowerCase(Locale.ROOT).contains(letter);
                    if (t) {
                        filteredList.add(program);
                    }
                }
            }
            programs.postValue(filteredList);
        });
    }
}
