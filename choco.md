
Ref: https://community.chocolatey.org/

##### chocolatey(choco package) installation

    @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command " [System.Net.ServicePointManager]::SecurityProtocol = 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"


##### Install google chrome using choco

    choco install googlechrome -y
    
##### Install Java    

    choco install jdk8 -y
    
#### Install Maven

    choco install maven -y
    
#### Install notepad++ 

    choco install notepadplusplus
    
#### Install tomcat 

    choco install tomcat
    
    
#### Install all the required tools in one command

    choco install googlechrome jdk8 maven -y

#### Install specific version

    choco install maven --version=3.8.1

#### Upgrade

    choco upgrade maven --version=3.8.2

#### Un-install

    choco uninstall maven --version=3.8.1
    

    
