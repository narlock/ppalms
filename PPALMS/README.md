# **PPALMS v0.1**
[Parson’s problems](https://en.wikipedia.org/wiki/Parsons_problems) are a form of assessment which differ from the typical code writing form of question on a typical exam in an introductory computer science class. The purpose of this application is to allow users to create a Parson's problem and export that problem to their LMS of choice. In version 0.1, the application supports only *ordering* problems.

This is the first version of the PPALMS application. This document provides information related to instructing users to run the program and how the program is built and executed.

The PPALMS application utilizes the json-simple JAR file provided by the [Google Code Archive](https://code.google.com/archive/p/json-simple/downloads).

<hr>

## **Group Roster and Contributions**

| Member Name  | x500 | GitHub | Contribution | 
| ------------- | ------------- | ------------- |------------- |
| Anthony Narlock | narlo041  | [narlock](https://github.com/narlock) | UI Design & Development, MVC Developer, Code Documentation, Tester, Test Documentation, README Creator, End-to-end Tester, End-to-end Test Documentation. |
| Stephanie Ye | ye000080  | [Ye000080](https://github.com/Ye000080) | Tester, Test Documentation. |
| Jaden Rodriguez | rodr0571 | [YaBoiSkinnyP](https://github.com/yaboiskinnyp) | Logic and Export Developer, Code Documentation, Tester, Test Documentation. | 
| Shen Lua | lua00005 | [cshenlua](https://github.com/cshenlua) | Tester, Test Documentation. |

<hr>

## **How to Build/Execute the Program**

### **Java**
To run the PPALMS application, you must be on a device that supports Java Runtime Environment version 8 or higher. It is trivial to note that Java applications run on computers that have popular operating systems like Windows, Linux, and macOS. This document assumes that the user of this application is running the PPALMS application in an operating system that supports Java version 8 or higher. This document also makes the assumption that the user has external peripherals to make use of the PPALMS application like a monitor for displaying the application, a keyboard for processing user key inputs, and a mouse for selecing and clicking buttons and combo boxes on the user interface. The figure below provides a visual representation of how to check if you have Java installed on your computer. In a command line, type the following command.

```
java --version
```

If you have Java installed on your machine, you may see something similar to the following:

<center>
    <img src="./readmeAssets/javaVersion.png" width=70%>
</center>

If you do not see something similar to the image above, you can click [this link](http://java.com/) to download Java. This document assumes that the user is able to figure out how to install an application on their computer, so instructions on installing Java will not be mentioned.

<hr>

### **Running the application**
Now that you have verified that you have an installation of Java (version 8 or higher), you may now proceed to run the application. The application is in the form of a Executable JAR file titled `PPALMS.jar`. The user can simply run this application using the Java Runtime Environment. The process is the same for any operating system. The following image displays the application on a user's desktop and it running.

<center>
    <img src="./readmeAssets/javaRunning.png" width=50%>
</center>


| ❗️ **Java-Specific Application Requirements** |
| ------|
| It is important that the user of this application reads this section to verify they have their system's environment setup to execute Java applications. If the user cannot figure out how to run a Java application, or an issue using the application related to Java permissions occurs, here are some common issues that inexperienced users may find useful for troubleshooting run issues.|

##### **macOS**
- Due to the nature of Apple's macOS, there are some permissions that are not satisfied by default Java. A common issue that user's may run in on macOS operating system is that their selected code file is located in a folder that the PPALMS application does not have access. This issue will also cause the application to not properly export the problem because the program has not been given permissions to use the file system.

The user can solve this issue by following one of the following options:
1. Move the code file and `PPALMS.jar` executable file to the user's home directory.
2. Run the jar file in the terminal
    - To run the PPALMS application in the terminal, you will need to open your terminal and locate your terminal in the directory where `PPALMS.jar` exists in your file system. You can check if `PPALMS.jar` is in your file system using the `ls` command.
    - When you have correctly navigated to the correct directory, you can run the application using `java -jar PPALMS.jar`. This will launch the application and give access to all folders on the user's disk.

##### **Linux**
- Due to the nature of most distributions of the Linux operating system, the user will need to give executable access to `PPALMS.jar` in order to execute the application. Simply right-click the `PPALMS.jar` file and navigate to properties and check the box that allows the file to be executed. The user will now be able to execute the application.

The remainder of this document will assume the user has granted permissions to their Java environment to execute programs.

<hr>

### **Using the PPALMS Application**
*__Please Note__: This is not the end-to-end test of the PPALMS application but an approach that will assist users in operating the application.*

To begin using the application, the user can click on the "Select File" on the user interface. This will send a signal from the controller to open the file chooser dialog menu. From here, the user can select their source code file to upload. Please note, the input file must be a supported programming file type. As of version 0.0.1 of the PPALMS application, the file extensions `.java`, `.py`, `.c`, `.cpp`, and `.cc` are supported. The following image shows the user selecting a file.

<center>
    <img src="./readmeAssets/javaOpenFile.png" width=50%>
</center>

Once the file is selected, the view will change to indicate that the user can input their target LMS and problem type. As already stated, version 0.1 only provides the *ordering* option to the user. The following image shows a sample form filled out. After the form is filled out, the user will be able to confirm their selections by clicking the confirm button. This will change the view to allow the user to input specific information for their problem.

<center>
    <img src="./readmeAssets/javaLmsInfo.png" width=50%>
</center>

On the following image below, we can see the user has entered a title, description, and selected annotations (in the case of the ordering problem, the user can click on the lines of code they wish to include in their problem) from their source code. The user can now hit the export button which will generate a JSON file of their created problem.

<center>
    <img src="./readmeAssets/javaProblemForm.png" width=50%>
</center>

It is important to note that the current version of PPALMS will export this file to the same directory as the location of the JAR file. That is, if the path of the jar file is `User/Desktop/PPALMS.jar`, the file that will be exported will be `User/Desktop/problem.json`.