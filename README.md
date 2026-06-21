# Onlyfy - Arquitectura de Microservicios Distribuidos

## Información de la Entrega
- **Asignatura:** DSY1103 - Desarrollo FullStack I
- **Evaluación:** Evaluación Parcial 3 (Encargo con Defensa Técnica)
- **Estudiante / Integrante:** Lukas 

---

## 1. Descripción del Ecosistema
Onlyfy es una plataforma distribuida basada en microservicios diseñada para la gestión, clasificación, búsqueda y recomendación de contenidos musicales. La arquitectura asegura una separación funcional estricta, alta cohesión, persistencia de datos independiente y comunicación síncrona mediante clientes declarativos.

---

## 2. Componentes de la Arquitectura y Puertos
El ecosistema está compuesto por los siguientes módulos instrumentados:

* **eureka-server (Puerto `8761`):** Servidor centralizado de descubrimiento de servicios. Permite el registro dinámico de instancias operando bajo Docker.
* **api-gateway (Puerto `8080`):** Punto único de entrada (Gateway) encargado del enrutamiento de peticiones hacia los microservicios internos.
* **music-category-service (Puerto `8081`):** Microservicio encargado del catálogo de categorías, géneros y clasificaciones.
* **search-service (Puerto `8082`):** Motor de búsqueda que indexa registros en memoria y centraliza consultas externas mediante llamadas síncronas.
* **recommendation-service (Puerto `8083`):** Sistema que gestiona las sugerencias y listas automatizadas.

---

## 3. Pruebas Unitarias (Checklist de Calidad)
Siguiendo la guía práctica oficial de la asignatura, el microservicio central de búsquedas implementa pruebas unitarias automatizadas con **JUnit 5** orientadas a validar la lógica de negocio sin dependencias externas del entorno web.

### Casos de Prueba Cubiertos:
1.  **`testBuscarConResultadosExitosos`:** Verifica la correcta respuesta del catálogo ante términos coincidentes de artistas ("Radiohead"), asegurando aserciones de no-nulidad y tamaño exacto.
2.  **`testObtenerPorIdExitoso`:** Garantiza la integridad del indexador al mapear claves primarias válidas (ID 101 -> 'Creep').
3.  **`testObtenerPorIdFallido`:** Valida el flujo alternativo y el control de errores cuando se solicita un identificador inexistente (ID 999).

### Cómo ejecutar las pruebas localmente:
Ubicarse en el directorio del servicio (`/search-service`) y ejecutar a través de la terminal:
```powershell
.\mvnw test
