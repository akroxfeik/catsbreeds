# Cats

Here's a brief explanation of the architecture:

1. **Language**: The app is written in Kotlin.

2. **Dependency Injection**: Dagger Hilt is used for dependency injection. It simplifies the process of managing dependencies in the app, making the code easier to test and maintain.

3. **Networking**: Retrofit is used for HTTP requests, along with OkHttp for efficient network operations. Moshi is used for JSON serialization/deserialization.

4. **Coroutines**: Kotlin Coroutines are used for handling asynchronous tasks, such as network requests.


6. **Navigation**: The app uses the Navigation component from Jetpack for navigating between different screens in the app.

7. **Image Loading**: Coil is used for image loading. It's a Kotlin-first image loading library that's lightweight and easy to use.

The architecture tries to follow the principles of separation of concerns and modularity, making the code easier to maintain and possibly test.


## Strategies

Well in the last weeks i've been obsessing about Hilt so i've tried using it with Jetpack Compose.

Eventually i could grasp the concept and understand the workflow (never thought i would miss Android Lifecycle - just kidding, it's amazing Compose State Flow / Flow State, whatever :P ).

Everytime something clicked, i had to come back and refactor everything. I refactored alot... and i believe it still has space for a better approach.

My main focus was setup MVVM with Hilt facilities and then understand how things really worked with Jetpack Compose. 

The Jetpack Navigation, became my second focus when i've realised how powerful this setup could be for big projects. Really neat!


## Requirements
1. A screen with a list of cat breeds **(Okayish)**
2. The cat breeds screen should contain a search bar to filter the list by breed name. **(Sorry)**
3. The cat breeds screen should contain a button to mark the breed as favourite. **(Yes but... Room impl is really needed so No.)**
4. Implement a new screen to show the breeds marked as favourites. **(Okayish)**
a. Show the average lifespan of all the favourite breeds (you can use either
the lower or the higher value in the range). **(Okay)**
5. Implement a screen with a detailed view of a breed **(Okay)**
6. In order to navigate between the different screens use a Jetpack Navigation
Component. **(Okay)**
7. Pressing on one of the list elements (be it in whatever screen) it should open the detailed view of a breed. **(Okay)**


## Technical Requirements
* MVVM architecture **(Okayish)**
* Usage of Jetpack Compose for UI building **(Okay)**
* Unit test coverage **(Sorry)**
* Offline functionality (i.e: consider using Room for data persistence) **(Sorry)**
* Error Handling **(Okay)**
* Pagination for the list of cat breeds **(Sorry)**
* Modular design **(Okayish)**
* Integration and E2e tests **(Sorry)**
