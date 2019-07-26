package com.example.inelec;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ListarMedidor extends Activity {

	static String idUsuario, rolUsuario, nombreUsuario;
	ListView listaMedidores;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_medidor);
		this.setTitle("LISTA DE MEDIDORES");
		
		menuLateral(rolUsuario);
		listarMedidor("http://192.168.8.108/services/listarMedidor.php");
		
	}

	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral;

	public void menuLateral(String roles) {

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		linearContent = (LinearLayout) findViewById(R.id.linearcontent);
		menuLateral = (ListView) findViewById(R.id.menuLateral);

		if (roles.equals("Administrador")) {
			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Inicio", "Consultar Incidencia", "Registrar Usuario",
					"Consultar Usuario", "Registrar Medidor", "Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ListarMedidor.this,
					android.R.layout.simple_list_item_1, opciones);
			menuLateral.setAdapter(adp);

			menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					// TODO Auto-generated method stub
					String opcSelect = (String) menuLateral.getAdapter().getItem(position);

					switch (position) {
					case 1:
						WelcomeAdm wa = new WelcomeAdm();
						wa.idUsuario = idUsuario;
						wa.rolUsuario = rolUsuario;
						wa.nombreUsuario = nombreUsuario;
						Intent goMPrincipal = new Intent(getApplicationContext(), WelcomeAdm.class);
						startActivity(goMPrincipal);
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
						RegistroUsuario ru = new RegistroUsuario();
						ru.idUsuario = idUsuario;
						ru.rolUsuario = rolUsuario;
						ru.nombreUsuario = nombreUsuario;
						Intent goRUser = new Intent(getApplicationContext(), RegistroUsuario.class);
						startActivity(goRUser);
						onBackPressed();
						break;
					case 4:
						ConsultaUsuario cu = new ConsultaUsuario();
						cu.idUsuario = idUsuario;
						cu.rolUsuario = rolUsuario;
						cu.nombreUsuario = nombreUsuario;
						Intent goCUser = new Intent(getApplicationContext(), ConsultaUsuario.class);
						startActivity(goCUser);
						onBackPressed();
						break;
					case 5:
						RegistroMedidor rm = new RegistroMedidor();
						rm.idUsuario = idUsuario;
						rm.rolUsuario = rolUsuario;
						rm.nombreUsuario = nombreUsuario;
						Intent backRMed = new Intent(getApplicationContext(), RegistroMedidor.class);
						startActivity(backRMed);
						onBackPressed();
						break;
					case 6:
						ConsultaMedidor cm = new ConsultaMedidor();
						cm.idUsuario = idUsuario;
						cm.rolUsuario = rolUsuario;
						cm.nombreUsuario = nombreUsuario;
						Intent goCMed = new Intent(getApplicationContext(), ConsultaMedidor.class);
						startActivity(goCMed);
						onBackPressed();
						break;
					case 7:
						break;
					case 8:
						Intent goLog = new Intent(getApplicationContext(), Logeo.class);
						startActivity(goLog);
						onBackPressed();
						break;

					}
				}
			});
		} else if (roles.equals("Operario")) {

			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Incidencias", "Consultar Incidencia", "Registrar Medidor",
					"Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ListarMedidor.this,
					android.R.layout.simple_list_item_1, opciones);
			menuLateral.setAdapter(adp);
			menuLateral.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					// TODO Auto-generated method stub
					String opcSelect = (String) menuLateral.getAdapter().getItem(position);

					switch (position) {
					case 1:
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
						onBackPressed();
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

	public void listarMedidor(String URL) {

		listaMedidores = (ListView) findViewById(R.id.listaMedidor);
		final ArrayList<String> lista = new ArrayList<String>();
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);

						lista.add("Codigo: " + jsonObject.getString("id") + "\n" 
								+ "Numero Contrato: "+ jsonObject.getString("numeroContrato") + "\n" 
								+ "Nombre Titular: " + jsonObject.getString("nombreTitular"));

						ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListarMedidor.this,
								android.R.layout.simple_list_item_1, lista);
						listaMedidores.setAdapter(adapter);
					} catch (JSONException e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}

			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "ERROR DE CONEXION", Toast.LENGTH_LONG).show();
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
		requestQueue.add(jsonArrayRequest);

	}
	
}
