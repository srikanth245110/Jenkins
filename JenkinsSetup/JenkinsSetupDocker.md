*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/docker/

~**Install Java**~ **Install Docker**:

    #Create an Ubuntue machine and connect to it.

    #Update the repositories
    sudo apt update
    
    sudo apt-get install docker.io -y
    
    sudo docker version
    
    #find more docker commands https://github.com/DevOpsPlatform/Phase-2/blob/master/Docker/Docker-Commands.md
    
~**Install Jenkins**~ **Run Jenkins container**: https://hub.docker.com/_/jenkins

    sudo docker run --detach --name jenkins --network jenkins --publish 8080:8080 --publish 50000:50000 --volume jenkins-data:/var/jenkins_home jenkins
    
    sudo docker ps -a
    
    Output:
    ubuntu@ip-172-31-14-206:~$ sudo docker ps -a
    CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                                              NAMES
    9161c2a4c25b        jenkins             "/bin/tini -- /usr/l…"   About a minute ago   Up About a minute   0.0.0.0:8080->8080/tcp, 0.0.0.0:50000->50000/tcp   silly_kepler
    
**Setup Jenkins**:

  Launch the URL in any browser: http://{public-ip-address}:8080
  
  From here, follow the same steps from the section "Setup Jenkins":  https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/JenkinsSetupUbuntu.md

    
    
    
