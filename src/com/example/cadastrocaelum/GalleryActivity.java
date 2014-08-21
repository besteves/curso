package com.example.cadastrocaelum;

import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.cadastrocaelum.adapter.GaleriaAlunosAdapter;
import com.example.cadastrocaelum.dao.AlunoDAO;
import com.example.cadastrocaelum.model.Aluno;

public class GalleryActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getStudents();
		ViewPager viewPager = (ViewPager) findViewById(R.id.gallery);
		viewPager.setAdapter(new GaleriaAlunosAdapter(alunos, this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.gallery, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
