# Utiliza una imagen base de Java.  Selecciona la versión que uses.
FROM eclipse-temurin:17-jdk AS compile 

# Establece el directorio de trabajo dentro del contenedor.
WORKDIR /app

# Copia el archivo JAR de tu microservicio al contenedor.
COPY . .    

RUN chmod +x ./mvnw
# Compila el microservicio usando Maven.

RUN ./mvnw clean package

FROM eclipse-temurin:17-jdk AS prod

WORKDIR /app
# Copia el archivo JAR de tu microservicio al contenedor.

COPY --from=compilado /app/target/*.jar app.jar

# Indica el puerto que la aplicación escucha.
# Cambia 8080 si tu aplicación usa un puerto diferente.
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]



