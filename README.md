# 🎵 Proyecto OnlyFy

## 📌 Integrantes

* Lukas Valdes
* Gustavo Gardilcic

---

## 🧩 Descripción

**OnlyFy** es una plataforma tipo Spotify basada en arquitectura de microservicios, que permite gestionar usuarios, música, listas de reproducción y pagos de suscripción.

El sistema está diseñado para simular un entorno real, donde cada funcionalidad se implementa como un microservicio independiente que se comunica con otros servicios mediante APIs REST.

---

## ⚙️ Funcionalidades implementadas

* CRUD de usuarios
* CRUD de canciones y categorías musicales
* Gestión de listas de reproducción
* Sistema de suscripciones
* Gestión de pagos
* Sistema de notificaciones
* Recomendaciones de contenido
* Validaciones de datos
* Persistencia en base de datos con JPA + Hibernate
* Comunicación entre microservicios

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura de microservicios con Spring Boot, donde cada servicio incluye:

* Controller
* Service
* Repository
* Modelo de datos propio
* Base de datos independiente

---

## 🚀 Cómo ejecutar

1. Clonar el repositorio desde GitHub
2. Abrir el proyecto en Visual Studio Code o IntelliJ
3. Configurar la base de datos en `application.properties`
4. Ejecutar cada microservicio individualmente
5. Probar endpoints usando Postman

---

## 🧪 Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 / MySQL
* Maven
* GitHub

---

## 📡 Endpoints (ejemplo - pagos)

* GET /pagos → listar pagos
* POST /pagos → crear pago

---

## 📌 Estado del proyecto

En desarrollo – implementación progresiva de microservicios y funcionalidades.

---
