package wiut.id00010174.workouttracker.domain.news.impl;

import java.util.List;

import javax.inject.Inject;

import wiut.id00010174.workouttracker.data.models.news.NewsData;
import wiut.id00010174.workouttracker.data.models.news.NewsResponseData;
import wiut.id00010174.workouttracker.data.repository.UseDataRepository;
import wiut.id00010174.workouttracker.domain.news.NewsUseCase;
import wiut.id00010174.workouttracker.utils.helpers.NewsBackHelper;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-23
 **/

public class NewsUseCaseImpl implements NewsUseCase {

    private final UseDataRepository repository;

    @Inject
    public NewsUseCaseImpl(UseDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getNews(NewsBackHelper<List<NewsData>> callback) {
        repository.getNews(new NewsBackHelper<NewsResponseData>() {
            @Override
            public void success(NewsResponseData data) {
                callback.success(data.getResults());
            }

            @Override
            public void error(String message) {
                callback.error(message);
            }
        });
    }
}
