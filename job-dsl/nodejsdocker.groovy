job('NodeJS Docker example') {
    scm {
        git('git://github.com/drorzp/docker-demo') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Dror Zeplovitch')
            node / gitConfigEmail('drorzp@hotmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('node') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('drorzp/node-example')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('docker')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
