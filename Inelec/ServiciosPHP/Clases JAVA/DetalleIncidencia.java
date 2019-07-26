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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleIncidencia extends Activity {

	static String idUsuario, rolUsuario, nombreUsuario, codigoIncidencia;

	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral;

	TextView txtCodigo, txtNumeroC, txtUbicacion, txtNombreT, txtFecha, txtTipo;
	Button btnAtender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_incidencia);
		this.setTitle("DETALLE DE INCIDENCIA");

		menuLateral(rolUsuario);

		txtCodigo = (TextView) findViewById(R.id.codigo);
		txtTipo = (TextView) findViewById(R.id.txtTipo);
		txtNumeroC = (TextView) findViewById(R.id.txtContrato);
		txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
		txtNombreT = (TextView) findViewById(R.id.txtNombreT);
		txtFecha = (TextView) findViewById(R.id.txtFecha);

		btnAtender = (Button) findViewById(R.id.btnAtender);

		txtCodigo.setText("INCIDENCIA: " + codigoIncidencia);
		
		detalleIncidencia("http://192.168.8.108/services/detalleIncidencia.php?id=" + codigoIncidencia );
		
		btnAtender.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				AtencionIncidencia ai = new AtencionIncidencia();
				ai.codigoIncidencia = codigoIncidencia;
				ai.idUsuario = idUsuario;
				ai.rolUsuario = rolUsuario;
				ai.nombreUsuario = nombreUsuario;
				Intent goAI = new Intent(getApplicationContext(), AtencionIncidencia.class);
				startActivity(goAI);
				onBackPressed();
			}
		});
	}

	public void menuLateral(String roles) {

		if (roles.equals("Operario")) {
			drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
			linearContent = (LinearLayout) findViewById(R.id.linearcontent);
			menuLateral = (ListView) findViewById(R.id.menuLateral);

			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Incidencias", "Consultar Incidencia", "Registrar Medidor",
					"Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(DetalleIncidencia.this,
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

	public void detalleIncidencia(String URL) {
		
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				for (int i = 0; i < response.length(); i++) {
					try {
						jsonObject = response.getJSONObject(i);
						txtTipo.setText(jsonObject.getString("nombreTipo"));
						txtUbicacion.setText(jsonObject.getString("direccion"));
						txtNumeroC.setText(jsonObject.getString("numeroContrato"));
						txtNombreT.setText(jsonObject.getString("nombreTitular"));
						txtFecha.setText(jsonObject.getString("fechaIncidencia"));
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
