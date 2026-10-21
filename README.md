# Aplicación Swing: gestión de usuarios

Aplicación Java Swing con Maven para conectarse a MySQL y gestionar registros de `dam2.usuarios`.

## Requisitos
- JDK 17 o superior.
- Maven.
- MySQL en ejecución.
- Base de datos `dam2` y tabla `usuarios` creadas.

## Configuración
El archivo `src/main/resources/db.properties` contiene la configuración de conexión. Actualiza URL, usuario y contraseña según tu instalación.

**Seguridad:** este ejemplo muestra la contraseña almacenada y compara las credenciales tal como están en la tabla, porque así está planteado el ejercicio. No es apropiado para una aplicación real. En producción se deben almacenar hashes de contraseñas (por ejemplo BCrypt o Argon2), nunca contraseñas en texto plano, y no se deben mostrar las contraseñas en una tabla. Evita compartir o subir `db.properties` con credenciales reales.

## Ejecutar desde la terminal
Desde la carpeta del proyecto:

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="es.dam2.usuarios.App"
```

Si `mvn exec:java` no está disponible, puedes añadir el plugin de ejecución al `pom.xml` o abrir el proyecto Maven en IntelliJ IDEA / Eclipse y ejecutar `es.dam2.usuarios.App`.

## Funcionamiento
1. Se abre una ventana de login no redimensionable.
2. La aplicación valida usuario y contraseña contra la tabla `usuarios`.
3. Si son correctos, abre la ventana con los usuarios.
4. En la tabla se pueden editar las columnas **Usuario** y **Password**. Selecciona la fila y pulsa **Guardar cambios**.
5. **Recargar** vuelve a consultar los datos y **Cerrar sesión** regresa al login.

La columna `ID` se muestra para identificar el registro que se actualiza; `fecha_creacion` se muestra como lectura y no se modifica.

## Esquema esperado
La tabla debe tener las columnas `id`, `username`, `password` y `fecha_creacion`, tal como en el script facilitado.
