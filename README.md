[![Build Status](https://travis-ci.org/gabrielcedran/coding-puzzle.svg?branch=master)](https://travis-ci.org/gabrielcedran/coding-puzzle)

# For players:
### History
Freak Island is a game where you meet creatures that you never thought before. It a place to explore, battle against huge and scary creatures and earn experience to become more and more strong.

##### Running the game:
After [downloading](https://github.com/gabrielcedran/coding-puzzle/raw/master/runnable/freak-island.jar) the game, open your terminal in the folder where the freak-island.jar is. Type java -jar freak-island.jar and ENTER.

#### Selecting an option
This game runs in turns. Every time you are asked for an action you have to type your option e press ENTER. If you type an invalid option it will be discarded and you will be asked again. It's important to say that the game is case insensitive therefore if you are asked to choose between A or B and you type A or a it makes no different, but if you type aa or Aa or AA, it will be considered an invalid entrance.

##### Cautions to not lose your saved game
1. First of all, this game auto saves itself and it creates a file called character.saved. Do not delete this file or you will lose all your progress
2. You can only have one saved progress per time. It means that if you starts a new game you will lose the previous saved data


# For developers:

##### Creating new Scenarios:
This game runs in rounds and each user action leads to the next scenario. If you want to create a new scenario you just have to create a new class that extends the Scenario class and implement the "execute" method. Execute method must return a Scenario that will be run next. Not returning a next scenario finishes the game.
After your scenario is done you just have to determine when it should be executed, so change any flow you want and return a instance of your new scenario.

##### Creating new Characters:
Nowadays, there is only one option of character therefore it is automatically picked up and it wasn't necessary to create a scenario that would enable the user to choose between one option. If you want to add more options you have to create a scenario between menu and build profile use cases that enables the user to select which character he wants (see the section [Creating new Scenarios](https://github.com/gabrielcedran/coding-puzzle#creating-new-scenarios)). To create new profile, just follow these steps:
1. Create a new class that extends the Character class
2. Override the abstract method getDrawing (that prints the character's picture)
3. Change the CharacterFactory to consider the new character (increase the nextInt function)

##### Creating new Monsters:
1. Create a new class that extends the Monster class
2. Override the abstract methods
3. Change the MonsterFactory to consider the new monster (increase the nextInt function)
```java
    public Monster getMonster() {
        Integer monsterNumber = random.nextInt(2);
        Monster monster = null;
        if (monsterNumber == 0) {
            monster = new Dragon();
        } else if(monsterNumber == 2) {
            monster = new RedDragon();
        }
        return monster;
    }
```

##### Changing monsters battle engine:
If you want to change de default battle engine or even if you want to change the engine a specific monster, you have just have to follow these steps:
1. Create a class that extends the DamageEngine abstract class
2. Override the method calculate
3. Choose when this new engine should be used in the DamageEngineFactory:
```java
    public DamageEngine getInstance(Monster monster) {
        if (monster instanceof Dragon) {
            new DragonDamageEngine(this.character, this.monster, this.random, this.output);
        }
        return new DefaultDamage(this.character, this.monster, this.random, this.output);
    }
```
4. In this context you have available the character, the monster and the Action chose by the user. You can implement your own rules to calculate damages and behaviors. If you want to enable new Actions, see the section [Creating new Actions](https://github.com/gabrielcedran/coding-puzzle#creating-new-actions), but be aware to not break any legacy engines.

##### Creating new Text Colors:
Add a new entrance to the enum "TextColors". The first parameter should be the [color](http://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#16-colors), the second parameter should be a number that represents this color and the third the description.
When you add a new color, it is automatically shown in the build profile scene, where the user is asked to pick one color (if you leave the description null it won't be displayed).

##### Creating new Actions:
Add a new entrance to the enum "Actions". The first parameter is the name of the action and the second is the key that represents that action.
When you add a new action, it is automatically shown in the battle scene.
In the current mechanism, doesn't matter the action selected, it will always have the same behavior. If you want this action to cause something different during a battle, you have to implement it's behaviour in the method calculate of the DamageEngine - see the section [Changing monsters battle engine](https://github.com/gabrielcedran/coding-puzzle#changing-monsters-battle-engine).

##### Architecture
This project tries to follow [the clean architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) therefore its layers are splitted in three tiers:
1. Models
2. Use Cases
3. Gateways

###### Model
Contains the strong entities of the system like Character, Monsters, etc

###### Use Case
Contains the rules of the system. It is supposed to build new characters, start battles, validate options, etc

###### Gateways
Communications between the system and outside world. It does matter to the business if the input of an user is being made through keybord, mouse or controller or even if it's through console terminal or web page. Everything that does not matter to the business itself should be in this layer

##### Automated tests
To test the use cases, it's being used [spock frameowork](http://spockframework.org/). It is a testing and specification framework that assists building tests using expressive language that provides maintainability through BDD style notation.

To the gateways tests, it's being used traditional junit with mockito and hamcrest. After seeing the benefits that spock framework brought to the maintainability through its bdd style notation, it was defined that unit tests are also supposed to be broken in three parts: GIVEN, WHEN and THEN. Although junit does not provide this kind of notation, you are supposed to write them as comments.
It will help you to write better tests and it will help you to figure out what exactly you are testing. Others in the future will be very thank when they easily understand what that unit test was made for.

*After changing the project, don't forget to make sure that your code is properly covered with automated tests. Nowadays, the coverage is higher than 95%!*


##### Build:
For every commit one build is run. If it is succeeded, the runnable jar is automated updated and a new version of the game is released.
