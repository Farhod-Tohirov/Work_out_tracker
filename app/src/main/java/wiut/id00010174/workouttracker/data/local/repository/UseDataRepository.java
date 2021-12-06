package wiut.id00010174.workouttracker.data.local.repository;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.DailyData;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-49
 **/

public interface UseDataRepository {
    void createSampleData(List<DailyData> dataList);

    List<DailyData> getDailyData();
}
