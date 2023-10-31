package edu;

import DB.Estudiantes;
import DB.DAO.EstudiantesDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz {
    private SessionFactory sessionFactory;


    public Interfaz(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void abrirVentanaAsignarCursos() {
        VentanaAsignarCursos ventanaAsignarCursos = new VentanaAsignarCursos(sessionFactory);
        ventanaAsignarCursos.mostrarVentana();
    }

    public void createAndShowGUI() {
        // Crear el marco principal
        JFrame frame = new JFrame("Registro de Estudiantes e Inscripciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel para botones y componentes
        JPanel buttonPanel = new JPanel();

        // Botones para acceder a las diferentes funcionalidades
        JButton registroEstudiantesButton = new JButton("Registrar Estudiantes");
        JButton asignarCursosButton = new JButton("Asignar Cursos");

        buttonPanel.add(registroEstudiantesButton);
        buttonPanel.add(asignarCursosButton);

        frame.add(buttonPanel);

        // Acción para el botón de registro de estudiantes
        registroEstudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistroEstudiantes();
            }
        });

        // Acción para el botón de asignar cursos
        asignarCursosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAsignarCursos();
            }
        });


        frame.setVisible(true);
    }

    private void abrirVentanaRegistroEstudiantes() {
        JFrame estudiantesFrame = new JFrame("Registro de Estudiantes");
        estudiantesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        estudiantesFrame.setSize(400, 250);
        estudiantesFrame.setLayout(new GridLayout(7, 2));

        // Componentes de la interfaz de registro de estudiantes
        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel apellidoLabel = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField();
        JLabel identificacionLabel = new JLabel("Identificación:");
        JTextField identificacionField = new JTextField();
        JLabel direccionLabel = new JLabel("Dirección:");
        JTextField direccionField = new JTextField();
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JTextField telefonoField = new JTextField();
        JLabel correoLabel = new JLabel("Correo Electrónico:");
        JTextField correoField = new JTextField();
        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField();
        JButton registrarEstudianteButton = new JButton("Registrar Estudiante");

        estudiantesFrame.add(nombreLabel);
        estudiantesFrame.add(nombreField);
        estudiantesFrame.add(apellidoLabel);
        estudiantesFrame.add(apellidoField);
        estudiantesFrame.add(identificacionLabel);
        estudiantesFrame.add(identificacionField);
        estudiantesFrame.add(direccionLabel);
        estudiantesFrame.add(direccionField);
        estudiantesFrame.add(telefonoLabel);
        estudiantesFrame.add(telefonoField);
        estudiantesFrame.add(correoLabel);
        estudiantesFrame.add(correoField);
        estudiantesFrame.add(usuarioLabel);
        estudiantesFrame.add(usuarioField);
        estudiantesFrame.add(contrasenaLabel);
        estudiantesFrame.add(contrasenaField);
        estudiantesFrame.add(new JLabel());
        estudiantesFrame.add(registrarEstudianteButton);

        registrarEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recoger datos del formulario

                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();
                String identificacion = identificacionField.getText();
                String direccion = direccionField.getText();
                String telefono = telefonoField.getText();
                String correoElectronico = correoField.getText();
                String usuario = usuarioField.getText();
                char[] contrasenaChars = contrasenaField.getPassword();

                // Crear un objeto Estudiantes con los datos
                Estudiantes estudiante = new Estudiantes();
                estudiante.setNombre(nombre);
                estudiante.setApellido(apellido);
                estudiante.setNumeroIdentificacion(identificacion);
                estudiante.setDireccion(direccion);
                estudiante.setTelefono(telefono);
                estudiante.setCorreoElectronico(correoElectronico);
                estudiante.setUsuario(usuario);
                estudiante.setContrasena(new String(contrasenaChars));

                // Llama al DAO para guardar el nuevo estudiante en la base de datos
                EstudiantesDAO estudiantesDAO = new EstudiantesDAO(sessionFactory);
                estudiantesDAO.guardarEstudiante(estudiante);

                // Mensaje de confirmación
                JOptionPane.showMessageDialog(estudiantesFrame, "Estudiante registrado con éxito");

                // Limpia los campos
                nombreField.setText("");
                apellidoField.setText("");
                identificacionField.setText("");
                direccionField.setText("");
                telefonoField.setText("");
                correoField.setText("");
                usuarioField.setText("");
                contrasenaField.setText("");
            }
        });

        estudiantesFrame.setVisible(true);
    }



    public static void main(String[] args) {
        // Inicializar Hibernate y crear la SessionFactory
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // Crear la interfaz gráfica y mostrarla
        SwingUtilities.invokeLater(() -> {
            Interfaz interfaz = new Interfaz(sessionFactory);
            interfaz.createAndShowGUI();
        });
    }
}
