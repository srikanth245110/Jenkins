*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/docker/

~**Install Java**~ **Install Docker**:

    #Create an Ubuntue machine and connect to it.

    #Update the repositories and install docker
    
    sudo -i
    
    sudo apt update
    
    sudo apt-get install docker.io -y
    
    sudo docker version
    
    #find more docker commands https://github.com/DevOpsPlatform/Phase-2/blob/master/Docker/Docker-Commands.md
    
~**Install Jenkins**~ **Run Jenkins container**: https://hub.docker.com/_/jenkins
    
    sudo docker run -d -n jenkins -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home jenkins/jenkins:latest
    
    sudo docker ps -a
    
    find / -name "initialAdminPassword" (a path will be printed, copy this path)
    
    cat <copied path above>
    
    Or 
    
    docker exec -it jenkins cat /var/lib/jenkins/secrets/initialAdminPassword (a path will be printed, copy this path)
    
    cat <copied path above>
    
**Setup Jenkins**:

  Access the URL in any browser: `http://{public-ip-address}:8080`
  
  From here, follow the steps from the section "Setup Jenkins": 
  
  
  https://github.com/DevOpsOnlineTraining-2021/Jenkins/blob/master/JenkinsSetup/JenkinsSetupUbuntu.md
  
  
    
    
    
