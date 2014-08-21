package com.example.cadastrocaelum;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.cadastrocaelum.helper.FormularioHelper;
import com.example.cadastrocaelum.model.Aluno;

public class FormularioActivity extends Activity {
	FormularioHelper formHelper;
	private ImageView alunoImageView;
	private String caminhoFoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
		
		//Criação do helper com os métodos que manipulam os dados
		formHelper = new FormularioHelper(FormularioActivity.this);
		
		if(getIntent().hasExtra("alunoSelecionado")){
			formHelper.putStudent((Aluno)getIntent().getSerializableExtra("alunoSelecionado"));
			((Button) findViewById(R.id.buttonSave)).setText("Alterar");
		}
		
		alunoImageView = (ImageView) findViewById(R.id.imageView1);
		alunoImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				caminhoFoto = getExternalFilesDir(null) + "/"
						+ System.currentTimeMillis() + ".jpg";
				
				Uri uri = Uri.fromFile(new File(caminhoFoto));
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				startActivity(intent);
				startActivityForResult(intent, 123);
			}
		});
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 123){
			if(resultCode == Activity.RESULT_OK){
				formHelper.carregaFoto(caminhoFoto);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if(item.getItemId() == R.id.home){
			onBackPressed();
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
