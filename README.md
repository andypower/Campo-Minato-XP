Campo-Minato-XP
===============

Maven version of the educational mines game in Java Swing. 

To test it is necessary to install in the local repository the jar inside the "ant lib" folder using the following command:
mvn install:install-file -Dfile=ping-1.1-build0.jar -DgroupId=it.unibas -DartifactId=ping -Dversion=1.1-build0 -Dpackaging=jar

After this operation is possible to launch a build: mvn clean install

At the end execute from target dir: .\target>java -jar campominato-1.0-SNAPSHOT.jar

Enjoy it!
