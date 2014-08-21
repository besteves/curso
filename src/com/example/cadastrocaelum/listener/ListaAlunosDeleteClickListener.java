package com.example.cadastrocaelum.listener;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

import com.example.cadastrocaelum.ListaAlunosActivity;
import com.example.cadastrocaelum.dao.AlunoDAO;

public class ListaAlunosDeleteClickListener implements OnClickListener, OnMenuItemClickListener{

	ListaAlunosActivity activity;
	AlunoDAO alunoDAO;
	boolean excluir;
	
	public ListaAlunosDeleteClickListener(ListaAlunosActivity activity, boolean excluir) {
		super();
		this.activity = activity;
		this.excluir = excluir;
        alunoDAO = new AlunoDAO(activity);
	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		delete();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		delete();
		return false;
	}
	
	private void delete() {
		if(excluir){
			alunoDAO.deletar(activity.getAlunoSelecionado());
			alunoDAO.close();
			activity.loadList();
		}
	}
}
