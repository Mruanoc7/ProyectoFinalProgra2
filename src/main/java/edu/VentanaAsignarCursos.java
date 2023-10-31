package edu;

import DB.Cursos;
import DB.DAO.EstudiantesDAO;
import DB.Estudiantes;
import DB.Inscripciones;
import DB.DAO.CursosDAO;
import DB.DAO.InscripcionesDAO;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaAsignarCursos {
    private final SessionFactory sessionFactory;
    private JFrame frame;
    private JComboBox<Estudiantes> estudiantesComboBox;
    private JList<Cursos> cursosList;
    private DefaultListModel<Cursos> cursosListModel;

    public VentanaAsignarCursos(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        frame = new JFrame("Asignar Cursos a Estudiante");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Componentes de la interfaz
        JPanel panel = new JPanel(new GridLayout(4, 2));

        // ComboBox para seleccionar un estudiante
        JLabel estudianteLabel = new JLabel("Selecciona un estudiante:");
        estudiantesComboBox = new JComboBox<>();
        cargarEstudiantes(); // Carga los estudiantes disponibles en el ComboBox
        panel.add(estudianteLabel);
        panel.add(estudiantesComboBox);

        // Lista de cursos disponibles
        JLabel cursosLabel = new JLabel("Cursos disponibles:");
        cursosListModel = new DefaultListModel<>();
        cursosList = new JList<>(cursosListModel);
        cargarCursosDisponibles(); // Carga los cursos disponibles en la lista
        panel.add(cursosLabel);
        panel.add(new JScrollPane(cursosList));

        // Botón para asignar cursos
        JButton asignarCursosButton = new JButton("Asignar Cursos");
        panel.add(new JLabel());
        panel.add(asignarCursosButton);

        frame.add(panel);

        // Acción para el botón de asignar cursos
        asignarCursosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarCursosAEstudiante();
            }
        });
    }

    // Carga la lista de estudiantes desde la base de datos
    private void cargarEstudiantes() {
        // Utiliza un DAO para obtener la lista de estudiantes y agregarlos al ComboBox
        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(sessionFactory);
        List<Estudiantes> estudiantes = estudiantesDAO.obtenerTodosLosEstudiantes();
        estudiantesComboBox.setModel(new DefaultComboBoxModel<>(estudiantes.toArray(new Estudiantes[0])));
    }

    // Carga la lista de cursos disponibles desde la base de datos
    private void cargarCursosDisponibles() {
        // Utiliza un DAO para obtener la lista de cursos y agregarlos a la lista
        CursosDAO cursosDAO = new CursosDAO(sessionFactory);
        List<Cursos> cursos = cursosDAO.obtenerTodosLosCursos();
        for (Cursos curso : cursos) {
            cursosListModel.addElement(curso);
        }
    }

    // Realiza la asignación de cursos al estudiante seleccionado
    private void asignarCursosAEstudiante() {
        Estudiantes estudianteSeleccionado = (Estudiantes) estudiantesComboBox.getSelectedItem();
        List<Cursos> cursosSeleccionados = cursosList.getSelectedValuesList();

        // Verifica si se seleccionó un estudiante y cursos
        if (estudianteSeleccionado != null && !cursosSeleccionados.isEmpty()) {
            InscripcionesDAO inscripcionesDAO = new InscripcionesDAO(sessionFactory);

            for (Cursos curso : cursosSeleccionados) {
                Inscripciones inscripcion = new Inscripciones();
                inscripcion.setEstudiantes(estudianteSeleccionado);
                inscripcion.setCursos(curso);
                inscripcion.setFechaInscripcion(new java.sql.Date(System.currentTimeMillis())); // Fecha actual
                inscripcion.setEstadoInscripcion("Inscrito");

                inscripcionesDAO.guardarInscripcion(inscripcion);
            }

            JOptionPane.showMessageDialog(frame, "Cursos asignados con éxito.");
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un estudiante y al menos un curso.");
        }
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
