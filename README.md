# Covid24
Egdroid course graduation project.
A simple application that delivers real-time Covid 19 global statistics.
USING: FastAndroidNetworking & Room Database.

it follows:
- MVVM architecture pattern.
- Offline first architecture approach.

it supports:
- offline usage (Obviously, Only the most recent cached data will be displayed)
- Portrait/Landscape orientations


NOTE: This was my first approach to code in MVVM and to implement an offline first arch. Thus, bad coding practices may be found as i'm still in the beginner phase.

This diagram may be of use to get a better understanding of my desin.
![UML](https://user-images.githubusercontent.com/51246543/82740655-3d9c8f80-9d4b-11ea-99b0-4345f7dcf3ad.jpeg)
The DataInteractor class acts as a middleware between any viewModel and its corresponding repository. It manipulates recieved data to a form that is ready to be returned to the viewModel and shown on the UI.
Everything else is pretty much straight forward. 
