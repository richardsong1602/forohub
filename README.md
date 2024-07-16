# ForoHub

ForoHub es una API REST desarrollada con Spring Boot para gestionar temas de foro, incluyendo operaciones CRUD (CREAR, LEER, ACTUALIZAR, ELIMINAR). El proyecto también incorpora servicios de autenticación y autorización.

## Tabla de Contenidos
- [Instalación](#instalación)
- [Uso](#uso)
- [Endpoints de la API](#endpoints-de-la-api)
- [Configuración](#configuración)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Instalación

Para instalar y ejecutar este proyecto localmente, sigue estos pasos:

1. **Clonar el repositorio:**
    ```sh
    git clone https://github.com/richardsong1602/forohub.git
    cd forohub
    ```

2. **Configurar la base de datos:**
    - Instalar MySQL y crear una base de datos llamada `forohub`.
    - Actualizar el archivo `application.properties` con tus credenciales de base de datos.

3. **Construir el proyecto:**
    ```sh
    ./mvnw clean install
    ```

4. **Ejecutar la aplicación:**
    ```sh
    ./mvnw spring-boot:run
    ```

## Uso

Puedes interactuar con la API usando herramientas como [Insomnia](https://insomnia.rest/) o [Postman](https://www.postman.com/). Importa el archivo de espacio de trabajo de Insomnia proporcionado en el repositorio para empezar a probar los endpoints.

## Endpoints de la API

### Autenticación
- **POST /auth/login**: Autenticar a un usuario y obtener un token.
  - Solicitud:
    ```json
    {
      "username": "tu_usuario",
      "password": "tu_contraseña"
    }
    ```
  - Respuesta:
    ```json
    {
      "token": "jwt_token"
    }
    ```

### Temas
- **GET /topics**: Recuperar una lista de todos los temas.
- **GET /topics/{id}**: Recuperar un tema específico por ID.
- **POST /topics**: Crear un nuevo tema.
  - Requiere autenticación.
- **PUT /topics/{id}**: Actualizar un tema existente por ID.
  - Requiere autenticación.
- **DELETE /topics/{id}**: Eliminar un tema por ID.
  - Requiere autenticación.

## Configuración

La aplicación se puede configurar a través del archivo `application.properties` ubicado en el directorio `src/main/resources`.

```properties
spring.application.name=ForoHub
spring.datasource.url=jdbc:mysql://localhost:3306/forohub
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
