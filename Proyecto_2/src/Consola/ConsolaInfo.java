package Consola;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exceptions.LoginDuplicadoException;
import Modelo.Galeria;
import Piezas.Autor;
import Piezas.Escultura;
import Piezas.Fotografia;
import Piezas.Impresion;
import Piezas.Otro;
import Piezas.Pieza;
import Piezas.Pintura;
import Piezas.Video;
import Usuarios.Propietario;
import Usuarios.Usuario;

public class ConsolaInfo {
	
	public static String mostrarHistorialPieza(Galeria gal,Pieza pieza) {
		try {
			String i;
    		List<String> info = gal.historialPiezas(pieza);
    		i=("\nHISTORIAL DE LA PIEZA");
    		i+="\n"+("\nDatos Generales de "+ info.get(0));
			i+="\n"+("La pieza es un " + pieza.getTipoPieza());
			if (pieza.getTipoPieza().equalsIgnoreCase("Escultura")) {
				i+="\n"+("Alto: " + ((Escultura) pieza).getAlto());
				i+="\n"+("Ancho: " + ((Escultura) pieza).getAncho());
				i+="\n"+("Profundidad: " + ((Escultura) pieza).getProfundidad());
				i+="\n"+("Peso: " + ((Escultura) pieza).getPeso());
				i+="\n"+("Instalacion: " + ((Escultura) pieza).getInstalacion());
				if (((Escultura) pieza).isElectricidad()) {
					i+="\n"+("La Escultura funciona con electricidad ");	
				}
				else {
					i+="\n"+("La Escultura no funciona con electricidad ");
				}
				i+="\n"+("Materiales: ");
				String materiales="";
				for (String material :((Escultura) pieza).getMateriales() ) {
					System.out.print(material + ", ");
				}
				i=i+materiales;
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Fotografia")) {
				i+="\n"+("Tamaño: " + ((Fotografia) pieza).getTamanio());
				i+="\n"+("Alto: " + ((Fotografia) pieza).getAlto());
				i+="\n"+("Resolucion: " + ((Fotografia) pieza).getResolucion());
				i+="\n"+("Descripcion: " + ((Fotografia) pieza).getDescripcion());
				i+="\n"+("formato: " + ((Fotografia) pieza).getFormato());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Impresion")) {
				i+="\n"+("Material del papel: " + ((Impresion) pieza).getTamanio());
				i+="\n"+("Tamaño: " + ((Impresion) pieza).getTamanio());
				i+="\n"+("Resolucion: " + ((Impresion) pieza).getResolucion());
				i+="\n"+("Descripcion: " + ((Impresion) pieza).getDescripcion());
				i+="\n"+("Flexibilidad: " + ((Impresion) pieza).getFlexibilidad());
				i+="\n"+("Resistencia: " + ((Impresion) pieza).getResistencia());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Pintura")) {
				i+="\n"+("Tecnica: " + ((Pintura) pieza).getTecnica());
				i+="\n"+("Alto: " + ((Pintura) pieza).getAlto());
				i+="\n"+("Ancho: " + ((Pintura) pieza).getAncho());
				i+="\n"+("Descripcion: " + ((Pintura) pieza).getDescripcion());
				i+="\n"+("Origen: " + ((Pintura) pieza).getOrigen());
				i+="\n"+("Forma: " + ((Pintura) pieza).getForma());
				i+="\n"+("tiempoDeCreacion: " + ((Pintura) pieza).getTiempoDeCreacion());
			}

			else if (pieza.getTipoPieza().equalsIgnoreCase("Video")) {
				i+="\n"+("Duracion en minutos: " + ((Video) pieza).getDuracion());
				i+="\n"+("Tamaño: " + ((Video) pieza).getTamanio());
				i+="\n"+("Idioma: " + ((Video) pieza).getIdioma());
				i+="\n"+("Descripcion: " + ((Video) pieza).getDescripcion());
				i+="\n"+("Resolucion: " + ((Video) pieza).getResolucion());
				i+="\n"+("Formato: " + ((Video) pieza).getFormato());
			}

			else {
				i+="\n"+("Informacion: " + ((Otro) pieza).getInfoExtra());

			}

			i+="\n"+("Año: " + pieza.getAnio());
			i+="\n"+("Lugar de creacion: " + pieza.getLugarDeCreacion());
			i+="\n"+("Valor Inicial para subastar la pieza (si es 0 no se subasta): " + pieza.getValorInicial());
			i+="\n"+("Precio: " + pieza.getValorFijo());
			i+="\n"+("Autores: ");
			String autores="";
			for (Autor autor :pieza.getAutores() ) {
				System.out.print(autor.getNombre() + ", ");
			}
			i=i+autores;
			
			if(info.get(1).equalsIgnoreCase("vendida")) {
				i+="\n"+("\nLa pieza ya fue vendida");
				i+="\n"+("Su propietario fue "+info.get(2));
				i+="\n"+("Fue comprada por "+info.get(3));
				i+="\n"+("La pieza fue vendida: "+info.get(4));
				i+="\n"+("El precio por la que fue vendida: "+info.get(5));
			}
			
			else if (info.get(1).equalsIgnoreCase("disponible")){
				i+="\n"+("\nLa pieza no ha sido vendida");
				i+="\n"+("Su propietario es "+info.get(2));
				i+="\n"+("La pieza se encuentra en " + info.get(3));
				if(info.get(4).equalsIgnoreCase("si")) {
				i+="\n"+("La pieza se encuentra en modalidad de consignacion" );}
				else {
					i+="\n"+("La pieza se encuentra en modalidad de consignacion" );
				}
				
				if(info.get(5).equalsIgnoreCase("a")) {
					i+="\n"+("la pieza puede ser vendida directamente en la galeria y en subasta");
				}
				
				else if(info.get(5).equalsIgnoreCase("s")) {
					i+="\n"+("la pieza puede ser vendida unicamente en subasta");
				}
				
				else if(info.get(5).equalsIgnoreCase("g")) {
					i+="\n"+("la pieza solo se puede comprar directamente en la galeria");
				}
				else {
					i+="\n"+("la pieza no se pueede vender");
				}
				
			}
			
    		return i;
    		
    	}
    	catch (Exception e) {
    		System.out.println(e);
    	}
		return null;
	}
	
