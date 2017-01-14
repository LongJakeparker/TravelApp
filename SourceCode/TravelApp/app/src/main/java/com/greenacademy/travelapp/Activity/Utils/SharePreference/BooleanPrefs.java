package com.greenacademy.travelapp.Activity.Utils.SharePreference;

/**
 * Class for splash screen.
 * 
 * @author nthanhphong
 * @email nthanhphong89@gmail.com
 */
import android.content.Context;
import android.content.SharedPreferences;

public class BooleanPrefs extends PrefsItem {
	public BooleanPrefs(Context context, String name, boolean defValue) {
		super(context, name);
		this.defValue = defValue;
	}

	@Override
	public void setPrefValue(Object value) {
		Boolean v = (Boolean) value;
		SharedPreferences settings = context.getSharedPreferences(PrefsName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean(getName(), v.booleanValue());
		editor.commit();
	}

	@Override
	public Object getPrefValue() {
		// TODO Auto-generated method stub
		SharedPreferences settings = context.getSharedPreferences(PrefsName,
				Context.MODE_PRIVATE);
		boolean v = settings.getBoolean(getName(), defValue);
		return v;
	}

	public boolean getValue() {
		Boolean b = (Boolean) getPrefValue();
		return b.booleanValue();
	}

	public void setValue(boolean value) {
		setPrefValue(value);
	}

	private boolean defValue;
}
