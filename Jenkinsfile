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
         sh 'mvn clean install -DskipTests'
      }
      post{
	      success{
		     echo 'Archiving artifact'
		    archiveArtifacts artifacts: '**/*.jar'
		    }
	    }
    }
    
    stage(' Code check '){
      steps{
        sh 'mvn  checkstyle:checkstyle'
      }
    post {
        success {
          echo 'Generated Analysis Result'
                }
      }
    }

    stage('CODE ANALYSIS with SONARQUBE') {
          
		  environment {
             scannerHome = tool 'sonarscanner4'
          }

          steps {
            withSonarQubeEnv('sonar') {
               sh '''${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=AmazonService \
                   -Dsonar.projectName=AmazonService \
                   -Dsonar.projectVersion=1.0 \
                   -Dsonar.sources=src/ \
                   -Dsonar.java.binaries=target/ \
                   -Dsonar.junit.reportsPath=target/surefire-reports/ \
                   -Dsonar.jacoco.reportsPath=target/jacoco.exec \
                   -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml'''
            }

            timeout(time: 10, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: true
            }
          }
        }
    
    }
  }

