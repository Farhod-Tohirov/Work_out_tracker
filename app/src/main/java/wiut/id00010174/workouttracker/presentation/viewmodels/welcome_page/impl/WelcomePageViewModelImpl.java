package wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page.impl;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.domain.welcome_page.WelcomePageUseCase;
import wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page.WelcomePageViewModel;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-41
 **/
@HiltViewModel
public class WelcomePageViewModelImpl extends ViewModel implements WelcomePageViewModel {

    private final WelcomePageUseCase useCase;

    @Inject
    WelcomePageViewModelImpl(WelcomePageUseCase useCase) {
        this.useCase = useCase;
    }

    @Override
    public void createSampleDataBase() {
        useCase.createSampleData();
        navigateNextScreen.postValue(true);
    }
}
