# employee-info
*Employee Services*

Spring Cloud Function and Azure Functions development. Domain driven design approach using a root aggregate. 

The tech stack for this POC is:
* Spring Boot 2.4.4
* Java 11
* Spring Data JDBC 2 
* Postgresql Cloud 
* Azure Functions
* Azure Key-Vault
 
### Local environment requirements
* Java 11 JDK - https://openjdk.java.net/projects/jdk/11/
* Maven - https://maven.apache.org/download.cgi
* Postgresql - https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
* Azure CLI - https://docs.microsoft.com/en-us/cli/azure/install-azure-cli

### Database
Next is the database entity model:

![Database Diagram](/doc/EntityModel.png)

The database script is here [script](/doc/database.sql)

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
set DATABASE_URL=jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc
set DATABASE_USER=myuser
set DATABASE_PASSWORD=mypassword
```

Run the Spring Boot application:
```bash
mvn clean package spring-boot:run
```

Test the application:
```
POST http://localhost/api/employee-info

POST data:
{"id": 5}
```

Run the application with Azure Function Plugin:
```bash
mvn azure-functions:run
```

Test the application:
```
POST http://localhost:7071/api/employee-info

POST data:
{"id": 5}
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
POST https://<YOUR_SPRING_FUNCTION_NAME>.azurewebsites.net/api/employee-info

POST data:
{"id": 5}
```