package chk.api.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.core.annotation.OrderUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chk.api.asistencia.GeneradorDeAsistencia;
import chk.api.genschk.GeneradorChk;
import chk.api.models.PAsistUnitaria;
import chk.api.models.UserModel;

@RestController
@RequestMapping("/chkws")
public class Chkapi {
	private GeneradorChk gens=null;
	private GeneradorDeAsistencia gen_asist=null;
	//private List<PAsistUnitaria>lista;
	
	public Chkapi() {
		this.gens=new GeneradorChk();
		//this.lista=new ArrayList<PAsistUnitaria>();
		
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
	public HashMap<Object,Object>getAsistencia(@PathVariable("fecha") String paramfecha){
		HashMap<Object,Object>mapa=new HashMap<Object,Object>();
		this.gen_asist=new GeneradorDeAsistencia();
		
		mapa.clear();
		mapa.put("url",this.gen_asist.URIReporteDeAsistencia(paramfecha));		
		return mapa;
	}
	
	@PostMapping("/PAsistUnitaria")
	public HashMap<Object,Object> getAsistenciaUnitaria(@RequestBody PAsistUnitaria p) {
		HashMap<Object,Object>mapa=new HashMap<Object,Object>();
		mapa.clear();
		this.gen_asist=new GeneradorDeAsistencia();
		mapa.put("url",this.gen_asist.URIReporteDeAsistenciaUnitaria(p.getNombre(),p.getFechai(),p.getFechaf()));
		
		return mapa;
	}
	
	@GetMapping(path="/actjam/{qrcode}")
	public HashMap<Object,Object>getDatosInvitado(@PathVariable("qrcode") String qr){
		HashMap<Object,Object>datosInvitado=new HashMap<Object,Object>();
		datosInvitado.clear();
		return datosInvitado;
	}

}
