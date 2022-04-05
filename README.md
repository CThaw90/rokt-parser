# Rokt Parser

The Rokt parser is an api that parses through text files sitting within a designated folder on a server and entries
within a json object format that match a user query.

# Language and Build pipeline
The language of choice was Java and the build tool was gradle. Using an opinionated version of the Spring framework
with Spring Boot allows for dependency injection management where all controllers and service components are using a
singleton instance through the life of the application allowing for a small memory footprint. 
A Gradle 7.2 wrapper jar is shipped with the code as pretty much the standard way to allow the gradle daemon to be 
downloaded and run on the build machine or vm without Gradle already existing.

# Code Walkthrough
Application.java - The main class that kicks off the api service application using the Spring Boot library framework
ApplicationController.java - This class is responsible for receiving and parsing the request into a RequestDto that is
later processed by the application
ApplicationService.java - Is an interface that exposes the serveRequest implementation to the application controller
ApplicationImplementation.java - This is the api implementation, the business logic of the application is implemented here
TextRecordEntity.java - This represents a record row object pulled from the text file.
RequestDto.java - This represents a user/client request to the api server
ResponseDto.java - This represents a single api server request object. A collection constructs the response received by
the client
Transformer.java - This is a utility class that converts classes between different representations
Validator.java - This is a utility class that performs basic validations on the user request object
application.properties - The base configuration of the application

# Testing Methodology
Unit tests are available and are run before the docker image is built.
The following runtime tests were executed against the application:
- Incorrect time format used in the "from" and "to" attributes of the request payload
- Input a filename that does not exist on the api server
- Input time "from" and "to" in the incorrect order
- Input times for a given sample file in which the date range does not exist within the file
- Input a valid date and time range for a valid file name

# Improvements
- The retrieval time for larger files can be improved by building an in-memory index of the existing text files 
by time so that requests can process quicker and the application has some idea of what part of the file to 
start searching without having to scan the entire file. The application will also know immediately when a query
falls outside an existing file's date time range
- There can be an implementation of pagination for very large queries that supports returning pages of results
and there isn't a need to send massive data sets of the wire at a time for larger queries against larger text files
