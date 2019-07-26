package com.example.inelec;

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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaIncidencia extends Activity {

	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral;

	static String idUsuario, rolUsuario, nombreUsuario;
	String estadoIncidencia;
	Button btnConsulta;
	TextView codigo, numeroC, incidencia, operario, fechaI, fechaA, estado, observaciones;
	TextView Ecodigo, EnumeroC, Eincidencia, Eoperario, EfechaI, EfechaA, Eestado, Eobservaciones;
	EditText txtCodigo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta_incidencia);
		this.setTitle("CONSULTA DE INCIDENCIAS");

		menuLateral(rolUsuario);

		txtCodigo = (EditText) findViewById(R.id.txtCodigo);

		codigo = (TextView) findViewById(R.id.txtCod);
		numeroC = (TextView) findViewById(R.id.txtContrato);
		incidencia = (TextView) findViewById(R.id.txtIncidencia);
		operario = (TextView) findViewById(R.id.txtOperario);
		fechaI = (TextView) findViewById(R.id.txtFechaI);
		fechaA = (TextView) findViewById(R.id.txtFechaA);
		estado = (TextView) findViewById(R.id.txtEstado);
		observaciones = (TextView) findViewById(R.id.txtObv);

		Ecodigo = (TextView) findViewById(R.id.vCodigo);
		EnumeroC = (TextView) findViewById(R.id.vContrato);
		Eincidencia = (TextView) findViewById(R.id.vIncidencia);
		Eoperario = (TextView) findViewById(R.id.vOperario);
		EfechaI = (TextView) findViewById(R.id.vFechaI);
		EfechaA = (TextView) findViewById(R.id.vFechaA);
		Eestado = (TextView) findViewById(R.id.vEstado);
		Eobservaciones = (TextView) findViewById(R.id.vObv);

		btnConsulta = (Button) findViewById(R.id.btnConsultar);

		btnConsulta.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(txtCodigo.getText().length() == 0 || txtCodigo.getText().toString().trim().equals("")){
					Toast.makeText(getApplicationContext(), "INGRESE UN CODIGO", Toast.LENGTH_LONG).show();
				}else{
					validarEstado("http://192.168.8.108/services/validarEstado.php?codigo=" + txtCodigo.getText() + "&codigo=" + txtCodigo.getText());
				}
			}
		});
	}

	public void menuLateral(String roles) {

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		linearContent = (LinearLayout) findViewById(R.id.linearcontent);
		menuLateral = (ListView) findViewById(R.id.menuLateral);

		if (roles.equals("Administrador")) {
			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Inicio", "Consultar Incidencia",
					"Registrar Usuario", "Consultar Usuario", "Registrar Medidor", "Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ConsultaIncidencia.this,
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
						Intent goWA = new Intent(getApplicationContext(), WelcomeAdm.class);
						startActivity(goWA);
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

			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Incidencias",
					"Consultar Incidencia", "Registrar Medidor", "Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(ConsultaIncidencia.this,
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

	public void validarEstado(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						estadoIncidencia = jsonObject.getString("nombreEstado");
					} catch (JSONException e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
				}
				if (estadoIncidencia == null || estadoIncidencia.equals(" ")) {
					Toast.makeText(getApplicationContext(), "NO EXISTE REGISTRO", Toast.LENGTH_LONG).show();
				} else {
					compararEstado(estadoIncidencia);
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

	public void compararEstado(String estadoIncidencia) {

		if (estadoIncidencia.equals("activo")) {

			consultarIncidenciaAct("http://192.168.8.108/services/consultarIncidenciaAct.php?codigo="
					+ txtCodigo.getText() + "&codigo=" + txtCodigo.getText());

		} else if (estadoIncidencia.equals("inactivo")) {

			consultaIncidenciaInac("http://192.168.8.108/services/consultarIncidenciaInac.php?codigo="
					+ txtCodigo.getText() + "&codigo=" + txtCodigo.getText());

		}
	}

	public void consultarIncidenciaAct(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				llenarEtiquetas();
				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						codigo.setText((jsonObject.getString("id")));
						incidencia.setText((jsonObject.getString("nombreTipo")));
						numeroC.setText((jsonObject.getString("numeroContrato")));
						fechaI.setText((jsonObject.getString("fechaIncidencia")));
						fechaA.setText((jsonObject.getString("fechaAtencion")));
						observaciones.setText((jsonObject.getString("descripcion")));
						estado.setText((jsonObject.getString("nombreEstado")));
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

	public void consultaIncidenciaInac(String URL) {

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub
				llenarEtiquetas();
				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						codigo.setText((jsonObject.getString("id")));
						incidencia.setText((jsonObject.getString("nombreTipo")));
						numeroC.setText((jsonObject.getString("numeroContrato")));
						fechaI.setText((jsonObject.getString("fechaIncidencia")));
						fechaA.setText((jsonObject.getString("fechaAtencion")));
						observaciones.setText((jsonObject.getString("descripcion")));
						operario.setText((jsonObject.getString("nombre")));
						estado.setText((jsonObject.getString("nombreEstado")));
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

	public void llenarEtiquetas() {

		Ecodigo.setText("CODIGO: ");
		EnumeroC.setText("NUMERO DE CONTRATO: ");
		Eincidencia.setText("INCIDENCIAS: ");
		Eoperario.setText("OPERARIO: ");
		EfechaI.setText("FECHA DE INCIDENCIA: ");
		EfechaA.setText("FECHA DE ATENCION: ");
		Eestado.setText("ESTADO: ");
		Eobservaciones.setText("OBSERVACIONES: ");

	}

	public void limpiarPantalla() {

		Ecodigo.setText("");
		EnumeroC.setText("");
		Eincidencia.setText("");
		Eoperario.setText("");
		EfechaI.setText("");
		EfechaA.setText("");
		Eestado.setText("");
		Eobservaciones.setText("");

		codigo.setText("");
		incidencia.setText("");
		numeroC.setText("");
		fechaI.setText("");
		fechaA.setText("");
		observaciones.setText("");
		operario.setText("");
		estado.setText("");

	}

}
