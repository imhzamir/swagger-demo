pipeline{
    agent any

    stages{
        stage('checkout'){
            steps{
                sh '''
                echo "I'm ready"
                '''
            }
        }
        stage('jar build'){
            steps{
                sh '''
                mvn clean package
                '''
            }
        }
    }
}