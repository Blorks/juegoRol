package facade.Login;

import java.io.Serializable;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;



public class UsuarioFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private DatastoreService datastore;
	private Entity entidad;
	private Key key;
	private Transaction conexion;
	
	//Constructor
	public UsuarioFacade(){}
	
}

