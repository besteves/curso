package com.example.cadastrocaelum.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.cadastrocaelum.ContextActionBar;
import com.example.cadastrocaelum.FormularioActivity;
import com.example.cadastrocaelum.ListaAlunosActivity;
import com.example.cadastrocaelum.model.Aluno;

public class ListaAlunosItemClickListener implements OnItemClickListener, OnItemLongClickListener {

	ListaAlunosActivity activity;
	
	public ListaAlunosItemClickListener(){}
	
	public ListaAlunosItemClickListener(ListaAlunosActivity activity){
		this.activity = activity;
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Intent intent = new Intent(activity, FormularioActivity.class);
		intent.putExtra("alunoSelecionado", ((Aluno)arg0.getItemAtPosition(position)));
		activity.startActivity(intent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		activity.setAlunoSelecionado((Aluno)arg0.getItemAtPosition(position));
		ContextActionBar actionBar = new ContextActionBar(((Aluno)arg0.getItemAtPosition(position)), activity);
		activity.startSupportActionMode(actionBar);
		return true;
	}

}
