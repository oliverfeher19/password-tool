# password-tool
A personal project to practice OOP and Java development: generates strong passwords, checks strength with detailed metrics, and will include AES encryption, JavaFX GUI, and units tests. 
#Features
-Generate strong password with configurable length
-Analyze password strenght
  -Number of digits, lowercase, uppercase, and special charactrers
  -Suggestions to improve weak passwords
-Planned Features:
  -Save password securely to local file
  -AES encryption for secure password storage
  -JavaFX GUI interface (maybe)

#Technologies Used
-Java 
-Object Orieneted Programming (OOP)
-Scanner API (CLI input)
-AES Encryption (planned)
JUnit for Testing (planned)


##Running Project
#Prerequisites
-Java 17+ Installed
-IDE like IntelliJ or VS Code (recommended)
#Running it
```bash
git clone https://github.com/oliverfeher19/password-tool.git
cd password-tool
javac passwords/*.java
java passwords.Home
