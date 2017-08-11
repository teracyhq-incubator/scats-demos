Feature: teracyautotestdemo
		This is a demo test on PC
Background:
	#textbox
	Given navigate to "demo.registurl" 
	When move backward one page
	Then move forward one page
	And reload the page
	
Scenario: scenario2
	When register demo step
