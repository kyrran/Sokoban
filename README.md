## Tested on Windows 10, Maven Build Script, Java 14, JavaFX 15

## Git
###### Finished
* Committed regularly with good commit messages
* Created new branches and merged some of them, to make clear structure
* Created Tags, to mark specific version
###### Not Finished
* .gitignore
## Refactoring
###### Finished
* Added **JavaDoc** (including getters & setters) for classes and methods, to describe functionality.
    * Path is /BestSokobanEverV6/doc/index.html_
* Created **Hierarchy package** structures and source folders to organise project
* Basic initial **maintainance**:
    * Followed Bob's **coding convention** to make code more readable
    * **Renamed classes** to more descriptive names, e.g. StartMeUp -> CurrentStatus
    * Created **new classes**, e.g.
        * StartController, ModelStartScreen, 
        * ModelMainScreen, MainController, 
        * MVCpopup, ModelHighScore, HighScoreController, 
        * UndoMovement, 
        * Red, Beige, Blue, DefaultColor, Color, ColorFactory,       
        * WavPlayer, Mp4Player, InterfaceMediaPlayer, ConcreteMediaPlayer AdvancedMediaPlayerInterface, AdapterMedia
        * Container, Iterator
    * Didn't change class name but **modified class** to make function work
        * GraphicObject, GameLogger, Main, Level, GameGrid
    * Replaced if statement with **switch statement**, to improve readability
    * Removed hardcoded value and defined them as **final variable**, to improve reusability
* **Split classes** to make class more organised, e.g. ProcessFiletoGetContent & MusicClass from StartMeUp, Dialog from main
* Used **Design Pattern** to enhance maintainability
    * **MVC Pattern** in StartScreen, MainScreen and HighScore, to easily update the program
        * created model class to contains not UI elements
        * All UI elements are in controller
        * .fxml as view
        * Another function as Main or MVCPopup to run MVC pattern
    * **Singleton** in MusicClass class, to create only one instance
    * **Factory pattern** to change crate color to red, beige, blue and yellow (default color), to create different game objects
    * **Iterator pattern** in Level and GameGrid classes, to print out them easier
* Used **Junit tests** for some classes, to avoid breaking desired functionality
* Converted project to **Maven** project to help with comprehension
###### Not Finished
* Didn't do observer pattern
## Additions
* Created **start screen** 
    * to enter player's name
    * to change wall color
    * Added a button on start screen to access game
* Created **permanent high-score** list based on level number, steps and the time when you finished this level
    * 5 lists for different levels
    * a button here to proceed to next level
    * shows current player's name
* Changed game characters to pictures using **sprites**
    * 4 wall colors
    * 4 crate colors
    * 4 directions of keeper
    * created new game object keeper_on_diamond
* **Additional Layout**:
    * added extra menu to change crate color
    * press SHIFT to play game instruction video
    * press any wrong key to play meow sound
* **Achieved all default functions** 
    * **toggle music** to play and stop music
    * **undo** to undo previous step
        * steps minus 1
    * **reset level** to reset to original status
        * steps clears to 0
        * deep copy level constructor to save its original status
    * **save** all afterwards levels from the current level with original level index, level steps, and total previous steps if you have completed at least one level
    * **load** game count steps from where you saved and remain original index
###### Not Finished
* Just did Junit Test for some classes









