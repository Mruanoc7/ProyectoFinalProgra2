package DB.DAO;


import DB.Cursos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CursosDAO {
    private final SessionFactory sessionFactory;

    public CursosDAO(SessionFactory sessionFactory) {
        // Configurar Hibernate y crear la SessionFactory
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void guardarCurso(Cursos curso) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(curso);
        transaction.commit();
        session.close();
    }

    public Cursos obtenerCursoPorId(int cursoId) {
        Session session = sessionFactory.openSession();
        Cursos curso = session.get(Cursos.class, cursoId);
        session.close();
        return curso;
    }

    public void actualizarCurso(Cursos curso) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(curso);
        transaction.commit();
        session.close();
    }

    public void eliminarCurso(int cursoId) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Cursos curso = session.get(Cursos.class, cursoId);
        if (curso != null) {
            session.delete(curso);
        }
        transaction.commit();
        session.close();
    }

    public List<Cursos> obtenerTodosLosCursos() {
        Session session = sessionFactory.openSession();
        List<Cursos> cursos = session.createQuery("FROM Cursos", Cursos.class).list();
        session.close();
        return cursos;
    }
}

