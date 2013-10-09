package domain;

public class Rol{

   //////////////////////
   // Propiedades del Rol
   ///////////////////////
   
   private int id;
   private String descripcion;
   
   //////////////////////
   // Constructores 
   //////////////////////
   
   // constructor por defecto
   public Rol (){
      id = 0;
	  descripcion = null;
   }
   
   // Costructor con argumentos
   public Rol (int id2,String descripcion2){
      id = id2;
	  descripcion = descripcion2;
   }
   
   
   //////////////////////
   //Metodos de la clase
   //////////////////////
   
   public int getId(){
      return id;
   }
   
   public String getDescripcion(){
       return descripcion;
   }
   
   
}
