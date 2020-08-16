package lk.lab24.visihatharaclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

	private static final String TAG = "fuck";

	public static Bitmap buildUpdate(Context context, String year, String month, String days, String exam) {
		Bitmap myBitmap = Bitmap.createBitmap(300, 90, Bitmap.Config.ARGB_4444);
		Canvas myCanvas = new Canvas(myBitmap);
		Paint paint = new Paint();
		Typeface clock = ResourcesCompat.getFont(context, R.font.ranmadu);
		paint.setAntiAlias(true);
		paint.setSubpixelText(true);
		paint.setTypeface(clock);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(35);
		paint.setTextAlign(Paint.Align.CENTER);
		Paint examp = new Paint();
		examp.setStyle(Paint.Style.FILL);
		examp.setColor(Color.WHITE);
		examp.setTextSize(35);
		examp.setTextAlign(Paint.Align.LEFT);
		myCanvas.drawText(exam, 0, 25, examp);
		myCanvas.drawText("j,g ;j", 220, 20, paint);
		myCanvas.drawText(year, 60, 70, paint);
		myCanvas.drawText(month, 150, 70, paint);
		myCanvas.drawText(days, 250, 70, paint);

		return myBitmap;
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		Date dateandtime = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time12Fromat = new SimpleDateFormat("HH: mm");

		String date = dateFormat.format(dateandtime);
		String time = time12Fromat.format(dateandtime);


		for (int appWidgetId : appWidgetIds) {

			Intent i = new Intent("MY_PACKAGE_NAME.WIDGET_BUTTON");
			// Construct the RemoteViews object
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
			views.setImageViewBitmap(R.id.how, buildUpdate(context, "wjq 2", "udi 2", "oska 26", "2021 A/L"));
			views.setTextViewText(R.id.date, date);
			views.setTextViewText(R.id.time, time);
			// Instruct the widget manager to update the widget
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
			views.setOnClickPendingIntent(R.id.refresh, pendingIntent);
			appWidgetManager.updateAppWidget(appWidgetId, views);
			Log.d(TAG, "updateAppWidget: ");


		}

	}

	@Override
	public void onEnabled(Context context) {
		// Enter relevant functionality for when the first widget is created
		Toast.makeText(context, "onEnabel", Toast.LENGTH_SHORT).show();
		context.sendBroadcast(new Intent("lab24.visihathara.onEnabel"));

	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
		Toast.makeText(context, "onDisabel", Toast.LENGTH_SHORT).show();
		context.sendBroadcast(new Intent("lab24.visihathara.onDisabel"));
	}

	@Override
	public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
		Toast.makeText(context, "onAppWidgetOptionChnaged", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Toast.makeText(context, "Update On Recive" + intent.getAction(), Toast.LENGTH_SHORT).show();
		if (intent.getAction().equals(context.getString(R.string.widgetUPdateAction))) {
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			int ids[] = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, NewAppWidget.class));
			this.onUpdate(context, appWidgetManager, ids);
		}


		Log.d(TAG, "onReceive: ");
	}
}

