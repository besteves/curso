package com.example.cadastrocaelum.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.model.Prova;

public class DetalhesProvasFragment extends Fragment{
	
	private Prova prova;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layoutProvas = inflater.inflate(R.layout.fragment_provas_detalhe, container, false);
		
		if(null != getArguments()){
			this.prova = (Prova) getArguments().getSerializable("prova");
		}
		
		if(null != this.prova){
			TextView materia = (TextView) layoutProvas.findViewById(R.id.detalhe_prova_materia);
			materia.setText(prova.getMateria());
			
			TextView data = (TextView) layoutProvas.findViewById(R.id.detalhe_prova_data);
			data.setText(prova.getData());
			
			ListView topicos = (ListView) layoutProvas.findViewById(R.id.detalhe_prova_topicos);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
			topicos.setAdapter(adapter);
		}
		return layoutProvas;
	}

}
