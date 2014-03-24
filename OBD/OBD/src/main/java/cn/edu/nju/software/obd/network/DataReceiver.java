package cn.edu.nju.software.obd.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

/**
 * Receive data from the server and pass them to UI
 */
public class DataReceiver extends BroadcastReceiver
{
	private static final String TAG = "DataReceiver";

	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction()))
		{
			String title = null;
			if (bundle != null)
			{
				title = bundle.getString(JPushInterface.EXTRA_TITLE);
				String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
				Log.d(TAG, "Title: " + title);
				Log.d(TAG, "Message: " + message);
			}
		}
	}
}
