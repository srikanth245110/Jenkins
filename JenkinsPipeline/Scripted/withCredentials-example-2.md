
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

Step-4: Go to job configuration >> and configure git repo url, creds, branch, jenkinsfile name and other details(if any)

![image](https://user-images.githubusercontent.com/24622526/131825948-32ee9e80-378f-48b2-bd80-9d996912e2f0.png)


![image](https://user-images.githubusercontent.com/24622526/131826060-f378101f-ef2e-4e1a-ab34-d2d779b02238.png)


Step-5: Execute job and find the maven home directory path (as we configured maven as to download and install automatically under Global Tools configuration)

![image](https://user-images.githubusercontent.com/24622526/131827223-e5850d6c-adf4-40d7-be21-4a5e13db809d.png)


       find / -name "maven-3.8.1"

       vi /var/lib/docker/volumes/jenkins-data/_data/tools/hudson.tasks.Maven_MavenInstallation/maven-3.8.1/conf/settings.xml

       Go to settings.xml file and configure nexus credentials.

            ...../maven-3.8.1/conf/settings.xml

            deployment user details:
                    Username: deployment
                    Password: deployment123
              
	      
		<server>
			<id>deployment</id>
			<username>deployment</username>
			<password>deployment123</password>
		</server>
     
     
![image](https://user-images.githubusercontent.com/24622526/131828032-fd2c1340-3e8b-48d0-ad52-ad59af0608ad.png)

Now build again, nexus deploy will work.

![image](https://user-images.githubusercontent.com/24622526/131830569-f4b0af72-6565-4fc2-8270-a015f5df89e1.png)


As per the above image, our artifat is there in nexus repo - URL is 

	http://18.119.125.175:8081/nexus/content/repositories/releases/com/devops/devopswebapp/DevOpsWebApp/1.0.6/DevOpsWebApp-1.0.6.war

Step-6: Configiure Nexus credentials in Jenkins

![image](https://user-images.githubusercontent.com/24622526/131828710-d7efa2be-ff00-4e43-83dc-197228e7bf68.png)

![image](https://user-images.githubusercontent.com/24622526/131828895-428ea9ba-10e8-42d9-bd97-59497d9720eb.png)


Step-7: Create new pipleine job - deploy

Configure git url - https://github.com/venkatasykam/DevOpsWebApp.git

branch - web

jenkinsfile - DeployJenkinsfileWithCredentials

![image](https://user-images.githubusercontent.com/24622526/131829176-d4caf327-8ab2-4873-9b77-3eed2a380837.png)


![image](https://user-images.githubusercontent.com/24622526/131829409-a60ba4bd-8f33-4923-9fac-f8ca2b082c3a.png)


Step-8: Pipeline script withCredentials is


	node{

		stage('deploy-to-server'){

			print 'download the package from nexus'

			withCredentials([usernamePassword(credentialsId: 'jenkinsnexus', passwordVariable: 'nexusPassWrd', usernameVariable: 'nexusUsrName')]) {
			    wget --user ${nexusUsrName} --password ${nexusPassWrd} http://18.119.125.175:8081/nexus/content/repositories/releases/com/devops/devopswebapp/DevOpsWebApp/1.0.6/DevOpsWebApp-1.0.6.war
			}

		}
	}
