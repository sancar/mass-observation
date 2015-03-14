## TEST SCENARIO FOR INITIATOR(WEBSITE) ##
Description : Validation of the initiator's requirements
|Case | Action and Data | Expected Result | OK |
|:----|:----------------|:----------------|:---|
| 1 | On the Register page, register with email "mustafa.sancar.koyunlu@gmail.com" and password "password"| A message "To activate your account click the link in the email that we send"  |    |
| 2 |  On the login page, login with "mustafa.sancar.koyunlu@gmail.com" and password "password" | A message "Sorry your username or password is wrong" |     |
| 3 | The url in the activation mail is clicked | A message "Your email address is confirmed and your account is activated"  |   |
| 4 |On the register page, register with "mustafa.sancar.koyunlu@gmail.com" and with any password | A message "There is already a user using that email, please try to login from here(link to login page)"|    |
| 5 |On the login page, login with email "mustafa.sancar.koyunlu@gmail.com" and password "password"| The page should be redirected to home screen of the user |    |
| 6 | On the home screen, click "Create Observation Event"| Options of Observation Event should appear(name, summary, who can participate etc -see mockup\_scenario-) and the "Create" button |    |
| 7 | In the same screen, name the OE as "trial1" and click the "Create" button | A message "Your event is created" and the event is stored in database   |    |
| 8 | On the home screen of user, click "My Observation Events"  | A search field is shown |    |
| 9 | Search "tri" in the search field  |The OE named "trial1" is shown with "Activate","Edit" and "Terminate" options|    |
| 10 | In the same screen, click the "Activate" button | A message "Observation Event "trial1" is activated" and status of the OE is changed to "ACTIVE" in database  |    |
| 11 | In the same screen, click the "Edit" button | Page is redirected to edit page. Form in the edit page should be already chosen the current configurations of the OE |    |
| 12 | In the same screen, name the OE as "trial2" click the "Edit" button | A message "Your event is edited" and the event is edited in database  |    |
| 13 | Go to "My Observation Events" screen, search "tri" and click the "Terminate" button of trial2 | A message "Selected OE is terminated" and its status in database is changed to "TERMINATED" |  |
| . | . | . | . |

## TEST SCENARIO FOR OBSERVER(WEBSITE) ##
Description : Validation of the observer's website requirements
|Case | Action and Data | Expected Result | OK |
|:----|:----------------|:----------------|:---|
| 1 | Login to the system using "mustafa.sancar.koyunlu@gmail.com" account. | In the home page, there should be a search field  |  |
| 2 | In the home page of the user, search "gezi"  | There should be a list of OEs which have "gezi" in its name or in its description or in its tag |  |
| 3 | Select an OE from list which you are not chosen to participate | The page should redirected to an "observation event" page, |  |
| 4 | In the "observation event" page, try to write a comment under an observation | There is no comment text box |  |
| 5 | Select an OE from list which you can participate | The page should redirected to an "observation event" page |  |
| 6 | In the "observation event" page, try to write a comment under an observation | A message, "Done" and comment is written to database |  |
| 7 | In the "observation event" page, click the "upload" button | ? |  |
| 8 | In the "observation event" page, add tag to an observation | The tag should be saved to database |  |
| 9 | In the "observation event" page, the "delete" button of one of the observations(only the observations that user added by himself/herself should have delete button)| The observation is deleted from the database |  |
| . | . | . | . |

## TEST SCENARIO FOR OBSERVER (MA) ##
| 1 | Start the mobile application  | Login page is appeared | |
|:--|:------------------------------|:-----------------------|:|
| 2 | Give credentials username "oguz.demir@boun.edu.tr" with password "drowssap" | Home page is appeared |  |
| 3 | Search an Observation Event "Bogazici" from searchbox | A "Bogazici University" OE is displayed. |
| 4 | Click "Upload" | A screen is displayed that prompts user to select type of the data |  |
| 5 | Click the type "Photos" from the list | The "Files" page of the mobile phone is opened |  |
| 6 | Select the observation file "eta.jpeg" and click OK | File name is printed to screen and upload progress bar is shown and loading. After progress is over successfully, the system messages user: "Upload is successful". If there is a failure in progress, the system gives an error: "Upload failed" |  |

## TEST SCENARIO FOR CONSUMER(WEBSITE) ##
Description : Validation of the consumer's website requirements
|Case | Action and Data | Expected Result | OK |
|:----|:----------------|:----------------|:---|
| 1 | Login to the system using "oguz.demir@boun.edu.tr" account. | A welcome message, "Welcome to MOb, Oguz!" should be encountered | . |
| 2 | Search "University" keyword with usage of search text field. | The relevant observation events are listed  | . |
| 3 | Sort search results according to date | The results are sorted in relevant order |  |
| 4 | Click an observation event from list | Explanation of OE is shown together with preestablished observation types. |  |
| 5 | Click an observation type "Textual Observation" | Textual Observations are listed |  |
| 6 | Go back and click an observation type "Photos" | Photos are listed |  |
| 7 | Click a photo in "Photos" section | Photo info is seen |  |
| 8 | Click "give a score" | The rating scale is shown |  |
| 9 | Give a score and submit | The rating scale is hidden |  |
| 10 | Click on the sender of the photo "Oguz Demirci" | Profile of "Oguz Demirci" is opened |  |
| 11 | Click "Send a Message" | A textbox is opened |  |
|12 | Write a message and Click "Send" | A message, "Your message is sent to Oguz Demirci" is shown |  |