package wiut.id00010174.workouttracker.data.local.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import kotlin.jvm.Volatile;
import wiut.id00010174.workouttracker.app.App;
import wiut.id00010174.workouttracker.data.local.room.dao.DailyDao;
import wiut.id00010174.workouttracker.data.local.room.entity.DailyData;

/**
 * Created by Farhod Tohirov on 01-December-2021, 17-17
 **/
@Database(entities = DailyData.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DailyDao dailyDao();

    @Volatile
    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        INSTANCE = Room.databaseBuilder(
                App.getInstance().getApplicationContext(),
                AppDatabase.class,
                "app_database")
                .build();
        return INSTANCE;
    }
}
