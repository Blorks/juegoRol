package entity;

import com.google.appengine.api.datastore.Key;

public class FotoPerfil {

	//Variables
	private Key fotoPerfilKey;
	private String fotoPerfil;
	
	
	//Getters y Setters
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	public Key getFotoPerfilKey() {
		return fotoPerfilKey;
	}
	public void setFotoPerfilKey(Key fotoPerfilKey) {
		this.fotoPerfilKey = fotoPerfilKey;
	}

}
