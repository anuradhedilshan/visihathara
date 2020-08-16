package lk.lab24.visihatharaclock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class RecycleViewTheams extends RecyclerView.Adapter<RecycleViewTheams.RecycleviewHolder> {
	public static int SELECTEDTHEAMPOTION;
	private static List data = new ArrayList<String>();
	Context context;
	Activity activity;


	public RecycleViewTheams(Context context, Activity activity) {
		this.context = context;
		this.activity = activity;

	}

	public static void setData(List data) {
		RecycleViewTheams.data = data;
		Log.d("fuck", "setData: "+data.toString());
	}

	public static int getSELECTEDTHEAMPOTION() {
		return SELECTEDTHEAMPOTION;
	}

	public static void setSELECTEDTHEAMPOTION(int SELECTEDTHEAMPOTION) {
		RecycleViewTheams.SELECTEDTHEAMPOTION = SELECTEDTHEAMPOTION;
	}

	@NonNull
	@Override
	public RecycleviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.theamitem, parent, false);
		return new RecycleviewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull RecycleviewHolder holder, int position) {
		final ImageView v = holder.theamPreview;
		if (data.size() != 0) {
			Log.d("fuck", "onBindViewHolder: " + data.get(position));
		}
		holder.layout.setOnClickListener((View view) -> {
			Log.d("fuck", "onBindViewHolder: ONCLCCK");

		});
	}

	private void galleryAddPic(String imagePath) {
		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(imagePath);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		context.sendBroadcast(mediaScanIntent);
	}


	@Override
	public int getItemCount() {
		if (data != null) {
			return data.size();
		} else {
			return 0;
		}
	}


	class RecycleviewHolder extends RecyclerView.ViewHolder {
		ImageView theamPreview;
		LinearLayout layout;

		public RecycleviewHolder(@NonNull View itemView) {
			super(itemView);
			this.theamPreview = itemView.findViewById(R.id.theamPreview);
			this.layout = itemView.findViewById(R.id.theamItemLayout);
		}
	}
}
