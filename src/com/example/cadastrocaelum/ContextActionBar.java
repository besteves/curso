package com.example.cadastrocaelum;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import com.example.cadastrocaelum.listener.ListaAlunosDeleteClickListener;
import com.example.cadastrocaelum.model.Aluno;

public class ContextActionBar implements ActionMode.Callback {

	private Aluno alunoSelecionado;
	private ListaAlunosActivity activity;
	
	public ContextActionBar(Aluno alunoSelecionado, ListaAlunosActivity activity) {
		super();
		this.alunoSelecionado = alunoSelecionado;
		this.activity = activity;
	}

	@Override
	public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode arg0, Menu menu) {
		MenuItem ligar = menu.add("Ligar");
		Intent intentLigar = new Intent(Intent.ACTION_DIAL);
		intentLigar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
		ligar.setIntent(intentLigar);
		
		MenuItem sms = menu.add("Enviar SMS");
		Intent intentSMS = new Intent(Intent.ACTION_VIEW);
		intentSMS.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
		intentSMS.putExtra("sms_body", "Mensagem");
		sms.setIntent(intentSMS);
		
		MenuItem mapa = menu.add("Achar no Mapa");
		Intent intentMapa = new Intent(Intent.ACTION_VIEW);
		intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()));
		mapa.setIntent(intentMapa);
		
		MenuItem site = menu.add("Navegar no site"); 
		Intent intentSite = new Intent(Intent.ACTION_VIEW);
		intentSite.setData(Uri.parse("http://" + alunoSelecionado.getSite()));
		site.setIntent(intentSite);
		
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener((OnMenuItemClickListener) new ListaAlunosDeleteClickListener(activity, true));

		return true;
	}

	@Override
	public void onDestroyActionMode(ActionMode arg0) {
		
	}

	@Override
	public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
		return false;
	}

}
