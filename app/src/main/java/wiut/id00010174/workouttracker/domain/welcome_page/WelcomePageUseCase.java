package wiut.id00010174.workouttracker.domain.welcome_page;

import java.util.List;

import wiut.id00010174.workouttracker.data.models.welcome_page.WelcomePageModel;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-48
 **/

public interface WelcomePageUseCase {
    void createSampleData();

    List<WelcomePageModel> getWelcomePageModels();
}
