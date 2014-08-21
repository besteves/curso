package com.example.cadastrocaelum.fragment;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;

import com.example.cadastrocaelum.ProvasActivity;
import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.adapter.ListaProvasAlunosAdapter;
import com.example.cadastrocaelum.model.Prova;

public class ListaProvasFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.fragment_prova_lista, container, false);
		ExpandableListView listView = (ExpandableListView) layoutProvas.findViewById(R.id.lista_provas);
		
		Prova provaMatematica = new Prova("18/08/2014", "Matemática");
		provaMatematica.setTopicos(Arrays.asList("Algebra Linear", "Integral", "Diferencial"));
		
		Prova provaPortugues = new Prova("19/08/2014", "Português");
		provaPortugues.setTopicos(Arrays.asList("Complemento Nominal", "Orações Subordinadas"));
		
		List<Prova> listaProvas = Arrays.asList(provaMatematica, provaPortugues);
		listView.setAdapter(new ListaProvasAlunosAdapter(getActivity(), R.layout.item_prova, listaProvas));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				Prova provaSelecionada = (Prova) adapter.getItemAtPosition(position);
				ProvasActivity provasActivity = (ProvasActivity) getActivity();
				provasActivity.selecionaProva(provaSelecionada);

			}
		});
		
		return layoutProvas;
	}

}
