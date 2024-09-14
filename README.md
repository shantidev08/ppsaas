# ppsaas
Payroll Solution
Payment Processing System (PPSaaS)
Project Details
This project is a Payment Processing System (PPSaaS), designed as a multi-tenant SaaS offering for cloud environments. It allows organizations to register and utilize the platform for managing payroll and generating reports.

Features
Organization Registration: Clients can register their organization using the provided APIs. Once approved, they gain access to the platform.
Payroll Information Upload: Admins can upload payroll data in CSV format using our upload API. The CSV format is specified in the requirement document, and a sample is provided in the /test/resources directory of the project.
Reporting: Various reporting features are available for admins to generate insights based on uploaded payroll data.
How to Use
Setup Instructions
Clone the Repository

Open a bash shell and run:

bash
Copy code
git clone https://github.com/shantidev08/ppsaas.git
Build and Run the Project

If Maven is installed on your system, you can use Maven commands to build and run the project.
Alternatively, you can open the project in any IDE such as Spring Tool Suite (STS) or IntelliJ IDEA and run it.
Configure MongoDB

Update the MongoDB connection URL in the application.properties file:

properties
Copy code
spring.data.mongodb.uri=mongodb://localhost:27017
If using MongoDB Atlas, configure the connection string as follows:

properties
Copy code
spring.data.mongodb.uri=mongodb+srv://<user>:<secret>@pps.mtavw.mongodb.net/?retryWrites=true&w=majority&appName=PPS
Run the Project

Start the project and verify the Swagger documentation at:

bash
Copy code
http://localhost:8087/swagger-ui/index.html#/
API Usage
Below are the cURL requests for interacting with the API. You can import these into Postman for testing.

Register an Organization

curl --location 'http://localhost:8087/api/organizations' \
--header 'Content-Type: application/json' \
--header 'org-id: Lemon' \
--data-raw '{
    "name": "TeraBt",
    "email": "contact@tbt.com"
}'

Upload Payroll Information

curl --location 'http://localhost:8087/api/upload?orgId=TeraBt' \
--form 'file=@"/path/to/file"'
Replace "/path/to/file" with the path to your CSV file.

Generate Reports

Total Employees:


curl --location 'http://localhost:8087/api/reports/total-employees?orgId=TeraBT' \
--header 'Content-Type: application/json'
Employees Joined:


curl --location 'http://localhost:8087/api/reports/employees-joined?orgId=TeraBT' \
--header 'Content-Type: application/json'
Employees Exited:


curl --location 'http://localhost:8087/api/reports/employees-exited?orgId=TeraBT' \
--header 'Content-Type: application/json'
Monthly Salary Report:


curl --location 'http://localhost:8087/api/reports/monthly-salary-report?orgId=TeraBT' \
--header 'Content-Type: application/json'
Employee Financial Report:


curl --location 'http://localhost:8087/api/reports/employee-financial-report?orgId=TeraBT' \
--header 'Content-Type: application/json'
Monthly Amount Released:


curl --location 'http://localhost:8087/api/reports/monthly-amount-released?orgId=TeraBT' \
--header 'Content-Type: application/json'
Yearly Financial Report:


curl --location 'http://localhost:8087/api/reports/yearly-financial-report?orgId=TeraBT' \
--header 'Content-Type: application/json'
Features Not Yet Added
Document Verification: Currently, document verification is not implemented. Trust is placed on the user uploading the data.
Organizaton Verification :Needs to be added
Error Handling: Some error scenarios are not handled comprehensively and will be addressed in future updates.
