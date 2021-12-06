package wiut.id00010174.workouttracker.presentation.ui.screen.home.programs.show_program;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.databinding.DialogAboutProgramDataBinding;
import wiut.id00010174.workouttracker.utils.helpers.ExperienceHelper;
import wiut.id00010174.workouttracker.utils.helpers.OnClickListenerItem;

/**
 * Created by Farhod Tohirov on 06-December-2021, 20-22
 **/

public class ShowProgramDialog extends AlertDialog {

    public ShowProgramDialog(@NonNull Context context, ProgramData data, OnClickListenerItem<ProgramData> updateListener, OnClickListenerItem<ProgramData> deleteListener) {
        super(context);
        setCancelable(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DialogAboutProgramDataBinding binding = DialogAboutProgramDataBinding.inflate(LayoutInflater.from(context));
        binding.programData.experience.setText(binding.getRoot().getContext().getString(R.string.experience_n, data.getExperience()));
        binding.programData.numberOfTimes.setText(binding.getRoot().getContext().getString(R.string.n_times, data.getNumberOfTimes()));
        binding.programData.title.setText(data.getTitle());
        if (data.getAdditionalNotes().isEmpty() || data.getAdditionalNotes() == null) {
            binding.programData.subTitle.setVisibility(View.GONE);
        } else {
            binding.programData.subTitle.setVisibility(View.VISIBLE);
            binding.programData.subTitle.setText(data.getAdditionalNotes());
        }
        binding.programData.startFinishTime.setText(binding.getRoot().getContext().getString(R.string.start_finish, data.getStartedTime(), data.getFinishedTime()));
        switch (ExperienceHelper.getInstance().getExperienceStatus(data.getExperience())) {
            case EXCELLENT: {
                binding.programData.statusImage.setImageResource(R.drawable.excellent);
                break;
            }
            case GOOD: {
                binding.programData.statusImage.setImageResource(R.drawable.good);
                break;
            }
            case NOT_BAD: {
                binding.programData.statusImage.setImageResource(R.drawable.not_very_bad);
                break;
            }
            case BAD: {
                binding.programData.statusImage.setImageResource(R.drawable.bad);
                break;
            }
            default: {
                binding.programData.statusImage.setImageResource(R.drawable.awful);
                break;
            }
        }
        binding.updateButton.setOnClickListener(v -> {
            updateListener.click(data);
            dismiss();
        });
        binding.deleteButton.setOnClickListener(v -> {
            deleteListener.click(data);
            dismiss();
        });
        binding.backButton.setOnClickListener(v -> {
            dismiss();
        });
        setView(binding.getRoot());
    }
}
