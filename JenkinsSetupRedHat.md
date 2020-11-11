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
    
    Other useful commands:
    sudo systemctl stop jenkins
    sudo systemctl restart jenkins
