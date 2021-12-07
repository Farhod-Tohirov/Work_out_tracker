package wiut.id00010174.workouttracker.utils.helpers;

/**
 * Created by Farhod Tohirov on 07-December-2021, 11-52
 **/

public interface NewsBackHelper<T> {
    void success(T data);

    void error(String message);
}
