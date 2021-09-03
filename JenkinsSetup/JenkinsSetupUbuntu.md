*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/linux/#debianubuntu

**Name the server**:

    sudo -i

    hostname Jenkins-Master
    
    echo "Jenkins-Master" > /etc/hostname
    
    exit
    
    exit
    
    conenct again
    
    
![image](https://user-images.githubusercontent.com/24622526/131941469-f219451b-7378-40f4-a48f-bb15d0d339bc.png)



**Install Java**:

    #Update the repositories
    sudo apt update
    
    #search of all available jdk packages
    sudo apt search openjdk
    
    #Pick one package from search result and install jdk 
    sudo apt install openjdk-8-jdk -y
    
        If you see any similar message as below follow below steps to solve the issue
        
        Waiting for cache lock: Could not get lock /var/lib/dpkg/lock-frontend. It is held by process 3327 (apt)... 37s
        
        check the running process: ps aux | grep -i apt
        
        Run the command: sudo kill <processId>

    #check the installation
    java -version
    
**Install Jenkins**: copy each copy command line by line and execute

    wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
    
    sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > \
        /etc/apt/sources.list.d/jenkins.list'
        
    sudo apt-get update
    
    sudo apt-get install jenkins -y
    
    Few commands:
        Check the status of Jenkins: sudo systemctl status jenkins (press q button to exist from status console)
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

Launch the URL in any browser: http://{public-ip-address}:8080
    
![image](https://user-images.githubusercontent.com/24622526/98561852-a5b74180-22a9-11eb-9319-5b9f59639077.png)


    sudo cat /var/lib/jenkins/secrets/initialAdminPassword
    
![image](https://user-images.githubusercontent.com/24622526/98562037-de571b00-22a9-11eb-8eb9-674ce71b3597.png)

    Paste the password and click on continue

![image](https://user-images.githubusercontent.com/24622526/98562081-f169eb00-22a9-11eb-8a5f-bfb71caa7d0c.png)

    Select the first one if you are not sure about the plugins you want to install and if you want to setup jenkins with default list of plugin. Select second option if you want to select the plugin and if you are sure about to install the required plugins. Here in this example choosing first one.

![image](https://user-images.githubusercontent.com/24622526/98562244-1fe7c600-22aa-11eb-8643-05e6097b1bbd.png)

    If you see the below error either you are using very weak network or some issue. Because when you click on any option our Jenkins server try to connect to the internet and download the required plugins from Jenkins plugins repo. Click on retry.

![image](https://user-images.githubusercontent.com/24622526/98562779-b4eabf00-22aa-11eb-9c7a-768565eeec25.png)

    Plugins installation process

![image](https://user-images.githubusercontent.com/24622526/98563789-e3b56500-22ab-11eb-9ea8-eaf695bd049f.png)

    Signup for new admin user, so that it will remove both the default user 'admin' and password from /var/lib/jenkins/secrets/initialAdminPassword. If you want to proceed with 'admin' user click on 'Skip and Continus as admin'. In this example, I am creating a new user.

![image](https://user-images.githubusercontent.com/24622526/98564226-64746100-22ac-11eb-9ce3-5be6e4af7c5d.png)

    Save and Finish

![image](https://user-images.githubusercontent.com/24622526/98564545-c7fe8e80-22ac-11eb-9aab-4d7a2c320422.png)

    Click on "Start using Jenkins" button, it will navigate to Jenkins home page.
    
![image](https://user-images.githubusercontent.com/24622526/98564695-f54b3c80-22ac-11eb-9984-6e83026a2554.png)
