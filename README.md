# Buscar Mutantes

Proyecto de entreviste Mercado Libre

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo.r._

### Pre-requisitos 📋

_Que cosas necesitas para instalar el software_

```
Java 11
Maven 
Docker
Docker Compose
```

### Instalación 🔧
Clonar el repositorio [nicoparola89/adnmutant](https://github.com/nicoparola89/adnmutant)
#### Local

Para ejecutar el proyecto localmente:
1- Levantar imagen docker de elasticsearch.
En la consola ejecutar.

```
docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:6.6.2
```
2- Ejecutar el proyecto con mvn
En la consola ejecutar en la raiz del proyecto 

```
mvn spring-boot:run
```
Listo! Al api esta lista para usar.



_Para finalizar, 2 ejemplos de como utlizar la API_

Recurso para validar si un ADN es mutante
Metodo: POST
URL: http://localhost:8080/api/v1/mutant
body : {"dna" : ["TTGCGA","CAGTGC","TTATGG","AGAGGG","GGGGAA","TCACTG"]}



Recurso para ver las estadisticas de ADNS mutantes.
Metodo: GET
URL: http://localhost:8080/api/v1/stats