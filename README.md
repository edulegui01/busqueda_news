# Busqueda News

## Requisitos


1. Tener instalado gradle.
2. Tener instalado docker.

## Instalación

1. Clonar el repositorio
2. Entrar por consola en la raiz del proyecto
3. Ejecuta `gradle build` en la raiz del proyecto
4. Ejecuta `docker build -t busqueda:latest .` para construir la imagen
5. Ejecuta `docker run -d -p 8090:8090 --name busqueda_container busqueda:latest` para ejecutar el contenedor
`


## Get request con Postman
Para obtener las noticias hacer un get request a la url http://localhost:8090/consulta con el parametro q.
Por ejemplo `http://localhost:8090/consulta?q=asuncion` o `http://localhost:8090/consulta?q=san lorenzo`
Para obetener ademas de las noticias, tambien el contenido de las fotos y el content type agregar el parametro f con valor true
Por ejemplo `http://localhost:8090/consulta?q=asuncion&f=true`



