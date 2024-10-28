# ECSE-428-Fall-2024
This is a GitHub repository for the semester long group project

## Class Diagram

![image](https://github.com/user-attachments/assets/444a573a-c6e9-4f83-9685-438cdc7c7418)

The umple model can be found [here](https://cruise.umple.org/umpleonline/umple.php?model=24100819u1vp1ccio73#genArea)

## Setup

- install [jdk 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

- install [postgresql 17](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) 
    - use port 5432 (if not available, it means you already have postgresql installed)
    - **IMPORTANT** use password *ECSE428*
    - no need for stack builder
    - after install, launch psql
        - press enter on every option and enter password *ECSE428*
        - execute command `CREATE DATABASE house_it;`
        - execute command `\l` to confirm db was created succesfully

- install [node.js 20.18.0](https://nodejs.org/en/download/prebuilt-installer)
    - run `npm install` in `ECSE-428-Fall-2024\houseit-frontend`


## Testing

- run `./gradlew clean build` in `ECSE-428-Fall-2024\HouseIt-Backend`
    - this will build the project and run the configured tests
    - You might need to change permission using `chmod 700 gradlew` 

- run `npm start` in `ECSE-428-Fall-2024\houseit-frontend`
