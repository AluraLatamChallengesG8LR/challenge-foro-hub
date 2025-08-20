-- Insertar usuarios de prueba (contraseña: 123456)
INSERT INTO usuarios (login, clave) VALUES 
('admin', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
('usuario1', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.'),
('usuario2', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');

-- Insertar cursos de prueba
INSERT INTO cursos (nombre, categoria) VALUES 
('Spring Boot', 'Backend'),
('React', 'Frontend'),
('Java Básico', 'Programación'),
('MySQL', 'Base de Datos'),
('JavaScript', 'Programación');

-- Insertar tópicos de prueba
INSERT INTO topicos (titulo, mensaje, fecha_creacion, status, autor_id, curso_id) VALUES 
('¿Cómo configurar Spring Security?', 'Tengo problemas para configurar la autenticación en mi proyecto de Spring Boot', NOW(), 'ABIERTO', 1, 1),
('Error en componente React', 'Mi componente no se está renderizando correctamente', NOW(), 'ABIERTO', 2, 2),
('Consultas con JOIN en MySQL', '¿Cuál es la mejor forma de hacer consultas complejas?', NOW(), 'CERRADO', 1, 4);