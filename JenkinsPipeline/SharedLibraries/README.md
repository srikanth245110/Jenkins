Step-1: Create a git repo for shared library and configure folder structure similar to this same repo - [JenkinsSharedLibrary](https://github.com/DevOpsPlatform/JenkinsSharedLibrary.git)

![image](https://user-images.githubusercontent.com/24622526/131632216-5249872b-a584-419a-a9c4-4cdb2d5a0ee7.png)


![image](https://user-images.githubusercontent.com/24622526/131632245-7842b264-60ff-4838-9e27-20f4df68f31d.png)


Step-2: Go to Jenkins >> Manage Jenkins >> Configure System >> Global Pipeline Libraries - give any name for library, and *Default version* is git branch of shared library repo.

![image](https://user-images.githubusercontent.com/24622526/131631665-f7e8aa4d-5a23-40bd-8a33-ec1c72d5803a.png)


Step-3: Configure shared library repo details as shown below - git repo url, creds and save the configuration. Now these shared libraries are available to all the pipeline jobs in Jenkins.

![image](https://user-images.githubusercontent.com/24622526/131631812-548db546-a069-4940-bec4-3821a862a74b.png)

Step-4: Create another git repo for maven project or any other type of project and access the shared library as similar to this repo - https://github.com/MAVEN-Projects/WebApp/tree/sharedLib

![image](https://user-images.githubusercontent.com/24622526/131632562-515d86a8-a153-45fd-8f3e-1fbb607d39a3.png)

Step-5: Create a Jenkins pipeline job and execute it.


![image](https://user-images.githubusercontent.com/24622526/131632827-fc9e5268-89df-4564-8caf-359dcadc9989.png)


![image](https://user-images.githubusercontent.com/24622526/131632884-dabff283-bc2f-4817-a163-23c0c496009a.png)


Stage View after build execution


![image](https://user-images.githubusercontent.com/24622526/131632964-ddd8d6b2-9b2a-4af1-8135-80d47d37643b.png)

