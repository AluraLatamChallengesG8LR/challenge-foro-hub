# 🏛️ ForoHub - API REST

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Maven](https://img.shields.io/badge/Maven-3.8.7-red)

Una API REST robusta para gestionar un foro de preguntas y respuestas, desarrollada como parte del Challenge Back End de Alura LATAM.

## 📋 Descripción

ForoHub es una plataforma de foro que permite a los usuarios autenticados crear, visualizar, actualizar y eliminar tópicos de discusión. Cada tópico está asociado a un curso específico y cuenta con un sistema de estados para gestionar su ciclo de vida.

## ✨ Características Principales

- **🔐 Autenticación JWT**: Sistema seguro de autenticación basado en tokens
- **📝 Gestión Completa de Tópicos**: CRUD completo para tópicos
- **👥 Gestión de Usuarios**: Sistema de usuarios con roles
- **📚 Categorización por Cursos**: Organización de tópicos por cursos
- **🔍 Búsqueda Avanzada**: Filtros por curso y año
- **📄 Paginación**: Listados optimizados con paginación
- **🛡️ Validaciones**: Validación completa de datos de entrada
- **🚫 Prevención de Duplicados**: Evita tópicos duplicados por título y mensaje

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2**
- **Spring Security 6**
- **Spring Data JPA**
- **MySQL 8**
- **Flyway Migration**
- **JWT (JSON Web Tokens)**
- **Maven**
- **Lombok**

## 🗄️ Modelo de Base de Datos

### Entidades Principales

1. **Usuario**

   - `id` (Long, PK)
   - `login` (String, único)
   - `clave` (String, encriptada)
   - `activo` (Boolean)

2. **Curso**

   - `id` (Long, PK)
   - `nombre` (String, único)
   - `categoria` (String)

3. **Tópico**
   - `id` (Long, PK)
   - `titulo` (String)
   - `mensaje` (Text)
   - `fechaCreacion` (DateTime)
   - `status` (Enum: ABIERTO, CERRADO, SOLUCIONADO, DUPLICADO)
   - `autor_id` (FK → Usuario)
   - `curso_id` (FK → Curso)

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 17 o superior
- Maven 3.8+
- MySQL 8.0+
- Git

### Pasos de Instalación

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/tu-usuario/forohub.git
   cd forohub
   ```

2. **Configurar MySQL**

   ```sql
   CREATE DATABASE forohub_db;
   ```

3. **Configurar application.properties**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   ```

4. **Ejecutar la aplicación**
   ```bash
   ./mvnw spring-boot:run
   ```

La aplicación estará disponible en `http://localhost:8080`

## 📚 Documentación de la API

### Autenticación

Todos los endpoints (excepto `/login`) requieren un token JWT válido en el header:

```
Authorization: Bearer <tu-jwt-token>
```

### Endpoints Principales

#### 🔐 Autenticación

```http
POST /login
Content-Type: application/json

{
  "login": "admin",
  "clave": "123456"
}
```

#### 📝 Tópicos

**Listar tópicos (paginado)**

```http
GET /topicos?page=0&size=10&sort=fechaCreacion,asc
```

**Crear tópico**

```http
POST /topicos
Content-Type: application/json
Authorization: Bearer <token>

{
  "titulo": "¿Cómo usar Spring Security?",
  "mensaje": "Necesito ayuda con la configuración de Spring Security en mi proyecto",
  "cursoId": 1
}
```

**Obtener tópico por ID**

```http
GET /topicos/{id}
Authorization: Bearer <token>
```

**Actualizar tópico**

```http
PUT /topicos/{id}
Content-Type: application/json
Authorization: Bearer <token>

{
  "id": 1,
  "titulo": "Título actualizado",
  "mensaje": "Mensaje actualizado",
  "status": "SOLUCIONADO"
}
```

**Eliminar tópico**

```http
DELETE /topicos/{id}
Authorization: Bearer <token>
```

**Buscar por curso y año**

```http
GET /topicos/buscar?curso=Spring Boot&anio=2024
Authorization: Bearer <token>
```

### Códigos de Respuesta

- `200 OK` - Solicitud exitosa
- `201 Created` - Recurso creado exitosamente
- `204 No Content` - Eliminación exitosa
- `400 Bad Request` - Datos de entrada inválidos
- `401 Unauthorized` - Token JWT inválido o faltante
- `403 Forbidden` - Acceso denegado
- `404 Not Found` - Recurso no encontrado
- `409 Conflict` - Tópico duplicado

## 🧪 Testing

### Usuarios de Prueba

El sistema viene con usuarios precargados para testing:

| Usuario  | Contraseña | Descripción           |
| -------- | ---------- | --------------------- |
| admin    | 123456     | Usuario administrador |
| usuario1 | 123456     | Usuario regular       |
| usuario2 | 123456     | Usuario regular       |

### Cursos Precargados

| ID  | Nombre      | Categoría     |
| --- | ----------- | ------------- |
| 1   | Spring Boot | Backend       |
| 2   | React       | Frontend      |
| 3   | Java Básico | Programación  |
| 4   | MySQL       | Base de Datos |
| 5   | JavaScript  | Programación  |

### Colección de Postman

Puedes importar la colección de Postman incluida en el proyecto para probar todos los endpoints fácilmente.

## 🔧 Configuración Avanzada

### Variables de Entorno

Puedes configurar las siguientes variables de entorno:

```bash
export JWT_SECRET=tu-secreto-super-seguro-aqui
export MYSQL_URL=jdbc:mysql://localhost:3306/forohub_db
export MYSQL_USERNAME=tu_usuario
export MYSQL_PASSWORD=tu_password
```

### Perfiles de Spring

**Desarrollo (default)**

```properties
spring.profiles.active=dev
spring.jpa.show-sql=true
logging.level.org.springframework.security=DEBUG
```

**Producción**

```properties
spring.profiles.active=prod
spring.jpa.show-sql=false
logging.level.org.springframework.security=WARN
```

## 📁 Estructura del Proyecto

```
src/main/java/com/alura/forohub/
├── 📄 ForohubApplication.java
├── 📁 controller/
│   ├── AutenticacionController.java
│   └── TopicoController.java
├── 📁 domain/
│   ├── 📁 curso/
│   │   ├── Curso.java
│   │   └── CursoRepository.java
│   ├── 📁 topico/
│   │   ├── Topico.java
│   │   ├── TopicoRepository.java
│   │   ├── StatusTopico.java
│   │   └── 📁 dto/ (DTOs)
│   └── 📁 usuario/
│       ├── Usuario.java
│       ├── UsuarioRepository.java
│       └── DatosAutenticacionUsuario.java
├── 📁 infra/
│   ├── 📁 errores/
│   │   └── TratadorDeErrores.java
│   └── 📁 security/
│       ├── AutenticacionService.java
│       ├── SecurityConfigurations.java
│       ├── SecurityFilter.java
│       ├── TokenService.java
│       └── DatosJWTtoken.java
└── 📁 resources/
    ├── application.properties
    └── 📁 db/migration/
        ├── V1__create-table-usuarios.sql
        ├── V2__create-table-cursos.sql
        ├── V3__create-table-topicos.sql
        └── V4__insert-datos-iniciales.sql
```

## 🛡️ Seguridad

### Características de Seguridad Implementadas

- **🔐 Autenticación JWT**: Tokens seguros con expiración de 2 horas
- **🔒 Contraseñas Encriptadas**: BCrypt para hash de contraseñas
- **🚫 Protección CSRF**: Deshabilitada para API REST
- **⏱️ Sesiones Stateless**: Sin manejo de sesiones del lado del servidor
- **🔍 Validaciones**: Validación exhaustiva de entrada de datos

### Mejores Prácticas Implementadas

- Principio de menor privilegio
- Tokens con tiempo de expiración
- Validación de entrada de datos
- Manejo centralizado de errores
- Logs de seguridad

## 📈 Posibles Mejoras Futuras

- [ ] **Roles y Permisos**: Sistema más granular de autorización
- [ ] **Respuestas a Tópicos**: Funcionalidad completa de respuestas
- [ ] **Notificaciones**: Sistema de notificaciones en tiempo real
- [ ] **Búsqueda Avanzada**: Elasticsearch para búsquedas complejas
- [ ] **Rate Limiting**: Limitación de peticiones por usuario
- [ ] **Documentación API**: Integración con Swagger/OpenAPI
- [ ] **Tests Automatizados**: Cobertura completa de tests
- [ ] **Docker**: Containerización de la aplicación
- [ ] **CI/CD**: Pipeline de integración y despliegue continuo

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 👨‍💻 Autor

**Luis Alfredo Rios Huanca**

- GitHub: [RiosLuis10245](https://github.com/RiosLuis10245)
- LinkedIn: [Luis Rios](https://www.linkedin.com/in/luis-ri0s/)

## 🙏 Agradecimientos

- [Alura LATAM](https://www.aluracursos.com/) por el desafío y la formación
- Comunidad Spring Boot por la excelente documentación
