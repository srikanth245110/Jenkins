*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/kubernetes/

~**Install Java**~ **Install Docker**:

    sudo apt-get update
    sudo apt-get docker.io -y
    sudo docker version

 **Add new User**
  
    adduser developer
      password@7
    usermod -aG sudo developer
    
**Login to the newly created User**

    su - developer
      password@7

**Add User to the Docker Group**

      sudo groupadd docker
      sudo usermod -aG docker $USER
      
      - Re-Login or Restart the Server
      
**Install Minicube**

    curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
    chmod +x minikube
    sudo mv ./minikube /usr/local/bin/minikube
    
    sudo minikube version
    
    sudo minikube start --driver=docker
    
    sudo docker ps
    
    sudo minikube status
    
**Install kubectl**

    sudo apt-get update && sudo apt-get install -y apt-transport-https gnupg2 curl
    curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list
    sudo apt-get update
    sudo apt-get install -y kubectl

    kubectl version --client



