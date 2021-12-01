package wiut.id00010174.workouttracker.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import wiut.id00010174.workouttracker.app.App;

/**
 * Created by Farhod Tohirov on 01-December-2021, 16-46
 **/
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    Context getContext() {
        return App.getInstance();
    }
}
