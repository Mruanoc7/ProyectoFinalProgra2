package edu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DB.Estudiantes;
import DB.DAO.EstudiantesDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class VentanaInicioSesion {

    private final SessionFactory sessionFactory;

    public VentanaInicioSesion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new GridLayout(3, 2));

        JLabel usuarioLabel = new JLabel("Usuario:");
        JTextField usuarioField = new JTextField();
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField();
        JButton iniciarSesionButton = new JButton("Iniciar Sesión");

        frame.add(usuarioLabel);
        frame.add(usuarioField);
        frame.add(contrasenaLabel);
        frame.add(contrasenaField);
        frame.add(new JLabel());
        frame.add(iniciarSesionButton);

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí se verifica el inicio de sesión
                String usuario = usuarioField.getText();
                char[] contrasenaChars = contrasenaField.getPassword();
                String contrasena = new String(contrasenaChars);

                if (verificarCredenciales(usuario, contrasena)) {
                    frame.dispose();
                    mostrarInterfazPrincipal();
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciales incorrectas");
                    usuarioField.setText("");
                    contrasenaField.setText("");
                }
            }
        });

        frame.setVisible(true);
    }

    private boolean verificarCredenciales(String usuario, String contrasena) {
        // Verificar las credenciales en la base de datos utilizando Hibernate
        SessionFactory factory = sessionFactory;
        EstudiantesDAO estudiantesDAO = new EstudiantesDAO(factory);

        // Obtener el estudiante por usuario (asumiendo que el usuario es único)
        Estudiantes estudiante = estudiantesDAO.obtenerEstudiantePorUsuario(usuario);

        if (estudiante != null && estudiante.getContrasena().equals(contrasena)) {
            return true;
        }

        return false;
    }

    private void mostrarInterfazPrincipal() {
        // Crear una instancia de la clase Interfaz
        Interfaz interfaz = new Interfaz(sessionFactory);
        // Llamar al método createAndShowGUI para mostrar la interfaz principal
        SwingUtilities.invokeLater(() -> {
            interfaz.createAndShowGUI();
        });
    }


    public static void main(String[] args) {
        // Inicializar Hibernate y crear la SessionFactory
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        SwingUtilities.invokeLater(() -> {
            new VentanaInicioSesion(sessionFactory);
        });
    }
}
