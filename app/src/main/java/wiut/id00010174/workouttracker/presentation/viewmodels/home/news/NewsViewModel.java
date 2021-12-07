package wiut.id00010174.workouttracker.presentation.viewmodels.home.news;

import androidx.lifecycle.LiveData;

import java.util.List;

import wiut.id00010174.workouttracker.data.models.news.NewsData;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-17
 **/

public interface NewsViewModel {
    LiveData<Boolean> showLoaderLiveData();

    LiveData<List<NewsData>> newsListLiveData();

    LiveData<String> messageLiveData();

    void getNews();
}
