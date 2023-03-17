# abax-iot-core

My take home coding assignment


## Getting Started

How to get a copy to run on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```RavenDB``` - https://ravendb.net/downloads

```Java 19``` - https://www.java.com/en/download/

```Maven``` - https://maven.apache.org/install.html

### Installing

A step by step series of examples that tell you how to get a development env running

1. Clone the repository
2. Open the project in your favorite IDE
3. Change raven server port (or use a real server) or port in ```application.properties``` file to make sure that the 2 localhost wont crash
4. Run the ```RavenDB``` server
5. Run the project
6. Open your browser and go to ```http://localhost:8080/```
7. Enjoy!

## Running in Docker

1. Clone the repository.
2. Open the project in your favorite IDE.
3. In the DocumentStoreHolder class, update the connection string to your RavenDB server. This will ensure that the application can connect to your database properly.
4. Start the RavenDB server on your local machine.
5. Build the Docker image by running the following command from the project root directory: docker build -t my-spring-app .. This will create a Docker image named my-spring-app.
6. Start the Docker container by running the following command: docker run -d -p 8080:8080 my-spring-app. This will start the container in detached mode (-d) and forward port 8080 on the container to port 8080 on the host machine (-p 8080:8080).
7. Open your web browser and navigate to http://localhost:8080/ to access the API.
8. Enjoy!

## Get commands to try

http://localhost:8080/api/vehicles/${latitude}/${longitude} Input a latitude and longitude get busses in 1km radius
60.170741/24.935609 is a location in downtown Helsinki for example

http://localhost:8080/api/vehicles/ping Test if you can get data

http://localhost:8080/api/vehicles/6_647 Gives you the data of a bus with id 6_647 

## My Assumptions and Thoughts
I wanted to make the project in Java and decided to use Paho for my MQTT client. To make the API searchable, I decided to use HTTP and Java Spring Boot. For the database, I had no real preference but saw that ABAX mentioned RavenDB as their solution, so I decided to use that. I have never used RavenDB before, so I had to learn how to use it. For the return of the data, I chose to use a string for demonstration purposes.
To try and add more data to the output I imported a haversine formula to calculate how far away the bus is. For the bus_stop i sent the id to the user and assumed the app would have a map of ids and names
After I was finished making the project I added docker support to make it easier to run.

I hope this helps! Let me know if you have any further questions.