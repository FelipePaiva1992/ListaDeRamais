package br.com.mec.model;

public class Empresa {
	
	private String adLogo;
	private String idEmpresas;
	private String nmEmpresa;
	private String nuTelefone;
	
	public String getAdLogo() {
		return adLogo;
	}
	public void setAdLogo(String adLogo) {
		this.adLogo = adLogo;
	}
	public String getIdEmpresas() {
		return idEmpresas;
	}
	public void setIdEmpresas(String idEmpresas) {
		this.idEmpresas = idEmpresas;
	}
	public String getNmEmpresa() {
		return nmEmpresa;
	}
	public void setNmEmpresa(String nmEmpresa) {
		this.nmEmpresa = nmEmpresa;
	}
	public String getNuTelefone() {
		return nuTelefone;
	}
	public void setNuTelefone(String nuTelefone) {
		this.nuTelefone = nuTelefone;
	}

	//------------------------------------------------------------------------------
	//VEJA: http://developer.android.com/reference/android/widget/ArrayAdapter.html
	
	
	/*	A concrete BaseAdapter that is backed by an array of arbitrary objects. 
	By default this class expects that the provided resource id references 
	a single TextView. If you want to use a more complex layout, use the 
	constructors that also takes a field id. That field id should reference 
	a TextView in the larger layout resource.
		However the TextView is referenced, it will be filled with the toString() 
	of each object in the array. You can add lists or arrays of custom objects. 
	Override the toString() method of your objects to determine what text will 
	be displayed for the item in the list.
	To use something other than TextViews for the array display, for instance, 
		ImageViews, or to have some of data besides toString() results fill the 
	views, override getView(int, View, ViewGroup) to return the type of view 
	you want.
	*/
	
	@Override
	public String toString(){
		return nmEmpresa;
	}
	
	//-------------------------------------------------------------------------------

}
