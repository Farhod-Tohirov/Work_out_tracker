package wiut.id00010174.workouttracker.app;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Created by Farhod Tohirov on 01-December-2021, 16-34
 **/
@HiltAndroidApp
public class App extends Application {

    private static App _instance;
    public static App getInstance() {
        return _instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }
}
