### Prolific Library

This project shows a proof of concept for an Android library application for the prolific reader.

Users can use the app to discover new books, curate a list of books, and associate notes with books that are currently checked out.


### Running the app

The application uses very basic information hiding. The Config class exposes the encoded api keys and returns the decoded value. Clone and open with Android Studio. Feel free to replace the return value of the getter methods in the Config.java file to match your own api keys. Otherwise, you can also run this as-is using the encoded/decoded values.

### Features

Api consumption from Prolific Library,  IDreamBooks API, and NYTimes Api

Check out a book or return by selecting it in the Library tab or long-clicking on it in the Library Tab.

The Library Tab displays all content from the Prolific Api.

The explore tab allows for discovering new books from NY Times' best sellers list and other lists. These books can in turn be added to the Prolific Library tab.

All Prolific books that are currently checked out are available in the Notes tab, which contains a nested recycler view backed by a Sql Lite database.

The detail page utilizes the IDreamBooks Api to query the selected book for recommendation and rating information.

## What I would do with more time

- Caching - This application is a bit data intense, as it uses a number of Api's to further the user's experience.

- Dependency management was a challenge for this project. I used a global scope in my Application to keep track of the current PresenterActivity and context, but would like to explore Dagger for this end goal.

- More integrity checks on the Database system. Ex: The Notes Tab is only pertinent to books that are currently checked out in the Prolific api, but books that have since been deleted from the api will be retained in the Notes database. A better approach may be to use one database table for all books ever checked out, and another for the notes related to each of said books.

- Acceptance testing and Performance testing


## Networking and Tools I found helpful

- [JsonSchema2Pojo](jsonschema2pojo.org)
- [Retrofit](https://square.github.io/retrofit/)
- [Tango Material intro Screen](https://github.com/TangoAgency/material-intro-screen)
- [Blur Dialog Fragment](https://github.com/tvbarthel/BlurDialogFragment)
- [Stetho](http://facebook.github.io/stetho/)


 Rx Java would have been helpful with concurrent networking work, but was not vital to this project.


Made with love by Abass Bayo-Awoyemi
