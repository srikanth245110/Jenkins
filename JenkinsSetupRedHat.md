*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/linux/#red-hat-centos

**Install Java**:

    sudo yum install java-1.8.0-openjdk-devel -y
    
    java -version
    
**Install Jenkins**:

    sudo yum install wget -y
    
    sudo wget -O /etc/yum.repos.d/jenkins.repo \
    https://pkg.jenkins.io/redhat-stable/jenkins.repo
    sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
    sudo yum upgrade
    sudo yum install jenkins -y
    sudo systemctl daemon-reload
    
    sudo systemctl start jenkins
    sudo systemctl status jenkins
    
    Few commands:
        Check the status of Jenkins: sudo systemctl status jenkins
        Restart Jenkins: sudo systemctl restart jenkins
        Start the Jenkins: sudo systemctl start jenkins
        Stop the Jenkins: sudo systemctl stop jenkins
        Uninstall Jenkins: sudo apt-get remove --auto-remove jenkins -y
        Uninstall Jenkins with dependencies: sudo apt-get remove --auto-remove jenkins -y
        delete your local/config files for jenkins: sudo apt-get purge jenkins -y (Or) sudo apt-get purge --auto-remove jenkins -y

    Important paths:
        JENKINS_HOME = /var/lib/jenkins
        initial admin password file for the user admin: sudo cat /var/lib/jenkins/secrets/initialAdminPassword
        Setup Jenkins as a daemon launched on start: /etc/init.d/jenkins
        console log output to the file: /var/log/jenkins/jenkins.log
        To change the default port number, Jenkins default path: /etc/default/jenkins
        Jenkins user and group: jenkins (see the details in  /etc/default/jenkins)
        location of the jenkins war file: /usr/share/jenkins/jenkins.war
        PIDFILE=/var/run/jenkins/jenkins.pid
        If jenkins loaded successfully it will generate: /etc/rc.d/init.d/jenkins
        
 
**Setup Jenkins**

Launch the URL in any browser: http://<public-ip-address>:8080
    
    From here, follow the same steps from the section "Setup Jenkins":  https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/JenkinsSetupUbuntu.md
