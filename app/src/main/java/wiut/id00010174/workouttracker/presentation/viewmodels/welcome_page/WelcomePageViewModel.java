package wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.data.models.welcome_page.WelcomePageModel;
import wiut.id00010174.workouttracker.domain.welcome_page.WelcomePageUseCase;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-41
 **/
@HiltViewModel
public class WelcomePageViewModel extends ViewModel {

    public MutableLiveData<List<WelcomePageModel>> welcomePageList = new MutableLiveData<>();

    @Inject
    WelcomePageViewModel(WelcomePageUseCase useCase) {
        // creates sample data
        useCase.createSampleData();
        List<WelcomePageModel> models = useCase.getWelcomePageModels();
        welcomePageList.setValue(models);
    }
}
