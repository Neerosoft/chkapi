package chk.api.asistencia;

import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.HashMap;

import chk.api.dblink.Connections;
import chk.api.path.Rutas;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneradorDeAsistencia {
	private Connections dblink;
	private Rutas rutas;
	private Path p;
	private HashMap<String, String> path;
	
	public GeneradorDeAsistencia() {
		this.dblink=new Connections();
		this.rutas=new Rutas();
		this.path=this.rutas.getRutas();		
		
	}
	
	public String URIReporteDeAsistencia(String fecha) {
		String link=this.path.get("url");
		try {
			HashMap<String,Object>mapa=new HashMap<String,Object>();
			mapa.clear();
			mapa.put("fecha",fecha);
			
			Connection con=null;
			con=this.dblink.getConnection();
			System.out.println("\nLoad report from: "+this.path.get("reportes")+"asistencia.jasper");
			this.p=Paths.get(this.path.get("reportes")+"asistencia.jasper");
			URI uri=p.toUri();
			URL url=uri.toURL();
			JasperReport report=(JasperReport)JRLoader.loadObject(url);
			JasperPrint print=JasperFillManager.fillReport(report,mapa,con);
			System.out.println("\nLocation saved file: "+this.path.get("path")+fecha+".pdf");
			JasperExportManager.exportReportToPdfFile(print,this.path.get("path")+fecha+".pdf");			
			link=link+fecha+".pdf";
			this.dblink.CloseConnection(con);
			System.out.println("\nurl :"+link);
			
		} 
		catch (Exception e) {
			System.out.println("Error al generar el pdf de asistencia "+e);
			e.printStackTrace();
			link=this.path.get("urlprueba");
		}
		return link;
	}
	
	public String URIReporteDeAsistenciaUnitaria(String nombre,String fechai,String anio,String txtmes) {
		String link=this.path.get("url");
		String urx=fechai+"-"+anio+".pdf";
		String file=this.path.get("path")+urx;
		
		try {
			HashMap<String,Object>mapa=new HashMap<String,Object>();
			mapa.clear();
			mapa.put("nombre",nombre);
			mapa.put("fechai",fechai);
			mapa.put("anio",anio);
			mapa.put("txtmes", txtmes);
			
			Connection con=null;
			con=this.dblink.getConnection();
			System.out.println("\nLoad report from: "+this.path.get("reportes")+"asistUnitaria.jasper");
			this.p=Paths.get(this.path.get("reportes")+"asistUnitaria.jasper");
			URI uri=p.toUri();
			URL url=uri.toURL();
			JasperReport report=(JasperReport)JRLoader.loadObject(url);
			JasperPrint print=JasperFillManager.fillReport(report,mapa,con);
			System.out.println("\nLocation saved file: "+file);
			JasperExportManager.exportReportToPdfFile(print,file);			
			link=link+urx;
			this.dblink.CloseConnection(con);
			System.out.println("\nurx :"+link);
			
		} 
		catch (Exception e) {
			System.out.println("Error al generar el pdf de asistenciaUnitaria "+e);
			e.printStackTrace();
			link=this.path.get("urlprueba");
		}
		return link;
	}

}
