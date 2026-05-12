# 🎵 Proyecto OnlyFy

## 📌 Integrantes
* Lukas Valdes
* Gustavo Gardilcic

---

## 🧩 Descripción
**OnlyFy** es una plataforma tipo Spotify basada en arquitectura de microservicios, que permite gestionar usuarios, música, suscripciones y pagos. Cada funcionalidad es un microservicio independiente que se comunica con otros mediante APIs REST usando **OpenFeign**.

---

## ⚙️ Microservicios implementados

| Microservicio | Puerto | Base de datos |
|---|---|---|
| auth-service | 8081 | auth_db |
| subscription-service | 8082 | subscription_db |
| music-category-service | 8083 | music_category_db |
| messaging-service | 8084 | messaging_db |
| jam-service | 8085 | jam_db |
| notification-service | 8086 | notification_db |
| user-perfil | 8087 | profile_db |
| recommendation-service | 8088 | recommendation_db |
| search-service | 8089 | search_db |
| payment-service | 8090 | payment_db |

---

## 📡 Comunicación entre microservicios

| Consumidor | Proveedor | Endpoint consumido | Tecnología |
|---|---|---|---|
| recommendation-service | music-category-service | GET /api/v1/music-categories/{id} | OpenFeign |
| search-service | music-category-service | GET /api/v1/music-categories/{id} | OpenFeign |

**Regla de negocio:** Antes de crear una recomendación o búsqueda, el servicio consulta si la categoría musical existe y está activa. Si el music-category-service no responde, se devuelve un error controlado.

---

## 🏗️ Arquitectura
Cada microservicio incluye:
* Controller → recibe solicitudes HTTP
* Service → lógica de negocio
* Repository → acceso a datos con JpaRepository
* DTOs → transferencia de datos
* GlobalExceptionHandler → manejo de errores
* Base de datos independiente

---

## 🚀 Cómo ejecutar
1. Clonar el repositorio desde GitHub
2. Abrir cada microservicio en VS Code o IntelliJ
3. Crear las BDs en MySQL Workbench
4. Configurar contraseña en cada `application.properties`
5. Ejecutar cada microservicio individualmente
6. Probar endpoints con Postman

---

## 🧪 Tecnologías utilizadas
* Java 21
* Spring Boot 4.0.6
* Spring Data JPA + Hibernate
* MySQL
* OpenFeign
* Maven
* GitHub

---

## 📌 Estado del proyecto
✅ 10 microservicios implementados con persistencia real.
✅ 3 microservicios con comunicación activa entre servicios via OpenFeign:
- music-category-service (proveedor)
- recommendation-service (consumidor)
- search-service (consumidor)
