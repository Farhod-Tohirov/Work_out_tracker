package wiut.id00010174.workouttracker.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import wiut.id00010174.workouttracker.data.local.room.AppDatabase;
import wiut.id00010174.workouttracker.data.local.room.dao.DailyDao;

/**
 * Created by Farhod Tohirov on 01-December-2021, 17-31
 **/
@Module
@InstallIn(SingletonComponent.class)
public class AppDatabaseModule {

    @Provides
    @Singleton
    AppDatabase getAppDatabase() {
        return AppDatabase.getInstance();
    }

    @Provides
    @Singleton
    DailyDao getDailyDao(AppDatabase database) {
        return database.dailyDao();
    }
}
