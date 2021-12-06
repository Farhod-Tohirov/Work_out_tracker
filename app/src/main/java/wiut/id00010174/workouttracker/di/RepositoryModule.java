package wiut.id00010174.workouttracker.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import wiut.id00010174.workouttracker.data.local.repository.UseDataRepository;
import wiut.id00010174.workouttracker.data.local.repository.impl.UserDataRepositoryImpl;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-57
 **/
@Module
@InstallIn(SingletonComponent.class)
public interface RepositoryModule {

    @Binds
    UseDataRepository getUserDataRepository(UserDataRepositoryImpl repo);
}
