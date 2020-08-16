package lk.lab24.visihatharaclock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "fuck";
	int appWidgetId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		upgradeSecurityProvider();
		updateview();

	}


	private void updateview() {
		String inputDate = "2021 05 06";
		String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
		String currentTime = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());

		Log.d(TAG, "updateview: cu");
		RadioGroup radioGroup = findViewById(R.id.radioGroup);
		TextView date = findViewById(R.id.date);
		TextView time = findViewById(R.id.time);
		ImageView imageView = findViewById(R.id.how);

		date.setText(currentDate);
		time.setText(currentTime);
		//_________________________________________________________________


	}

	private void commitExamChange(int examId) {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(getString(R.string.examId), examId);
		// b1/l = 0 , o/l - 1
		editor.commit();
	}

	private String getExam() {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		int i = sharedPreferences.getInt(getString(R.string.examId), 0);
		String exam = i == 0 ? "A/L" : "O/L";
		return exam;

	}

	private int getExamInt() {
		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		int i = sharedPreferences.getInt(getString(R.string.examId), 0);
		return i;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Toast.makeText(this, "" + resultCode, Toast.LENGTH_SHORT).show();
	}

	private void upgradeSecurityProvider() {
		try {
			ProviderInstaller.installIfNeeded(this);
		} catch (GooglePlayServicesRepairableException e) {
			e.printStackTrace();
		} catch (GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}
		ProviderInstaller.installIfNeededAsync(this, new ProviderInstaller.ProviderInstallListener() {
			@Override
			public void onProviderInstalled() {
				Log.d("fuck", "onProviderInstalled: ");

			}

			@Override
			public void onProviderInstallFailed(int errorCode, Intent recoveryIntent) {
//        GooglePlayServicesUtil.showErrorNotification(errorCode, MainApplication.this);
				GoogleApiAvailability.getInstance().showErrorNotification(MainActivity.this, errorCode);
			}
		});
	}

	public void openCustomize_activity(View view) {
		View fragment = findViewById(R.id.widgetViewFragment);
		Intent i = new Intent(this, customize_widget.class);
		ActivityOptionsCompat options = ActivityOptionsCompat.
				makeSceneTransitionAnimation(MainActivity.this, (View) fragment, "profile");
		startActivityForResult(i, 5008, options.toBundle());
	}
}