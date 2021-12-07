package wiut.id00010174.workouttracker.presentation.ui.screen.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.databinding.FragmentHomeBinding;
import wiut.id00010174.workouttracker.presentation.ui.adapters.home.HomeVPAdapter;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    HomeVPAdapter viewPagerAdapter;
    private FragmentHomeBinding binding;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        loadViews();
    }

    @SuppressLint("NonConstantResourceId")
    private void loadViews() {
        viewPagerAdapter = new HomeVPAdapter(getChildFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            if (position == 0) tab.setText(R.string.program);
            else tab.setText(R.string.habits);
        }).attach();
        binding.menuButton.setOnClickListener(v -> {
            binding.getRoot().openDrawer(GravityCompat.START);
        });

        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.addProgram: {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToAddProgramFragment());
                    break;
                }
                case R.id.aboutApp: {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToAboutAppFragment());
                    break;
                }
                case R.id.exit: {
                    requireActivity().finishAffinity();
                    break;
                }
                case R.id.news: {
                    navController.navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment());
                    break;
                }
                default: {
                    break;
                }
            }
            binding.getRoot().closeDrawer(GravityCompat.START);
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.viewPager.setAdapter(null);
        binding = null;
    }
}