
Debugging in eclipse when running jetty server
-----------------------------------------------

Open "Run > Debug -> Debug Configuration..." from the menu

# Set Main Class to "org.codehaus.classworlds.Launcher"

Go to the argument tab:
# Set Program arguments to "jetty:run"
# Set VM arguments to "-Xmx512M -Dclassworlds.conf=[MAVEN_HOME]/bin/m2.conf -Dmaven.home=[MAVEN_HOME]"
(Replace MAVEN_HOME with the location of maven on your system)

Go to the classpath tab:
# remove the application from the user entries
# add the "[MAVEN_HOME]/core/boot/classworlds-1.1.jar" to the user entries
# remove maven dependencies

Go to the source tab:
# add the current project to debug

Set "break points" where you like the code to stop

Click "debug" button

Now you can start debugging your application like you do it always

