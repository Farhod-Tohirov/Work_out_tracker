package wiut.id00010174.workouttracker.presentation.viewmodels.home.news.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import wiut.id00010174.workouttracker.data.models.news.NewsData;
import wiut.id00010174.workouttracker.domain.news.NewsUseCase;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.news.NewsViewModel;
import wiut.id00010174.workouttracker.utils.helpers.NewsBackHelper;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-17
 **/
@HiltViewModel
public class NewsViewModelImpl extends ViewModel implements NewsViewModel {

    private final NewsUseCase useCase;

    @Inject
    public NewsViewModelImpl(NewsUseCase useCase) {
        this.useCase = useCase;
    }

    private final MutableLiveData<Boolean> loader = new MutableLiveData<>();
    private final MutableLiveData<List<NewsData>> newsList = new MutableLiveData<>();
    private final MutableLiveData<String> messageLiveData = new MutableLiveData<>();

    @Override
    public LiveData<Boolean> showLoaderLiveData() {
        return loader;
    }

    @Override
    public LiveData<List<NewsData>> newsListLiveData() {
        return newsList;
    }

    @Override
    public LiveData<String> messageLiveData() {
        return messageLiveData;
    }

    @Override
    public void getNews() {
        loader.setValue(true);
        useCase.getNews(new NewsBackHelper<List<NewsData>>() {
            @Override
            public void success(List<NewsData> data) {
                loader.setValue(false);
                newsList.setValue(data);
            }

            @Override
            public void error(String message) {
                loader.setValue(false);
                messageLiveData.setValue(message);
            }
        });
    }
}
