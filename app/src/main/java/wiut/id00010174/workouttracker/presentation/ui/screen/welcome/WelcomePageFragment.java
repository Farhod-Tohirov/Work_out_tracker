package wiut.id00010174.workouttracker.presentation.ui.screen.welcome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.models.welcome_page.WelcomePageModel;
import wiut.id00010174.workouttracker.databinding.FragmentWelcomePageBinding;
import wiut.id00010174.workouttracker.presentation.ui.adapters.welcome_page.WelcomePageRVAdapter;
import wiut.id00010174.workouttracker.presentation.viewmodels.welcome_page.WelcomePageViewModel;

@AndroidEntryPoint
public class WelcomePageFragment extends Fragment {

    private FragmentWelcomePageBinding binding;
    private WelcomePageViewModel viewModel;
    private final WelcomePageRVAdapter adapter = new WelcomePageRVAdapter();
    private final Observer<List<WelcomePageModel>> welcomePageListObserver = adapter::submitList;
    NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWelcomePageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WelcomePageViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        loadViews();
        loadObservers();
    }

    private void loadViews() {
        binding.viewPager.setAdapter(adapter);
        binding.navigateHomeScreen.setOnClickListener(v -> {
            if (binding.viewPager.getCurrentItem() != adapter.getCurrentList().size() - 1)
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1, true);
            else {
                navController.navigate(R.id.action_welcomePage_to_homeFragment);
            }
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2)
                    binding.navigateHomeScreen.setText(R.string.start);
                else
                    binding.navigateHomeScreen.setText(R.string.next);
            }
        });
        binding.dots.setViewPager2(binding.viewPager);
        getContext().getSharedPreferences("LocalStorage", Context.MODE_PRIVATE).edit().putBoolean("isItFirstTime", false).apply();
    }

    private void loadObservers() {
        viewModel.welcomePageList.observe(getViewLifecycleOwner(), welcomePageListObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.viewPager.setAdapter(null);
        binding = null;
    }
}