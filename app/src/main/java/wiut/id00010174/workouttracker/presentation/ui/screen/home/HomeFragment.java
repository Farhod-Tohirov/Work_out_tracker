package wiut.id00010174.workouttracker.presentation.ui.screen.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.databinding.FragmentHomeBinding;
import wiut.id00010174.workouttracker.presentation.ui.adapters.home.HomeVPAdapter;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    HomeVPAdapter viewPagerAdapter;
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadViews();
    }

    private void loadViews() {
        viewPagerAdapter = new HomeVPAdapter(getChildFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            if (position == 0) tab.setText(R.string.program);
            else tab.setText(R.string.habits);
        }).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.viewPager.setAdapter(null);
        binding = null;
    }
}