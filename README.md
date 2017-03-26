# coding-puzzle

# For players:
### History
Freak Island is a game where you meet creatures that you never thought before. It a place to explore, battle against huge and scary creatures and earn experience to become more and more strong.

##### Running the game:
After [downloading](https://github.com/gabrielcedran/coding-puzzle/raw/master/runnable/freak-island.jar) the game, open your terminal in the folder where the freak-island.jar is. Type java -jar freak-island.jar and ENTER.

#### Selecting a option
This game runs in turns. Every time you are asked for an action you have to type your option e press ENTER. If you type an invalid option it will be discarded and you will be asked again. It's important to say that the game is case insensitive therefore if you are asked to chose between A or B and you type A or a it makes no different, but if you type aa or Aa or AA, it will be considered an invalid entrance.

##### Cautions to not lose your saved game
1. First of all, this game auto saves itself and it creates a file called character.saved. Do not delete this file or you will lose all your progress
2. You can only have one saved progress per time. It means that if you starts a new game you will lose the previous saved data


# For developers:

##### Creating new Scenarios:
This game runs in rounds and each user action leads to the next scenario. If you want to create a new scenario you just have to create a new class that extends the Scenario class and implement the "execute" method. Execute method must return a Scenario that will be run next. Not returning a next scenario finishes the game.
After your scenario is done you just have to determine when it should be executed, so change any flow you want and return a instance of your new scenario.

##### Creating new Characters:
Nowadays, there is only one option of character therefore it is automatically picked up and it wasn't necessary to create a scenario that would enable the user to choose between one option. If you want to add more options you have to create a scenario between menu and build profile use cases that enables the user to select which character he wants (see the section "Creating new Scenarios"). To create new profile, just follow these steps:
1. Create a new class that extends the Character class
2. Override the abstract method getDrawing (that prints the character's picture)
3. Change the CharacterFactory to consider the new character (increase the nextInt function)

##### Creating new Monsters:
1. Create a new class that extends the Monster class
2. Override the abstract methods
3. Change the MonsterFactory to consider the new monster (increase the nextInt function)

##### Creating new Text Colors:
Add a new entrance to the enum "TextColors". The first parameter should be the [color](http://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#16-colors) and the second parameter should be a number that represents this color (usually this number is asked to the user when he has to choose between colors).

##### Creating new Actions:
Add a new entrance to the enum "Actions". The first parameter is the name of the action and the second is the key that represents that action.
If you want this action to cause something different during a battle, you have to implement it's behaviour in the method calculateDamage of the Battle use case.


