/*	
	{
	    "empresa": [
	        {
	            "adLogo": "resources/imagem/houston_trust.png",
	            "idEmpresas": "1",
	            "nmEmpresa": "Houston Trust",
	            "nuTelefone": "11 5181 5050"
	        },
	        {
	            "adLogo": "resources/imagem/acqua.png",
	            "idEmpresas": "2",
	            "nmEmpresa": "Acqua Manager RJ",
	            "nuTelefone": "21 2508 4200"
	        },
	        {
	            "adLogo": "resources/imagem/cocm.png",
	            "idEmpresas": "3",
	            "nmEmpresa": "Clinica Open Center",
	            "nuTelefone": "11 3772 5292"
	        },
	        {
	            "adLogo": "resources/imagem/mobilidade.png",
	            "idEmpresas": "4",
	            "nmEmpresa": "Mobilidade em Campo",
	            "nuTelefone": "11 5182 9999"
	        },
	        {
	            "adLogo": "resources/imagem/acqua.png",
	            "idEmpresas": "5",
	            "nmEmpresa": "Acqua Manager SP",
	            "nuTelefone": "11 5152 4040"
	        }
	    ]
	}
 */

package br.com.mec.WSRest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.JsonReader;
import android.util.Log;
import br.com.mec.model.Empresa;
import br.com.mec.model.Funcionario;

@SuppressLint("NewApi")
public class ConexaoWSRest {

    private static final String URL_WS = "http://portalacqua.sytes.net:8100/WSRamaisRest/api/ramais/";
	private static final String CATEGORIA = "appTeste";
    
