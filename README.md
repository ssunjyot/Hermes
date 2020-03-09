# Hermes
Hermes is an application for sending emails asynchronously. It is named after the Greek god of communication :)

Hermes sends emails asynchronously but provides synchronous acknowledgement over REST.<br/>
It supports attachments via URI pointing to the actual document binaries.<br/>
Mails are queued in a Kafka topic until successful response is returned from SMTP Server.<br/>
Max number of retries and duration is configurable and backoff duration before subsequent retries in configurable.

It uses Spring Boot to create a RESTful API which offers a single endpoint for sending emails : `/mail`.
Internally, it uses Kafka as a queue in front of the service. Kafka is configurable using the properties file. By default it uses a Kafka topic `mail-queue`

# Prerequisites

- Kafka is installed locally and running on port 9092<br/>
- The Kafka topic `mail-queue` is created
- Having Maven & Java 8 installed and on $PATH


# Project Structure and Run Instructions

Hermes is a Java 8 Maven project. 

Before building the jar, you must modify `src/main/resources/application.properties` to use your Email adress and password along with the proper SMTP host for your particular email provider.A list of SMTP hosts can be found here : https://www.arclab.com/en/kb/email/list-of-smtp-and-pop3-servers-mailserver-list.html

To compile the project, run the following command :

`mvn clean install`

This command will build the project as well as run all unit tests.

After building the jar navigate to the target directory, you can start the application using :

`java -jar hermes-0.0.1-SNAPSHOT.jar`

# Exploring the service

Swagger has been used to define the API. It allows for route exploration and testing.

Once the application is run, it can be found at the following address : <br/>
http://localhost:4444/swagger-ui.html
