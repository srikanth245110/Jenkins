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

          Install "Dokcer" plugin on Jenkins: Manage Jenkins >> Manage Plugins >> Available tab >> Search for "Docker" plugin >> Select from search result and click on "Install without Restart"
	  
![image](https://user-images.githubusercontent.com/24622526/98566068-a0102a80-22ae-11eb-9563-ff8d9f3acc4f.png)

	Install "Dokcer" plugin on Jenkins: Manage Jenkins >> Manage Plugins >> Available tab >> Search for "Docker" plugin >> Select from search result and click on "Install without Restart"
	  
![image](https://user-images.githubusercontent.com/24622526/98566275-dbaaf480-22ae-11eb-85ca-e70dbaa7e216.png)



          Manage Jenkins –> Manage Nodes and Clouds >> Click on "Add a New Cloud" >> Select "Docker" >> 
	  
![image](https://user-images.githubusercontent.com/24622526/98566553-2d537f00-22af-11eb-8acd-c0564ed95591.png)

	  
![image](https://user-images.githubusercontent.com/24622526/98566618-452b0300-22af-11eb-8515-6b00daf79ef8.png)


          -> Enter "Docker Cloud Details"
            Docker Host URI "tcp://{privateIP-or-publicIP}:4243"
            Select checkbox: Enabled
            Select checkbox: Expose DOCKER_HOST
	    Click on "Test Connection"
	    
![image](https://user-images.githubusercontent.com/24622526/98566995-ba96d380-22af-11eb-9117-73d8868a1dd5.png)

          -> Click on "Docker Agent templates" and add "Docker Agent templates"
            Labels = "anyName"
            Select checkbox: Enabled
            Docker Image = bibinwilson/jenkins-slave:latest
            Remote File System Root = /home/jenkins (user direcotry) or give different path which is accessible
            Connect method = Connect with SSH 
                SSH Key = Use Configured Credentials (add uname 'jenkins' & password as 'jenkins' - these crediantials are required to connect to the container)

![image](https://user-images.githubusercontent.com/24622526/98500572-272cb680-224d-11eb-8bd6-0e051e4abc66.png)

![image](https://user-images.githubusercontent.com/24622526/98500629-488da280-224d-11eb-82b6-170241f70a48.png)

![image](https://user-images.githubusercontent.com/24622526/98500660-5b07dc00-224d-11eb-8fc3-0686d75901b1.png)

![image](https://user-images.githubusercontent.com/24622526/98500706-75da5080-224d-11eb-9dfb-1826db353b92.png)

![image](https://user-images.githubusercontent.com/24622526/98500768-9acec380-224d-11eb-8561-7471fcfe34c4.png)

