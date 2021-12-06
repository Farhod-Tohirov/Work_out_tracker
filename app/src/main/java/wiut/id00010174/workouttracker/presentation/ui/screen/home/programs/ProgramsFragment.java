package wiut.id00010174.workouttracker.presentation.ui.screen.home.programs;

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
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.databinding.FragmentProgramBinding;
import wiut.id00010174.workouttracker.presentation.ui.adapters.program.ProgramsRVAdapter;
import wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.add_program.AddProgramFragment;
import wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.program_update.UpdateProgramFragment;
import wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.show_program.ShowProgramDialog;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program.ProgramViewModel;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.all_program.impl.ProgramViewModelImpl;

/**
 * Created by Farhod Tohirov on 06-December-2021, 16-00
 **/
@AndroidEntryPoint
public class ProgramsFragment extends Fragment {

    private final ProgramsRVAdapter programsAdapter = new ProgramsRVAdapter();
    private final Observer<List<ProgramData>> programsListObserver = programData -> {
        List<ProgramData> t = programData;

        programsAdapter.submitList(programData);
    };
    private FragmentProgramBinding binding;
    private ProgramViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProgramViewModelImpl.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProgramBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadViews();
        loadObservers();
    }

    private void loadViews() {
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        binding.programList.setAdapter(programsAdapter);

        binding.addButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_homeFragment_to_addProgramFragment);
        });

        navController.getCurrentBackStackEntry().getSavedStateHandle().getLiveData(AddProgramFragment.class.getName()).observe(getViewLifecycleOwner(), o -> {
            viewModel.getPrograms();
            navController.getCurrentBackStackEntry().getSavedStateHandle().remove(AddProgramFragment.class.getName());
        });

        navController.getCurrentBackStackEntry().getSavedStateHandle().getLiveData(UpdateProgramFragment.class.getName()).observe(getViewLifecycleOwner(), o -> {
            viewModel.getPrograms();
            navController.getCurrentBackStackEntry().getSavedStateHandle().remove(AddProgramFragment.class.getName());
        });

        programsAdapter.setOnClickListener(data -> {
            new ShowProgramDialog(requireContext(), data, updateData -> {
                Bundle bundle = new Bundle();
                bundle.putInt("programId", updateData.getId());
                navController.navigate(R.id.updateProgramFragment, bundle, getAnimationNavOptions());
            }, deleteData -> {
                viewModel.deleteProgram(deleteData);
            }).show();
        });
    }

    private NavOptions getAnimationNavOptions() {
        return new NavOptions.Builder().setEnterAnim(R.anim.slide_in_right).setExitAnim(R.anim.slide_out_left).setPopEnterAnim(R.anim.slide_in_left).setPopExitAnim(R.anim.slide_out_right).build();
    }

    private void loadObservers() {
        viewModel.programsLiveData().observe(getViewLifecycleOwner(), programsListObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
