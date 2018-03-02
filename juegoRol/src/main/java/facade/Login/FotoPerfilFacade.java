package facade.Login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import entity.FotoPerfil;
import entity.Moto;







public class FotoPerfilFacade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Variables
	private DatastoreService datastore;
	private Entity entidad;
	private Transaction conexion;
	
	private String initString = "URL no valida";
	
	
	//Constructor
	public FotoPerfilFacade(){}
	
	
	//Metodos Privados
	private List<FotoPerfil> crearEntidades(List<Entity> listaEntidades) {
		List<FotoPerfil> lista = new ArrayList<>();
		
		for(Entity e: listaEntidades) {
			FotoPerfil fp = new FotoPerfil();
			
			//ID -- Uso el generado por el datastore
			fp.setFotoPerfilKey(e.getKey());
			
			Object val = e.getProperty("fotoPerfilURL");
			fp.setFotoPerfil(val.toString());

			lista.add(fp);
		}
		return lista;
	}
	
	
	//Metodos CRUD
	public void crearFotoPerfil(FotoPerfil fp) {
		datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
		entidad = new Entity("FotoPerfil");

		try {
			entidad.setProperty("fotoPerfilURL", fp.getFotoPerfil() != null ? fp.getFotoPerfil() : initString);
			
			conexion = datastore.beginTransaction();
			datastore.put(conexion, entidad);
		}catch (Exception e) {
			System.out.println("Error en FotoPerfilFacade -> crearFotoPerfil");
		}finally {
			conexion.commit();
		}	
	}
	
	
	public void editarFotoPerfil(FotoPerfil fp) {
		datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
		
		//Recojo la key de la moto
		Key keyTemp = fp.getFotoPerfilKey();
		
		try {
			//Busco en el datastore por esa key
			Entity entidad = datastore.get(keyTemp);
			
			entidad.setProperty("fotoPerfilURL", fp.getFotoPerfil() != null ? fp.getFotoPerfil() : initString);

			conexion = datastore.beginTransaction();
			datastore.put(conexion, entidad);

		}catch (Exception e) {
			System.out.println("Error en FotoPerfilFacade -> editarFotoPerfil");
		}finally {
			conexion.commit();
		}
	}
	
	
	public void eliminarFotoPerfil(FotoPerfil fp) {
		datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service

		Key keyTemp = fp.getFotoPerfilKey();
		
		try {
			conexion = datastore.beginTransaction();
			datastore.delete(conexion, keyTemp);
		}catch (Exception e) {
			System.out.println("Error en FotoPerfilFacade -> eliminarFotoPerfil");
		}finally {
			conexion.commit();
		}
	}
	
	
	//Metodos Find
	public List<FotoPerfil> encontrarFotosPerfilDisponibles(){
		datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
		List<FotoPerfil> lista = new ArrayList<>();
		
		try {
			Query q = new Query("FotoPerfil");
			
			List<Entity> listaEntidades = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
			
			lista = crearEntidades(listaEntidades);
		}catch (Exception e) {
			System.out.println("Error en FotoPerfilFacade -> encontrarFotosPerfilDisponibles");
		}finally {
			conexion.commit();
		}
		return lista;
	}
	
	public List<FotoPerfil> encontrarFotoPerfilPorURL(String url){
		datastore = DatastoreServiceFactory.getDatastoreService(); // Authorized Datastore service
		List<FotoPerfil> lista = new ArrayList<>();
		
		try {
			Query q = new Query("FotoPerfil");
			
			List<Entity> listaEntidades = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
			
			lista = crearEntidades(listaEntidades);
		}catch (Exception e) {
			System.out.println("Error en FotoPerfilFacade -> mostrarFotosPerfilDisponibles");
		}finally {
			conexion.commit();
		}
		return lista;
	}
}