	public static void mostrarHistorialArtista(Galeria gal, Autor a) {
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
		}	
    
    	catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	//Imprimir Pieza
	public static String imprimirPieza(Pieza pieza) { 
		String info;
		info="\n"+ pieza.getTitulo();
		info=info+("\nLa pieza es un " + pieza.getTipoPieza());
		if (pieza.getTipoPieza().equalsIgnoreCase("Escultura")) {
			info=info+"\n"+("Alto: " + ((Escultura) pieza).getAlto());
			info=info+"\n"+("Ancho: " + ((Escultura) pieza).getAncho());
			info=info+"\n"+("Profundidad: " + ((Escultura) pieza).getProfundidad());
			info=info+"\n"+("Peso: " + ((Escultura) pieza).getPeso());
			info=info+"\n"+("Instalacion: " + ((Escultura) pieza).getInstalacion());
			if (((Escultura) pieza).isElectricidad()) {
				info=info+"\n"+("La Escultura funciona con electricidad ");	
			}
			else {
				info=info+"\n"+("La Escultura no funciona con electricidad ");
			}
			info=info+"\n"+("Materiales: ");
			String materiales="";
			for (String material :((Escultura) pieza).getMateriales() ) {
				materiales+=(material + ", ");
			}
			info=info+materiales;
		}

		else if (pieza.getTipoPieza().equalsIgnoreCase("Fotografia")) {
			info=info+"\n"+("Tamaño: " + ((Fotografia) pieza).getTamanio());
			info=info+"\n"+("Alto: " + ((Fotografia) pieza).getAlto());
			info=info+"\n"+("Resolucion: " + ((Fotografia) pieza).getResolucion());
			info=info+"\n"+("Descripcion: " + ((Fotografia) pieza).getDescripcion());
			info=info+"\n"+("formato: " + ((Fotografia) pieza).getFormato());
		}

		else if (pieza.getTipoPieza().equalsIgnoreCase("Impresion")) {
			info=info+"\n"+("Material del papel: " + ((Impresion) pieza).getTamanio());
			info=info+"\n"+("Tamaño: " + ((Impresion) pieza).getTamanio());
			info=info+"\n"+("Resolucion: " + ((Impresion) pieza).getResolucion());
			info=info+"\n"+("Descripcion: " + ((Impresion) pieza).getDescripcion());
			info=info+"\n"+("Flexibilidad: " + ((Impresion) pieza).getFlexibilidad());
			info=info+"\n"+("Resistencia: " + ((Impresion) pieza).getResistencia());
		}

		else if (pieza.getTipoPieza().equalsIgnoreCase("Pintura")) {
			info=info+"\n"+("Tecnica: " + ((Pintura) pieza).getTecnica());
			info=info+"\n"+("Alto: " + ((Pintura) pieza).getAlto());
			info=info+"\n"+("Ancho: " + ((Pintura) pieza).getAncho());
			info=info+"\n"+("Descripcion: " + ((Pintura) pieza).getDescripcion());
			info=info+"\n"+("Origen: " + ((Pintura) pieza).getOrigen());
			info=info+"\n"+("Forma: " + ((Pintura) pieza).getForma());
			info=info+"\n"+("tiempoDeCreacion: " + ((Pintura) pieza).getTiempoDeCreacion());
		}

		else if (pieza.getTipoPieza().equalsIgnoreCase("Video")) {
			info=info+"\n"+("Duracion en minutos: " + ((Video) pieza).getDuracion());
			info=info+"\n"+("Tamaño: " + ((Video) pieza).getTamanio());
			info=info+"\n"+("Idioma: " + ((Video) pieza).getIdioma());
			info=info+"\n"+("Descripcion: " + ((Video) pieza).getDescripcion());
			info=info+"\n"+("Resolucion: " + ((Video) pieza).getResolucion());
			info=info+"\n"+("Formato: " + ((Video) pieza).getFormato());
		}

		else {
			info=info+"\n"+("Informacion: " + ((Otro) pieza).getInfoExtra());

		}

		info=info+"\n"+("Año: " + pieza.getAnio());
		info=info+"\n"+("Lugar de creacion: " + pieza.getLugarDeCreacion());
		info=info+"\n"+("Valor Inicial para subastar la pieza (si es 0 no se subasta): " + pieza.getValorInicial());
		info=info+"\n"+("Precio: " + pieza.getValorFijo());
		info=info+"\n"+("Autores: ");
		String autores="";
		for (Autor autor :pieza.getAutores() ) {
			autores+=(autor.getNombre() + ", ");
		}
		info=info+autores;
		
		return info;
	}
 
	
	//Pedir info pieza	
	public static Pieza pedirInfoPieza(Propietario pro) throws Exception {

		try {
			Pieza pieza=null;
			Scanner scanner = ConsolaInicial.scanner;

			System.out.print("\nRecuerde que los tipos de pieza que tenemos presentes son los siguientes: ");
			System.out.print("\n- Escultura \n - Fotografia \n - Impresion \n - Pintura \n - Video \n - Otro");
			System.out.print("\n\nPor favor, ingrese el tipo de la Pieza: ");
			String tipoDePieza = scanner.nextLine().trim();
			
			

			System.out.print("Por favor, ingrese el titulo de la Pieza: ");
			String titulo = scanner.nextLine().trim();

			System.out.print("Por favor, ingrese el año de creación de la Pieza: ");
			String aniot = scanner.nextLine().trim();
			int anio=Integer.parseInt(aniot);

			System.out.print("Por favor, ingrese el lugar de creación de la Pieza: ");
			String lugarDeCreacion = scanner.nextLine().trim();

			System.out.print("Por favor, ingrese los autores (separados por comas) de la Pieza, si no se conoce el autor ingrese \"Anonimo\": ");
			String autorest = scanner.nextLine().trim();
			String[] autoresf = autorest.split(",");
			List<String> autores = Arrays.asList(autoresf);

			System.out.print("Por favor, ingrese si desea aplicar la modalidad de \"marginalidad\" (Si o No): ");
			String modalidadt = scanner.nextLine().trim();

			boolean modalidad = false;
			int fechaMax = 0;

			if (modalidadt.equals("Si") || modalidadt.equals("si")) {
				modalidad = true;
				System.out.print("Por favor, ingrese la fecha maxima, para la modalidad de \"marginalidad\" (AAMMDD): ");
				String fechaMaxt = scanner.nextLine().trim();
				fechaMax=Integer.parseInt(fechaMaxt);
			}

			System.out.print("Por favor, ingrese el valor inicial de la pieza, si desea que sea incluida en una subasta, si no ingrese 0: ");
			String valorInicialt = scanner.nextLine().trim();
			int valorInicial=Integer.parseInt(valorInicialt);

			System.out.print("Por favor, ingrese  si desea mostrar su Pieza (Si o No): ");
			String ubicaciont = scanner.nextLine().trim();

			String ubicacion = "Bodega";
			if (ubicaciont.equals("Si") || ubicaciont.equals("si")) {
				ubicacion = "Mostrador";
			}

			boolean vendido = false;

			System.out.print("Por favor, ingrese el valor fijo al que desea vender la Pieza, si no desea que tenga valor fijo ingrese 0: ");
			String valorFijot = scanner.nextLine().trim();
			int valorFijo=Integer.parseInt(valorFijot);


			if (tipoDePieza.equals("Escultura") || tipoDePieza.equals("escultura")) {

				System.out.print("Por favor, ingrese el alto de la Pieza: ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);

				System.out.print("Por favor, ingrese el ancho de la Pieza: ");
				String anchot = scanner.nextLine().trim();
				int ancho=Integer.parseInt(anchot);

				System.out.print("Por favor, ingrese la profundidad de la Pieza: ");
				String profundidadt = scanner.nextLine().trim();
				int profundidad=Integer.parseInt(profundidadt);

				System.out.print("Por favor, ingrese los materiales (separados por comas) de la Pieza: ");
				String materialest = scanner.nextLine().trim();
				String[] materialesf = materialest.split(",");
				List<String> materiales = Arrays.asList(materialesf);

				System.out.print("Por favor, ingrese el peso de la Pieza: ");
				String pesot = scanner.nextLine().trim();
				int peso=Integer.parseInt(pesot);

				System.out.print("Por favor, ingrese su Pieza requiere electricidad (Si o No): ");
				String electricidadt = scanner.nextLine().trim();

				boolean electricidad = false;
				if (electricidadt.equals("Si") || electricidadt.equals("si")) {
					electricidad = true;
				}

				System.out.print("Por favor, ingrese alguna especificacion de la instalación: ");
				String instalacion = scanner.nextLine().trim();

				pieza=new Escultura("Escultura",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
						valorInicial, ubicacion, vendido, valorFijo, alto, ancho, profundidad, materiales, peso, electricidad, instalacion);

				

			}
			else if (tipoDePieza.equals("Fotografia") || tipoDePieza.equals("fotografia")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese el tamaño de la Pieza (En pulgadas): ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el alto de la Pieza (En pulgadas): ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En ppp): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el formato de la Pieza: ");
				String formato = scanner.nextLine().trim();
				
				pieza=new Fotografia("Fotografia",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, ubicacion, vendido, 
						valorFijo, tamanio, alto, resolucion, descripcion, formato);

				
			}else if (tipoDePieza.equals("Impresion") || tipoDePieza.equals("fotografia")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese el material de papel de la Pieza (En pulgadas): ");
				String materialPapel = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el tamaño de la Pieza (En pulgadas): ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En ppp): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese la flexibilidad de la Pieza (Alta, media, baja): ");
				String flexibilidad = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resistencia de la Pieza (Alta, media, baja): ");
				String formato = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				pieza=new Impresion("Impresion",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicacion, vendido, valorFijo, materialPapel, tamanio, resolucion, flexibilidad, formato, descripcion);

				
			}else if (tipoDePieza.equals("Otro") || tipoDePieza.equals("otro")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la información extra que haya de la Pieza: ");
				String infoExtra = scanner.nextLine().trim();
				
				
				pieza=new Otro("Otro",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicacion, vendido, valorFijo, infoExtra);

				
			}else if (tipoDePieza.equals("Pintura") || tipoDePieza.equals("pintura")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la tecnica de la Pieza: ");
				String tecnica = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el origen de la Pieza: ");
				String origen = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la altura de la Pieza (En pulgadas): ");
				String altot = scanner.nextLine().trim();
				int alto=Integer.parseInt(altot);
				
				System.out.print("Por favor, ingrese el ancho de la Pieza (En pulgadas): ");
				String anchot = scanner.nextLine().trim();
				int ancho=Integer.parseInt(anchot);
				
				System.out.print("Por favor, ingrese la forma de la Pieza (Cuadrada, Rectancular, etc): ");
				String forma = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el tiempo de creación de la Pieza: ");
				String tiempoDeCreacion = scanner.nextLine().trim();
				
				pieza=new Pintura("Pintura",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, valorInicial, 
						ubicacion, vendido, valorFijo, tecnica, origen, descripcion, alto, ancho, forma, tiempoDeCreacion);
				
			}else if (tipoDePieza.equals("Video") || tipoDePieza.equals("video")){
				//Falta hacer para el resto de las Piezas, con la info necesaria
				System.out.print("Por favor, ingrese la duración de la Pieza (En minutos): ");
				String duraciont = scanner.nextLine().trim();
				int duracion=Integer.parseInt(duraciont);
				
				System.out.print("Por favor, ingrese el tamaño de la Pieza: ");
				String tamanio = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese el idioma de la Pieza: ");
				String idioma = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la descripcion de la Pieza: ");
				String descripcion = scanner.nextLine().trim();
				
				System.out.print("Por favor, ingrese la resolución de la Pieza (En p): ");
				String resoluciont = scanner.nextLine().trim();
				int resolucion=Integer.parseInt(resoluciont);
				
				System.out.print("Por favor, ingrese el formato de la Pieza: ");
				String formato = scanner.nextLine().trim();

				pieza=new Video("Video",(Usuario)pro, titulo, anio, lugarDeCreacion, autores, modalidad, fechaMax, 
						valorInicial, ubicacion, vendido, valorFijo, duracion, tamanio, idioma, descripcion, resolucion, formato);
							
			}
			return pieza;
		}catch(Exception e) {
			throw e;
		}

	}
	
	//Pedir informacion para hacer usuario

		public static ArrayList<String> pedirInfoUsuario() throws LoginDuplicadoException  {
			try {
				Scanner scanner = ConsolaInicial.scanner;
			
				ArrayList<String> respuesta = new ArrayList<String> ();
				
				System.out.print("Por favor, ingrese su contraseña: ");
				String password = scanner.nextLine().trim();
				respuesta.add(password);

				System.out.print("Por favor, su numero de telefono: ");
				String telefonof = scanner.nextLine().trim();
				respuesta.add(telefonof);

				System.out.print("Por favor, ingrese su nombre : ");
				String nombre =  scanner.nextLine().trim();
				respuesta.add(nombre);

				System.out.print("Por favor, ingrese su correo electronico: ");
				String correo = scanner.nextLine().trim();
				respuesta.add(correo);
				
				return respuesta;				
			}
			catch(Exception e) {
				throw e;
			}

		}



}
