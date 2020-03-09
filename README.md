# Hermes
Hermes is a Micro Service for sending emails asynchronously. It is named after the Greek god of communication :)

Hermes sends emails asynchronously but provides synchronous acknowledgement over REST.<br/>
It supports attachments via URI pointing to the actual document binaries.<br/>
Mails are queued in a Kafka topic until successful response is returned from SMTP Server.<br/>
Max number of retries and duration is configurable and backoff duration before subsequent retries in configurable.

It uses Spring Boot to create a RESTful API which offers a single endpoint for sending emails : `/mail`.
Internally, it uses Kafka as a queue in front of the service. Kafka is configurable using the properties file.
