## Employee details API

The application has been developed using the Spring framework with Maven.

### Features

- Perform various CRUD operations on the Employee database
- Create new employee
- Find all employees with and without constraints
- Update and Delete employees

### Running the Application

I have not included tests in the projecct so we need to build by skipping tests :
```sh
mvn clean install -DskipTests
```
Then continue by running the `StartEmployeeApplication` class use command :
```sh
mvn spring-boot:run
```

### Json Formats for different Rest services are mentioned below :

#### 1. Add Employee:

Route(Method - POST) : `localhost:8080/employees`

Rawdata(json):
{
"name": "ExampleName1",
"age": 25,
"dep" : "Development"
"salary":10000
}


#### 2. Get employees:

Route(Method - GET) : `localhost:8080/employees`

#### 3. Get employee by id:

Route(Method - GET) : `localhost:8080/employees/{id}`

#### 4. Find employees with constraint:

Route(Method - GET) : `localhost:8080/employees/greaterthan/{age}`

#### 5. Update employee:

Route(Method - PUT) : `localhost:8080/employees/{id}`

Rawdata(json):
{
"name": "ExampleName2",
"age": 25,
"dep" : "Development"
"salary":10000
}

#### 6. Update a single field:

Route(Method - PATCH) : `localhost:8080/employees/{id}`

Rawdata(json):
{
"dep":"Testing"
}

#### 7. Deleting an employee:

Route(Method - DELETE) : `localhost:8080/employees/{id}`

Use Postman API for carrying out the different CRUD operations on a better UI.


