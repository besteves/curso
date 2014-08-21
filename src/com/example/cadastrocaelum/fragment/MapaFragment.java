package com.example.cadastrocaelum.fragment;

import java.util.List;

import com.example.cadastrocaelum.dao.AlunoDAO;
import com.example.cadastrocaelum.model.Aluno;
import com.example.cadastrocaelum.model.Localizador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaFragment extends SupportMapFragment{
	@Override
	public void onResume() {
		super.onResume();
		
		//Colocar o endereco na mão se você já sabe a latitude e longitude
//		GoogleMap mapa = getMap();
//		mapa.addMarker(new MarkerOptions().title("Caelum").snippet("Ensino e Inovação")
//				.position(new LatLng(-23.588305, -46.632395)));
		
		Localizador localizador = new Localizador(getActivity());
//		LatLng local = localizador.getCoordenada("Rua Darci Rangel, 29, Campo Grande");
//		Log.i("Mapa", "Coordenadas de Casa:" + local);
//		centralizaNo(local);
		
		AlunoDAO dao = new AlunoDAO(getActivity());
		List<Aluno> alunos = dao.getStudents();
		
		for(Aluno aluno : alunos){
			MarkerOptions marker = new MarkerOptions();
			marker.title(aluno.getNome()).snippet(aluno.getTelefone())
			.position(localizador.getCoordenada(aluno.getEndereco()));
			getMap().addMarker(marker);
			centralizaNo(localizador.getCoordenada(aluno.getEndereco()));
		}
	}
	
	public void centralizaNo(LatLng local){
		GoogleMap mapa = getMap();
		mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 17));
	}
}
