package DB;

import java.sql.Date;
import java.util.Objects;

public class Cursos {
    private int cursoId;
    private String nombreCurso;
    private String descripcion;
    private Date fechaInicio;
    private Date fechaFinalizacion;
    private Integer cupoMaximo;

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursos cursos = (Cursos) o;
        return cursoId == cursos.cursoId && Objects.equals(nombreCurso, cursos.nombreCurso) && Objects.equals(descripcion, cursos.descripcion) && Objects.equals(fechaInicio, cursos.fechaInicio) && Objects.equals(fechaFinalizacion, cursos.fechaFinalizacion) && Objects.equals(cupoMaximo, cursos.cupoMaximo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cursoId, nombreCurso, descripcion, fechaInicio, fechaFinalizacion, cupoMaximo);
    }
}
