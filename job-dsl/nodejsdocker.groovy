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
        conditionalSteps {
            condition {
                stringsMatch('${SOME_PARAMETER}', 'pants', false)
            }
            runner('Fail')
            steps {
                shell("echo 'just one step'")
            }
        }
}
}