package wiut.id00010174.workouttracker.domain.welcome_page.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.models.welcome_page.WelcomePageModel;
import wiut.id00010174.workouttracker.data.repository.UseDataRepository;
import wiut.id00010174.workouttracker.domain.welcome_page.WelcomePageUseCase;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-48
 **/

public class WelcomePageUseCaseImpl implements WelcomePageUseCase {

    private final UseDataRepository repository;

    @Inject
    WelcomePageUseCaseImpl(UseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createSampleData() {
        repository.createSampleData();
    }

    @Override
    public List<WelcomePageModel> getWelcomePageModels() {
        List<WelcomePageModel> list = new ArrayList<>();
        list.add(new WelcomePageModel(R.string.welcome_title_1, R.string.welcome_subtitle_1, R.drawable.daily_task_2));
        list.add(new WelcomePageModel(R.string.welcome_title_2, R.string.welcome_subtitle_2, R.drawable.schedule));
        list.add(new WelcomePageModel(R.string.welcome_title_3, R.string.welcome_subtitle_3, R.drawable.rocket));
        return list;
    }
}
