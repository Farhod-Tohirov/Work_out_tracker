package wiut.id00010174.workouttracker.data.repository.impl;

import android.os.Handler;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wiut.id00010174.workouttracker.BuildConfig;
import wiut.id00010174.workouttracker.data.local.room.dao.ProgramDao;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.data.models.news.NewsResponseData;
import wiut.id00010174.workouttracker.data.remote.NewsRemoteData;
import wiut.id00010174.workouttracker.data.repository.UseDataRepository;
import wiut.id00010174.workouttracker.utils.helpers.CallbackHelper;
import wiut.id00010174.workouttracker.utils.helpers.NewsBackHelper;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-49
 **/

public class UserDataRepositoryImpl implements UseDataRepository {

    private final ProgramDao programDao;
    private final NewsRemoteData newsRemoteData;

    private final ExecutorService executors = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Inject
    public UserDataRepositoryImpl(ProgramDao programDao, NewsRemoteData newsRemoteData) {
        this.programDao = programDao;
        this.newsRemoteData = newsRemoteData;
    }

    @Override
    public void createSampleData() {
        executors.execute(() -> {

        });
    }

    @Override
    public void getPrograms(CallbackHelper<List<ProgramData>> callBack) {
        executors.execute(() -> {
            List<ProgramData> list = programDao.getAllPrograms();
            handler.post(() -> {
                callBack.success(list);
            });
        });
    }

    @Override
    public void createProgram(ProgramData data, CallbackHelper<Boolean> callback) {
        executors.execute(() -> {
            programDao.createProgramData(data);
            handler.post(() -> {
                callback.success(true);
            });
        });
    }

    @Override
    public void getProgramData(int programId, CallbackHelper<ProgramData> callback) {
        executors.execute(() -> {
            ProgramData data = programDao.getProgramData(programId);
            handler.post(() -> {
                callback.success(data);
            });
        });
    }

    @Override
    public void updateProgram(ProgramData data, CallbackHelper<Boolean> callback) {
        executors.execute(() -> {
            programDao.updateProgramData(data);
            handler.post(() -> {
                callback.success(true);
            });
        });
    }

    @Override
    public void deleteProgram(ProgramData data, CallbackHelper<Boolean> callback) {
        executors.execute(() -> {
            programDao.deleteProgramData(data);
            handler.post(() -> {
                callback.success(true);
            });
        });
    }

    @Override
    public void getNews(NewsBackHelper<NewsResponseData> newsResponse) {
        try {
            newsRemoteData.getNews(BuildConfig.NEWS_API_KEY, "gym").enqueue(new Callback<NewsResponseData>() {
                @Override
                public void onResponse(Call<NewsResponseData> call, Response<NewsResponseData> response) {
                    newsResponse.success(response.body());
                }

                @Override
                public void onFailure(Call<NewsResponseData> call, Throwable t) {
                    newsResponse.error(t.getMessage());
                }
            });
        } catch (Exception ignored) {
            newsResponse.error(ignored.getMessage());
        }
    }
}
