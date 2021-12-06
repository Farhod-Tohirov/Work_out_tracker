package wiut.id00010174.workouttracker.presentation.ui.adapters.welcome_page;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import wiut.id00010174.workouttracker.data.models.welcome_page.WelcomePageModel;
import wiut.id00010174.workouttracker.databinding.LayoutWelcomePageItemBinding;

/**
 * Created by Farhod Tohirov on 06-December-2021, 14-37
 **/

public class WelcomePageRVAdapter extends ListAdapter<WelcomePageModel, WelcomePageRVAdapter.ViewHolder> {

    public static final DiffUtil.ItemCallback<WelcomePageModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<WelcomePageModel>() {
        @Override
        public boolean areItemsTheSame(
                @NonNull WelcomePageModel oldUser, @NonNull WelcomePageModel newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getImageRes() == newUser.getImageRes();
        }

        @Override
        public boolean areContentsTheSame(
                @NonNull WelcomePageModel oldUser, @NonNull WelcomePageModel newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.getTitle() == newUser.getTitle();
        }
    };

    public WelcomePageRVAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutWelcomePageItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LayoutWelcomePageItemBinding binding;

        public ViewHolder(@NonNull LayoutWelcomePageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(WelcomePageModel data) {
            binding.image.setImageResource(data.getImageRes());
            binding.title.setText(data.getTitle());
            binding.subTitle.setText(data.getSubTitle());
        }
    }

}