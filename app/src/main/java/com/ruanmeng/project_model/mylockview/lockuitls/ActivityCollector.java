package com.ruanmeng.project_model.mylockview.lockuitls;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

	public static List<Activity> activities = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		activities.add(activity);


	}

	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	public static void finishAll() {
		Log.d("allen", "activities" + activities.size());
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
}
