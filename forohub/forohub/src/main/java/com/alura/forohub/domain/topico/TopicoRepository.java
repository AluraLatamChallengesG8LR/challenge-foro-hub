package com.alura.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Verificar duplicados por título y mensaje
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Búsqueda por curso y año
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso AND YEAR(t.fechaCreacion) = :anio")
    Page<Topico> findByCursoAndAnio(@Param("nombreCurso") String nombreCurso, @Param("anio") Integer anio,
            Pageable pageable);

    // Buscar por status
    Page<Topico> findByStatus(StatusTopico status, Pageable pageable);

    // Buscar por autor
    @Query("SELECT t FROM Topico t WHERE t.autor.login = :loginAutor")
    Page<Topico> findByAutorLogin(@Param("loginAutor") String loginAutor, Pageable pageable);

    // Buscar por curso específico
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso")
    Page<Topico> findByCursoNombre(@Param("nombreCurso") String nombreCurso, Pageable pageable);
}