package wiut.id00010174.workouttracker.data.models.welcome_page;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Created by Farhod Tohirov on 06-December-2021, 14-38
 **/

public class WelcomePageModel {
    @DrawableRes
    private int imageRes;
    @StringRes
    private int title;
    @StringRes
    private int subTitle;

    public WelcomePageModel(int title, int subTitle, int imageRes) {
        this.title = title;
        this.subTitle = subTitle;
        this.imageRes = imageRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(int subTitle) {
        this.subTitle = subTitle;
    }
}
