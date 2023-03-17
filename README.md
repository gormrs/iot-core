# abax-iot-core

My take home coding assignment


## Getting Started

How to get a copy to run on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them

```RavenDB``` - https://ravendb.net/downloads

```Java``` - https://www.java.com/en/download/

```Maven``` - https://maven.apache.org/install.html

### Installing

A step by step series of examples that tell you how to get a development env running

1. Clone the repository
2. Open the project in your favorite IDE
3. Change raven server or port in ```application.properties``` file to make sure that the 2 localhost dosent crash
4. Run the ```RavenDB``` server
5. Run the project
6. Open your browser and go to ```http://localhost:8080/```
7. Enjoy!

## running in docker

1. Clone the repository
2. Open the project in your favorite IDE
3. Change raven server in to match your url or localhost port
4. Run the ```RavenDB``` server
5. Build the project in docker
6. Run the project in docker docker run -d -p 8080:8080  my-spring-app
7. Open your browser and go to ```http://localhost:8080/```
8. Enjoy!

## Get commands to try

http://localhost:8080/api/vehicles/${latitude}/${longitude} Input a latitude an get busses in 1km radius

http://localhost:8080/api/vehicles/ping Test if you can get data

http://localhost:8080/api/vehicles/6_647 Gives you the data of an Bus

## My Assumptions and Thoughts
I wanted to make the project in Java and decided to use Paho for my MQTT client. To make the API searchable, I decided to use HTTP and Java Spring Boot. For the database, I had no real preference but saw that ABAX mentioned RavenDB as their solution, so I decided to use that. I have never used RavenDB before, so I had to learn how to use it. For the return of the data, I chose to use a string for demonstration purposes.
After I was finished making the project I added docker support to make it easier to run.

I hope this helps! Let me know if you have any further questions.