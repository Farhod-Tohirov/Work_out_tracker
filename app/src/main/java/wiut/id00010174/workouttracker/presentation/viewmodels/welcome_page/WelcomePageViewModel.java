package wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-39
 **/

public interface WelcomePageViewModel {
    MutableLiveData<Boolean> navigateNextScreen = new MutableLiveData<>();

    void createSampleDataBase();
}
