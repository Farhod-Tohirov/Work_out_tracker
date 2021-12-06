package wiut.id00010174.workouttracker.presentation.ui.screen.welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.databinding.FragmentWelcomePageBinding;
import wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page.WelcomePageViewModel;
import wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page.impl.WelcomePageViewModelImpl;

@AndroidEntryPoint
public class WelcomePageFragment extends Fragment {

    private FragmentWelcomePageBinding binding;
    private WelcomePageViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WelcomePageViewModelImpl.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadViews();
        loadObservers();
    }

    private void loadViews() {
        viewModel.createSampleDataBase();
    }

    private void loadObservers() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}