folder('Tools') {
    description('Folder for miscellaneous tools.')
}
freeStyleJob('Tools/clone-repository') {
    // Clean the workspace before running the job
    wrappers {
        preBuildCleanup()
    }
    // Define parameters
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    // Clone the repository
    steps {
        shell('git clone ${GIT_REPOSITORY_URL}')
    }
}
freeStyleJob('Tools/SEED') {
    // Define parameters
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', '', 'Display name of the job')
    }
    steps {
        // Create a new job using the Job DSL plugin
        dsl {
            text (
                '''
                freeStyleJob("\${DISPLAY_NAME}") {
                    wrappers {
                        preBuildCleanup()
                    }
                    // github properties pointing to the repository and get the repository name
                    scm {
                        triggers {
                            cron('* * * * *')
                        }
                        github("\${GITHUB_NAME}")
                    }
                    steps {
                        shell('make fclean')
                        shell('make')
                        shell('make tests_run')
                        shell('make clean')
                    }
                }
                '''
            )
        }
    }
}
