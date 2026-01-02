version: '3.8'
services:
  app:
    build:
      context: ./assistant  # Директория с pom.xml/src (подпапка assistant)
    ports:
      - "8080:8080"  # Сервер на 8080
    environment:
      - SPRING_R2DBC_URL=r2dbc:postgresql://db:5432/assist_db
      - SPRING_R2DBC_USERNAME=postgres
      - SPRING_R2DBC_PASSWORD=yourpassword
    depends_on:
      - db
    volumes:
      - ./assistant:/app  # Монтирует код для hot reload (mvnw clean install для rebuild)
    command: ./mvnw spring-boot:run  # Запуск app через Maven

  db:
    image: timescale/timescaledb:latest-pg16  # Последняя версия на PG16
    environment:
      - POSTGRES_DB=assist_db
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=yourpassword
      - PGDATA=/var/lib/postgresql/data/pgdata
    ports:
      - "5432:5432"  # Для доступа снаружи (pgAdmin)
    volumes:
      - db-data:/var/lib/postgresql/data  # Persistent данные

volumes:
  db-data: