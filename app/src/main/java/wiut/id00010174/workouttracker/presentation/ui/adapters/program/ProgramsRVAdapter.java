package wiut.id00010174.workouttracker.presentation.ui.adapters.program;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.local.room.entity.ProgramData;
import wiut.id00010174.workouttracker.databinding.LayoutProgramItemBinding;
import wiut.id00010174.workouttracker.utils.helpers.ExperienceHelper;
import wiut.id00010174.workouttracker.utils.helpers.OnClickListenerItem;

/**
 * Created by Farhod Tohirov on 06-December-2021, 14-37
 **/

public class ProgramsRVAdapter extends ListAdapter<ProgramData, ProgramsRVAdapter.ViewHolder> {

    private static OnClickListenerItem<ProgramData> clickListener;

    public ProgramsRVAdapter() {
        super(DIFF_CALLBACK);
    }

    public void setOnClickListener(OnClickListenerItem<ProgramData> listener) {
        clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutProgramItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LayoutProgramItemBinding binding;

        public ViewHolder(@NonNull LayoutProgramItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ProgramData data) {
            binding.experience.setText(binding.getRoot().getContext().getString(R.string.experience_n, data.getExperience()));
            binding.numberOfTimes.setText(binding.getRoot().getContext().getString(R.string.n_times, data.getNumberOfTimes()));
            binding.title.setText(data.getTitle());
            if (data.getAdditionalNotes() == null || data.getAdditionalNotes().isEmpty()) {
                binding.subTitle.setVisibility(View.GONE);
            } else {
                binding.subTitle.setVisibility(View.VISIBLE);
                binding.subTitle.setText(data.getAdditionalNotes());
            }
            binding.startFinishTime.setText(binding.getRoot().getContext().getString(R.string.start_finish, data.getStartedTime(), data.getFinishedTime()));
            switch (ExperienceHelper.getInstance().getExperienceStatus(data.getExperience())) {
                case EXCELLENT: {
                    binding.statusImage.setImageResource(R.drawable.excellent);
                    break;
                }
                case GOOD: {
                    binding.statusImage.setImageResource(R.drawable.good);
                    break;
                }
                case NOT_BAD: {
                    binding.statusImage.setImageResource(R.drawable.not_very_bad);
                    break;
                }
                case BAD: {
                    binding.statusImage.setImageResource(R.drawable.bad);
                    break;
                }
                default: {
                    binding.statusImage.setImageResource(R.drawable.awful);
                    break;
                }
            }
            binding.getRoot().setOnClickListener(v -> {
                clickListener.click(data);
            });
        }
    }

    public static final DiffUtil.ItemCallback<ProgramData> DIFF_CALLBACK = new DiffUtil.ItemCallback<ProgramData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ProgramData oldUser, @NonNull ProgramData newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getId() == newUser.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProgramData oldUser, @NonNull ProgramData newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.getTitle().equals(newUser.getTitle()) || oldUser.getStartedTime().equals(newUser.getStartedTime()) || oldUser.getFinishedTime().equals(newUser.getFinishedTime()) ||
                    oldUser.getNumberOfTimes() == newUser.getNumberOfTimes() || oldUser.getAdditionalNotes().equals(newUser.getAdditionalNotes()) || oldUser.getExperience().equals(newUser.getExperience());
        }
    };

}