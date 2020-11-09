configure a cloud(docker) to create an agent - https://devopscube.com/docker-containers-as-build-slaves-jenkins/

    Create an instance - Ubuntu
        Install docker on Ubuntu server
          sudo apt-get update
          sudo apt-get install docker.io -y
          sudo docker version

          Update the file with below line : /lib/systemd/system/docker.service Search for ExecStart and replace with below line
          ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:4243 -H unix:///var/run/docker.sock

          sudo systemctl daemon-reload
          sudo service docker restart

          curl http://localhost:4243/version
          curl http://{publicIP}:4243/version

          Install Dokcer plugin on Jenkins

          Manage Jenkins –> Manage Nodes and Clouds -> Select Docker 

          -> Enter "Docker Cloud Details"
            Docker Host URI "tcp://{privateIP}:4243"
            Enabled
            Expose DOCKER_HOST
	    Click on Test Connection
          -> Enter "Docker Agent templates"
            Labels = "anyName"
            Enabled
            Docker Image = bibinwilson/jenkins-slave:latest
            Remote File System Root = /home/jenkins (user direcotry) or give different path which is accessible
            Connect method = Connect with SSH 
                SSH Key = Use Configured Credentials (add uname 'jenkins' & password as 'jenkins' - these crediantials are required to connect to the container)



	

![image](https://user-images.githubusercontent.com/24622526/98500572-272cb680-224d-11eb-8bd6-0e051e4abc66.png)

![image](https://user-images.githubusercontent.com/24622526/98500629-488da280-224d-11eb-82b6-170241f70a48.png)

![image](https://user-images.githubusercontent.com/24622526/98500660-5b07dc00-224d-11eb-8fc3-0686d75901b1.png)

![image](https://user-images.githubusercontent.com/24622526/98500706-75da5080-224d-11eb-9dfb-1826db353b92.png)

![image](https://user-images.githubusercontent.com/24622526/98500768-9acec380-224d-11eb-8561-7471fcfe34c4.png)

