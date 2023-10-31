package DB.DAO;

import DB.Estudiantes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EstudiantesDAO {
    private final SessionFactory sessionFactory;

    public EstudiantesDAO(SessionFactory sessionFactory) {
        // Configurar Hibernate y crear la SessionFactory
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void guardarEstudiante(Estudiantes estudiante) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(estudiante);
        transaction.commit();
        session.close();
    }

    public Estudiantes obtenerEstudiantePorId(int estudianteId) {
        Session session = sessionFactory.openSession();
        Estudiantes estudiante = session.get(Estudiantes.class, estudianteId);
        session.close();
        return estudiante;
    }


    public Estudiantes obtenerEstudiantePorUsuario(String usuario) {
        Session session = sessionFactory.openSession();
        Estudiantes estudiante = (Estudiantes) session.createQuery("FROM Estudiantes WHERE usuario = :usuario")
                .setParameter("usuario", usuario)
                .uniqueResult();
        session.close();
        return estudiante;
    }

    public void actualizarEstudiante(Estudiantes estudiante) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(estudiante);
        transaction.commit();
        session.close();
    }

    public void eliminarEstudiante(int estudianteId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Estudiantes estudiante = session.get(Estudiantes.class, estudianteId);
        if (estudiante != null) {
            session.delete(estudiante);
        }
        transaction.commit();
        session.close();
    }

    public List<Estudiantes> obtenerTodosLosEstudiantes() {
        Session session = sessionFactory.openSession();
        List<Estudiantes> estudiantes = session.createQuery("FROM Estudiantes", Estudiantes.class).list();
        session.close();
        return estudiantes;
    }


    public boolean verificarCredenciales(String usuario, String contrasena) {
        Estudiantes estudiante = obtenerEstudiantePorUsuario(usuario);
        if (estudiante != null) {
            // Si se encontró un estudiante con el usuario proporcionado, verifica la contraseña
            return estudiante.getContrasena().equals(contrasena);
        }
        return false; // Usuario no encontrado
    }


}
