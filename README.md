# Gym Manager

## Authors
- Carlos Sánchez Herrero - carlos.sanchezh@alumnos.upm.es
- Asier Rioja Perales - asier.rioja@alumnos.upm.es 

## Description
Gym management system for handling class enrollments. Allows registration of people and classes, enrollment management, cancellations, and sorted listings.

## System Requirements
- Java 11 or higher
- Maven 3.6+
- Git

## Project Structure
- `gymmanager/`
  - `src/`
    - `main/java/es/upm/pproject/gym/`
      - `App.java`
      - `GymManager.java`
      - `IGymManager.java`
      - `Person.java`
      - `GymClass.java`
    - `test/java/es/upm/pproject/gym/`
      - `GymManagerTest.java`
  - `logs/`
  - `pom.xml`
  - `README.md`

## Build & Execution

### Compile
```bash
mvn compile
```
### Run Tests
```bash
mvn test
```
### Run SonarQube Analysis
```bash
mvn clean verify sonar:sonar -Dsonar.id=$XXX -Dsonar.login=$YYY
```
(where $XXX is your número de matrícula and $YYY is a token you have to create)





