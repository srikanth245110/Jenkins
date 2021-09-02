

Reference doc: https://www.jenkins.io/doc/pipeline/steps/credentials-binding/


#### withCredentials: Bind credentials to variables

* Step-1: Go to Jenkins >> Manage Jenkins >> Manage Credentials >> click on Jenkins >> Global credentials >> Add Credentials

![image](https://user-images.githubusercontent.com/24622526/131817002-3bf43b49-75c0-4128-a056-5c84dca24df5.png)

* Step-2: Enter github username and Person Access Token(PAT) as password. Generate PAT with the help of doc - https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token


![image](https://user-images.githubusercontent.com/24622526/131818453-d56a5c34-c90f-479d-93a3-a716021fb91a.png)



![image](https://user-images.githubusercontent.com/24622526/131817572-b63cc667-154a-4cea-a8b6-46155cb04898.png)


![image](https://user-images.githubusercontent.com/24622526/131818701-26efc1d2-483f-4696-a25c-5809cf7d5a18.png)


* Step-3: generate pipeline script for withCredentilas


![image](https://user-images.githubusercontent.com/24622526/131818920-d19232dc-2f53-40f8-bf30-81aa6dd68a13.png)


![image](https://user-images.githubusercontent.com/24622526/131819344-5e6edca7-c417-4754-aa83-8acce9770040.png)


    withCredentials([usernamePassword(credentialsId: 'jenkinsgithub', passwordVariable: 'PASSWD', usernameVariable: 'USRNAME')]) {
        // some block
    }


* Step-4: Go to pipeline job configuration and add below code and run the job (in the below script you need to update the github org name where you have admin access.

    node{

        stage("list repos"){
            withCredentials([usernamePassword(credentialsId: 'jenkinsgithub', passwordVariable: 'PASSWD', usernameVariable: 'USRNAME')]) {

                sh'''
                    Org=DevOpsOnlineTraining-2021
                    
                    curl -# -i -u ${USRNAME}:${PASSWD} https://api.github.com/orgs/${Org}/repos?type=all | grep -w '"name":' | awk ' /'"name"'/ {print $1,$2} '
                '''
            }
        }
    }
    
    
![image](https://user-images.githubusercontent.com/24622526/131820447-2343fd9d-22b2-446d-8e19-076d6b053501.png)

