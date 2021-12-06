package wiut.id00010174.workouttracker.presentation.ui.adapters.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import wiut.id00010174.workouttracker.presentation.ui.screen.home.habits.HabitsFragment;
import wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.ProgramsFragment;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-01
 **/

public class HomeVPAdapter extends FragmentStateAdapter {

    public HomeVPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0)
            fragment = new ProgramsFragment();
        else
            fragment = new HabitsFragment();
        return fragment;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
