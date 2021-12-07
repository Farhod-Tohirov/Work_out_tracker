package wiut.id00010174.workouttracker.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import wiut.id00010174.workouttracker.data.models.news.NewsResponseData;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-47
 **/

public interface NewsRemoteData {

    @GET("news")
    Call<NewsResponseData> getNews(@Query("apikey") String apiKey, @Query("q") String about);
}
