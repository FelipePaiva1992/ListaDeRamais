package br.com.mec.listaramais;


import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.mec.WSRest.ConexaoWSRest;
import br.com.mec.model.Empresa;

public class MainActivity extends Activity 
{
	
    private ProgressDialog dialog;
    private Handler handler = new Handler();
    private static final String CATEGORIA = "appTeste";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaempresas);
		efetuarPagamento();
		//listarEmpresas();
	}

	private void efetuarPagamento() {
		dialog = ProgressDialog.show(this, "", "Chamando o web service, por favor aguarde...", false, true);
    	new Thread()
    	{
    		public void run() 
    		{
    			try
    			{
					//CHAMAR WS E POPULAR LISTA DE EMPRESAS
					ConexaoWSRest ws = new ConexaoWSRest();
					final List<Empresa> listaEmpresas = ws.getListaEmpresas("listEmpresas", null);
					
					handler.post(new Runnable() 
					{
 						@Override
						public void run() 
 						{
							LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutMain);
							layout.removeAllViews();
							
							ListView listView = new ListView(MainActivity.this);
							ArrayAdapter<Empresa> arrAdapter = new ArrayAdapter<Empresa>(MainActivity.this, android.R.layout.simple_list_item_1, listaEmpresas);
							listView.setAdapter(arrAdapter);
							
							listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
							{
						        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
						        {
						        	Intent it = new Intent(getBaseContext(),FuncionariosActivity.class);
						        	String idEmpresa = listaEmpresas.get(position).getIdEmpresas();
						        	it.putExtra("idEmpresa", idEmpresa);
						        	startActivity(it);
						        }
						    });
							
							layout.addView(listView);
 							
							setContentView(layout);
						}
					});
    					
    				}catch(Exception e){
    					
    					Log.e(CATEGORIA, e.getMessage());
    					
    				}finally{    					
    					dialog.dismiss();
    				}
    		}
    	}.start();    						
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


    
    public void listarEmpresas()
    {
    	//dialog = ProgressDialog.show(this, "Ramais", "Chamando o web service, por favor aguarde...", false, true);
    	new Thread()
    	{
    		public void run() 
    		{
    			try
    			{
					//CHAMAR WS E POPULAR LISTA DE EMPRESAS
					ConexaoWSRest ws = new ConexaoWSRest();
					final List<Empresa> listaEmpresas = ws.getListaEmpresas("listEmpresas", null);
					
					handler.post(new Runnable() 
					{
 						@Override
						public void run() 
 						{
							LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutMain);
							layout.removeAllViews();
							
							ListView listView = new ListView(MainActivity.this);
							ArrayAdapter<Empresa> arrAdapter = new ArrayAdapter<Empresa>(MainActivity.this, android.R.layout.simple_list_item_1, listaEmpresas);
							listView.setAdapter(arrAdapter);
							
							listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
							{
						        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
						        {
						        	Intent it = new Intent(getBaseContext(),FuncionariosActivity.class);
						        	String idEmpresa = listaEmpresas.get(position).getIdEmpresas();
						        	it.putExtra("idEmpresa", idEmpresa);
						        	startActivity(it);
						        }
						    });
							
							layout.addView(listView);
							setContentView(layout);
						}
					});
    					
    				}catch(Exception e){
    					
    					Log.e(CATEGORIA, e.getMessage());
    					
    				}finally{    					
    					//dialog.dismiss();
    				}
    		}
    	}.start();    				
    }
}
