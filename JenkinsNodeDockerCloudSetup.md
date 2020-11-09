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
            Docker Image = bibinwilson/jenkins-slave:latest or venkatasykam/mavengit:1.0 (see the Dockerfile for this Image -https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/dockerfiles/Dockerfile)
            Remote File System Root = /home/jenkins (user direcotry) or give different path which is accessible
            Connect method = Connect with SSH 
                SSH Key = Use Configured Credentials (add uname 'jenkins' & password as 'jenkins' - these crediantials are required to connect to the container)

![image](https://user-images.githubusercontent.com/24622526/98500572-272cb680-224d-11eb-8bd6-0e051e4abc66.png)

![image](https://user-images.githubusercontent.com/24622526/98500629-488da280-224d-11eb-82b6-170241f70a48.png)

![image](https://user-images.githubusercontent.com/24622526/98500660-5b07dc00-224d-11eb-8fc3-0686d75901b1.png)

![image](https://user-images.githubusercontent.com/24622526/98500706-75da5080-224d-11eb-9dfb-1826db353b92.png)

![image](https://user-images.githubusercontent.com/24622526/98500768-9acec380-224d-11eb-8561-7471fcfe34c4.png)


Test this slave:

Step-1: Create Jenkins free style job (Jenkins home page >> New Item >> Enter the job name and seelct Freestyle project >> Click Ok)

![image](https://user-images.githubusercontent.com/24622526/98568576-8c19f800-22b1-11eb-947f-cde8c0730a7e.png)

Step-2: In the Job configuration >> Select the checkbox "Restrict where this project can be run" >> Start typing the slave name, we can see the available node names >> select one

![image](https://user-images.githubusercontent.com/24622526/98568796-c97e8580-22b1-11eb-815d-4d9e3358164b.png)

Step-3: Sample commands/script under Build >> Execute Shell

![image](https://user-images.githubusercontent.com/24622526/98569261-62ad9c00-22b2-11eb-868a-bd5079444255.png)

![image](https://user-images.githubusercontent.com/24622526/98569636-d485e580-22b2-11eb-8635-8a2c8f42914d.png)

![image](https://user-images.githubusercontent.com/24622526/98569732-e9fb0f80-22b2-11eb-80ba-32a168bedc85.png)

Step-4: Click on "Build Now" to run the Jenkins job

	Initially you will the build status is in pending and the build is unabke to find the slave 

![image](https://user-images.githubusercontent.com/24622526/98569879-1a42ae00-22b3-11eb-918c-5fb7482d11ec.png)

	This time, slave setup done but slave still not online.

![image](https://user-images.githubusercontent.com/24622526/98570053-48c08900-22b3-11eb-955b-6578e42a660e.png)

	In the Jenkins home page, under "Build Queue" you can observe that the job is pending and waiting for the online node. Under "Build Executor Status", you will observ the newly created slave.

![image](https://user-images.githubusercontent.com/24622526/98570290-9210d880-22b3-11eb-991d-12a425764651.png)

	Finally our job is moved from "Build Queue" to "Build Executor Status". That means, job is executing under the new slave.

![image](https://user-images.githubusercontent.com/24622526/98570392-afde3d80-22b3-11eb-9f67-a27347698e49.png)

	If you go back to the Jenkins job, you will observe that the jenkins job is running. 

![image](https://user-images.githubusercontent.com/24622526/98570586-e6b45380-22b3-11eb-929a-ecd70d5dac42.png)

	Jenkins build console output. This is expected output as per our script/commands comfigured in job configuration Build part.

![image](https://user-images.githubusercontent.com/24622526/98570763-106d7a80-22b4-11eb-8198-863fbe7ccf01.png)





