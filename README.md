# README

### Coding Challenge
The challenge took me an hour.

### Description

This app utilizes a Unidirectional MVVM design pattern that leverages the reactive nature of LiveData
to propagate changes from the ViewModel to the View. This unidirectional flow ensures that there's a
clear separation of concerns between the View and the ViewModel, making it easier to write unit tests
and maintain the codebase.

The following is very high level overview of this pattern:

-----------------             -----------------              -----------------
|   GuideModel  |             |  GuidesActivity|              | GuidesViewModel|
-----------------             -----------------              -----------------
| - Data        | ----->      | - Layout       |  <---------> | - LiveData       |
|               |             |                |              | - Business Logic |
-----------------             -----------------              -----------------

In this flow, the GuidesActivity triggers an input event, ScreenLaunched, which is communicated to
the GuidesViewModel. The GuidesViewModel processes this input by fetching a list of guides from a
remote source (UpcomingGuideRepository). This repository interacts with the UpcomingGuideService
to retrieve the data. Upon consuming the response, the data is mapped to a domain model, GuideModel.
Subsequently, the GuidesViewModel forwards this GuideModel to the GuidesActivity for presentation.

### Improvements Given More Time

With additional time, I would have incorporated a local data store using RoomDB or SQLite database.
This would enable users to utilize the app offline.

### Testing

Quickly wrote unit tests for the GuidesViewModel, verifying that the ViewModel accurately delivers 
the correct state in response to the provided input.

Given more time, I would have expanded the testing suite to include comprehensive end-to-end tests 
for the ViewModel. These tests would assess the mapping functionality for the responses received 
from the UpcomingGuideService. This could be accomplished by mocking the UpcomingGuideService to 
provide predefined responses, allowing the mapper to parse them and verify the accuracy of the mapping process.
