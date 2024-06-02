package Piezas;

import java.util.List;

import Modelo.Galeria;

public class Autor{
	
	private String nombre;
	
	public Autor(String n) {
		this.nombre=n;
	}

	public String getNombre() {
		return nombre;
	}
	
	public static String mostrarHistorialArtista(Galeria gal, Autor a) {
		try {
    		List<List<String>> piezasArtista = gal.historialArtista(a.getNombre());
    		String info="";
    		info=("\nHISTORIAL DEL ARTISTA");
    		if (piezasArtista.size()!=1) {
    			for(List<String> pieza : piezasArtista){
    			
    				info+="\n"+("\nCreador de:");
    				info+="\n"+("- "+pieza.get(1));
    				info+="\n"+("Fue creada en el año "+pieza.get(2));
    				
    				if (pieza.get(3).equals("vendida")) {
    					info+="\n"+("La pieza ya fue vendida");
    					info+="\n"+("Su propietario fue "+pieza.get(4) );
    					info+="\n"+("Fue comprada por "+pieza.get(5));
    					info+="\n"+("La pieza fue vendida: "+pieza.get(6));
						info+="\n"+("El precio por la que fue vendida: "+pieza.get(7));
    				}
    				
    				if (pieza.get(3).equals("disponible")){
    					info+="\n"+("La pieza no ha sido vendida");
    					info+="\n"+("Su propietario es "+pieza.get(4));
    					info+="\n"+("La pieza se encuentra en " + pieza.get(5));
    					if(pieza.get(6).equals("si")) {
    						info+="\n"+("La pieza se encuentra en modalidad de consignacion" );
    					}		
    				}	
    				
    			}
    		}
    		
    		else {
    			
    			List<String> pieza = piezasArtista.get(0);
    			info+="\n"+(pieza.get(0));
				info+="\n"+("Creador de:");
				info+="\n"+("- "+pieza.get(1));
				info+="\n"+("Fue creada en el año "+pieza.get(2));
				
				if (pieza.get(3).equals("vendida")) {
					info+="\n"+("La pieza ya fue vendida");
					info+="\n"+("Su propietario fue "+pieza.get(4) );
					info+="\n"+("Fue comprada por "+pieza.get(5));
					info+="\n"+("La pieza fue vendida: "+pieza.get(6));
					info+="\n"+("El precio por la que fue vendida: "+pieza.get(7));
				}
				
				else {
					info+="\n"+("La pieza no ha sido vendida");
					info+="\n"+("Su propietario es "+pieza.get(4));
					info+="\n"+("La pieza se encuentra en " + pieza.get(5));
					if(pieza.get(6).equals("si")) {
						info+="\n"+("La pieza se encuentra en modalidad de consignacion" );
					}	
				}
    		}
    		
    	return info;	
		}	
    
    	catch (Exception e) {
    		System.out.println(e);
    	}
		return null;
	}
}
