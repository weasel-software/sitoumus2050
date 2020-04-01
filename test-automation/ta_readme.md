* run TA cases
*test-automation folder (where pom.xml file is):
mvn clean install

*you can add tags to cases
*for exanple:
*Edit own profile
  [Tags]    Test
* run
mvn clean install -Drobot.tag=Test

*if you want to run in local environment
change ${URL}	variable in sitoumus_keywords.txt
