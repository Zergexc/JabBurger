package com.jab.burger.jabburger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jab.burger.jabburger.models.User;

/**
 * Repositorio para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca un usuario por su correo electrónico y contraseña.
     * @param email el correo electrónico del usuario.
     * @param password la contraseña del usuario.
     * @return el usuario encontrado o null si no existe.
     */
    User findByEmailAndPassword(String email, String password);

    /**
     * Busca un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return el usuario encontrado o null si no existe.
     */
    User findByEmail(String email);
}
