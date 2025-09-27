package chk.api.path;

import java.io.Reader;
import java.util.HashMap;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class Rutas {
	private Properties propiedad = null;

	public Rutas() {

	}

	private Properties getpropiedad() {
		if (this.propiedad == null) {
			this.propiedad = new Properties();
			try {
				//this.propiedad.load(getClass().getResourceAsStream("/jcdlg/chequera/path/path.properties"));
				String resource="remotepath.properties";
				Reader reader=Resources.getResourceAsReader(resource);
				this.propiedad.load(reader);
			} 
			catch (Exception e) {
				System.out.println("Error al leer  las propiedades del archivo init  " + e);
				e.printStackTrace();
			}
			if (!this.propiedad.isEmpty()) {
				return this.propiedad;
			}
			return null;
		}
		return this.propiedad;
	}
	
	public HashMap<String,String>getRutas(){
		HashMap<String,String>rutas=new HashMap<String,String>();
		rutas.clear();
		try {
			rutas.put("url",getpropiedad().getProperty("url"));
			rutas.put("path",getpropiedad().getProperty("path"));
			rutas.put("reportes",getpropiedad().getProperty("reportes"));
			
			
			
		}
		catch(Exception e) {
			System.out.println("Error al ejecutar getRutas "+e);
			e.printStackTrace();
		}
		return rutas;
	}

}
