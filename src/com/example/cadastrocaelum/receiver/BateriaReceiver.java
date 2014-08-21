package com.example.cadastrocaelum.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BateriaReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int valor = intent.getIntExtra("level", 0);
		Toast.makeText(context, valor + "%", Toast.LENGTH_LONG).show();
	}
}
