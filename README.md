# 🧠 Integración de ChatGPT con Java

Este proyecto consiste en una aplicación básica construida en **Java con Spring Boot**, cuyo objetivo principal es permitir realizar **consultas al modelo ChatGPT** de OpenAI y recibir respuestas automáticas generadas por IA.

El servicio recibe una solicitud con un mensaje (`prompt`) y devuelve la respuesta generada por ChatGPT.

---

## ✅ Mejoras implementadas

A partir del proyecto base, se aplicaron múltiples mejoras para optimizar tanto la funcionalidad como la estructura del código:

### 1. ✋ Validación del prompt

- Se ignoran mensajes triviales como `"hola"`, `"hey"`, `"hi"` o entradas muy cortas.
- Se exige un contenido mínimo (≥ 10 caracteres) para garantizar una consulta útil.

### 2. ⚡ WebFlux para arquitectura reactiva

- Se implementó **WebClient** para llamadas HTTP no bloqueantes a la API de ChatGPT.
- El controlador y el servicio usan `Mono<>`, haciendo la app completamente **reactiva**.

### 3. 💉 Inyección de dependencias por interfaz

- El controlador no depende directamente de una clase, sino de la interfaz `IaService`.
- Esto permite sustituir fácilmente el proveedor de IA en el futuro (por ejemplo, usar otro modelo).

### 4. 🧹 Limpieza y reestructuración del código

- Toda la lógica fue movida del controlador al servicio, eliminando responsabilidades innecesarias del controlador.
- Se adoptó una **arquitectura más limpia**, con responsabilidades bien separadas entre capas (controlador, servicio, cliente HTTP, DTOs).

---

## 🧱 Patrones de diseño aplicados

| Patrón                  | Uso en el proyecto                                                                 |
|-------------------------|-------------------------------------------------------------------------------------|
| **Strategy / Decorator** | Transformar la respuesta de ChatGPT: limpiar, capitalizar, traducir, etc.         |
| **Builder**             | Construir dinámicamente el cuerpo JSON de la solicitud enviada a OpenAI.          |
| **DTO (Data Transfer Object)** | Separar los datos internos de los datos recibidos desde o enviados hacia la API. |
| **Inyección de dependencias** | Facilita la extensión del sistema a otros servicios de IA.                         |

---

## 🔧 Otras mejoras

- **Manejo de errores**: Se devuelve una respuesta clara al cliente si ocurre un fallo con la API de ChatGPT.
- **Transformación en cadena**: Se aplican múltiples decoradores a la respuesta (ej. limpieza, traducción, etc.) de forma extensible.
- **Arquitectura desacoplada**: Código más mantenible y abierto a escalabilidad.

---

## 💬 Ejemplo de uso

### Petición

POST /chatgpt/generate
Content-Type: application/json

### Respuesta esperada

```json
{
  "response": "La inteligencia artificial es una rama de la informática que se centra en la creación de sistemas capaces de realizar tareas que normalmente requieren inteligencia humana..."
}

```