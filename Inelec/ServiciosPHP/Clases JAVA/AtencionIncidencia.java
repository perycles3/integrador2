package com.example.inelec;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class AtencionIncidencia extends Activity {

	static String codigoIncidencia, idUsuario, rolUsuario, nombreUsuario;
	TextView txtCodigo, txtTipo, txtUbicacion, txtNumeroC, txtNombreT, txtOperario, txtFechaI, txtFechaA;
	MultiAutoCompleteTextView txtObservaciones;
	Button btnGuardar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atencion_incidencia);
		this.setTitle("ATENCION DE INCIDENCIA");

		txtCodigo = (TextView) findViewById(R.id.codigo);
		txtTipo = (TextView) findViewById(R.id.txtTipo);
		txtNumeroC = (TextView) findViewById(R.id.txtContrato);
		txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
		txtNombreT = (TextView) findViewById(R.id.txtNombreT);
		txtFechaI = (TextView) findViewById(R.id.txtFecha);
		txtFechaA = (TextView) findViewById(R.id.txtFechaA);
		txtOperario = (TextView) findViewById(R.id.txtOperario);
		txtObservaciones = (MultiAutoCompleteTextView) findViewById(R.id.txtObsc);

		btnGuardar = (Button) findViewById(R.id.btnguardar);

		txtCodigo.setText("INCIDENCIA: " + codigoIncidencia);

		btnGuardar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				registrarAtencion("http://192.168.8.108/services/registrarAtencion.php?id=" + codigoIncidencia );

				Intent goInc = new Intent(getApplicationContext(), Incidencias.class);
				startActivity(goInc);
				onBackPressed();
			}
		});

		llenarCampos("http://192.168.8.108/services/detalleIncidencia.php?id=" + codigoIncidencia);

	}

	public void llenarCampos(String URL) {

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
						txtFechaI.setText(jsonObject.getString("fechaIncidencia"));
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

		txtOperario.setText(nombreUsuario);

		Date objDate = new Date();
		String strDateFormat = "HH: mm: ss a dd/MM/yyyy";
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);

		txtFechaA.setText(objSDF.format(objDate));
	}

	public void registrarAtencion(String URL) {
		StringRequest stringRequest = new StringRequest(Method.POST, URL, new Response.Listener<String>() {
			@Override
			public void onResponse(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "OPERARION EXITOSA", Toast.LENGTH_LONG).show();
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
				parametros.put("fechaAtencion", txtFechaA.getText().toString());
				parametros.put("idUsuario", idUsuario);
				parametros.put("descripcion", txtObservaciones.getText().toString());
				parametros.put("idEstado", "2");

				return parametros;

			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);


	}

}
