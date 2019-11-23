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

Advertisement link for our Go application: <br> 
https://www.youtube.com/watch?v=v47HjLz63co&feature=youtu.be


## Design

![Top Level Diagram](/design/TopLeveldiagram.png)

## Design Notes
### Model View Controller (MVC)
We used the Model View Controller architecture for this project.  The Controller basically retrieves information regarding where the user clicks on the screen. As the user clicks the screen multiple times to set a piece on the board, this is a value that is constantly changing.  The model then gets this information, and passes it to the view.  Thus the view, or the "UI", is also constantly changing accordingly with the values that it is getting from the model.  
