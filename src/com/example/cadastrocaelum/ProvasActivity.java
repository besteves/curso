package com.example.cadastrocaelum;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.example.cadastrocaelum.fragment.DetalhesProvasFragment;
import com.example.cadastrocaelum.fragment.ListaProvasFragment;
import com.example.cadastrocaelum.model.Prova;

public class ProvasActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provas);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.provas_view, new ListaProvasFragment());	
		if(getResources().getBoolean(R.bool.isTablet))
			transaction.replace(R.id.provas_detalhe_view, new DetalhesProvasFragment());
		transaction.commit();
	}
	
	public void selecionaProva(Prova provaSelecionada){
		Bundle argumentos = new Bundle();
		argumentos.putSerializable("prova", provaSelecionada);
		
		DetalhesProvasFragment detalhesProvasFragment = new DetalhesProvasFragment();
		detalhesProvasFragment.setArguments(argumentos);
		
		if(getResources().getBoolean(R.bool.isTablet)){

			FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
			fragmentTransaction.replace(R.id.provas_detalhe_view, detalhesProvasFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}		
	}
}
