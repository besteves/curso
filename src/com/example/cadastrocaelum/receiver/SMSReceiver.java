package com.example.cadastrocaelum.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		AlunoDAO  dao = new AlunoDAO(context);
		Bundle bundle = intent.getExtras();
		Object[] messages = (Object[]) bundle.get("pdus");
		byte[] message = (byte[]) messages[0];
		SmsMessage sms = SmsMessage.createFromPdu(message);
		if(dao.isAluno(sms.getDisplayOriginatingAddress())){
			MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.msg);
			mediaPlayer.start();
			Toast.makeText(context, "Chegou um sms do aluno " + sms.getDisplayOriginatingAddress() , Toast.LENGTH_LONG).show();
		}
		
	}
}