    @SuppressLint("NewApi")
	public ArrayList<Empresa> getListaEmpresas(String METHOD_NAME,ArrayList<ArrayList<String>> param) throws IOException{
    	
    	String link;
    	ArrayList<Empresa> empresas = new ArrayList<Empresa>();
    	
    	//SE NÃO EXISTIR PARAMETROS
    	if(param == null){
    		link = URL_WS + METHOD_NAME;
    	}else{
		//SE EXISTIR PARAMETROS
    		String METHOD_PARAMETERS = montarURLParam(param);
    		link = URL_WS + METHOD_NAME + METHOD_PARAMETERS;
    	}    	
    	Log.i(CATEGORIA, link);
    	
    	//CRIA UMA URL COM O LINK OBTIDO
    	URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage());
		}
		
		//INICIA UM READER COM A URL
		Reader forecastReader = new InputStreamReader(url.openStream());
		
		//INICIA UM JSONREADER A PARTIR DO READER OBTIDO ANTERIORMENTE
		JsonReader forecastJsonReader = new JsonReader(forecastReader);
		
		
		String name;

		
		/*
		 * PEGA O PRIMERO OBJETO DO JSON
		 */
		forecastJsonReader.beginObject();
		
		//PEGA O PRIMERO NAME DO JSON, NO CASO "empresa"
		name = forecastJsonReader.nextName();  	
		
		//PEGA O ARRAY REFERENTE A "empresa"
		forecastJsonReader.beginArray();
		
		//ENQUANTO EXISTIR ITEM NO ARRAY
		while(forecastJsonReader.hasNext()){
			
			//PEGA O OBJETO NA POSISAO ATUAL
			forecastJsonReader.beginObject();
			
			Empresa empresa= new Empresa();
			//ENQUANTO EXISTIR ITENS NO OBJETO ATUAL
			while(forecastJsonReader.hasNext()){
				
				//PEGA O NAME
				name = forecastJsonReader.nextName();
				        	
	        	if(name.equals("adLogo")){
	    			empresa.setAdLogo(forecastJsonReader.nextString());
	    		}else if(name.equals("idEmpresas")){
	    			empresa.setIdEmpresas(forecastJsonReader.nextString());
	    		}else if (name.equals("nmEmpresa")) {
	    			empresa.setNmEmpresa(forecastJsonReader.nextString());
	    		}else if (name.equals("nuTelefone")) {
	    			empresa.setNuTelefone(forecastJsonReader.nextString());
	    		}
				
			}
			
			//ADICIONA EMPRESA EM UM ARRAYLIST DO TIPO EMPRESA
			empresas.add(empresa);	
			//FINALIZA O OBJETO ATUAL
			forecastJsonReader.endObject();
		}
		
		return empresas;
    }
    
    @SuppressLint("NewApi")
	public ArrayList<Funcionario> getListaFuncionariosByEmpresa(String METHOD_NAME,List<ArrayList<String>> parameters) throws IOException{
    	
    	String link;
    	ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    	
    	//SE NÃO EXISTIR PARAMETROS
    	if(parameters == null){
    		link = URL_WS + METHOD_NAME;
    	}else{
		//SE EXISTIR PARAMETROS
    		String METHOD_PARAMETERS = montarURLParam(parameters);
    		link = URL_WS + METHOD_NAME + METHOD_PARAMETERS;
    	}    	
    	Log.i(CATEGORIA, link);
    	
    	//CRIA UMA URL COM O LINK OBTIDO
    	URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage());
		}
		
		//INICIA UM READER COM A URL
		Reader forecastReader = new InputStreamReader(url.openStream());
		
		//INICIA UM JSONREADER A PARTIR DO READER OBTIDO ANTERIORMENTE
		JsonReader forecastJsonReader = new JsonReader(forecastReader);
		
		
		String name;

		
		/*
		 * PEGA O PRIMERO OBJETO DO JSON
		 */
		forecastJsonReader.beginObject();
		
		//PEGA O PRIMERO NAME DO JSON, NO CASO "empresa"
		name = forecastJsonReader.nextName();  	
		
		try{
			//PEGA O ARRAY REFERENTE A "empresa"
			forecastJsonReader.beginArray();
		}catch(Exception e){
			
		}
		
		
		//ENQUANTO EXISTIR ITEM NO ARRAY
		while(forecastJsonReader.hasNext()){
			
			//PEGA O OBJETO NA POSISAO ATUAL
			forecastJsonReader.beginObject();
			
			Funcionario funcionario = new Funcionario();
			//ENQUANTO EXISTIR ITENS NO OBJETO ATUAL
			while(forecastJsonReader.hasNext()){
				
				//PEGA O NAME
				name = forecastJsonReader.nextName();	
				
				if(name.equals("adEmail")){
					funcionario.setAdEmail(forecastJsonReader.nextString());
				}else if(name.equals("idFuncionario")){
					funcionario.setIdFuncionario(forecastJsonReader.nextString());
				}else if (name.equals("nmFuncionario")) {
					funcionario.setNmFuncionario(forecastJsonReader.nextString());
				}else if (name.equals("nuCelular")) {
					funcionario.setNuCelular(forecastJsonReader.nextString());
				}else if (name.equals("nuRamal")) {
					funcionario.setNuRamal(forecastJsonReader.nextString());
				}else if (name.equals("nuTelefone")) {
					funcionario.setNuRamal(forecastJsonReader.nextString());
				}
				
			}
			
			//ADICIONA EMPRESA EM UM ARRAYLIST DO TIPO EMPRESA
			funcionarios.add(funcionario);	
			//FINALIZA O OBJETO ATUAL
			forecastJsonReader.endObject();
		}
		
		return funcionarios;
    }
    
    @SuppressLint("NewApi")
	public ArrayList<Funcionario> getListaFuncionariosById(String METHOD_NAME,ArrayList<ArrayList<String>> param) throws IOException{
    	
    	String link;
    	ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    	
    	//SE NÃO EXISTIR PARAMETROS
    	if(param == null){
    		link = URL_WS + METHOD_NAME;
    	}else{
		//SE EXISTIR PARAMETROS
    		String METHOD_PARAMETERS = montarURLParam(param);
    		link = URL_WS + METHOD_NAME + METHOD_PARAMETERS;
    	}    	
    	Log.i(CATEGORIA, link);
    	
    	//CRIA UMA URL COM O LINK OBTIDO
    	URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage());
		}
		
		//INICIA UM READER COM A URL
		Reader forecastReader = new InputStreamReader(url.openStream());
		
		//INICIA UM JSONREADER A PARTIR DO READER OBTIDO ANTERIORMENTE
		JsonReader forecastJsonReader = new JsonReader(forecastReader);
		
		
		String name;

		
		/*
		 * PEGA O PRIMERO OBJETO DO JSON
		 */
		forecastJsonReader.beginObject();
		
		//PEGA O PRIMERO NAME DO JSON, NO CASO "empresa"
		name = forecastJsonReader.nextName();
		
		try{
			//PEGA O ARRAY REFERENTE A "empresa"
			forecastJsonReader.beginArray();
		}catch(Exception e){
			
		}
		
		
		//ENQUANTO EXISTIR ITEM NO ARRAY
		while(forecastJsonReader.hasNext()){
			
			//PEGA O OBJETO NA POSISAO ATUAL
			forecastJsonReader.beginObject();
			
			Funcionario funcionario = new Funcionario();
			//ENQUANTO EXISTIR ITENS NO OBJETO ATUAL
			while(forecastJsonReader.hasNext()){
				
				//PEGA O NAME
				name = forecastJsonReader.nextName();  	
				
				if(name.equals("adEmail")){
					funcionario.setAdEmail(forecastJsonReader.nextString());
				}else if(name.equals("idFuncionario")){
					funcionario.setIdFuncionario(forecastJsonReader.nextString());
				}else if (name.equals("nmFuncionario")) {
					funcionario.setNmFuncionario(forecastJsonReader.nextString());
				}else if (name.equals("nuCelular")) {
					funcionario.setNuCelular(forecastJsonReader.nextString());
				}else if (name.equals("nuRamal")) {
					funcionario.setNuRamal(forecastJsonReader.nextString());
				}else if (name.equals("nuTelefone")) {
					funcionario.setNuRamal(forecastJsonReader.nextString());
				}
				
			}
			
			//ADICIONA EMPRESA EM UM ARRAYLIST DO TIPO EMPRESA
			funcionarios.add(funcionario);	
			//FINALIZA O OBJETO ATUAL
			forecastJsonReader.endObject();
		}
		
		return funcionarios;
    }
    
    @SuppressLint("NewApi")
	public Funcionario getFuncionariosById(String METHOD_NAME,ArrayList<ArrayList<String>> param) throws IOException{
    	
    	String link;
    	Funcionario funcionario = new Funcionario();
    	
    	//SE NÃO EXISTIR PARAMETROS
    	if(param == null){
    		link = URL_WS + METHOD_NAME;
    	}else{
		//SE EXISTIR PARAMETROS
    		String METHOD_PARAMETERS = montarURLParam(param);
    		link = URL_WS + METHOD_NAME + METHOD_PARAMETERS;
    	}    	
    	Log.i(CATEGORIA, link);
    	
    	//CRIA UMA URL COM O LINK OBTIDO
    	URL url = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			Log.e(CATEGORIA, e.getMessage());
		}
		
		//INICIA UM READER COM A URL
		Reader forecastReader = new InputStreamReader(url.openStream());
		
		//INICIA UM JSONREADER A PARTIR DO READER OBTIDO ANTERIORMENTE
		JsonReader forecastJsonReader = new JsonReader(forecastReader);
		
		String name;
		
		/*
		 * PEGA O PRIMERO OBJETO DO JSON
		 */
		forecastJsonReader.beginObject();
		
		
		//ENQUANTO EXISTIR ITENS NO OBJETO ATUAL
		while(forecastJsonReader.hasNext()){
			
			//PEGA O NAME
			name = forecastJsonReader.nextName(); 	
			
			if(name.equals("adEmail")){
				funcionario.setAdEmail(forecastJsonReader.nextString());
			}else if(name.equals("idFuncionario")){
				funcionario.setIdFuncionario(forecastJsonReader.nextString());
			}else if (name.equals("nmFuncionario")) {
				funcionario.setNmFuncionario(forecastJsonReader.nextString());
			}else if (name.equals("nuCelular")) {
				funcionario.setNuCelular(forecastJsonReader.nextString());
			}else if (name.equals("nuRamal")) {
				funcionario.setNuRamal(forecastJsonReader.nextString());
			}else if (name.equals("nuTelefone")) {
				funcionario.setNuTelefone(forecastJsonReader.nextString());
			}
			
		}
		
		return funcionario;
    }
    
    
    @SuppressLint("NewApi")
   	public Funcionario getSuperiorById(String METHOD_NAME,ArrayList<ArrayList<String>> param) throws IOException{
       	
       	String link;
       	Funcionario funcionario = new Funcionario();
       	
       	//SE NÃO EXISTIR PARAMETROS
       	if(param == null){
       		link = URL_WS + METHOD_NAME;
       	}else{
   		//SE EXISTIR PARAMETROS
       		String METHOD_PARAMETERS = montarURLParam(param);
       		link = URL_WS + METHOD_NAME + METHOD_PARAMETERS;
       	}    	
       	Log.i(CATEGORIA, link);
       	
       	//CRIA UMA URL COM O LINK OBTIDO
       	URL url = null;
   		try {
   			url = new URL(link);
   		} catch (MalformedURLException e) {
   			Log.e(CATEGORIA, e.getMessage());
   		}
   		
   		//INICIA UM READER COM A URL
   		Reader forecastReader = new InputStreamReader(url.openStream());
   		
   		//INICIA UM JSONREADER A PARTIR DO READER OBTIDO ANTERIORMENTE
   		JsonReader forecastJsonReader = new JsonReader(forecastReader);
   		
   		String name;
   		
   		/*
   		 * PEGA O PRIMERO OBJETO DO JSON
   		 */
   		forecastJsonReader.beginObject();
   		
   		
   		//ENQUANTO EXISTIR ITENS NO OBJETO ATUAL
   		while(forecastJsonReader.hasNext()){
   			
   			//PEGA O NAME
   			name = forecastJsonReader.nextName(); 	
   			
   			if(name.equals("adEmail")){
   				funcionario.setAdEmail(forecastJsonReader.nextString());
   			}else if(name.equals("idFuncionario")){
   				funcionario.setIdFuncionario(forecastJsonReader.nextString());
   			}else if (name.equals("nmFuncionario")) {
   				funcionario.setNmFuncionario(forecastJsonReader.nextString());
   			}else if (name.equals("nuCelular")) {
   				funcionario.setNuCelular(forecastJsonReader.nextString());
   			}else if (name.equals("nuRamal")) {
   				funcionario.setNuRamal(forecastJsonReader.nextString());
   			}else if (name.equals("nuTelefone")) {
   				funcionario.setNuTelefone(forecastJsonReader.nextString());
   			}
   			
   		}
   		
   		return funcionario;
       }
    
    
    private String montarURLParam(List<ArrayList<String>> parameters){
    	String URL = "?";
    	for(int i = 0; i < parameters.size(); i++){
    		URL += parameters.get(i).get(0) + "=" + Long.valueOf(parameters.get(i).get(1));
    		URL += "&";
    	}
    	URL = URL.substring (0, URL.length() - 1);
    	return URL;
    }
    
}