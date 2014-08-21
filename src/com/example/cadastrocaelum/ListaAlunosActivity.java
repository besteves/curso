package com.example.cadastrocaelum;

import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.cadastrocaelum.adapter.ListaAlunosAdapter;
import com.example.cadastrocaelum.dao.AlunoDAO;
import com.example.cadastrocaelum.listener.ListaAlunosDeleteClickListener;
import com.example.cadastrocaelum.listener.ListaAlunosItemClickListener;
import com.example.cadastrocaelum.model.Aluno;
import com.example.cadastrocaelum.receiver.BateriaReceiver;
import com.example.cadastrocaelum.task.SendStudentClass;


public class ListaAlunosActivity extends ActionBarActivity {
	
	private ListView listaAlunos;
	private List<Aluno> alunos;
    private Aluno alunoSelecionado;
	private AlunoDAO alunoDAO;
	private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alunoDAO = new AlunoDAO(this);
        setContentView(R.layout.activity_lista_alunos);
        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        registerReceiver(new BateriaReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        //registerForContextMenu(listaAlunos);
    }

	public void loadList() {
        alunos = alunoDAO.getStudents();
        alunoDAO.close();
        
    	adapter = new ListaAlunosAdapter(getApplicationContext(),R.layout.item, alunos);
        listaAlunos.setAdapter(adapter);
        if(null == listaAlunos.getOnItemClickListener()){
        	listaAlunos.setOnItemClickListener(new ListaAlunosItemClickListener(this));
        }
        if(null == listaAlunos.getOnItemLongClickListener()){
            listaAlunos.setOnItemLongClickListener(new ListaAlunosItemClickListener(this));
        }
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getMenuInflater().inflate(R.menu.lista_alunos, menu);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.ligar:
			break;
		case R.id.sms:
			break;
		case R.id.navegar:
			break;
		case R.id.deletar:
			if(null != alunoSelecionado)
				showDialog();
			break;
		case R.id.email:
			break;

		default:
			break;
		}
    	return super.onContextItemSelected(item);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	loadList();
    }
    
    public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}
	
	public void showDialog(){
		new AlertDialog.Builder(this)
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setTitle(getResources().getString(R.string.menu_contexto_deletar_deletar))
			.setMessage(getResources().getString(R.string.alert_deletar_mensagem)+ " \"" +
			alunoSelecionado.getNome() + "\" " + getResources().getString(R.string.alert_deletar_mensagem_complemento))
			.setPositiveButton(getResources().getString(R.string.alert_deletar_positive_button), new ListaAlunosDeleteClickListener(ListaAlunosActivity.this, true))
			.setNegativeButton(getResources().getString(R.string.alert_deletar_negative_button), new ListaAlunosDeleteClickListener(ListaAlunosActivity.this, false))
			.show();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_novo:
			startActivity(new Intent(ListaAlunosActivity.this,FormularioActivity.class));
			break;
		case R.id.menu_mapa:
			Intent intent = new Intent(this, MostraAlunosProximosActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_opcoes:
			break;
		case R.id.menu_enviar_provas:
			AlunoDAO dao = new AlunoDAO(this);
			List<Aluno> alunos = dao.getStudents();
			dao.close();
			SendStudentClass sendStudentClassTask = new SendStudentClass(this, alunos);
			sendStudentClassTask.execute();
			break;
		case R.id.menu_receber_provas:
			Intent intentProvas = new Intent(this, ProvasActivity.class);
			startActivity(intentProvas);
			break;
		case R.id.menu_preferencias:
			break;
		case android.R.id.home:
			super.onBackPressed();
			break;
			
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
