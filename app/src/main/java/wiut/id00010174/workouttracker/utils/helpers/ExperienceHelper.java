package wiut.id00010174.workouttracker.utils.helpers;

/**
 * Created by Farhod Tohirov on 06-December-2021, 19-08
 **/

public class ExperienceHelper {

    private static ExperienceHelper _instance = null;

    private ExperienceHelper() {

    }

    public static ExperienceHelper getInstance() {
        if (_instance == null) {
            _instance = new ExperienceHelper();
        }
        return _instance;
    }

    public Experiences getExperienceStatus(String experience) {
        switch (experience) {
            case "Excellent": {
                return Experiences.EXCELLENT;
            }
            case "Good": {
                return Experiences.GOOD;
            }
            case "Not very bad": {
                return Experiences.NOT_BAD;
            }
            case "Bad": {
                return Experiences.BAD;
            }
            default: {
                return Experiences.AWFUL;
            }
        }
    }
}
