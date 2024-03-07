folder('Tools') {
    description('Folder for miscellaneous tools.')
}
freeStyleJob('Tools/SEED') {
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine")')
        stringParam('DISPLAY_NAME', '', 'Display name of the job')
    }
    steps {
        dsl {
            text {
                '''
                freeStyleJob('${DISPLAY_NAME}') {
                wrappers {
                    prebuildCleanup()
                }
                steps {
                    shell('make fclean')
                    shell('make')
                    shell('make tests_run')
                    shell('make clean')
                }'''
            }
        }
    }
}
