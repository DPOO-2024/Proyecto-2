package Modelo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Consola.ConsolaInfo;
import Exceptions.MensajedeErrorException;
import Usuarios.*;
import Piezas.*;
import Persistencia.CentralPersistencia;

public class Galeria {
	private Administrador admin;
	private String nombre;
	private Cajero cajero;
	private Operador operador;
	private Inventario inventario;
	private List<Empleado> empleados;
    private List<Subasta> subastasActivas;


	public Galeria() {
		this.nombre = "Galeria";
		this.admin = null;
		this.cajero = null;
		this.operador= null;
		this.inventario= new Inventario();
		this.empleados = new ArrayList<>();
        this.subastasActivas = new ArrayList<>();
	}
	

	
// Métodos para implementar las funcionalidades específicas (por ejemplo, crearSubasta(), crearUsuario(), etc.)
	
	//Metodos para verificar inicios de sesion
	public Empleado verificarEmpleado(String login2, String password2) throws MensajedeErrorException {
		Empleado empleado = null;
		for(Empleado e :getEmpleados()) {
			if(e.getNombreUsuario().equals(login2)&&e.getContraseña().equals(password2)) {
				empleado = e;
			}
		}
		if (empleado == null) {
			throw new MensajedeErrorException("No se pudo verificar al empleado");
		}
		return empleado;
		
	}
	
	
	//AumentarCupo

	public boolean inicioSesionAdmin(String login,String password) throws MensajedeErrorException {
		if (login.equals(this.admin.getLogin()) && password.equals(this.admin.getPassword())) {
			return true;
		}
		else {
			throw new MensajedeErrorException("Los datos ingresados para iniciar sesion como administrador fueron erroneos");
		}
	}

	public void aumentarCupo(String loginComprador,int valor) {
		this.admin.aumentarCupoComprador(loginComprador,valor);			
		
	}
	
	//Crea una subasta, unicamente por el administrador
	public void crearSubasta(int fecha, String opcion) throws Exception {
		try {			
			for(Subasta sub:this.subastasActivas) {
				if(sub.getFechaSubasta() == fecha) {
					throw new MensajedeErrorException("Ya existe una subasta en esa fecha");
				}
				
			}
			Subasta subasta = this.admin.crearSubastaAdmin(fecha, this,opcion);
			subastasActivas.add(subasta);
		}
		catch(Exception e) {
			throw e;
		}

	}
	
	

	//Le permite a los compradores participar en una subasta
	public Subasta participarSubasta(int fecha,Comprador c, int opcion) throws Exception {
			boolean encontrado = false;
			Subasta subasta = null;
			int size = subastasActivas.size();
			if(size==0) {
				throw new MensajedeErrorException("No hay ninguna subasta activa");
			}
			for (Subasta s : subastasActivas) {
				if (s.getFechaSubasta()==fecha) {
					subasta = s;
					if(s.isActiva()) {
						encontrado =true;
						if(opcion==1) {
							subasta.agregarComprador(c);
						}
						else if (opcion==0) {
							if (!subasta.revisarInscripcion(c)) {
								throw new MensajedeErrorException("No estas registrado no puedes participar todavia");
							}
						
						
						}
					}
				else {
					throw new MensajedeErrorException("Esta subasta ya no esta activa");
					}
				
				}
				
			}
			
			return subasta;	
	}
		

	
	//Termina la subasta y ejecuta los pagos a realizar de los ganadores
	public void terminarSubasta(int fecha) throws Exception {
		try {
			Subasta subasta = null;
			for (Subasta s : subastasActivas) {
				if (s.getFechaSubasta()==fecha) {
					subasta = s;
				}
			}
			if (subasta !=null) {
				this.admin.terminarSubastaAdmin(subasta,this.cajero,this);
				this.subastasActivas.remove(subasta);
			}
			else {
				throw new MensajedeErrorException("No hay subastas activas para esa fecha");
			}	
		}catch(MensajedeErrorException e) {
			throw e;
		}

	}

	public Subasta encontrarSubasta(int fecha) {
		Subasta subasta = null;
		for (Subasta s : subastasActivas) {
			if (s.getFechaSubasta()==fecha) {
				subasta = s;}}
		return subasta;
	}
	


	public ArrayList<Pieza> mostrarPiezasDisponibles() {
		ArrayList<Pieza> piezasDisponibles= this.inventario.getPiezasDisponibles();
	    return piezasDisponibles;
	}
	
	public ArrayList<Pieza> mostrarHistorialPiezas() {
		ArrayList<Pieza> historialPiezas= this.inventario.getHistorialPiezas();
		 return historialPiezas;
    }

    
   

