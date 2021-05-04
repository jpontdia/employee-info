# employee-info
*Employee Services*

Spring Cloud Function with Azure Functions development - Domain driven design approach using a root aggregate. 

The tech stack for this POC is:
* Spring Boot 2.4.4
* Java 8
* Sonarqube  
* Spring Data JDBC 2
* Azure SQL DB or Postgresql
* Azure Functions
* Azure Key-Vault

Default configuration for the application: JDK 8 and Azure SQL DB

### Local environment requirements
* Java JDK 8 or 11
* [Maven 3.X](https://maven.apache.org/download.cgi)
* Azure Account (free trial would suffice as well).
* [Azure Functions Core Tools](https://docs.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=windows%2Ccsharp%2Cbash)
* [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli)
* Azure Sql DB instance / Postgresql

### Database
Next is the database entity model:

![Database Diagram](/doc/EntityModel.png)

The database script is here:
*[Azure SQL DB](/db/create-database-mssql.sql)
*[postgresql](/db/create-database-postgresql.sql)

Example data for the tables: [data-script](/db/data-script.sql)

### Running the application locally
In order to run the application in your computer, three environment variables must be provided
for database connection:
| Variable      | Description |
| ----------- | ----------- |
| DATABASE_URL      | URL connection, example: jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc       |
| DATABASE_USER      | Database user       |
| DATABASE_PASSWORD      | Database password       |
 
On Windows, set the environment variables with:
```
//POSTGRESQL
set DATABASE_URL=jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc

//AZURE SQL DB
set DATABASE_URL=jdbc:sqlserver://MY-DATABASE-SERVER.database.windows.net:1433;databaseName=MY-DATABASE-NAME

//COMMON CONFIGURATION
set DATABASE_USER=myuser
set DATABASE_PASSWORD=mypassword
```

Run the Spring Boot application:
```bash
mvn clean package spring-boot:run
```

Test the application:
```
POST http://localhost/api/employeeInfo

POST data:
{"id": 2}
```

Run the application with Azure Function Plugin:
```bash
mvn package azure-functions:run
```

Test the application:
```
POST http://localhost:7071/api/employeeInfo

POST data:
{"id": 2}
```
### Deploying to Azure Functions

Deploy the application on Azure Functions with the Azure Function Maven plug-in:
```bash
mvn azure-functions:deploy
```

Add Key-Vault references to the created Function App to provide the application with the database connection data.

First, we need to configure access to key vault secret, so the Azure function
will be able to read values from there. 
References: [Assign a Key Vault access policy]
(https://docs.microsoft.com/en-us/azure/key-vault/general/assign-access-policy-cli)

After that go to the Function App > Configuration and click Edit icon for the app setting which uses reference on KeyVault secret and check that there are no error.

Add the Key Vault reference using:
@Microsoft.KeyVault(VaultName=YOUR_KEYVAULT;SecretName=DATABASE-PASSWORD)

![Function Configuration](/doc/FunctionConfiguration.png)

Another example about how to set up Key Vault references:
[Using Azure Key Vault references with Azure Functions or App Service]
(https://blog.joaograssi.com/using-azure-key-vault-references-with-azure-functions-appservice)

Test the application:
```
POST https://<YOUR_SPRING_FUNCTION_NAME>.azurewebsites.net/api/employeeInfo

POST data:
{"id": 2}
```