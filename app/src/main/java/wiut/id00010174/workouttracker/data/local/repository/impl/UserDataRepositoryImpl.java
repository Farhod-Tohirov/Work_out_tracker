package wiut.id00010174.workouttracker.data.local.repository.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import wiut.id00010174.workouttracker.data.local.repository.UseDataRepository;
import wiut.id00010174.workouttracker.data.local.room.dao.DailyDao;
import wiut.id00010174.workouttracker.data.local.room.entity.DailyData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-49
 **/

public class UserDataRepositoryImpl implements UseDataRepository {

    private final DailyDao dailyDao;
    private final ExecutorService executors = Executors.newSingleThreadExecutor();

    @Inject
    UserDataRepositoryImpl(DailyDao dao) {
        dailyDao = dao;
    }

    @Override
    public List<DailyData> getDailyData() {
        return dailyDao.getDailyData();
    }

    @Override
    public void createSampleData(List<DailyData> dataList) {
        executors.execute(() -> {
            dailyDao.insertAll(dataList);
        });
    }
}
