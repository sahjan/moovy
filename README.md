# Moovy
Moovy is a web application that gives movie suggestions based on the user's mood. Sliders indicate two moods.
Upon moving the sliders, the application returns the movies which are closest to the user's currently selected mood.

## How to run
This is a Spring Boot Maven application. Extract all the files from the zip file, and import as a Maven project into any IDE.
Run it using the Maven command spring-boot:run, or directly from the MoovyApplication class, which contains the main method.
When you run the application, it will run on port 9000. The application can be accessed from http://localhost:9000/index.html.
Upload the data file movieData.xml (in src\main\resources\static\data) to start using Moovy.
Best viewed in Google Chrome.

## To do
- [x] Initial commit
#### Application
- [x] Basic functionality
- [ ] Clean up HTML header/nav/footer styling
- [ ] UI
##### UX/UI
- [ ] Wireframes
- [ ] Logo
- [ ] Prototypes
#### Readme
- [x] Description
- [x] How to run