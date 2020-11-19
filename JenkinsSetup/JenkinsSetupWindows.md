*JenkinsSetup*: https://www.jenkins.io/doc/book/installing/windows/

**Install Java**:

    @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command " [System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"

    choco install googlechrome -Y (if chrome is not available on windows where you are installng jenkins)

    choco install jdk8 -Y
    
    Either you can install Jenkins using this command "choco install jenkins -Y" or follow the below steps.
    
    Important paths(after installation):
    JENKINS_HOME = C:\Windows\system32\config\systemprofile\AppData\Local\Jenkins\.jenkins
    initial admin password file for the user admin: sudo cat C:\Windows\system32\config\systemprofile\AppData\Local\Jenkins\.jenkins\secrets\initialAdminPassword
    
    console log output to the file: C:\Program Files\Jenkins/jenkins.log
    To change the default port number, Jenkins default path: /etc/sysconfig/jenkins
    Jenkins user: jenkins (see the details in C:\Program Files\Jenkins)
    location of the jenkins war file: C:\Program Files\Jenkins\Jenkins.war


**Download and Install Jenkins**
    
![image](https://user-images.githubusercontent.com/24622526/98760896-8ff26b00-23d4-11eb-83bd-0a7d94d0b9f8.png)


![image](https://user-images.githubusercontent.com/24622526/98761091-fc6d6a00-23d4-11eb-8a61-cb6fbe803b0c.png)


![image](https://user-images.githubusercontent.com/24622526/98761041-db0c7e00-23d4-11eb-8e0e-407ab6f2ad43.png)


![image](https://user-images.githubusercontent.com/24622526/98761191-33438000-23d5-11eb-973b-524a2e4df13d.png)


![image](https://user-images.githubusercontent.com/24622526/98761236-4f472180-23d5-11eb-8880-3f68587b710e.png)


![image](https://user-images.githubusercontent.com/24622526/98761301-6e45b380-23d5-11eb-93a6-1b32871d8f5b.png)


![image](https://user-images.githubusercontent.com/24622526/98761349-83224700-23d5-11eb-9f22-5af46332bf7a.png)


![image](https://user-images.githubusercontent.com/24622526/98761382-96351700-23d5-11eb-86a8-d553e9f612ad.png)


![image](https://user-images.githubusercontent.com/24622526/98761400-a64cf680-23d5-11eb-9f7c-f6f890cef5eb.png)


![image](https://user-images.githubusercontent.com/24622526/98761438-b6fd6c80-23d5-11eb-8f10-e9c46682a9ba.png)


![image](https://user-images.githubusercontent.com/24622526/98761468-c67cb580-23d5-11eb-8fca-c3fb9640a4b8.png)


![image](https://user-images.githubusercontent.com/24622526/98761493-d4cad180-23d5-11eb-94fa-404db7215a77.png)


![image](https://user-images.githubusercontent.com/24622526/98761524-e57b4780-23d5-11eb-9c15-484077dbda2d.png)


**Setup Jenkins**

Launch the URL in any browser: http://localhost:8080 or http://{public-ip-address}:8080

From here, follow the same steps from the section "Setup Jenkins":  https://github.com/DayToDayDevOpsCourse/JenkinsDayToDayCourse/blob/master/JenkinsSetupUbuntu.md

![image](https://user-images.githubusercontent.com/24622526/98761868-a4cffe00-23d6-11eb-8637-8323403e6056.png)
