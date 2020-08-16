package lk.lab24.visihatharaclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmManagerBrodcastReciver extends BroadcastReceiver {
	AlarmManager am;

	public AlarmManagerBrodcastReciver() {
		super();

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "inReciveAlarmBrodcast"+intent.getAction(), Toast.LENGTH_SHORT).show();
//		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//		Intent i = new Intent(context, NewAppWidget.class).setAction(context.getString(R.string.widgetUPdateAction));
//		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
//		if (intent.getAction().equals("lab24.visihathara.onDisabel")) {
//			am.cancel(pi);
//			Toast.makeText(context, "Disseb action sen", Toast.LENGTH_SHORT).show();
//		}
//		am.cancel(pi);
//		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 30, pi); // Millisec * Second * Minut


	}
}
