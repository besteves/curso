package com.example.cadastrocaelum.listener;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.cadastrocaelum.fragment.MapaFragment;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

public class MapLocationListener implements GooglePlayServicesClient.ConnectionCallbacks, LocationListener{
	private LocationClient client;
	private MapaFragment fragment;
	
	public MapLocationListener(Context context, MapaFragment mapaFragment) {
		this.fragment = mapaFragment;
		client = new LocationClient(context, this, null);
		client.connect();
	}
	
	@Override
	public void onConnected(Bundle connectionHint) {
		LocationRequest request = LocationRequest.create();
		request.setInterval(2000);
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setSmallestDisplacement(50);
		this.client.requestLocationUpdates(request,this);
	}

	@Override
	public void onDisconnected() {		
	}

	@Override
	public void onLocationChanged(Location location) {	
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		LatLng local = new LatLng(latitude, longitude);
		Log.i("Local", "Local -" + local);
		this.fragment.centralizaNo(local);
	}
	
	public void cancela(){
		this.client.removeLocationUpdates(this);
		this.client.disconnect();
	}
}
