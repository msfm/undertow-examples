undertow-example-handler: Undertow Handler Example
======================
Author: 
Level: Intermediate
Technologies: Servlet, Undertow
Summary: The `undertow-example-handler` demonstrates an example of Undertow's Handler
Target Product: WildFly, JBoss EAP, Undertow


What is it?
-----------

The `undertow-example-handler` demonstrates an example of Undertow's Handler running on Red Hat JBoss Enterprise Application Platform 7.


System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later. See [Configure Maven for JBoss EAP 7](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP7.md#configure-maven-to-build-and-deploy-the-quickstarts) to make sure you are configured correctly for testing the quickstarts.


Use of EAP7_HOME
---------------

In the following instructions, replace `EAP7_HOME` with the actual path to your JBoss EAP installation. The installation path is described in detail here: [Use of EAP7_HOME and JBOSS_HOME Variables](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP7_HOME.md#use-of-eap_home-and-jboss_home-variables).


Build
-------------------------

To buid this example 

1. Open a command prompt and navigate to the root of the undertow-example-handler directory:

        cd ./undertow-example-handler

2. Type the following command to compile and generate jar named undertow-example-handler.jar:

        mvn clean package


Configure
---------------------------

For your convenience, an example batch script `configure-undertow-handler.cli` is provided in the root directory of this example. 

The script will do the following:

  - Add undertow handler example as module
  - Enable handler in undertow subsystem


1. Before you begin, make sure the following:
    * If it is running, stop the JBoss EAP server.
    * Backup your configuration file: `EAP7_HOME/standalone/configuration/standalone(-*).xml`

2. Start the JBoss EAP server by typing the following:
    * For Linux:  `EAP7_HOME/bin/standalone.sh -c standalone.xml`
    * For Windows:  `EAP7_HOME\bin\standalone.bat -c standalone.xml`

3. Review the `configure-undertow-handler.cli` file

4. Open a new command prompt, navigate to the root directory of this quickstart, and run the following command, replacing EAP7_HOME with the path to your server:
    * For Linux: `EAP7_HOME/bin/jboss-cli.sh --connect --file=configure-undertow-handler.cli`
    * For Windows: `EAP7_HOME\bin\jboss-cli.bat --connect --file=configure-undertow-handler.cli`

5. Restart the JBoss EAP server.


