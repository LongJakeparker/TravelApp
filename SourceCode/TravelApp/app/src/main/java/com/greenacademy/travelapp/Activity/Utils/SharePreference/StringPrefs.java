package com.greenacademy.travelapp.Activity.Utils.SharePreference;

/**
 * Class for splash screen.
 * 
 * @author nthanhphong
 * @email nthanhphong89@gmail.com
 */
import android.content.Context;
import android.content.SharedPreferences;

public class StringPrefs extends PrefsItem {
	public StringPrefs(Context context, String name, String defValue) {
		super(context, name);
		this.defValue = defValue;
	}

	@Override
	public void setPrefValue(Object value) {
		String v = (String) value;
		SharedPreferences settings = context.getSharedPreferences(PrefsName,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(getName(), v);
		editor.commit();
	}

	@Override
	public Object getPrefValue() {
		// TODO Auto-generated method stub
		SharedPreferences settings = context.getSharedPreferences(PrefsName,
				Context.MODE_PRIVATE);
		String v = settings.getString(getName(), defValue);
		return v;
	}

	public String getValue() {
		String s = (String) getPrefValue();
		return s;
	}

	public void setValue(String value) {
		setPrefValue(value);
	}

	private String defValue;
}
