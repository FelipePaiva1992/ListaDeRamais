package br.com.mec.listaramais;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import br.com.mec.WSRest.ConexaoWSRest;
import br.com.mec.model.Funcionario;

public class FuncionariosActivity extends Activity {

    //private ProgressDialog dialog;
    private Handler handler = new Handler();
    private static final String CATEGORIA = "appTeste";	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.funcionarioempresa);
		savedInstanceState = getIntent().getExtras(); 
		
		listarFuncionarioByEmpresa(savedInstanceState.get("idEmpresa").toString());
	}

    public void listarFuncionarioByEmpresa(final String id)
    {
    	
    	//dialog = ProgressDialog.show(this, "Ramais", "Chamando o web service, por favor aguarde...", false, true);
    	new Thread()
    	{
    		public void run()
    		{
    				try
    				{
    					//CHAMAR WS E POPULAR LISTA DE FUNCIONARIOS
    					ConexaoWSRest ws = new ConexaoWSRest();
    					
    					List<ArrayList<String>> parameters = new ArrayList<ArrayList<String>>();
    					List<String> nameValue = new ArrayList<String>();
    					nameValue.add("idEmpresa");
    					nameValue.add(id);
    					parameters.add((ArrayList<String>) nameValue);
    					
    					final List<Funcionario> listaFuncionarios = ws.getListaFuncionariosByEmpresa("listFuncionarioByEmpresa", parameters);
   					
    					handler.post(new Runnable() 
    					{
    						@Override
    						public void run() 
    						{
    							LinearLayout layout = (LinearLayout) findViewById(R.id.Layoutfuncionarios);
    							layout.removeAllViews();

								ListView listView = new ListView(FuncionariosActivity.this);
    							ArrayAdapter<Funcionario> ad = new ArrayAdapter<Funcionario>(FuncionariosActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, listaFuncionarios);
    							listView.setAdapter(ad); 
    							
    							listView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
    							{
    						        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    						        {
    						        	Intent it = new Intent(getBaseContext(),FuncionariosDetailActivity.class);
    						        	String idFuncionario = listaFuncionarios.get(position).getIdFuncionario();
    						        	it.putExtra("idFuncionario", idFuncionario);
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
