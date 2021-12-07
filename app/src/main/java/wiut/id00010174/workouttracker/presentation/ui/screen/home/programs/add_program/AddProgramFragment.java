package wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.add_program;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.databinding.FragmentAddProgramBinding;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.prgram_add.AddProgramViewModel;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.prgram_add.impl.AddProgramViewModelImpl;
import wiut.id00010174.workouttracker.utils.helpers.DatePickerDialogHelper;

@AndroidEntryPoint
public class AddProgramFragment extends Fragment {

    private FragmentAddProgramBinding binding;
    private NavController navController;
    private AddProgramViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddProgramViewModelImpl.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProgramBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        loadViews();
        loadObservers();
    }

    private void loadViews() {
        binding.backButton.setOnClickListener(v -> {
            navController.popBackStack();
        });
        binding.startedTime.setOnClickListener(v -> {
            DatePickerDialogHelper.getInstance().showDatePickerDialog(requireContext(), selectedTime -> {
                binding.startedTime.setText(selectedTime);
            });
        });
        binding.finishedTime.setOnClickListener(v -> {
            DatePickerDialogHelper.getInstance().showDatePickerDialog(requireContext(), selectedTime -> {
                binding.finishedTime.setText(selectedTime);
            });
        });

        binding.createButton.setOnClickListener(v -> {
            if (binding.title.getText() == null) {
                showToast(getString(R.string.choose_program_name));
                return;
            }
            String title = binding.title.getText().toString();
            if (binding.startedTime.getText() == null) {
                showToast(getString(R.string.choose_started_time));
                return;
            }
            String startedTime = binding.startedTime.getText().toString();

            if (binding.finishedTime.getText() == null) {
                showToast(getString(R.string.choose_finished_time));
                return;
            }
            String finishedTime = binding.finishedTime.getText().toString();

            if (binding.numberOfTimes.getText() == null) {
                showToast(getString(R.string.choose_number_of_times));
                return;
            }
            String numberOfTimes = binding.numberOfTimes.getText().toString();
            int numberOfTimesInt = 0;
            try {
                numberOfTimesInt = Integer.parseInt(numberOfTimes);
            } catch (Exception e) {
                showToast(getString(R.string.choose_number_of_times));
                return;
            }
            String experience = binding.experienceList.getSelectedItem().toString();
            if (experience.equals("Experience…")) {
                showToast(getString(R.string.choose_experience));
                return;
            }
            String additionalInfo = binding.moreInfo.getText().toString();
            viewModel.saveProgram(title, startedTime, finishedTime, numberOfTimesInt, experience, additionalInfo);
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void loadObservers() {
        viewModel.closeScreenLiveData().observe(this, closeScreenObserver);
    }

    private final Observer<Boolean> closeScreenObserver = status -> {
        navController.getPreviousBackStackEntry().getSavedStateHandle().set(AddProgramFragment.class.getName(), Bundle.EMPTY);
        navController.popBackStack();
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}