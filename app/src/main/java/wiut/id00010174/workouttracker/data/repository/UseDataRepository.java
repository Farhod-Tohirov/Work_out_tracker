package wiut.id00010174.workouttracker.data.repository;

import java.util.List;

import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.data.models.news.NewsResponseData;
import wiut.id00010174.workouttracker.utils.helpers.CallbackHelper;
import wiut.id00010174.workouttracker.utils.helpers.NewsBackHelper;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-49
 **/

public interface UseDataRepository {
    void createSampleData();

    void getPrograms(CallbackHelper<List<ProgramData>> callBack);

    void createProgram(ProgramData data, CallbackHelper<Boolean> callback);

    void getProgramData(int programId, CallbackHelper<ProgramData> callback);

    void updateProgram(ProgramData data, CallbackHelper<Boolean> callback);

    void deleteProgram(ProgramData data, CallbackHelper<Boolean> callback);

    void getNews(NewsBackHelper<NewsResponseData> newsResponse);
}
