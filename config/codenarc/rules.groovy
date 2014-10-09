
ruleset {
    description 'A custom Groovy RuleSet'

    CyclomaticComplexity {
        maxMethodComplexity = 1
    }

    ClassName

    MethodName

    ConfusingTernary(priority:3)


}

