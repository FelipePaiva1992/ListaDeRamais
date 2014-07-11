package br.com.mec.listaramais;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import br.com.mec.WSRest.ConexaoWSRest;
import br.com.mec.model.Funcionario;

public class FuncionariosDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.funcionario_detail);
		savedInstanceState = getIntent().getExtras();  
		listarFuncionarioById(savedInstanceState.get("idFuncionario").toString());
		listarSuperiorById(savedInstanceState.get("idFuncionario").toString());
	}

	
    //private ProgressDialog dialog;
    private static final String CATEGORIA = "appTeste";
    
    
    public void listarFuncionarioById(final String id){
    	
    	//dialog = ProgressDialog.show(this, "Ramais", "Chamando o web service, por favor aguarde...", false, true);
    	
    	new Thread(){
    		
    		public void run() {

    				try{
    					ConexaoWSRest ws = new ConexaoWSRest();
    					
    					ArrayList<ArrayList<String>> parameters = new ArrayList<ArrayList<String>>();
    					ArrayList<String> nameValue = new ArrayList<String>();
    					nameValue.add("idFuncionario");
    					nameValue.add(id);
    					parameters.add(nameValue);
    					
    					//chamar WS
    					final Funcionario funcionarios = ws.getFuncionariosById("listFuncionarioById", parameters);
    					
						TextView txtNome = (TextView) findViewById(R.id.txtNome);
						txtNome.setText(funcionarios.getNmFuncionario().toString());
						
						EditText txtRamais = (EditText) findViewById(R.id.txtRamal);
						txtRamais.setText(funcionarios.getNuRamal().toString());
						
						EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
						txtEmail.setText(funcionarios.getAdEmail().toString());

    					
    				}catch(Exception e){
    					Log.e(CATEGORIA, e.getStackTrace().toString());
    				}finally{
    					//dialog.dismiss();
    				}
    		}
    	}.start();    				

    }
    
    
    public void listarSuperiorById(final String id){
    	
    	//dialog = ProgressDialog.show(this, "Ramais", "Chamando o web service, por favor aguarde...", false, true);
    	
    	new Thread(){
    		
    		public void run() {

    				try{
    					ConexaoWSRest ws = new ConexaoWSRest();
    					
    					ArrayList<ArrayList<String>> parameters = new ArrayList<ArrayList<String>>();
    					ArrayList<String> nameValue = new ArrayList<String>();
    					nameValue.add("idSuperior");
    					nameValue.add(id);
    					parameters.add(nameValue);
    					
    					//chamar WS
    					final Funcionario funcionarios = ws.getSuperiorById("listFuncionarioSuperior", parameters);
    					
						TextView txtNome = (TextView) findViewById(R.id.txtNomeSup);
						txtNome.setText(funcionarios.getNmFuncionario().toString()); 
						
						EditText txtRamais = (EditText) findViewById(R.id.txtRamailSup);
						txtRamais.setText(funcionarios.getNuRamal().toString());
						
						EditText txtEmail = (EditText) findViewById(R.id.txtEmailSup);
						txtEmail.setText(funcionarios.getAdEmail().toString());

    					
    				}catch(Exception e){
    					Log.e(CATEGORIA, e.getStackTrace().toString());
    				}finally{
    					//dialog.dismiss();
    				}
    		}
    	}.start();    				

    }
    
}
