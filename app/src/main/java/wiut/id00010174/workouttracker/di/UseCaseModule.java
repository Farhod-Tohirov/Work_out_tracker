package wiut.id00010174.workouttracker.di;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import wiut.id00010174.workouttracker.domain.home.program.ProgramsUseCase;
import wiut.id00010174.workouttracker.domain.home.program.impl.ProgramsUseCaseImpl;
import wiut.id00010174.workouttracker.domain.welcome_page.WelcomePageUseCase;
import wiut.id00010174.workouttracker.domain.welcome_page.impl.WelcomePageUseCaseImpl;

/**
 * Created by Farhod Tohirov on 06-December-2021, 10-59
 **/
@Module
@InstallIn(ViewModelComponent.class)
public interface UseCaseModule {

    @Binds
    WelcomePageUseCase getWelcomePageUseCase(WelcomePageUseCaseImpl useCase);

    @Binds
    ProgramsUseCase getProgramUseCase(ProgramsUseCaseImpl useCase);
}
