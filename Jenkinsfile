pipeline {
 
 agent any
   tools {
  // Install the Maven version configured as "M3" and add it to the path.
     maven 'LocalMaven'
     jdk 'LocalJDK'
   }
  
 stages {
    
   stage('Pull Code') {
     steps {
       // Get code from a GitHub repository
       echo "Pulling Code"
       git 'https://github.com/arijitslg1/Spring-OAuth2.0-Login-With-Facebook.git'
      }
    }
   
   stage('Build Code'){
     steps{
       echo "Bulding Code"
       echo "M2_HOME = ${M2_HOME}"
       dir("OAuth2.0"){
         sh 'mvn clean package'
      }
     }
    }
   
   // stage('Junit Test'){
   //  steps{
   //    echo "Test Code"
   //    dir("OAuth2.0"){
   //      sh 'mvn test'
   //    }
   //  }
   // }
   
   stage('Build Docker Image'){
     steps{
       dir("OAuth2.0"){
       echo "Building Docker Image"
       sh 'docker build -t oauth2 -f Dockerfile --no-cache .'
      }
     }
    }
   
 //  stage('Tag Docker Image'){
 //    steps{
 //      echo "Tagging Docker Image"
 //      bat "docker tag oauth2 registry.heroku.com/onlinecourseportal/web:${env.BUILD_NUMBER}"
 //    }
 //  }
    
  stage ('Push Docker image to DockerHub') { 
      withCredentials([string(credentialsId: 'DockerID', variable: 'DockerID')]) { 
        sh "docker login -u arijitslg1 -p ${DockerID}" 
      } 
        sh 'docker push arijitslg1/oauth2' 
    }
    
//  stage ('Deploy Docker Image')
//    {
//     steps{
//      withEnv(['HEROKU=C:\\Progra~1\\heroku\\bin']){
//     withCredentials([usernamePassword(credentialsId:'Heroku',usernameVariable:'USR',passwordVariable:'PWD')])
//   {
//    echo "Docker Logging In"  
//    bat "docker login registry.heroku.com -u ${env.USR} -p ${env.PWD}"
//   }
//      echo "Deploying Docker Image"
//      bat "$HEROKU\\heroku container:release web --app=onlinecourseportal"
//      }
//     }
    }
 }
