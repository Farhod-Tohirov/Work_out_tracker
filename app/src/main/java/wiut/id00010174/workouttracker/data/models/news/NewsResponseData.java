package wiut.id00010174.workouttracker.data.models.news;

import java.util.List;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-12
 **/

public class NewsResponseData {
    private String status;
    private int totalResults;
    private List<NewsData> results;
    private int nextPage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsData> getResults() {
        return results;
    }

    public void setResults(List<NewsData> results) {
        this.results = results;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
