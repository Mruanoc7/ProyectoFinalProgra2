package DB.DAO;

import DB.Inscripciones;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class InscripcionesDAO {
    private final SessionFactory sessionFactory;

    public InscripcionesDAO(SessionFactory sessionFactory) {
        // Configurar Hibernate y crear la SessionFactory
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void guardarInscripcion(Inscripciones inscripcion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(inscripcion);
        transaction.commit();
        session.close();
    }

    public Inscripciones obtenerInscripcionPorId(int inscripcionId) {
        Session session = sessionFactory.openSession();
        Inscripciones inscripcion = session.get(Inscripciones.class, inscripcionId);
        session.close();
        return inscripcion;
    }

    public void actualizarInscripcion(Inscripciones inscripcion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(inscripcion);
        transaction.commit();
        session.close();
    }

    public void eliminarInscripcion(int inscripcionId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Inscripciones inscripcion = session.get(Inscripciones.class, inscripcionId);
        if (inscripcion != null) {
            session.delete(inscripcion);
        }
        transaction.commit();
        session.close();
    }

    public List<Inscripciones> obtenerTodasLasInscripciones() {
        Session session = sessionFactory.openSession();
        List<Inscripciones> inscripciones = session.createQuery("FROM Inscripciones", Inscripciones.class).list();
        session.close();
        return inscripciones;
    }
}
