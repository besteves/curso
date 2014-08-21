package com.example.cadastrocaelum.adapter;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.model.Aluno;

public class GaleriaAlunosAdapter extends PagerAdapter{
	private List<Aluno> alunos;
	private Activity activity;
	
	public GaleriaAlunosAdapter(List<Aluno> listaAlunos, Activity context){
		this.alunos = listaAlunos;
		this.activity = context;
	}
	
	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}
	
	@Override
	public void destroyItem(View pager, int position, Object object) {
		((ViewPager) pager).removeView((View) object);
	}
	
	@Override
	public Object instantiateItem(View pager, int position) {
		ImageView foto = new ImageView(activity);
		Aluno aluno = alunos.get(position);
		
		if(aluno.getCaminhoFoto() != null){
			Bitmap imagem = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
			foto.setImageBitmap(Bitmap.createScaledBitmap(imagem, 200, 200, true));
		} else {
			foto.setImageResource(R.drawable.ic_no_image);
		}
		
		((ViewPager) pager).addView(foto, 0);
		return foto;
	}
}
