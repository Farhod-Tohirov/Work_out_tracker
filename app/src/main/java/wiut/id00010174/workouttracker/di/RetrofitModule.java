package wiut.id00010174.workouttracker.di;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wiut.id00010174.workouttracker.BuildConfig;
import wiut.id00010174.workouttracker.data.remote.NewsRemoteData;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-30
 **/
@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    ChuckerInterceptor getChuckerInterceptor(@ApplicationContext Context context) {
        return new ChuckerInterceptor.Builder(context)
                .collector(new ChuckerCollector(context))
                .maxContentLength(2500000L)
                .alwaysReadResponseBody(false)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getClient(ChuckerInterceptor chuckerInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(chuckerInterceptor).build();
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    NewsRemoteData getNewsRemoteData(Retrofit retrofit) {
        return retrofit.create(NewsRemoteData.class);
    }
}
