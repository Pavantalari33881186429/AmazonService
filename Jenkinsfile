pipeline{

  agent any
  
  tools{
      maven "MAVEN3"
	  jdk   "OracleJDK11"
   }
  
  stages{
    stage('Get code from Git'){
       steps{
           
           git branch: 'main', url: 'https://github.com/Pavantalari33881186429/AmazonService.git'
       }
    }
    
    stage('Build Code'){
      steps{
         sh 'mvn install --DskipTests'
      }
      post{
	    success{
		   echo 'Archiving artifact'
		   archiveArtifacts artifacts: '**/*.war'
		}
	  }
    
    }
    
    stage('Unit Test'){
      steps{
         sh 'mvn test'
      }
    
    }
  }
}