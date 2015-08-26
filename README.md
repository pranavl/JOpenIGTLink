#JOpenIGTLink

##Synopsis
This project is a [Java implementation](https://code.google.com/p/igtlink4j/) of the [OpenIGTLink protocol](http://openigtlink.org/)
intended for use with the [ARwinss project](https://github.com/pranavl/ARwinss).

##Installation
To use this project as a class library in an Android application, 
it is necessary to compile and add the `.jar` to the Android project's `libs` folder.

**Important:**
Android does not support use of JAR's compiled with Java 1.8. To use this library, the Java source must be compiled using JDK 7.

##Code Example
Two new classes were added to the existing library in the `main` package.

####`JOpenIGTClient`
This class is a sample client using the OpenIGTLink protocol.

**How to use:**
* Enter the key associated with a tracked marker to receive the associated frame transformation
* Enter "PROJ" to receive the projection matrix

####`JOpenIGTServer`
This class is a server using this OpenIGTLink protocol. 
It was primarily used for testing of the client class on a Java platform before use in the Android device.

##API Reference
This [Java implementation](https://code.google.com/p/igtlink4j/) of the [OpenIGTLink protocol](http://openigtlink.org/)
was created by Absynt Technologies (Copyright (c) 2010, Absynt Technologies). 
It is rehosted here from their Google code repository with the addition of two main classes used for testing.