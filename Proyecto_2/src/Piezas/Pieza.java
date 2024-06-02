package Piezas;

import java.util.ArrayList;
import java.util.List;

import Usuarios.Usuario;


public abstract class Pieza {
	
	private String tipoPieza;
	
	private Usuario propietario;
	
	private String titulo;
	
	private int anio;
	
	private String lugarDeCreacion;
	
	private List<Autor> autores;
	
	private boolean modalidad;
	
	private int fechaMax;
	
	private int valorInicial;
	
	private String ubicacion;
	
	private boolean vendido;
	
	private int valorFijo;

	
	
	public Pieza(String tipoPiezat,Usuario propietariot,String titulot,int aniot, String lugarDeCreaciont, List<String> autores2, boolean modalidadt, int fechaMaxt, int valorInicialt, String ubicaciont, boolean vendidot, int valorFijot) {
		this.tipoPieza=tipoPiezat;
		this.propietario=propietariot;
		this.titulo=titulot;
		this.anio=aniot;
		this.lugarDeCreacion=lugarDeCreaciont;
		this.autores=new ArrayList<Autor>();
		addAutores(autores2);
		this.modalidad=modalidadt;
		this.fechaMax=fechaMaxt;
		this.valorInicial=valorInicialt;
		this.ubicacion=ubicaciont;
		this.vendido=vendidot;
		this.valorFijo=valorFijot;
	}
	
	//Generar String para imprimir
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

	//Getters y Setters
	public Usuario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Usuario usuario) {
		this.propietario = usuario;
	}
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getLugarDeCreacion() {
		return this.lugarDeCreacion;
	}

	public void setLugarDeCreacion(String lugarDeCreacion) {
		this.lugarDeCreacion = lugarDeCreacion;
	}

	public List<Autor> getAutores() {
		return this.autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public void addAutores(List<String> autores) {
		for (String a:autores) {
			Autor autor = new Autor(a);
			this.autores.add(autor);
		}	
	}

	public boolean isModalidad() {
		return this.modalidad;
	}

	public void setModalidad(boolean modalidad) {
		this.modalidad = modalidad;
	}

	public int getFechaMax() {
		return this.fechaMax;
	}

	public void setFechaMax(int fechaMax) {
		this.fechaMax = fechaMax;
	}

	public int getValorInicial() {
		return this.valorInicial;
	}

	public void setValorInicial(int valorInicial) {
		this.valorInicial = valorInicial;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isVendido() {
		return this.vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	public int getValorFijo() {
		return this.valorFijo;
	}

	public void setValorFijo(int valorFijo) {
		this.valorFijo = valorFijo;
	}

	public String getTipoPieza() {
		return tipoPieza;
	}

	public void setTipoPieza(String tipoPieza) {
		this.tipoPieza = tipoPieza;
	}

	

}