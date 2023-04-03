# iot-core

Take home coding assignment


## Getting Started

How to get a copy to run on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```RavenDB``` - https://ravendb.net/downloads

```Java 19``` - https://www.oracle.com/java/technologies/downloads/

```Maven``` - https://maven.apache.org/install.html

```Docker``` - https://docs.docker.com/get-docker/
## Running in Docker
    How to run the project in Docker

1. Clone the repository.
2. Open the project in your favorite IDE.
3. Make sure you have the correct prerequisites installed.
4. In the DocumentStoreHolder class, update the connection string to your RavenDB server. Make sure that the port doesn't crash with the localhost for get http request (ravenDB port can be change in settings.json file). This will ensure that the application can connect to your database properly.
5. Change the RavenDB database name in the DocumentStoreHolder class to your database name or create a new database with the name HSL.
6. Start the RavenDB server on your local machine.
7. Build the Docker image by running the following command from the project root directory: docker build -t my-spring-app:latest .  This will create a Docker image named my-spring-app.
8. Start the Docker container by running the following command: "docker run -d -p 8080:8080 my-spring-app" This will start the container in detached mode (-d) and forward port 8080 on the container to port 8080 on the host machine (-p 8080:8080).
9. Open your web browser and navigate to http://localhost:8080/ to access the API.
10. Enjoy!

### Running Locally

    A step by step series of examples that tell you how to get a development env running

1. Clone the repository
2. Open the project in your favorite IDE
3. Make sure you have the correct prerequisites installed
4. Change raven server port (or use a real server) or port in ```application.properties``` file to make sure that the 2 localhost wont crash
5. Change the ```RavenDB``` database name in the ```DocumentStoreHolder``` class to your database name or create a new database with the name ```HSL```
6. Run the ```RavenDB``` server
7. Run the project
8. Open your browser and go to ```http://localhost:8080/```
9. Enjoy!


## Get commands to try

http://localhost:8080/api/vehicles/ping Test if you can get data

http://localhost:8080/api/vehicles/{latitude}/{longitude} Input a latitude and longitude get busses in 1km radius
60.170741/24.935609 is a location in downtown Helsinki for example


http://localhost:8080/api/vehicles/6_647 Gives you the data of a bus with id 6_647 (may not have data yet, if not choose at will from ravenDB)

