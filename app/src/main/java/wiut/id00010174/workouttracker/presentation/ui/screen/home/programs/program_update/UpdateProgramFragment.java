package wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.program_update;

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
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.databinding.FragmentUpdateProgramBinding;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.program_update.UpdateProgramViewModel;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.program_update.impl.UpdateProgramViewModelImpl;
import wiut.id00010174.workouttracker.utils.helpers.DatePickerDialogHelper;

@AndroidEntryPoint
public class UpdateProgramFragment extends Fragment {

    private FragmentUpdateProgramBinding binding;
    private NavController navController;
    private UpdateProgramViewModel viewModel;
    private int programId = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UpdateProgramViewModelImpl.class);
        programId = UpdateProgramFragmentArgs.fromBundle(getArguments()).getProgramId();
        viewModel.getProgramdata(programId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUpdateProgramBinding.inflate(inflater, container, false);
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
            if (binding.title.getText() == null || binding.title.getText().toString().isEmpty()) {
                showToast(getString(R.string.choose_program_name));
                return;
            }
            String title = binding.title.getText().toString();
            if (binding.startedTime.getText() == null || binding.startedTime.getText().toString().isEmpty()) {
                showToast(getString(R.string.choose_started_time));
                return;
            }
            String startedTime = binding.startedTime.getText().toString();

            if (binding.finishedTime.getText() == null || binding.finishedTime.getText().toString().isEmpty()) {
                showToast(getString(R.string.choose_finished_time));
                return;
            }
            String finishedTime = binding.finishedTime.getText().toString();

            if (binding.numberOfTimes.getText() == null || binding.numberOfTimes.getText().toString().isEmpty()) {
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
            viewModel.updateProgram(programId, title, startedTime, finishedTime, numberOfTimesInt, experience, additionalInfo);
        });
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void loadObservers() {
        viewModel.programDataLiveData().observe(getViewLifecycleOwner(), programDataObserver);
        viewModel.closeScreenLiveData().observe(this, closeScreenObserver);
    }

    private final Observer<ProgramData> programDataObserver = programData -> {
        binding.title.setText(programData.getTitle());
        binding.startedTime.setText(programData.getStartedTime());
        binding.finishedTime.setText(programData.getFinishedTime());
        binding.numberOfTimes.setText(String.valueOf(programData.getNumberOfTimes()));
        binding.moreInfo.setText(programData.getAdditionalNotes());
        String[] experiences = getActivity().getResources().getStringArray(R.array.experience);
        int index = 0;
        for (int i = 0; i < experiences.length; i++) {
            if (experiences[i].equals(programData.getExperience())) {
                index = i;
                break;
            }
        }
        binding.experienceList.setSelection(index);
    };

    private final Observer<Boolean> closeScreenObserver = status -> {
        navController.popBackStack();
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}