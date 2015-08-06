Image Resizer
=============

A simple image resizer.

Usage
-----
The `image_resizer` shell script is located in `src/main/scripts`. It requires `image-resizer-1.0-SNAPSHOT.jar` to execute which is built through Maven.

1. First we need to set the Maven home
```
export M2_HOME=/path/to/maven
export PATH=$PATH:$M2_HOME/bin
```
2. Next, we need to build the project with Maven
```
mvn clean install
```
3. This will place the script and the jar file into target
```
cd target
```
4. We can execute the script here
```
./image_resizer the_file.jpg
```

Design
-------
