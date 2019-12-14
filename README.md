Apache Maven download and installation
--------------------------------------

1. Download [Maven](https://maven.apache.org/download.cgi)
2. Follow the [instructions](https://maven.apache.org/install.html) (depending on your OS) to install it here:


To run the tests
----------------

In order to run the tests, some steps will be needed:

1. Clone this github project
2. Open a terminal (or shell) and run the following command line:

```
mvn test
```

To run the tests using a Selenium Grid hub or node:

```
gradle test -Durl=<url>
```

Where:

`<url>` = Any remote server (hub or node) running selenium grid


If you are running it locally, you can run the tests by using, for example:

```
gradle test -Durl=http://localhost:4444
```

(More info related to setting up Selenium Grid setup in the end of this README file)



Automated Test Cases Descriptions
---------------------------------

The following test cases have been implemented as automated tests.

#### Perform a search

1. Navigate to gohop website
2. Type Dublin for the departure airport
3. Select Destination “Algarve”
4. Change the departure date to 20/Feb/2020
5. Change the return date to 22/Feb/2020
5. Click Search button
6. Check if the results page has properly been loaded

#### Check “Today’s top deals” section

1. Navigate to gohop website 
2. Click the first image for “Today’s top deal”
3. Check the deal page has been properly loaded

#### Test top menu

1. Hover over “Holidays By Type” item from top menu
2. Hover over “Honeymoons” submenu item
3. Click “Honeymoon Packages” item
4. Check the Honeymoon Packages page has been properly loaded

#### Test quick holidays finder

1. Navigate to gohop website
2. In the Quick Holiday finder field, select the “Algarve holidays” options
3. Check if the Holiday Deals details is properly being displayed

#### Test Search field

1. Navigate to gohop website
2. Go to the main page search field and type “Rome Italy”
3. Suggested holidays options will be displayed, select “The Perfect Honeymoon - Venice, Florence & Rome” option
4. Click the search icon (magnifying glass)
5. Check if the Overview page tab is being displayed

#### Try to make a Search without specifying Destination (negative testing)

1. Navigate to gohop website
2. Keep departure airport as default
3. Don’t fill in the Destination field
4. Keep the dates as they are
5. Click the Search button
6. A message should be displayed stating that “You must choose a destination point”



To run in a Selenium Grid hub/node
-----------------------------------------------

#### Download Selenium 4 Grid

1. Go to selenium.dev/downloads
2. Download [Selenium 4 Alpha](https://selenium-release.storage.googleapis.com/4.0-alpha/selenium-server-4.0.0-alpha-3.jar)

#### Run Selenium Grid Standalone mode (local run)

1. Open a shell or terminal window.
2. Use the following command: 

```
java -jar selenium-server-4.0.0-alpha-3.jar standalone
```

#### Running on remote servers

1. There are also options to run it on remote servers (hubs or nodes). For more information on setting up remote servers, go to:

[Setting_up_your_own_grid](https://selenium.dev/documentation/en/grid/setting_up_your_own_grid/)

2. You should see the last output line like with the following info:

```
Started Selenium standalone 4.0.0-alpha-3
```

3. Access the following address in your browser: 

```
http://localhost:4444/status
````

4. You should see a JSON body like this one:

```
{
  "value": {
    "ready": true,
    "message": "Selenium Grid ready.",
    "nodes": [
      {
        "id": "ee0ab63c-5a63-4090-9246-a5de79b6801f",
        "uri": "http:\u002f\u002f192.168.0.129:4444",
        "maxSessions": 19,
        "stereotypes": [
          {
            "capabilities": {
              "browserName": "safari"
            },
            "count": 1
          },
          {
            "capabilities": {
              "browserName": "chrome"
            },
            "count": 9
          },
          {
            "capabilities": {
              "browserName": "firefox"
            },
            "count": 9
          }
        ],
        "sessions": [
        ]
      }
    ]
  }
}
```
