package wiut.id00010174.workouttracker.presentation.ui.screen.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import wiut.id00010174.workouttracker.data.models.news.NewsData;
import wiut.id00010174.workouttracker.databinding.FragmentNewsBinding;
import wiut.id00010174.workouttracker.presentation.ui.adapters.news.NewsRVAdapter;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.news.NewsViewModel;
import wiut.id00010174.workouttracker.presentation.viewmodels.home.news.impl.NewsViewModelImpl;

@AndroidEntryPoint
public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private NavController navController;
    private NewsViewModel viewModel;
    private final NewsRVAdapter adapter = new NewsRVAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(NewsViewModelImpl.class);
        viewModel.getNews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        navController = Navigation.findNavController(view);
        loadViews();
        loadObservers();
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void loadObservers() {
        viewModel.showLoaderLiveData().observe(this, showLoaderObserver);
        viewModel.newsListLiveData().observe(getViewLifecycleOwner(), newListObserver);
        viewModel.messageLiveData().observe(this, messageObserver);
    }

    private final Observer<String> messageObserver = message -> {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    };

    private final Observer<List<NewsData>> newListObserver = newsDataList -> {
        adapter.submitList(newsDataList);
    };

    private final Observer<Boolean> showLoaderObserver = showLoader -> {
        if (showLoader) {
            binding.loader.setVisibility(View.VISIBLE);
        } else {
            binding.loader.setVisibility(View.GONE);
        }
    };

    private void loadViews() {
        binding.newsList.setAdapter(adapter);
        adapter.setOnClickListener(newData -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newData.getLink()));
            startActivity(intent);
        });
        binding.backButton.setOnClickListener(v -> {
            navController.popBackStack();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.newsList.setAdapter(null);
        binding = null;
    }
}