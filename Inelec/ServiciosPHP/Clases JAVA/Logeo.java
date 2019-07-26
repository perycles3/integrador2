package com.example.inelec;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
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
import android.app.DownloadManager.Request;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logeo extends Activity implements View.OnClickListener {

	EditText usuario, clave;
	Button ingresar;

	static String idUsuario, rolUsuario, nombreUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logeo);
		this.setTitle("INCIDENCIAS ELECTRICAS");

		usuario = (EditText) findViewById(R.id.txtUsuario);
		clave = (EditText) findViewById(R.id.txtClave);

		ingresar = (Button) findViewById(R.id.btnIngresar);

		ingresar.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		validarAcceso("http://192.168.8.108/services/validarAcceso.php?user=" + usuario.getText().toString().toUpperCase() + "&pass="
				+ clave.getText());
	}

	private void validarAcceso(String URL) {
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray response) {
				// TODO Auto-generated method stub

				JSONObject jsonObject = null;

				if (response.length() > 0) {
					for (int i = 0; i < response.length(); i++) {
						try {
							jsonObject = response.getJSONObject(i);
							idUsuario = (jsonObject.getString("id"));
							nombreUsuario = (jsonObject.getString("nombre"));
							rolUsuario = (jsonObject.getString("nombreRol"));
							
						} catch (JSONException e) {
							Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
						}
					}

					if (rolUsuario.equals("Administrador")) {
						Intent i = new Intent(getApplicationContext(), WelcomeAdm.class);
						startActivity(i);
						WelcomeAdm wa = new WelcomeAdm();
						wa.idUsuario = idUsuario;
						wa.rolUsuario = rolUsuario;
						wa.nombreUsuario = nombreUsuario;
						onBackPressed();
					} else if (rolUsuario.equals("Operario")) {
						Intent i = new Intent(getApplicationContext(), Incidencias.class);
						Incidencias inc = new Incidencias();
						inc.idUsuario = idUsuario;
						inc.rolUsuario = rolUsuario;
						inc.nombreUsuario = nombreUsuario;
						startActivity(i);
						onBackPressed();
					}
				} else {
					usuario.setText("");
					clave.setText("");
					Toast.makeText(getApplicationContext(), "ACCESO DENEGADO", Toast.LENGTH_LONG).show();
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
