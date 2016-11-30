package es.salesianos.utils;

import java.util.List;

import es.salesianos.model.Pais;

public class Snipplet {
	
		/* 
		 *  PRE: 'original' es un String cualquiera
		 *  POST: devuelve origin sin espacios, con la primera letra en mayusculas y el resto en minusculas
		 */
			
			public String formatString(String original){
				
				if (original != null && !original.isEmpty()){
					return original.trim().substring(0,1).toUpperCase() + original.substring(1).toLowerCase();
				}
				else return original;
			}
			
			
		/* 
		 *  PRE: 'lista' es una lista de objetos de la clase Pais
		 *  POST: devuelve un string concatenando el nombre de los paises separandolos por una coma.
		 */
			
			public static String listaPaisesAString(List<Pais> lista){
				String cadena="";
				if (lista != null || !lista.isEmpty()){
					for (Pais pais : lista) {
						cadena += pais.getName()+", ";
					}
					/* quita la coma y espacio final, o se percibe el mensaje de que no hay pais asignado*/
					cadena += ".";
					cadena = cadena.replace(", .", ""); 
				}
				return cadena;
				
			}
}