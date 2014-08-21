package com.example.cadastrocaelum.task;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cadastrocaelum.converter.AlunoConverter;
import com.example.cadastrocaelum.model.Aluno;
import com.example.cadastrocaelum.support.WebClient;

public class SendStudentClass extends AsyncTask<Void, Void, String>{
	Activity activity;
	List<Aluno> alunos;
	ProgressDialog dialog;
	
	@Override
	protected void onPreExecute() {
		dialog = ProgressDialog.show(activity, "Aguarde...", "Enviando lista de alunos", true, true);
		dialog.show();
		super.onPreExecute();
	}
	
	
	public SendStudentClass (Activity activity, List<Aluno> alunos ){
		this.activity = activity;
		this.alunos = alunos;
	}

	@Override
	protected String doInBackground(Void... params) {
		String json = new AlunoConverter().toJSON(alunos);
		WebClient client = new WebClient("http://www.caelum.com.br/mobile");
		String resposta = client.post(json);
		return resposta;
	}
	
	@Override
	protected void onPostExecute(String resposta) {
		if(null != resposta)
			Toast.makeText(activity, resposta, Toast.LENGTH_LONG).show();
		dialog.dismiss();
		super.onPostExecute(resposta);
	}

}
