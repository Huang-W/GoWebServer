# GO

The Final Frontier


## Team

1. [Gabriel Hartman](https://github.com/ghartman1620)
2. [Pallavi Chaturvedi](https://github.com/pallavichaturvedi)
3. [Parvathy Kannankumarath Madom Krishnan](https://github.com/parvathysjsu)
4. [Ward Huang](https://github.com/Huang-W)
5. [Yash Sahani](https://github.com/ysahani)


## User Story Advertisement

[Advertisement link for our Go application](https://www.youtube.com/watch?v=v47HjLz63co&feature=youtu.be)

## Project Description

* [Game Rules](https://senseis.xmp.net/?BasicRulesOfGo) 

* [Project Repository](https://github.com/nguyensjsu/fa19-202-underwater-softball) 

<<<<<<< HEAD
![WelcomeScreen](/readme/WelcomeScreen.png)
=======
* [Web App](http://underwater-softball-go.com/) - [Mirror 1](http://go-underwater-softball.net/) - [Mirror 2](http://gabrielhart.webfactional.com/)

![WelcomeScreen](WelcomeScreen.png)
>>>>>>> 0fd331a099ace675cbcc3516c4b376a8bb16b12f

![](/readme/ConfigStart-min.gif)

![](/readme/GoGame-min.gif)

## Design

#### Top Level Diagram
![Top Level Diagram](/design/1-TopLevelDiagram.png)

#### Adapter Diagram
![Adapter Diagram](/design/2-GoAdapterDiagram.png)

#### Model Diagram
![Model Diagram](/design/3-GoModelDiagram.png)

#### View Diagram
![View Diagram](/design/4-GoViewDiagram.png)

## Key Features
* Interactable user interface
* Single-Player GO Board with complete capture logic.
* Support for multiple Scoring Strategies (defaulted to SimpleStrategy)
* Memento pattern + Undo Button to rewind the game.
* Supports multiple board sizes (9, 13, 19)
<<<<<<< HEAD
=======
* Web-App support for single-player and two-player
>>>>>>> 0fd331a099ace675cbcc3516c4b376a8bb16b12f

## Design Notes

### Model View Controller (MVC)
We used the Model View Controller architecture for this project.  The Controller basically retrieves information regarding where the user clicks on the screen. As the user clicks the screen multiple times to set a piece on the board, this is a value that is constantly changing.  The model then gets this information, and passes it to the view.  Thus the view, or the "UI", is also constantly changing accordingly with the values that it is getting from the model.  

### Adapter
We implemented the Adapter Design Pattern by including ModelViewAdapter and ViewModelAdapter classes in the project. The ModelViewAdapter translates the game coordinates(For Ex. (1,1)) from the game model into pixel coordinates(For Ex. (280,350)) for the game view. Whereas, the ViewModelAdapter class translates mouse clicks(For Ex. (280,350)) sent from the GoView screens into the game coordinates(For Ex. (1,1)) that can be used by the game model. Moreover, This class forwards pass, undo and the game configuration requests to the game model.

### Composite
The Ccmposite pattern was used for the main menu screen.  In this menu screen, there is a "Quick Start" button, and a "Config Start" button.  Both of these buttons were added each as a JButton, as we implemented this project in Java Swing.  These two buttons were then added to a single JPanel.  As you can see, a tree like structure of nodes were created, with the panel as acting like the "tree" and the buttons as "subcomponents."  This structure implies that the composite pattern was used.

### Observer
We Implemented Observer Design Pattern in the Model as well as View package of the project.

#### In Model package 

* GoGameObserver is an interface for objects that listen to when the Go game ends. Implementations can register themselves to   be notified at {@link GoGameSubject}

* GoModelConfigObserver is an interface for objects that listen to changes in the Go Game configuration. Implementations         can register themselves to be notified at {@link GoGameSubject}

* GoMoveObserver is an interface for objects that listen to Go Move events. Implementations can register themselves to be       notified at {@link GoGameSubject}

* GoGameSubject is a subject that notifies its observers of when interesting events happen in a game of Go. A subject notifies   a set of observers when a move is made or a piece is captured, and notifies a different set of observers when the game ends   and a winner is decided.
 
#### In View package

* GoScreenObserver is an interface for objects that listen to the User's Input. Implementations can register themselves to be   notified at {@link GoGameSubject}

* GoViewConfigObserver is an interface for objects that listen to when the Go game changes configuration. Implementations can   register themselves to be notified at {@link GoGameSubject}

* GoViewObserver is an interface for objects that listen to Go UI events. Implementations can register themselves to be         notified at {@link GoViewSubject}

* GoScreenSubject is a subject that notifies its observers of when interesting events happen in the screen of a game of Go. A   subject notifies a set of observers when the screen is clicked or when a button is clicked.

* GoViewConfigSubject is a subject that notifies its observers when the GoGame is being configured and when the BoardSize has   changed

* GoViewSubject is a subject that notifies its observers of user inputs in a game of Go and about the event when the screen is   clicked or when a button is clicked.
 

### Undo
We used a variant of Memento to implement undo functionality. In Memento, undo operations are implemented by remembering the state of the object before a change. We chose to instead keep an artifact of the change itself - so rather than returning the object to its previous state before a change to undo, we simply do the reverse of the change. Our undo behaves somewhat like version control - we keep a history of all the changes that were made to the game state to be able to undo them, rather than keeping track of the state after every change.

### Scoring
In Go, there are varying rulesets that describe how game boards are to be counted. We used a Strategy to encapsulate the part of this logic - the scoring alogithm - that varies. This lets us be resilient to future opportunities for change, and we could easily add in other scoring rulesets.

### Model and Controller extensibility and their application in the webapp vs the desktop app
One of the goals of our design was that that the model and controller layer would be able to be reused by multiple different view layers. We demonstrated this ability to change by creating two views for our project: one a desktop app in Swing and the other a webapp using P5JS and websockets. The webapp and desktop app are able to leverage the parts of the model functionality that are the same--the gameplay logic and scoring--while encapsualting the parts that vary. 

### Webapp View Layer
The neat and exciting bit of design going on in the webapp view layer is our use of the State pattern. In the webpage, there are 5 actions that result in different output and model changes based on the state of a given client. We maintain a map of websockets to state machines. With this map, we can access the state of each client when we receive a message from that client and decide what action to take from their input based on the state. GoWebSocketState describes the interface the client interacts with and its implementations describe how to handle each of these points of interaction based on the client's state.

We use these states to neatly implement multiplayer - by associating two state machines and having them make changes to one another as the two users make actions we can force the two clients to wait for one another's moves and make requests to the controller layer that use the existing observer design to report the model changes to both clients. 


## Individual Contributions

![Sprint Sheet](/readme/sprint_sheet.png)
