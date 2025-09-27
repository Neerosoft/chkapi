package chk.api.dblink;


import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class Connections {
	
	private String resource = "vps.properties";
	
	private Connection con = null;
	private String driver = "";
	private String puente = "";
	private String u = "";
	private String p = "";
	private String hostname = "";
	private String db = "";
	private String port;
	private Properties propiedad = null;

	public Connections() {

	}

	public Connection getConnection() {
		LoadDataConfig();
		try {
			Class.forName(this.driver).newInstance();
			this.puente = (this.puente + this.hostname + ":" + this.port + "/" + this.db);
			System.out.println(this.puente);
			this.con = DriverManager.getConnection(this.puente, this.u, this.p);

			System.out.println("__conexión establecida..:");
			return this.con;
		} 
		catch (Exception e) {
			System.out.print("problema " + e + "\n\n" + this.puente);
			e.printStackTrace();
		}
		
		return this.con;
	}
	
	public void CloseConnection(Connection con) {
		try {
			con.close();
		}
		catch (Exception e) {
			System.out.println("Error al cerrar la conexion "+e);
			e.printStackTrace();
		}			
		finally{
			System.out.println("__conexión cerrada..:");
		}
	}

	public void LoadDataConfig() {
		this.hostname = getpropiedad().getProperty("server");
		this.db = getpropiedad().getProperty("db");
		this.u = getpropiedad().getProperty("usuario");
		this.p = getpropiedad().getProperty("pws");
		this.driver = getpropiedad().getProperty("driver");
		this.puente = getpropiedad().getProperty("puente");
		this.port = getpropiedad().getProperty("port");
	}

	private Properties getpropiedad() {
		if (this.propiedad == null) {
			this.propiedad = new Properties();
			try {
				
				Reader reader = Resources.getResourceAsReader(resource);
				this.propiedad.load(reader);

			} catch (Exception e) {
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

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getPuente() {
		return puente;
	}

	public void setPuente(String puente) {
		this.puente = puente;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}

