## Bookkeeping CRM Project
- The project is specified for FluidCodes Co but could certainly be modified for other companies..

- This project is inspired to be my final project at Per Scholas - Prof.Java Developer extensive course **18 weeks** The project uses many technologies some are Java, JS, MariaDB, Spring, HTML, CSS, Materialize.


## Containerizing application
- `cd ../bookkeeping-crm/crm`
- `docker network create bookkeeping`
- `docker run -p 3306:3306  --name db --network bookkeeping -e MYSQL_DATABASE=fc_crm -e MYSQL_ROOT_PASSWORD=root -d mariadb`
- `mvn install -DskipTests`
- `docker build -t app .`
- `docker run --network bookkeeping --name bookkeeping-con -p 80:8080 -d app`
- Address to login `IP:80`
- Default user/password `habboubi`/`habboubi`

