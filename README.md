# GRIPTest
Wrapper project for auto-generated Java code from GRIP to form a fully-functioning stand-alone program on a Windows PC.

Notes:
* GripPipeline.java is the auto-generated Java file from GRIP.  It has been modified as described by the TODO tags in the file.
* Download OpenCV from https://sourceforge.net/projects/opencvlibrary/files/4.6.0/opencv-4.6.0-vc14_vc15.exe/download.
* Unzip OpenCV.
* Copy OpenCV jar file to lib/ folder.
* Update .classpath (in project root directory) to point the current version of the OpenCV jar file.
* Update path to OpenCV dll in GripPipeline.java.
* To run the App, right-click on App.java and select "Run Java".  When the image windows appear, press any key to step through images.
* The program needs to be terminated before re-running.  Go to the Terminal windows (bottom panel in VSCode) and press ctrl-C to terminate the program.
 