    public void asignarAdministrador(String login,String password){
        Administrador admin= new Administrador(login, password,this.inventario);
        this.setAdmin(admin);
    }
    
    public void asignarCajero(String loginC,String passwordC) throws Exception {
    	try {  		
    		Cajero c = new Cajero(loginC, passwordC, "Cajero");
    		for(Empleado e :empleados) {
				if(e.getNombreUsuario().equals(getCajero().getNombreUsuario())&&e.getContraseña().equals(getCajero().getContraseña())) {
					e.setRol("None");
				}
    		}
			setCajero(c);
    		boolean encontrado=false;
    		for(Empleado em :empleados) {
    			if(em.getNombreUsuario().equals(loginC)&&em.getContraseña().equals(passwordC)) {
    				em.setRol("Cajero");
    				encontrado=true;
    			}
			}
    		if (encontrado==false) {
    			empleados.add(this.cajero);
    		}
    	}
    	catch (Exception e) {
    		throw new MensajedeErrorException("No se pudo asignar el cajero");
    	}
    }
    
    public void agregarEmpleado(String loginE,String passwordE,String rol) throws MensajedeErrorException {
    	try {
    		if (rol.equalsIgnoreCase("Operador")) {
    			Operador operador = new Operador(loginE, passwordE,"Operador");
    			this.empleados.add(operador);
    		}

    		else if (rol.equalsIgnoreCase("Empleado")){
    			Empleado empleado = new Empleado(loginE, passwordE,"Empleado");
    			this.empleados.add(empleado);}
    		else {
    			throw new MensajedeErrorException("No existe ese rol de empleado");
    		}

    	}
    	catch (Exception e) {
    		throw new MensajedeErrorException("No se pudo agregar el empleado");
    	}


    }
	
  //Funciones de imprimir informacion piezas, Artistas o Usuarios 
	public List<String> historialPiezas(Pieza pieza) throws MensajedeErrorException {
		
		
		
		List<String> infoPieza = new ArrayList<>();
	
		
		if (pieza!=null) {
		infoPieza.add(pieza.getTitulo());
			
			if (pieza.isVendido()) {
				infoPieza.add("vendida");
				infoPieza.add(pieza.getPropietario().getLogin());
				Comprador c = null;
				boolean encontrado=false;
					for(Comprador comprador:this.admin.getCompradores()) {
					if(!encontrado) {
					int i =0;
					while(i<comprador.getHistorialCompras().size()) {
					String titulo = comprador.getHistorialCompras().get(i).replaceAll("\\s", "");
					String nombre = pieza.getTitulo().replaceAll("\\s", "");
					if(titulo.equalsIgnoreCase(nombre)) {
						infoPieza.add(comprador.getLogin());
						infoPieza.add(comprador.getHistorialCompras().get(i+1));
						infoPieza.add(comprador.getHistorialCompras().get(i+2));
						c=comprador;
						encontrado=true;
					}
					i=i+3;
					
					}
				}
				

								
					}
			}		
			
		else {
				infoPieza.add("disponible");
				infoPieza.add(pieza.getPropietario().getLogin());
				infoPieza.add(pieza.getUbicacion());
				if(pieza.isModalidad()) {
					infoPieza.add("si");
				}
				
				else {
					infoPieza.add("no");
					}
				if(pieza.getValorFijo()!=0 && pieza.getValorInicial()!=0){
					infoPieza.add("a");	
				}
				else if(pieza.getValorInicial()!=0 && pieza.getValorFijo()==0){
					infoPieza.add("s");	
				}
				
				else if(pieza.getValorInicial()==0 && pieza.getValorFijo()!=0){
					infoPieza.add("g");	
				}
				else {
					infoPieza.add("n");
				}
		}

	}
		
	else {
			throw new MensajedeErrorException("No tenemos esa pieza");
		}
		
		
		return infoPieza;
			
	}
		
	



