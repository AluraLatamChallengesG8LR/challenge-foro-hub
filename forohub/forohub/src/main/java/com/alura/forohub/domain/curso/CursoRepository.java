package com.alura.forohub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Método adicional para buscar por nombre (opcional)
    boolean existsByNombre(String nombre);

    // Método para buscar por categoría (opcional)
    java.util.List<Curso> findByCategoria(String categoria);
}