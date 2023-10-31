package DB;

import java.util.Objects;

public class Estudiantes {
    private int estudianteId;
    private String nombre;
    private String apellido;
    private String numeroIdentificacion;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String usuario;
    private String contrasena;

    private String cursosAsignados;
    public int getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiantes that = (Estudiantes) o;
        return estudianteId == that.estudianteId && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(numeroIdentificacion, that.numeroIdentificacion) && Objects.equals(direccion, that.direccion) && Objects.equals(telefono, that.telefono) && Objects.equals(correoElectronico, that.correoElectronico) && Objects.equals(usuario, that.usuario) && Objects.equals(contrasena, that.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudianteId, nombre, apellido, numeroIdentificacion, direccion, telefono, correoElectronico, usuario, contrasena);
    }
}