	public List<List<String>> historialArtista(String nombreA) throws MensajedeErrorException {
		boolean encontrado = false;
		List<List<String>> piezasTotales = new ArrayList<>();
		for(Pieza pieza :this.inventario.getPiezasDisponibles()) {
			for (Autor autor:pieza.getAutores()) {
				if (autor.getNombre().equals(nombreA)) {
					List<String> infoArtista = new ArrayList<>();
					encontrado = true;
					infoArtista.add(autor.getNombre());
					infoArtista.add(pieza.getTitulo());//1
					infoArtista.add(String.valueOf(pieza.getAnio()));//2
					
					
					if (!pieza.isVendido()) {
						infoArtista.add("disponible");//3
						infoArtista.add(pieza.getPropietario().getLogin());//4
						infoArtista.add(pieza.getUbicacion());//5
						if(pieza.isModalidad()) {
							infoArtista.add("si");//6
							}
						else {
							infoArtista.add("no");//6	
						}
						infoArtista.add("0");//7
						
					}
					
				piezasTotales.add(infoArtista);	
			}
				}
			}
		
		
		for(Pieza pieza1 :this.inventario.getHistorialPiezas()) {
			for (Autor autor:pieza1.getAutores()) {
				if (autor.getNombre().equals(nombreA)) {
					List<String> info = new ArrayList<>();
					encontrado = true;
					info.add(autor.getNombre());
					info.add(pieza1.getTitulo());//1
					info.add(String.valueOf(pieza1.getAnio()));//2
					if (pieza1.isVendido()) {
						info.add("vendida");//3
						info.add(pieza1.getPropietario().getLogin());//4
						Comprador c = null;
						for(Comprador comprador:this.admin.getCompradores()) {
							if(comprador.getHistorialCompras().contains(pieza1.getTitulo())) {
								info.add(comprador.getLogin());//5
								c=comprador;
							}
						}
						if (c!=null) {
							int i =0;
							while(i<c.getHistorialCompras().size()) {
								if( c.getHistorialCompras().get(i).equalsIgnoreCase(pieza1.getTitulo())) {
									info.add(c.getHistorialCompras().get(i+1));//6
									info.add(c.getHistorialCompras().get(i+2));//7
									}
								i=i+3;
							}
						}
						else {
							throw new MensajedeErrorException("Ningun comprador compro esta pieza");
						}
							
					}
					
					else {
						info.add("disponible");//3
						info.add(pieza1.getPropietario().getLogin());//4
						info.add(pieza1.getUbicacion());//5
						if(pieza1.isModalidad()) {
							info.add("si");//6
							}
						else {
							info.add("no");//6	
						}
						info.add("0");//7
						
					}
					
				piezasTotales.add(info);	
				
				}
			}
		}
		
		
		if (!encontrado) {
			throw new MensajedeErrorException("El artista que ingreso no ha hecho ninguna de nuestras obras");
			
		}
		
		return piezasTotales;
	}
	
	
  

	public void agregarPieza(ArrayList<String> info, String tipo,Propietario pro) {
		Pieza pieza=null;
		
		int fechaMax=Integer.parseInt(info.get(4));
		boolean modalidad=true;
		
		String[] autoresf = info.get(3).split(",");
		List<String> autores = Arrays.asList(autoresf);
		
		if (fechaMax==0) {
			modalidad=true;
		}
		
		if (tipo.equals("Escultura")) {
			
			String[] materialesf = info.get(11).split(",");
			List<String> materiales = Arrays.asList(materialesf);
			
			boolean electricidad = false;
			if (info.get(13).equals("Si") || info.get(13).equals("si")) {
				electricidad = true;
			}
			
			pieza=new Escultura("Escultura",(Usuario)pro, info.get(0), Integer.parseInt(info.get(1)), info.get(2), autores, modalidad, fechaMax, 
					Integer.parseInt(info.get(5)), info.get(6), false, Integer.parseInt(info.get(7)), Integer.parseInt(info.get(8)), 
					Integer.parseInt(info.get(9)), Integer.parseInt(info.get(10)),materiales,Integer.parseInt(info.get(12)), electricidad,info.get(14));
		}
	}
   
    
    

    // Getters y setters para las listas de empleados y subastas activas

    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Subasta> getSubastasActivas() {
        return this.subastasActivas;
    }

    public void setSubastasActivas(List<Subasta> subastasActivas) {
        this.subastasActivas = subastasActivas;
    }
    
    public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Administrador getAdmin() {
		return this.admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	public Inventario getInventario() {
		return this.inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

    public Cajero getCajero() {
		return this.cajero;
	}


	public void setCajero(Cajero cajero) {
		this.cajero = cajero;
	}


	public Operador getOperador() {
		return this.operador;
	}


	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	
	//Métodos de persistencia
    
	public void cargarGaleria(Galeria galeria){

        String archivoInicio = "InicioGaleria.txt";
        String archivoUsuarios = "Usuarios.txt";
        String archivoPiezas = "Piezas.txt";
        String archivoPagos = "Pagos.txt";
        
		try {
			CentralPersistencia.cargarGaleria(archivoInicio, archivoUsuarios, archivoPiezas, galeria, archivoPagos);	
		} catch (Exception e) {
			System.out.println("Ocurrio un error iniciando la aplicacion");
			System.out.println("Los archivos cargados para iniciar la aplicacion no son correctos");
		}
	}
		
	public void guardarGaleria() throws Exception { 
		try {
			CentralPersistencia.guardarGaleria(this);
		} catch (Exception e) {
			throw new Exception ();
	}
	
}






	





	
	
    
}
