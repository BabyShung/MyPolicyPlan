package Objects;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertMessage {

    public static void showAlertMessage(String alertTitle, String msg, Activity myActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)myActivity);
        builder.setTitle(alertTitle)
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("Close",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
