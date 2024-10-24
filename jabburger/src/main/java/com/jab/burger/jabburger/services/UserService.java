package com.jab.burger.jabburger.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jab.burger.jabburger.repositories.UserRepository;
import com.jab.burger.jabburger.models.User;
import com.jab.burger.jabburger.Exceptions.UserAlreadyExistsException;

/**
 * Servicio para gestionar las operaciones relacionadas con los usuarios.
 * Implementa UserDetailsService para la autenticación de usuarios.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Carga un usuario por su nombre de usuario (correo electrónico).
     * @param email el correo electrónico del usuario.
     * @return los detalles del usuario.
     * @throws UsernameNotFoundException si el usuario no se encuentra.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el correo electrónico: " + email);
        }
        return user;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * @param user el usuario a crear.
     * @return el usuario creado.
     * @throws UserAlreadyExistsException si el usuario ya existe.
     */
    @Transactional
    public User createUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistsException("Ya existe un usuario con este correo electrónico");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return el usuario encontrado o null si no existe.
     */
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Actualiza la información de un usuario existente.
     * @param user el usuario con la información actualizada.
     * @return el usuario actualizado.
     */
    @Transactional
    public User updateUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }
        existingUser.setEmail(user.getEmail());
        existingUser.setCelular(user.getCelular());
        return userRepository.save(existingUser);
    }

    /**
     * Verifica si la contraseña antigua proporcionada es válida.
     * @param user el usuario a verificar.
     * @param oldPassword la contraseña antigua.
     * @return true si la contraseña es válida, false en caso contrario.
     */
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    /**
     * Cambia la contraseña de un usuario.
     * @param user el usuario cuya contraseña se va a cambiar.
     * @param newPassword la nueva contraseña.
     */
    @Transactional
    public void changeUserPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
