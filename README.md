![The Green Machine Logo](http://edinarobotics.com/sites/all/themes/greenmachine/assets/images/Logo.gif)

#Scouting-Definitions
This is the repository for the API definitions for FRC Team 1816's plugin-based scouting program. If you are implementing a plugin it should be built against the APIs defined here.
The goal of this scouting program is to provide a common system for all FRC teams' scouting. The system is plugin based, so all teams are able (and encouraged) to contribute their own plugins to improve the system.

##Building the APIs
Not that these definitions are not complete and are subject to change, but if you want to build them, follow these instructions.
This project is managed by [Maven 3](http://maven.apache.org/), so to build it you will need to install Maven.

1. Install Maven 3. Follow the instructions on Maven 3's [download page](http://maven.apache.org/download.html)
2. Get a copy of this repository, either through git or with GitHub's downloads.
3. In your system's command prompt, navigate to the folder containing this repository.
4. Run ```mvn clean install```

You can now use Maven 3 to build against the contents of this API.
