package wiut.id00010174.workouttracker.domain.news;

import java.util.List;

import wiut.id00010174.workouttracker.data.models.news.NewsData;
import wiut.id00010174.workouttracker.utils.helpers.NewsBackHelper;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-23
 **/

public interface NewsUseCase {
    void getNews(NewsBackHelper<List<NewsData>> callback);
}
