package ecosistemas.com.clase15domicianorincon;

public class Usuario {
    private String nombre;
    private String correo;
    private String contra;

    public Usuario(){

    }

    public Usuario(String nombre, String correo, String contra) {
        this.nombre = nombre;
        this.correo = correo;
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
