package com.example.inelec;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.toolbox.JsonArrayRequest;
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

public class ConsultaMedidor extends Activity implements View.OnClickListener {
	
	EditText codigo, numeroContrato, nombreTitular, direccion;
	Button btnBuscar, btnActualizar, btnEliminar, btnListarMedidor;
	
	static String idUsuario, rolUsuario, nombreUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_medidor);
		this.setTitle("CONSULTA DE MEDIDORES");
		
		menuLateral(rolUsuario);

		codigo = (EditText) findViewById(R.id.txtCMId);
		numeroContrato = (EditText) findViewById(R.id.txtCMnumero);
		nombreTitular = (EditText) findViewById(R.id.txtCMNombre);
		direccion = (EditText) findViewById(R.id.txtCMDir);

		btnBuscar = (Button) findViewById(R.id.btnCM);
		btnActualizar = (Button) findViewById(R.id.btnCMActualizar);
		btnEliminar = (Button) findViewById(R.id.btnCMEliminar);
		btnListarMedidor = (Button) findViewById(R.id.btnListarMedidor);
		
		btnBuscar.setOnClickListener(this);
		btnActualizar.setOnClickListener(this);
		btnEliminar.setOnClickListener(this);
		btnListarMedidor.setOnClickListener(this);
		
	}
	
	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral;

	public void menuLateral(String roles) {

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		linearContent = (LinearLayout) findViewById(R.id.linearcontent);
		menuLateral = (ListView) findViewById(R.id.menuLateral);

		if (roles.equals("Administrador")) {
			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Inicio", "Consultar Incidencia", "Registrar Usuario", "Consultar Usuario",
					"Registrar Medidor", "Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ConsultaMedidor.this, android.R.layout.simple_list_item_1,
					opciones);
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
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ConsultaMedidor.this,
					android.R.layout.simple_list_item_1, opciones);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCM:
			if(codigo.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "INGRESE CODIGO", Toast.LENGTH_SHORT).show();
			}else{
			consultarMedidor("http://192.168.8.108/services/buscarMedidor.php?id=" + codigo.getText());
			}
			break;

		case R.id.btnCMActualizar:
			if(codigo.getText().toString().trim().equals("") && numeroContrato.getText().toString().trim().equals("") 
					&& nombreTitular.getText().toString().trim().equals("") && direccion.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "EXISTEN CAMPOS VACIOS", Toast.LENGTH_LONG).show();
			}else{
				actualizarMedidor("http://192.168.8.108/services/actualizarMedidor.php?id=" + codigo.getText());
			}

			
			break;

		case R.id.btnCMEliminar:
			
			if(codigo.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "INGRESE CODIGO", Toast.LENGTH_SHORT).show();
			}else{
				eliminarMedidor("http://192.168.8.108/services/eliminarMedidor.php?id="+ codigo.getText());
				Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO", Toast.LENGTH_SHORT).show();
			}
				
			break;

		case R.id.btnListarMedidor:
			ListarMedidor lm = new ListarMedidor();
			lm.idUsuario = idUsuario;
			lm.rolUsuario = rolUsuario;
			lm.nombreUsuario = nombreUsuario;
			Intent goLInc = new Intent(this, ListarMedidor.class);
			startActivity(goLInc);
			onBackPressed();

			break;
			
		}

	}
	
	public void consultarMedidor(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;
				
				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						numeroContrato.setText(jsonObject.getString("numeroContrato"));
						nombreTitular.setText(jsonObject.getString("nombreTitular"));
						direccion.setText(jsonObject.getString("direccion"));
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
		
		if(numeroContrato.getText().toString().trim().equals("") && nombreTitular.getText().toString().trim().equals("") &&
				direccion.getText().toString().trim().equals("")){
			Toast.makeText(getApplicationContext(), "NO EXISTE REGISTRO", Toast.LENGTH_LONG).show();
		}
	}

	public void actualizarMedidor(String URL) {

		StringRequest stringRequest = new StringRequest(Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
				limpiar();
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
				parametros.put("numeroContrato", numeroContrato.getText().toString());
				parametros.put("nombreTitular", nombreTitular.getText().toString());
				parametros.put("direccion", direccion.getText().toString());
				parametros.put("coordenada", "");

				return parametros;

			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
		
		
	}

	public void eliminarMedidor(String URL) {

		StringRequest stringRequest = new StringRequest(Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show();
				limpiar();

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

				return parametros;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
		
	

	}
	
	public void limpiar(){
		
		codigo.setText("");
		numeroContrato.setText("");
		direccion.setText("");
		nombreTitular.setText("");
		
	}

}
