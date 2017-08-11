Feature: Log in

    As a user,
    I want to log in Iorad
    so that I can use the features of Iorad.

    Background:
        Given I am an anonymous user

    @smoke
    Scenario: log in with valid username and password & user has tutorials in My Tutorials
        Given I have tutorials in My tutorials
          And I am at "/login"
         Then The Log In button should be enabled by default
          And The "Remember me" checkbox should be unchecked by default
         When I fill in valid registered username and password
          And I click "Log In"
         Then I should be logged in and redirected to the "My Tutorials" page at the URL "/tutorials"

    @smoke
    Scenario: check redirection when user has no tutorials in My Tutorials
        Given I have no tutorials into My Tutorials
          And I have tutorials in My learnings
         When I log in successfully
         Then I should be redirected to the My Learning tab

    @smoke
    Scenario: check redirection when the member has tutorials in Shared Edit only
        Given I have no tutorials into My tutorials and My learning
          And I have tutorials in Shared Edit
         When I log in as a team member
         Then I should be redirected to Shared Edit

    @smoke
    @chrome
    Scenario: check redirection when user has no tutorials in Tutorials
        Given I have no tutorials in My tutorials, My learning any Shared Edit tabs
         When I log in
         Then I should be redirected to the Choose page

    @smoke
    @firefox, @safari @IE11
    Scenario: check redirection when user has no tutorials in Tutorials & iorad app installed
        Given I have no tutorials in My tutorials, My learning any Shared Edit tabs
          And I installed the iorad app
         When I log in
         Then I should be redirected to the Connecting page
          And The capture frame should launch

    @smoke
    @firefox, @safari @IE11
    Scenario: check redirection when user has no tutorials in Tutorials & iorad app uninstalled
        Given I have no tutorials in My tutorials, My learning any Shared Edit tabs
          And I haven't installed the iorad app yet
         When I log in
         Then I should be redirected to the Download page
          And I should be able to download the iorad app

    Scenario: log in with empty email/password
        Given I go to "/login/"
         When I fill in space keys or nothing into email and password
          And I click Log In button
         Then The message "Please enter your email." should be shown
          And The message "Please enter your password." should be shown

    Scenario: log in with wrong email format
        Given I go to "/login"
         When I fill in invalid email format
          And I press Enter, or click Log In buton
         Then The message "Please enter a valid email address." should be shown

    Scenario: log in with incorrect email
        Given I go to "/login"
         When I fill in incorrect email
          And I fill in correct password
          And I click Log in
         Then The message "Email / password combination is not valid." should be shown

    Scenario: log in with incorrect password
        Given I go to "/login"
         When I fill in correct email
          And I fill in incorrect password
          And I click Log in
         Then I should see the message "Email / password combination is not valid."

    Scenario: check the links on Log In page
        Given I am at "/login"
         When I click any link on the left 
         Then I should be redirected to  the correct page

    Scenario: Open/close the left panel
        Given I am at "/login"
         When I click the menu icon on the left
         Then The left panel should be opened
         When I click X icon on the left
         Then The left panel should be closed

    Scenario: Log in with the the "Remember me" checked
        Given I am at "/login"
         When I log in with the "Remember me" checked
          And I close the browser
          And I return to the web
         Then I should be redirected to My Tutorials page

    Scenario: Log in with the "Remember me" unchecked
        Given I am at "/login"
         When I log in with the Remember me" unchecked
          And I close the browser
          And I return to the web
         Then I should be redirected to "/login"

    Scenario: check the left menu for free user
        Given I am logged-in as a free user
         Then The left panel should show: Capture, Tutorials, Account, Upgrade Plan, Connect, Contact Us, Blog and Log Out

    Scenario: check the left menu for a team owner
        Given I am logged-in as a team owner
         Then The left panel should show Capture, Tutorials, Account, Change Plan, Creators, Connect, Contact Us, Blog and Log Out

    Scenario: check the left menu for a premium user as individual
        Given I am logged-in as an individual premium user
         Then The left panel should show Capture, Tutorials, Account, Change Plan, Connect, Contact Us, Blog and Log Out

    Scenario: check the left menu for a team member
        Given I am logged-in as a team member
         Then The left panel should show: Capture, Tutorials, Account, Connect, Contact Us, Blog and Log Out
          And I should not see the Upgrade Plan or Change Plan in the left menu