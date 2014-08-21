package com.example.cadastrocaelum.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.model.Aluno;

public class ListaAlunosAdapter extends ArrayAdapter<Aluno> {

	List<Aluno> alunos;
	Context context;
	LayoutInflater li;
	int resource;
	TextView tv;
	
	public ListaAlunosAdapter(Context context, int resource, List<Aluno> alunos) {
		super(context, resource, alunos);
		this.alunos = alunos;
		this.context = context;
		this.resource = resource;
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
	    	view = li.inflate(resource, parent, false);
	    } 
		
		if(position % 2 == 0){
			view.setBackgroundColor(view.getResources().getColor(R.color.aluno_fundo_item_par));
		} 
		
		tv = (TextView) view.findViewById(R.id.tvNome);
		if(null != tv)
			tv.setText(alunos.get(position).getNome());
		
		tv = (TextView) view.findViewById(R.id.tvEndereco);
		if(null != tv)
			tv.setText(alunos.get(position).getEndereco());
		
		tv = (TextView) view.findViewById(R.id.tvTelefone);
		if(null != tv)
			tv.setText(alunos.get(position).getTelefone());
		
		tv = (TextView) view.findViewById(R.id.tvSite);
		if(null != tv)
			tv.setText(alunos.get(position).getSite());
		
		tv = (TextView) view.findViewById(R.id.tvNota);
		if(null != tv)
			tv.setText("" + alunos.get(position).getNota());
		
		ImageView alunoView = (ImageView)view.findViewById(R.id.alunoFoto);
		if(null != alunos.get(position).getCaminhoFoto()){
			Bitmap bitmap = BitmapFactory.decodeFile(alunos.get(position).getCaminhoFoto());
			int largura = 250;
			int altura = (bitmap.getHeight() * largura / bitmap.getWidth());
			Bitmap reduzido = Bitmap.createScaledBitmap(bitmap, largura, altura, true);
			 
			alunoView.setImageBitmap(reduzido);
		}
		
		return view;
	}
}
