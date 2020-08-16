#Introduction

Hearthstone game mocked in java, an educational project for AP course, Sharif University of technology, Spring 2020.


## Resources
Game logic and arts:
https://hearthstone.gamepedia.com/Hearthstone_Wiki

Logging (Log4j2): https://logging.apache.org/log4j/2.x/manual/

Graphics (Swing): https://docs.oracle.com/javase/tutorial/uiswing/

Json serialization using Gson: https://futurestud.io/tutorials/gson-getting-started-with-java-json-serialization-deserialization
## Structure
Following a pattern similar to MVC, here model, view and controller are separate.
Therefore, we have 3 main packages: logic, model and UI, plus some utility packages,
 such as: logging, interfaces and utils.
 DisplayAdmin and the other Mapper classes (currently in UI package) build and manage the communication
 between logic ad UI, if we are to build our application on network basis, 
 Mappers should be on the server-side, talking to the user interface on the client-side
 using String API.
 
 It's also important to note that Mappers, as the only ways to access the UI, have no
 direct access to data, nor the model classes. But they have access only to logic classes,
 which are named by a "Manager" postfix, such as PlayerManager, or GameManager.


The project developed in 3 phases, which were
exerted as 3 branches from head. The firsy phase is all about basic
data models (such as Card, Player and Hero) and uses CLI to interact
with user at simple operations. The Second phase was all about
graphic user interface using swing, developing collection and shop in the game
and also some simple logic of the gameplay. The third phase was about cards action
and a full gameplay, but without a real opponent.
It's also notable to say that the project is half-completed.
##External Libraries
Gson (2.8.6) 

Log4j (2.13.3)
```

مستند پروژه به فارسی:

https://docs.google.com/document/d/1cS3lvVdjlTnfs-VKhRar4KLApg5sg4uHGBBGOh6ufzI/edit?usp=sharing