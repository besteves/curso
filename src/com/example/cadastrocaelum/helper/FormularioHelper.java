package com.example.cadastrocaelum.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cadastrocaelum.FormularioActivity;
import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.dao.AlunoDAO;
import com.example.cadastrocaelum.model.Aluno;

public class FormularioHelper {
	private EditText editTextName;
	private EditText editTextPhone;
	private EditText editTextAddress;
	private EditText editTextSite;
	private SeekBar seekBarGrade;
	private Button buttonClear;
	private Button buttonSave;
	private Aluno aluno;
	private TextView tvNota;
	private FormularioActivity formActivity;
	private ImageView alunoImageView;
	
	public FormularioHelper() {}
	
	public FormularioHelper(FormularioActivity activity){
		this.formActivity = activity;
		
		editTextName = (EditText) formActivity.findViewById(R.id.editText1);
		editTextPhone = (EditText) formActivity.findViewById(R.id.editText2);
		editTextAddress = (EditText) formActivity.findViewById(R.id.editText3);
		editTextSite = (EditText) formActivity.findViewById(R.id.editText4);
		seekBarGrade = (SeekBar) formActivity.findViewById(R.id.seekBar1);
		buttonClear = (Button) formActivity.findViewById(R.id.buttonClearFields);
		buttonSave = (Button) formActivity.findViewById(R.id.buttonSave);
		seekBarGrade = (SeekBar) formActivity.findViewById(R.id.seekBar1);
		tvNota = (TextView) formActivity.findViewById(R.id.textView5);
		alunoImageView = (ImageView) formActivity.findViewById(R.id.imageView1);
		aluno = new Aluno();
		
		//Limpar os campos da tela ao clicar
		buttonClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearFields();
			}
		});
		
		//Chamar o método que salva as informações do aluno digitadas
		buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				aluno = getStudentFromForm();
				
				AlunoDAO dao = new AlunoDAO(formActivity);
				
				if(null == aluno.getId()){
					dao.insert(aluno);
					Toast.makeText(formActivity, " UOW!- Aluno: \"" + getStudentFromForm().getNome() + "\" foi salvo com sucesso!", Toast.LENGTH_SHORT).show();
					;
				}else{
					dao.alterar(aluno);
					Toast.makeText(formActivity, " UOW!- Aluno \"" + getStudentFromForm().getNome() + "\" foi alterado com sucesso!", Toast.LENGTH_SHORT).show();
					;
				}
				
				dao.close();
				//clearFields();
				formActivity.finish();
			}
		});
		
		//SeekBar mostra o valor da nota no textview
		seekBarGrade.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tvNota.setText(formActivity.getResources().getString(R.string.form_label_grade) + " " + seekBarGrade.getProgress());
			}
		});
		
	}
	
	public Aluno getStudentFromForm(){
		aluno.setNome(editTextName.getText().toString());
		aluno.setSite(editTextSite.getText().toString());
		aluno.setEndereco(editTextAddress.getText().toString());
		aluno.setTelefone(editTextPhone.getText().toString());
		aluno.setNota(Double.valueOf(seekBarGrade.getProgress()));
		return aluno;
	}
	
	public void putStudent(Aluno aluno){
		this.aluno = aluno;
		this.editTextName.setText(aluno.getNome());
		this.editTextAddress.setText(aluno.getEndereco());
		this.editTextPhone.setText(aluno.getTelefone());
		this.editTextSite.setText(aluno.getSite());
		this.seekBarGrade.setProgress(aluno.getNota().intValue());
		carregaFoto(aluno.getCaminhoFoto());
	}
	
	public void clearFields(){
		editTextName.setText("");
		editTextPhone.setText("");
		editTextAddress.setText("");
		editTextSite.setText("");
		seekBarGrade.setProgress(0);
		editTextName.requestFocus();
	}
	
	public ImageView getFoto(){
		return alunoImageView;
	}
	
	public void carregaFoto(String caminhoFoto){
		if(null != caminhoFoto){
			Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
			int largura = 250;
			int altura = (bitmap.getHeight() * largura / bitmap.getWidth());
			// Não tem nenhuma proporção
			// Bitmap reduzido = Bitmap.createScaledBitmap(bitmap, 250, 250, true);
			
			// Com proporção
			Bitmap reduzido = Bitmap.createScaledBitmap(bitmap, largura, altura, true);
			 
			alunoImageView.setImageBitmap(reduzido);
			this.aluno.setCaminhoFoto(caminhoFoto);
		}
	}
}
