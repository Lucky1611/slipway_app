version = "${params.version}"
env = "${params.env}"
def nexus_ip = "54.221.69.188:8081"
def slackChannel = "#demoapp_alerts"

node() {
  properties([[$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '7', numToKeepStr: '5']]])
  deleteDir()

  try {
    stage("SCM Checkout") {
      checkout scm
      println "Environment =====>>> $env"
      println "Version Number =====>>> $version"
    }
    
    stage("Decrypt and update value in ec2 yaml") {
      withCredentials([file(credentialsId: 'GIT_CRYPT_PRIVATE_KEY', variable: 'invkey')]) {
        sh """
          git-crypt unlock $invkey
          sed -i 's/web_instance_dev/web_instance_$env/g' aws_ec2.yaml
        """
      }
    }

    stage("Deploy App using Ansible in EAST") {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'nexusCred', usernameVariable: 'nexus_username', passwordVariable: 'nexus_password']]) {
        sh """
        ansible-playbook -i aws_ec2.yaml -u ubuntu --private-key=/home/jenkins/experiment-slipway-key-pair.pem deploy_app.yml --extra-vars "version=$version  nexus_ip=${nexus_ip} nexus_username=${nexus_username} nexus_password=${nexus_password}" -v
      """
      } 
    }

    stage("Deploy App using Ansible in WEST") {
      withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'nexusCred', usernameVariable: 'nexus_username', passwordVariable: 'nexus_password']]) {
        sh """
        sed -i "s/regions.*/regions: us-west-1/g" aws_ec2.yaml 
        ansible-playbook -i aws_ec2.yaml -u ubuntu --private-key=/home/jenkins/experiment-slipway-key-pair.pem deploy_app.yml --extra-vars "version=$version  nexus_ip=${nexus_ip} nexus_username=${nexus_username} nexus_password=${nexus_password}" -v
      """
      } 
    }

    stage("Post Build") {
      currentBuild.displayName = "${env}-DemoApp-${version}" 
      slackSuccess(slackChannel,env)
    }

  } catch (err) {
      println("================ ERROR: ${err}")
		  currentBuild.displayName = "${env}-DemoApp-${version}" 
      slackFailure(slackChannel,env)
      currentBuild.result = "FAILURE"
      error()
  }
}

def checkoutGit(branchName, targetDir, repoURL, credID) {
    checkout([$class: 'GitSCM',
      branches: [[name: branchName]],
      doGenerateSubmoduleConfigurations: false,
      extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: targetDir]],
      submoduleCfg: [],
      userRemoteConfigs: [[credentialsId: credID, url: repoURL]]
    ])
}

def slackSuccess(slackChannel, env) {
    slackSend (
        channel: slackChannel,
        color: "#008000",
        message: ":blush: *SUCCESS*\n_Deployment_ Completed in ${env} for *${currentBuild.displayName}*.")
}

def slackFailure(slackChannel, env) {
    slackSend (
        channel: slackChannel,
        color: "#FF0000",
        message: ":dizzy_face: *FAILURE*\n_Deployment_ Failed in ${env} for *${currentBuild.displayName}*.")
}