package org.example.aplication;

import org.example.aplication.service.UsuarioService;
import org.example.aplication.service.UsuarioServiceImpl;
import org.example.domain.Usuario;
import org.example.infraestructure.repository.UsuarioRepositoryImpl;
import org.example.interfaces.UsuarioRepository;

import java.util.List;

public class Main {
    private static final UsuarioService usuarioService;

    static {
        UsuarioRepository usuarioRepository = new UsuarioRepositoryImpl();
        usuarioService = new UsuarioServiceImpl(usuarioRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String menu = "MENÚ PARA USUARIOS:\n"
                    + "1. Listar usuarios\n"
                    + "2. Crear usuario\n"
                    + "3. Actualizar usuario\n"
                    + "4. Eliminar usuario\n"
                    + "5. Cambiar contraseña\n"
                    + "6. Salir\n"
                    + "SELECCIONE UNA OPCIÓN";

            String opcion = JOptionPane.showInputDialog(menu);
            if (opcion != null){
                switch (opcion) {
                    case 1:
                        listarUsuarios();
                        break;
                    case 2:
                        crearUsuario();
                        break;
                    case 3:
                        actualizarUsuario();
                        break;
                    case 4:
                        eliminarUsuario();
                        break;
                    case 5:
                        cambiarContraseña();
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            }else{
                salir = true;
            }
        }
    }

    private static void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.findAll();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados");
        } else {
            JOptionPane.showMessageDialog(null, "Listado de usuarios:");
            for (Usuario usuario : usuarios) {
                JOptionPane.showMessageDialog(null, usuario);
            }
        }
    }

    private static void crearUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del usuario:"));
        usuario.setID(JOptionPane.showInputDialog("Ingrese el ID del usuario:"));
        usuario.setContraseña(JOptionPane.showInputDialog("Ingrese la contraseña del usuario:"));

        usuarios.add(usuario);

        try {
            usuarioService.save(usuario);
            System.out.println("Usuario creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarUsuario() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario a actualizar:");
        for (Usuario usr : usuarios) {
            if (usr.getNombre().equalsIgnoreCase(nombre)) {
                String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre (dejar en blanco para no cambiar):");
                if (!nuevoNombre.isEmpty()) {
                    usr.setNombre(nuevoNombre);
                }

                String nuevaEdads = JOptionPane.showInputDialog("Nueva edad (0 para no cambiar):");
                if (!nuevaEdads.isEmpty()) {
                    int nuevaEdad = Integer.parseInt(nuevaEdads);
                    if (nuevaEdad > 0) {
                        usr.setEdad(nuevaEdad);
                    }
                }

        try {
            usuarioService.update(usuario);
            System.out.println("Usuario actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
        }
    }


    private static void cambiarContraseña(){
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario para cambiar su contraseña:");
        for (Usuario usr : usuarios) {
            if (usr.getNombre().equalsIgnoreCase(nombre)) {
                String nuevaContraseñas = JOptionPane.showInputDialog("Nueva contraseña:");
                if (!nuevaContraseñas.isEmpty()) {
                    int nuevaContraseña = Integer.parseInt(nuevaContraseñas);
                    if (!nuevaContraseña==Contraseña){
                        usr.setContraseña(nuevaContraseña);
                    }else {
                        JOptionPane.showMessageDialog(null, "ERROR. Las contraseñas no pueden ser iguales");
                    }
                }
                }
    }
}

    private static void eliminarUsuario() {
        String input = JOptionPane.showInputDialog("Ingrese el ID del usuario a eliminar:");
        int id = Integer.parseInt(input);

        Usuario usuario = usuarioService.findById(id);

        if (usuario != null) {
            usuarioService.delete(id);
            JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el usuario con ID " + id);
        }
}
