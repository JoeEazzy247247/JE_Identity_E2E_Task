Please find task completed to an extent.
I would not say it is perfect however a lot of small challenges that can be fixed with time.

Framework Overview
This is a simple Maven framework created with Intelij IDE. I have adopted BBD style for it ease of use, reusability and well orgnised pattern which i believe is the sort after type of framework to allow for collaboration.

Dependencies used includes:
1. selenium-java
2. cucumber-java
3. cucumber-junit
4. cucumber-picocontainer
5. commons-io
6. webdrivermanager
7. selenium-api

Solution Explorer is categorised in 2 'Main' & 'Test'
1. I have added feature file(This contains my scenario steps)
2. Basehooks(Contains setup and Teardown which initialises my brown instance and shuts down the browser when test has be executed)
3. Driver helper(This class only holds the IWebDriver object which then allows me to use the class to inject the object into other classes)
4. util files(This are provided files used within the test)
5. Pageobjects(This allows me to uniquely store individual elements identified in each pages of the browser) under the main directory. 
I also have the .properties file under this directory where i am retriving my url with the help of the method in the Preader class.

-----------------------------------------------------------------------------------------------
*I have 1 single stef. definition under the step directory for this task as i wanted everything to be in one place for now however this is not the best way to approach it as i would create seperate step definition for each pages as required.

*In the feature file i have used scenario outline which allows me run multiple scenarios at once

* Methods which allows me to read from file and match by pattern are located in the PReader class and called into the step definition class when required.

Any questions around the task i am happy to answer them in the next stage of the interview when required.

Kind regards
Joseph
