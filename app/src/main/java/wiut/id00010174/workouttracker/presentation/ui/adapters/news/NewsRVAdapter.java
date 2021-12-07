package wiut.id00010174.workouttracker.presentation.ui.adapters.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import wiut.id00010174.workouttracker.R;
import wiut.id00010174.workouttracker.data.models.news.NewsData;
import wiut.id00010174.workouttracker.databinding.NewsListItemBinding;
import wiut.id00010174.workouttracker.utils.helpers.OnClickListenerItem;

/**
 * Created by Farhod Tohirov on 06-December-2021, 14-37
 **/

public class NewsRVAdapter extends ListAdapter<NewsData, NewsRVAdapter.ViewHolder> {

    private static OnClickListenerItem<NewsData> clickListener;

    public NewsRVAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(NewsListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        NewsListItemBinding binding;

        public ViewHolder(@NonNull NewsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(NewsData data) {
            binding.title.setText(data.getTitle());
            String des = "";
            if (data.getDescription() != null) {
                des = data.getDescription();
            } else if (data.getFullDescription() != null) {
                des = data.getFullDescription();
            } else if (data.getContent() != null) {
                des = data.getContent();
            }
            binding.subTitle.setText(des);
            Glide.with(binding.image).load(data.getImageUrl()).placeholder(R.drawable.rocket).error(R.drawable.rocket).into(binding.image);
            binding.getRoot().setOnClickListener(v -> {
                clickListener.click(data);
            });
        }
    }

    public void setOnClickListener(OnClickListenerItem<NewsData> listener) {
        clickListener = listener;
    }


    public static final DiffUtil.ItemCallback<NewsData> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewsData>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsData oldUser, @NonNull NewsData newUser) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldUser.getTitle() == newUser.getTitle();
        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsData oldUser, @NonNull NewsData newUser) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldUser.getTitle().equals(newUser.getTitle()) || oldUser.getContent().equals(newUser.getContent()) || oldUser.getPubDate().equals(newUser.getPubDate());
        }
    };

}