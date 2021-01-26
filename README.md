## Restaurant Finder Sample Application

### Building and Running

This is a standard single-module Android app. You should open the project in Android Studio by selecting the root-level `build.gradle` file. Once fully loaded You should just be able to click the "Run app" button at the top.

To run the unit tests, just run this from the command line:

```
./gradlew test
```

### Architecture

The application is implemented utilizing MVVM and a "Clean architecture" separation of concerns with unidirectional data flow. Each layer in the dependency hierarchy has a role:

- Fragments are the top level of the dependency hierarchy and are solely responsible for rendering view state and responding to user interaction.
- The ViewModel is the fist layer of abstraction from the UI. Its job is to broker commands to the relevant business logic, obtain results, and provide resulting view state to the fragment.
- Use case classes represent the "Business logic" of the app.
- Repository classes consume data sources.

Tests are implemented with the Spek 2 framework using the Gherkin syntax. I greatly prefer Spek and Gherkin as it forces me to write unit tests the way I think about them.

### 3rd Party Libraries

A number of libraries were used in this project:

- Hilt for dependency injection
- Retrofit for API consumption
- GSON for serialization
- Spek 2 for unit test implementation
- Mockito-kotlin 2 for useful testing methods