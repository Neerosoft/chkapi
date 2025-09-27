package chk.api.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.core.annotation.OrderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chk.api.asistencia.GeneradorDeAsistencia;
import chk.api.genschk.GeneradorChk;
import chk.api.models.UserModel;

@RestController
@RequestMapping("/chkws")
public class Chkapi {
	private GeneradorChk gens=null;
	private GeneradorDeAsistencia gen_asist=null;
	
	public Chkapi() {
		this.gens=new GeneradorChk();
		
	}
	
	@GetMapping("/") 
    public String getMessage()
    {
		
		try {
			File directorio=new File(".");
			 if (directorio.exists() && directorio.isDirectory()) { // Verificar que el directorio exista y sea un directorio
		            String[] archivos = directorio.list();
		            System.out.println("Leyendo loas archivos...");

		            if (archivos != null) { // En caso de que la lista no sea nula
		                for (String archivo : archivos) {
		                    System.out.println(archivo+"\n\n");
		                }
		            } else {
		                System.out.println("El directorio está vacío.");
		            }
		        } else {
		            System.out.println("El directorio no existe o no es un directorio.");
		        }
		
		
		} 
		catch (Exception e) {
			System.out.println("Error al revisar el directorio "+e);
		}
        return "index.html";
        
    }
	
	@GetMapping(path="/{id}")
	public HashMap<Object,Object>getChkDir(@PathVariable Long id){
		HashMap<Object,Object>mapa=new HashMap<Object,Object>();
		mapa.clear();
		mapa.put("url",this.gens.BuildChk((id).toString()));		
		return mapa;
	}
	
	@GetMapping(path="/asistencia/{fecha}")
	public HashMap<Object,Object>getAsistencia(@PathVariable String paramfecha){
		HashMap<Object,Object>mapa=new HashMap<Object,Object>();
		this.gen_asist=new GeneradorDeAsistencia();
		
		mapa.clear();
		mapa.put("url",this.gen_asist.URIReporteDeAsistencia(paramfecha));		
		return mapa;
	}

}
