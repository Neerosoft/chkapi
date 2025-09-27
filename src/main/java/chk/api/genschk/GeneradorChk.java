package chk.api.genschk;

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

public class GeneradorChk {
	private Connections dblink;
	private Rutas rutas;
	private Path p;
	private HashMap<String, String> path;
	
	public GeneradorChk() {
		this.dblink=new Connections();
		this.rutas=new Rutas();
		this.path=this.rutas.getRutas();		
		
	}
	
	public String BuildChk(String numchk) {
		String link=this.path.get("url");
		try {
			HashMap<String,Object>mapa=new HashMap<String,Object>();
			mapa.clear();
			mapa.put("numchk",numchk);
			
			Connection con=null;
			con=this.dblink.getConnection();
			System.out.println("\nLoad report from: "+this.path.get("reportes")+"ws_chk.jasper");
			this.p=Paths.get(this.path.get("reportes")+"ws_chk.jasper");
			URI uri=p.toUri();
			URL url=uri.toURL();
			JasperReport report=(JasperReport)JRLoader.loadObject(url);
			JasperPrint print=JasperFillManager.fillReport(report,mapa,con);
			System.out.println("\nLocation saved file: "+this.path.get("path")+numchk+".pdf");
			JasperExportManager.exportReportToPdfFile(print,this.path.get("path")+numchk+".pdf");			
			link=link+numchk+".pdf";
			this.dblink.CloseConnection(con);
			System.out.println("\nurl :"+link);
			
		} 
		catch (Exception e) {
			System.out.println("Error al generar el cheque "+e);
			e.printStackTrace();
			link=this.path.get("urlprueba");
		}
		return link;
	}

}
