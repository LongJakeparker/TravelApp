package com.greenacademy.travelapp.Activity.Utils.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class PrefsItem {
	
	protected PrefsItem(Context context, String name) {
		this.name 		= name.intern();
		this.context 	= context;
	}
	
	public String getName() {
		return name;
	}
	
	public void unset() {
		SharedPreferences settings = context.getSharedPreferences(PrefsName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.remove(getName());
	}
	
	protected abstract Object getPrefValue();
	protected abstract void setPrefValue(Object value);
	
	public static final String PrefsName = "my_travel_prefs";
	private String name;
	protected Context context;
}
