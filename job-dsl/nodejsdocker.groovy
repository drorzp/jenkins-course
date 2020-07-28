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
            def SOME_PARAMETER = 'pants'
            condition {
                stringsMatch('${SOME_PARAMETER}', 'pants', false)
            }
            runner('DontRun')
            steps {
                shell("echo 'just one step'")
                shell("echo 'just second  step'")
            }
        }
         conditionalSteps {
            def SOME_PARAMETER = 'pants2'
            println SOME_PARAMETER
            condition {
                stringsMatch('${SOME_PARAMETER}', 'pants2', false)
            }
            runner('DontRun')
            steps {
                shell("echo 'just one step2'")
                shell("echo 'just second  step2'")
            }
        }
}
}