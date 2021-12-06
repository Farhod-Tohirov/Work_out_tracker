package wiut.id00010174.workouttracker.domain.welcome_page.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import wiut.id00010174.workouttracker.data.local.repository.UseDataRepository;
import wiut.id00010174.workouttracker.data.local.room.entity.DailyData;
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
        List<DailyData> list = new ArrayList<>();
        list.add(new DailyData(1, "2020.12.10 10:25"));
        list.add(new DailyData(2, "2020.12.10 10:25"));
        list.add(new DailyData(3, "2020.12.10 10:25"));
        list.add(new DailyData(4, "2020.12.10 10:25"));
        repository.createSampleData(list);
    }
}
