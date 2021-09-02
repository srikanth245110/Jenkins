
Step-1: Install maven manually or automatically and setup the path in jenkins >> Manage Jenkins >> Global Tool Configuration >> Add Maven

![image](https://user-images.githubusercontent.com/24622526/131825465-a157290e-bd5d-4d20-a498-87e7d4d9727d.png)


Step-2: Create jenkins pipeline job

![image](https://user-images.githubusercontent.com/24622526/131824426-084e41a9-e7cf-47a8-bb05-450ba27f082f.png)


Step-3: Refer the jenkins pipeline script(*JenkinsfileWithCredentials*) from - https://github.com/venkatasykam/DevOpsWebApp/blob/web/JenkinsfileWithCredentials

      /*
      1. Setup Maven path in Manage Jenkins >>  Global Tool Configuration >> under Maven >> 
        Name: maven-3.8.1
        Value: maven home path (ex: /usr/lib/maven/apache-maven-3.8.1 or auto download)
      2. Setup Git path in Manage Jenkins >>  Global Tool Configuration >> under Git >> 
        Name: Default
        Value: Git home path (ex: /usr/bin/git )
      3. Make sure jenkins master or node have the lable - build. (OR) update the lable name you want in the below code.
      4. Make sure install nexus and update the <nexusIp> property in pom.xml file
      */

      node("build"){
        stage('checkout'){
          checkout scm
        }
        stage('build'){
          sh"${tool 'maven-3.8.1'}/bin/mvn -V clean test -DreleaseVersion=1.0.${BUILD_NUMBER}"
        }
        stage('deploy-to-nexus'){
          print 'deploy the package to nexus'
          
          sh"${tool 'maven-3.8.1'}/bin/mvn -V clean package -DreleaseVersion=1.0.${BUILD_NUMBER}"
        }
      }

Step-4: 

