package wiut.id00010174.workouttracker.utils.helpers;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by Farhod Tohirov on 06-December-2021, 18-12
 **/

public class DatePickerDialogHelper {

    private static DatePickerDialogHelper _instance = null;

    private DatePickerDialogHelper() {

    }

    public static DatePickerDialogHelper getInstance() {
        if (_instance == null) {
            _instance = new DatePickerDialogHelper();
        }
        return _instance;
    }

    public void showDatePickerDialog(Context fragmentManager, DatePickerHelperInterface result) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(fragmentManager, AlertDialog.THEME_HOLO_LIGHT, (view, year, month, dayOfMonth) -> {
            String selectedTime = "";
            if (dayOfMonth <= 9) {
                selectedTime += "0";
            }
            selectedTime += String.valueOf(dayOfMonth + 1);
            selectedTime += ".";
            if (month <= 9) {
                selectedTime += "0";
            }
            selectedTime += String.valueOf(month + 1);
            selectedTime += ".";
            selectedTime += String.valueOf(year);
            result.dateSelected(selectedTime);
        },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }
}

