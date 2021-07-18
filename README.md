<h1>Formedix Coding Task</h1>


<h3>This project is used to develop 5 API based on currency</h3>

<h4>Stack used: Java, Spring boot, MySQL</h4>

<p>
API 1: Currency name list </br>
API 2: Exchange Rate of a currency on a datae </br>
API 3: Convert currency on a given date </br>
API 4: Find highest exchange rate of a currency on a given time period.</br>
API 5: Find average exchange rate of a currency on a given time period. </br>

Details of the API can be found on the API documentation which can be found from: 
<br>https://documenter.getpostman.com/view/3556359/TzmCgsfC</br>
</p>


<p>
<b>Note: For memory limitation, currency exchange rate data are available for 2021-06-17 to 2021-07-15. So the API will not
give data before 2021-06-17 and after 2021-07-15</b>
</p>


<p>
To understand how the application is built, please go thorugh Request_flow_diagram.pdf and Class_UML_Diagram.png
</p>


<p>
Test cases are made for service layer which can be found from src->test->services->MainServiceImplTest.java
<br/>
To setup the application in your local system please update the database configuration from application.properties file which can be found from src->main->resources->application.properties
<br/><br/>
It runs on port 8080 on local server which can be updated from application.properties
<br/><br/>
The code is also uploaded to AWS server. So the API are live and can be tested. Please refer to API documentation for more information.
</p>



