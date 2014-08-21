package com.example.cadastrocaelum.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.cadastrocaelum.R;
import com.example.cadastrocaelum.model.Prova;

public class ListaProvasAlunosAdapter extends BaseExpandableListAdapter {

	List<Prova> provas;
	Context context;
	LayoutInflater li;
	int resource;
	TextView tv;
	
	public ListaProvasAlunosAdapter(Context context, int resource, List<Prova> provas) {
		this.provas = provas;
		this.context = context;
		this.resource = resource;
		li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return provas.get(groupPosition).getTopicos().get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return provas.get(groupPosition).getTopicos().get(childPosition).hashCode();
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean arg2, View view,
			ViewGroup arg4) {
		TextView tvTopicoView = (TextView)li.inflate(android.R.layout.simple_list_item_1, arg4);
		
		((TextView)tvTopicoView.findViewById(android.R.id.text1)).setText(provas.get(groupPosition).getTopicos()
				.get(childPosition));

		return tvTopicoView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return provas.get(groupPosition).getTopicos().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return provas.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return provas.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return provas.get(groupPosition).hashCode();
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
		if (view == null) {
	    	view = li.inflate(resource, parent, false);
	    } 

		tv = (TextView) view.findViewById(R.id.tvNomeProva);
		if(null != tv)
			tv.setText(provas.get(groupPosition).getMateria());
		
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return false;
	}
}
