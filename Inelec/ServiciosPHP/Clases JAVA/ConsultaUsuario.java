package com.example.inelec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.Spinner;
import android.widget.Toast;

public class ConsultaUsuario extends Activity implements View.OnClickListener {

	EditText Codigo, Dni, Nombre, Telefono, Correo;
	Button btnBuscar, btnActualizar, btnEliminar, btnListar;
	Spinner spn;

	static String idUsuario, rolUsuario, nombreUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_usuario);
		this.setTitle("CONSULTA DE USUARIOS");

		menuLateral(rolUsuario);

		Codigo = (EditText) findViewById(R.id.txtCMId);
		Dni = (EditText) findViewById(R.id.txtCMnumero);
		Nombre = (EditText) findViewById(R.id.txtCMNombre);
		Telefono = (EditText) findViewById(R.id.txtCUTelefono);
		Correo = (EditText) findViewById(R.id.txtCUCorreo);

		btnBuscar = (Button) findViewById(R.id.btnCM);
		btnActualizar = (Button) findViewById(R.id.btnCMActualizar);
		btnEliminar = (Button) findViewById(R.id.btnCMEliminar);
		btnListar = (Button) findViewById(R.id.btnListar);

		spn = (Spinner) findViewById(R.id.spnCURol);

		btnBuscar.setOnClickListener(this);
		btnActualizar.setOnClickListener(this);
		btnEliminar.setOnClickListener(this);
		btnListar.setOnClickListener(this);

		cargarSpiner("http://192.168.8.108/services/cargarSpinner.php");

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
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ConsultaUsuario.this,
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
		}
	}

	public void cargarSpiner(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
			final List<String> labels = new ArrayList<String>();

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						labels.add(jsonObject.getString("nombreRol"));

						ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(ConsultaUsuario.this,
								android.R.layout.simple_spinner_item, labels);

						dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

						spn.setAdapter(dataAdapter);

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.btnCM:
			if(Codigo.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "INGRESE CODIGO", Toast.LENGTH_SHORT).show();
			}else{
			consultarUsuario("http://192.168.8.108/services/buscarUsuario.php?id=" + Codigo.getText());
			}
			break;

		case R.id.btnCMActualizar:
			
			if(Codigo.getText().toString().trim().equals("") && Dni.getText().toString().trim().equals("") 
					&& Nombre.getText().toString().trim().equals("") && Telefono.getText().toString().trim().equals("") &&
					Correo.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "EXISTEN CAMPOS VACIOS", Toast.LENGTH_LONG).show();
			}else{
			actualizarUsuario("http://192.168.8.108/services/actualizarUsuario.php?id=" + Codigo.getText());
			Toast.makeText(getApplicationContext(), "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();

			Codigo.setText("");
			Nombre.setText("");
			Dni.setText("");
			Telefono.setText("");
			Correo.setText("");
			spn.setSelection(0);
			}

			break;

		case R.id.btnCMEliminar:
			if(Codigo.getText().toString().trim().equals("")){
				Toast.makeText(getApplicationContext(), "INGRESE CODIGO", Toast.LENGTH_SHORT).show();
			}else{
			eliminarUsuario("http://192.168.8.108/services/eliminarUsuario.php?id="+ Codigo.getText());
			Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO", Toast.LENGTH_SHORT).show();
			Codigo.setText("");
			Nombre.setText("");
			Dni.setText("");
			Telefono.setText("");
			Correo.setText("");
			spn.setSelection(0);
			}
			break;

		case R.id.btnListar:
			ListarUsuarios lu = new ListarUsuarios();
			lu.idUsuario = idUsuario;
			lu.rolUsuario = rolUsuario;
			lu.nombreUsuario = nombreUsuario;
			Intent goLInc = new Intent(this, ListarUsuarios.class);
			startActivity(goLInc);
			onBackPressed();

			break;
		}
	}

	public void consultarUsuario(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						Nombre.setText(jsonObject.getString("nombre"));
						Dni.setText(jsonObject.getString("dni"));
						Correo.setText(jsonObject.getString("correo"));
						Telefono.setText(jsonObject.getString("telefono"));
						spn.setSelection(Integer.parseInt(jsonObject.getString("idRol"))-1);
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

	public void actualizarUsuario(String URL) {

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
				parametros.put("nombre", Nombre.getText().toString());
				parametros.put("dni", Dni.getText().toString());
				parametros.put("correo", Correo.getText().toString());
				parametros.put("telefono", Telefono.getText().toString());
				parametros.put("idRol", String.valueOf((spn.getSelectedItemPosition() + 1)));

				return parametros;

			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);

	}

	public void eliminarUsuario(String URL) {

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
				parametros.put("id", Codigo.getText().toString());

				return parametros;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);

	}

}
