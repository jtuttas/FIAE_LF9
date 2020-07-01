# ToDo Liste
Eine ToDo Liste als REST API für das LF8 LF9 im LF10a. Im 3. AJ hierzu eine GUI entwickelt. Als Datenbank dient eine *SQLite* Datenbank.

Die API wird dokumentiert mittels des *Open API* Standards (ehemals SWAGGER). Die API Dokumentation ist hier zu finden: [swaggerhub](https://app.swaggerhub.com/apis-docs/jtuttas/Todo/1.0.2#/task/get_todo)

## Lastenheft
Sie erhalten Sie Aufgabe für die EasyJob GmbH eine ToDo Liste zu entwickeln. Die ToDoListe sollte er ermöglichen Aufgaben (Todo's) Projekten zuzuordnen und diese mit Prioritäten zu versehen.

Von der Firma erhalten Sie folgende Excel Tabelle, wie diese bisher die Aufgaben verwaltet hat.

![todo](Doku/todos.png)

Das Produkt soll über eine *CI/CD* als *Docker Container* ausgeliefert werden und in der *Cloud* zur Verfügung stehen.

## Lerninhalte
### Datenbankentwurf
- DBMS (LF8)
- Entitäten und Attribute (LF8)
- Normalisierung (LF8)
- SQL Datentypen (LF8)
- ER-Diagramm (LF8)
- Relationsschema (LF8)
- DDL
    - CREATE (LF8)
    - Primär / Fremdschlüssel (LF8)
    - Integritätsbedingungen (LF8)

### Datenbankabfragen
- DML (CRUD) (LF8)
    - INSERT (LF8)
    - SELECT inkl. JOIN (LF8)
    - UPDATE (LF8)
    - DELETE (LF8)
### Objektorientierung
- Klassen erstellen (LF8)
- Instanzen erzeugen / Klassen verwenden (LF8)
- Vererbung (LF8)
- abstrakte Methoden  (LF8)
- Interfaces (LF8)

### Dokumentation
- Java Doc (LF8)
- OpenAPI Standard (Swagger) (LF8)
### UML
- Klassendiagramm (LF8)
### Cloud Computing
- CI/CD (LF9)
- HTTP Methoden und REST-API (LF9)
- VMs (LF9)
- Portfreigaben / Firewall (LF9)
- Docker (LF9)
### Projektmanagement
- Vorgensmodelle / SCRUM (LF8)
- TDD (LF8)
- Netzplan (LF8)
### GUI Design
- HTML5 (LF10a)
- Android App (Lf10a)

## Docker Image
Über eine CI/CD Pipeline wird ein Docker Image auf die lokale Registry gepusht. Dieses Image ist wie folgt zu starten:
```
sudo docker run -d -p 80:8000 service.joerg-tuttas.de:5555/root/fiae-ls8_9_10a
```
