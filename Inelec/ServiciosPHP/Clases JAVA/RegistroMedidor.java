package com.example.inelec;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class RegistroMedidor extends Activity implements View.OnClickListener {

	EditText codigo, numeroContrato, nombreTitular, direccion;
	Button registrarMedidor;

	static String idUsuario, rolUsuario, nombreUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registro_medidor);
		this.setTitle("REGISTRO DE MEDIDORES");

		codigo = (EditText) findViewById(R.id.txtRMId);
		numeroContrato = (EditText) findViewById(R.id.txtRMContrato);
		nombreTitular = (EditText) findViewById(R.id.txtRMNombreTitular);
		direccion = (EditText) findViewById(R.id.txtRMDireccion);

		registrarMedidor = (Button) findViewById(R.id.btnRM);
		registrarMedidor.setOnClickListener(this);

		menuLateral(rolUsuario);
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
			ArrayAdapter<String> adp = new ArrayAdapter<String>(RegistroMedidor.this,
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
						Intent goRMed = new Intent(getApplicationContext(), RegistroMedidor.class);
						startActivity(goRMed);
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
			ArrayAdapter<String> adp = new ArrayAdapter<String>(RegistroMedidor.this,
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnRM:
			if(codigo.getText().toString().trim().equals("") && numeroContrato.getText().toString().trim().equals("") 
					&& nombreTitular.getText().toString().trim().equals("") && direccion.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "EXISTEN CAMPOS VACIOS", Toast.LENGTH_LONG).show();
			}else{
			registrarMedidor("http://192.168.8.108/services/insertarMedidor.php");
			Toast.makeText(getApplicationContext(), "SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
			codigo.setText("");
			numeroContrato.setText("");
			nombreTitular.setText("");
			direccion.setText("");
			}
			break;
		}
	}

	public void registrarMedidor(String URL) {

		StringRequest stringRequest = new StringRequest(Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
			}
			
		}) {
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> parametros = new HashMap<String, String>();
				parametros.put("id", codigo.getText().toString());
				parametros.put("numeroContrato", numeroContrato.getText().toString());
				parametros.put("nombreTitular", nombreTitular.getText().toString());
				parametros.put("direccion", direccion.getText().toString());
				parametros.put("coordenada", "");
				parametros.put("idEstado", "1");

				return parametros;

			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);

	}
}
