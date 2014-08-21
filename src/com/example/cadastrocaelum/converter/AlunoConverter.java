package com.example.cadastrocaelum.converter;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import com.example.cadastrocaelum.model.Aluno;

public class AlunoConverter {
	public String toJSON(List<Aluno> alunos){
		try{
			JSONStringer jsonStringer = new JSONStringer();
			jsonStringer.object().key("list").array().object().key("aluno").array();
			for(Aluno aluno : alunos){
				jsonStringer.object()
					.key("id").value(aluno.getId())
					.key("nome").value(aluno.getNome())
					.key("telefone").value(aluno.getTelefone())
					.key("endereco").value(aluno.getEndereco())
					.key("site").value(aluno.getSite())
					.key("nota").value(aluno.getNota())
					.endObject();
			}
			return jsonStringer.endArray().endObject()
					.endArray().endObject().toString();
		}catch (JSONException exception){
			throw new RuntimeException(exception);
		}
	}
}
