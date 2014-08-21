package com.example.cadastrocaelum.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.cadastrocaelum.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper{

	private static final int VERSAO = 2;
	private static final String TABELA = "Alunos";
	private static final String DATABASE = "CadastroCaelum";
	private String sql;
	
	public AlunoDAO(Context context){
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		sql = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY," +
					" nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, " +
					" site TEXT, nota REAL, caminhoFoto TEXT);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public void insert(Aluno aluno){
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("caminhoFoto", aluno.getCaminhoFoto());
		values.put("nota", aluno.getNota());
		
		getWritableDatabase().insert(TABELA, null, values);
	}
	
	public List<Aluno> getStudents(){
		List<Aluno> studentsList = new ArrayList<Aluno>();
		sql = "SELECT * FROM " + TABELA + ";" + null;
		Cursor c = getReadableDatabase().rawQuery(sql, null);
		
		while(c.moveToNext()){
			Aluno aluno = new Aluno();
			populateStudent(aluno, c);
			studentsList.add(aluno);
		}
		
		c.close();
		
		return studentsList;
	}
	
	public void deletar(Aluno aluno){
		String[] args = {aluno.getId().toString()};
		getWritableDatabase().delete(TABELA, "id=?", args);
	}
	
	public Aluno getAlunoPorID(Long id){
		Aluno aluno = new Aluno();
		String sql = "SELECT * FROM " + TABELA + "WHERE id=?;";

		String[] args = {id.toString()};
		Cursor c = getWritableDatabase().rawQuery(sql, args);
		c.moveToFirst();
		
		populateStudent(aluno, c);
		c.close();
		
		return aluno;
	}

	private void populateStudent(Aluno aluno, Cursor c) {
		aluno.setId(c.getLong(c.getColumnIndex("id")));
		aluno.setNome(c.getString(c.getColumnIndex("nome")));
		aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
		aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
		aluno.setSite(c.getString(c.getColumnIndex("site")));
		aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
		aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));
	}
	

	public void alterar(Aluno aluno){
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("telefone", aluno.getTelefone());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("caminhoFoto", aluno.getCaminhoFoto());
		values.put("nota", aluno.getNota());
		
		getWritableDatabase().update(TABELA, values, "id=?", new String[]{aluno.getId().toString()});
	}
	
	public boolean isAluno(String telefone){
		String[] parametros = {telefone};
		Cursor rawQuery = getReadableDatabase()
				.rawQuery("SELECT telefone from " + TABELA
				+ " WHERE telefone = ?", parametros);
		
		int total = rawQuery.getCount();
		return total > 0;
	}
}
