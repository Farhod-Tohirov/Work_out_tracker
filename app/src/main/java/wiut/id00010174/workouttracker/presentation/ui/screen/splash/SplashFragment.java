package wiut.id00010174.workouttracker.presentation.ui.screen.splash;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.databinding.FragmentSplashBinding;

@AndroidEntryPoint
public class SplashFragment extends Fragment {

    private FragmentSplashBinding binding;
    NavController navController;
    Boolean firstTime = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);

        if (getContext().getSharedPreferences("LocalStorage", Context.MODE_PRIVATE) != null) {
            SharedPreferences preferences = getContext().getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);
            if (preferences != null) {
                firstTime = preferences.getBoolean("isItFirstTime", true);
            }
        }

        try {
            new CountDownTimer(1000, 10) {
                @Override
                public void onTick(long millisUntilFinished) {
                }

                @Override
                public void onFinish() {
                    navigateNextScreen();
                }
            }.start();
        } catch (Exception e) {
            navigateNextScreen();
        }
    }

    private void navigateNextScreen() {
        if (firstTime)
            navController.navigate(R.id.action_splashFragment_to_welcomePage);
        else
            navController.navigate(R.id.action_splashFragment_to_homeFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}