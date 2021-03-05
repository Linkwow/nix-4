@set ROOT=%~dp0
@cd %ROOT%compiler
@call runBuildCLI
@pause
@cls
@cd %ROOT%ant
@call runBuildAnt
@pause
@cls
@cd %ROOT%maven
@call runBuildMaven
@pause
@cls
@exit