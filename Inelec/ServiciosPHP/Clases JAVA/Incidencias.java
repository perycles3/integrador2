package com.example.inelec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Incidencias extends Activity {

	
	
	static String idUsuario, rolUsuario, nombreUsuario;

	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral, listaIncidencias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_incidencias);
		this.setTitle("LISTADO DE INCIDENCIAS");

		menuLateral(rolUsuario);
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable(){
			public void run(){
				cargarIncidencias("http://192.168.8.108/services/listarIncidencias.php");
				
				handler.postDelayed(this,1000);
			}
		},1000);
	}

	public void menuLateral(String rol) {

		if (rol.equals("Operario")) {
			drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
			linearContent = (LinearLayout) findViewById(R.id.linearcontent);
			menuLateral = (ListView) findViewById(R.id.menuLateral);

			String[] opciones = {  nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Incidencias", "Consultar Incidencia", "Registrar Medidor",
					"Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(Incidencias.this, android.R.layout.simple_list_item_1,
					opciones);
			menuLateral.setAdapter(adp);
			menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					// TODO Auto-generated method stub
					String opcSelect = (String) menuLateral.getAdapter().getItem(position);

					switch (position) {
					case 1:
						Incidencias inc = new Incidencias();
						inc.idUsuario = idUsuario;
						inc.rolUsuario = rolUsuario;
						inc.nombreUsuario = nombreUsuario;
						Intent backInc = new Intent(getApplicationContext(), Incidencias.class);
						startActivity(backInc);
						onBackPressed();
						break;
					case 2:
						ConsultaIncidencia ci = new ConsultaIncidencia();
						ci.idUsuario = idUsuario;
						ci.rolUsuario = rolUsuario;
						ci.nombreUsuario = nombreUsuario;
						Intent goCInc = new Intent(getApplicationContext(), ConsultaIncidencia.class);
						startActivity(goCInc);
						onBackPressed();
						break;
					case 3:
						RegistroMedidor rm = new RegistroMedidor();
						rm.idUsuario = idUsuario;
						rm.rolUsuario = rolUsuario;
						rm.nombreUsuario = nombreUsuario;
						Intent backRMed = new Intent(getApplicationContext(), RegistroMedidor.class);
						startActivity(backRMed);
						onBackPressed();
						break;
					case 4:
						ConsultaMedidor cm = new ConsultaMedidor();
						cm.idUsuario = idUsuario;
						cm.rolUsuario = rolUsuario;
						cm.nombreUsuario = nombreUsuario;
						Intent goCMed = new Intent(getApplicationContext(), ConsultaMedidor.class);
						startActivity(goCMed);
						onBackPressed();
						break;
					case 5:
						break;
					case 6:
						Intent backLog = new Intent(getApplicationContext(), Logeo.class);
						startActivity(backLog);
						onBackPressed();
						break;
					}
				}
			});
		}
	}

	public void cargarIncidencias(String URL) {

		listaIncidencias = (ListView) findViewById(R.id.listaIncidencias);
		final ArrayList<String> lista = new ArrayList<String>();
		final ArrayList<String> codigo = new ArrayList<String>();
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);

						lista.add("Tipo de Incidencia: " + jsonObject.getString("nombreTipo") + "\n" + "Ubicacion: "
								+ jsonObject.getString("direccion"));

						ArrayAdapter<String> incidencia = new ArrayAdapter<String>(Incidencias.this,
								android.R.layout.simple_list_item_1, lista);
						listaIncidencias.setAdapter(incidencia);
						
								codigo.add(jsonObject.getString("id"));

						final ArrayAdapter<String> listacodigo = new ArrayAdapter<String>(Incidencias.this,
								android.R.layout.simple_list_item_1, codigo);
						
						listaIncidencias.setOnItemClickListener(new AdapterView.OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
								// TODO Auto-generated method stub
								String id = listacodigo.getItem(arg2);
								
								DetalleIncidencia di = new DetalleIncidencia();
								di.idUsuario = idUsuario;
								di.rolUsuario = rolUsuario;
								di.nombreUsuario = nombreUsuario;
								di.codigoIncidencia = id;
								Intent dInc = new Intent(getApplicationContext(), DetalleIncidencia.class);
								startActivity(dInc);
								onBackPressed();

							}
						});
					
					} catch (JSONException e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		requestQueue.add(jsonArrayRequest);
		
		

	}
}
