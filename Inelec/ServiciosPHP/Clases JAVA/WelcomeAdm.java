package com.example.inelec;

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
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeAdm extends Activity {

	static String idUsuario, rolUsuario, nombreUsuario;
	TextView mensaje;
	private DrawerLayout drawerLayout;
	private LinearLayout linearContent;
	private ListView menuLateral;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_adm);
		this.setTitle("INCIDENCIAS ELECTRICAS");
		
		menuLateral(rolUsuario);
		
		mensaje = (TextView) findViewById(R.id.textView1);
		
		mensaje.setText("Bienvenido " + nombreUsuario + " al Aplicativo de Incidencias Electricas "
				+ "en el cual podra realizar la gestion de Usuarios y seguimiento de Incidencias. En la parte lateral izquiera se encuentra el menu deslizable,"
				+ " en el cual Ud. podra manipular las diversas opciones que le ofrece el Aplicativo.");
		
	}

	public void menuLateral(String roles) {

		drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
		linearContent = (LinearLayout) findViewById(R.id.linearcontent);
		menuLateral = (ListView) findViewById(R.id.menuLateral);

		if (roles.equals("Administrador")) {
			String[] opciones = { nombreUsuario.toUpperCase() + "\n" + rolUsuario, "Inicio", "Consultar Incidencia", "Registrar Usuario", "Consultar Usuario",
					"Registrar Medidor", "Consultar Medidores", "Cerrar Sesion" };
			ArrayAdapter<String> adp = new ArrayAdapter<String>(WelcomeAdm.this, android.R.layout.simple_list_item_1,
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
		}
	}
}
