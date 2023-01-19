# leasing-administrator-assignment
## Preconditions To Run The App
In Order to run the app we need to have installed on our computer:
* npm
* angular version 15
* java 11 or higher
* docker
* docker-compose
* ports **80**, **3306** and **8080** free on the machine

## How to start the application
### Inside the back-end application project
* run **./gradlew build** to build backend application
* run **docker image build -t leasing-administrator-backend .** to build jar file for backend application 
### Inside the front-end application project
* run **ng build** to build the Angular app
* run  **docker image build -t la-web-server .** to build docker image for nginx web server serving static content for the SPA
* run **docker-compose up** to run the app

### How it works

The application uses a nginx server to serve the static content of the Angular app and this server acts as a proxy for the backend application 
to redirect any request with /api/* to the Java application.

To access the app open http://localhost 

To access Swagger Open API documentation visit http://localhost:8080/api/swagger-ui/index.html

#### Entities
The Application has mainly three entities : Customer, Vehicle and LeasingContract. The leasing contract id is a composite key of both the customer and the vehicle ids, 
Every association is set per default to lazy, and we use join fetch when needed to avoid the n+1 query problems.

#### Service Layer
Service Layer has the business logic, which is just calling the repositories for CRUD operations mostly in this app.
In our case each entity has its own service that is connected to the repositories associated, and only interacts with 
other services without direct access to other entity repositories. So each service can enclose the business logic for its resource.

#### Controllers
Each entity has CRUD operations provided and therefore each controller basically presents those operations for that entity. It has request mapping with the resource name in plural
and the http method determines what the endpoint would do. 
In case of Creation of new entity we return the created entity with the auto-generated primary key (case of Customer and Vehicle)
in order for the SPA to be able to update the entity afterwards if needed.

Also, we created a ControllerAdvice class to handle exceptions and return appropriate Http Status to the client, instead of getting 500 for server errors.
And we used hibernate validator annotations to validate incoming resources.

The application uses DTOs for interactions with the client.

#### Angular App
The Angular app was build using Angular 15, and the libraries PrimeNG and Bootstrap.

We used predefined pipes to display dates and currency fields.