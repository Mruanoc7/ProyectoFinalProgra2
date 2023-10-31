package DB;

import java.sql.Date;
import java.util.Objects;

public class Inscripciones {
    private int inscripcionId;
    private Integer estudianteId;
    private Integer cursoId;
    private Date fechaInscripcion;
    private String estadoInscripcion;

    private Estudiantes estudiantes;
    private Cursos cursos;

    public Cursos getCursos() {
        return cursos;
    }

    public void setCursos(Cursos cursos) {
        this.cursos = cursos;
    }

    public Estudiantes getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiantes estudiantes) {
        this.estudiantes = estudiantes;
    }

    public int getInscripcionId() {
        return inscripcionId;
    }

    public void setInscripcionId(int inscripcionId) {
        this.inscripcionId = inscripcionId;
    }

    public Integer getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Integer estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(String estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inscripciones that = (Inscripciones) o;
        return inscripcionId == that.inscripcionId && Objects.equals(estudianteId, that.estudianteId) && Objects.equals(cursoId, that.cursoId) && Objects.equals(fechaInscripcion, that.fechaInscripcion) && Objects.equals(estadoInscripcion, that.estadoInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inscripcionId, estudianteId, cursoId, fechaInscripcion, estadoInscripcion);
    }
}
