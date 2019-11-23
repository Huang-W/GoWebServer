# GO

The Final Frontier


## Team

1. [Gabriel Hartman](https://github.com/ghartman1620)
2. [Pallavi Chaturvedi](https://github.com/pallavichaturvedi)
3. [Parvathy Kannankumarath Madom Krishnan](https://github.com/parvathysjsu)
4. [Ward Huang](https://github.com/Huang-W)
5. [Yash Sahani](https://github.com/ysahani)


## Project Description

* [Game Rules](https://senseis.xmp.net/?BasicRulesOfGo) 

* [Project Repository](https://github.com/nguyensjsu/fa19-202-underwater-softball) 

## User Story Advertisement

[Advertisement link for our Go application](https://www.youtube.com/watch?v=v47HjLz63co&feature=youtu.be)


## Design

#### Top Level Diagram
![Top Level Diagram](/design/TopLeveldiagram.png)

#### Adapter Diagram
![Adapter Diagram](/design/GoAdapters.png)

#### Model Diagram
![Model Diagram](/design/GoModelDiagram.png)

#### View Diagram
![View Diagram](/design/GoViewDiagram.png)

## Design Notes

### Model View Controller (MVC)
We used the Model View Controller architecture for this project.  The Controller basically retrieves information regarding where the user clicks on the screen. As the user clicks the screen multiple times to set a piece on the board, this is a value that is constantly changing.  The model then gets this information, and passes it to the view.  Thus the view, or the "UI", is also constantly changing accordingly with the values that it is getting from the model.  

### Adapter
We implemented the Adapter Design Pattern by including ModelViewAdapter and ViewModelAdapter classes in the project. The ModelViewAdapter translates the game coordinates(For Ex. (1,1)) from the game model into pixel coordinates(For Ex. (280,350)) for the game view. Whereas, the ViewModelAdapter class translates mouse clicks(For Ex. (280,350)) sent from the GoView screens into the game coordinates(For Ex. (1,1)) that can be used by the game model. Moreover, This class forwards pass, undo and the game configuration requests to the game model.
