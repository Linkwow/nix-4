@set ROOT=%~dp0
@cd %ROOT%level_1
@cd level_1
@call run
@cd..
@cd level_2
@call run
@cd..
@cd level_3
@call run
@pause
@cls