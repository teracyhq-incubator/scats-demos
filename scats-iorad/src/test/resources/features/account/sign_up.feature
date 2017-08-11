Feature: Sign Up

    As a user,
    I want to create an account,
    so that I could use the features of Iorad.

    Background:
        Given I am an anonymous user

    @smoke
    @chrome
    Scenario: Sign up with valid values
        Given I go to "/signup"
         Then The Sign Up button should be enabled by default
         When I fill in valid values into the placeholders: Public Name, Email and Password
          And I click "Sign Up"
         Then I should be redirected to the Choose mode page 
          And I should receive an account activation email

    @smoke
    @firefox @safari
    Scenario: Sign up with valid values
        Given I go to "/signup"
         Then The Sign Up button should be enabled by default
         When I fill in valid values into the placeholders: Public Name, Email and Password
          And I click "Sign Up"
         Then I should be redirected to the Download page 
          And I should receive an account activation email

# @currently not supported
# Check Next and Skip on each guide
    Scenario: new user goes to Learning tab at first time
        Given I've just signed up
         When I go to Your Learning tab at the first time
         Then I should see the message "Click New Tutorial to get started."
          And I should see "iorad guide"
          And I should see two buttons "Skip" and "Nex"
          And The iorad guide arrow points to "How iorad works" tutorial
         When I click Skip
         Then The iorad guide should be closed
         When I click Next
         Then I should be redirected to the next guide pointing to "New Tutorial" button
          And I should see Next and Skip buttons
         When I click Next
         Then I should go to the next guide pointing to "Your Learning"
          And I should see Next and Skip buttons
         When I click Next
         Then I should see the next guide pointing to "Your Tutorials"
          And I should see "Done" button
         When I click Done
         Then The iorad guide should be closed

    Scenario: Create account with empty or space-key Public Name
        Given I go to "/signup"
         When I fill in nothing or only space-key characters in the Public Name
          And I fill email and password
         Then I should see the message "Please enter your public name."

    Scenario: Create account without Email
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in nothing or space key characters in email
         Then I should see the message "Please enter your email."

    Scenario: Create account with invalid Email
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in invalid email
         Then I should see the message "Please enter a valid email address."

    Scenario: Create account with existing Email
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in an existing email
         Then I should see the message "Email address already in use."

    Scenario: Create account without password
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in valid email
          And I fill in nothing in the password
          And I press Enter or click Sign Up
         Then I should see the message "Please enter your password."


    Scenario: Create account with password having space keys
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in valid email
          And I fill password with 6 characters including space keys, number, and letter
          And I press Enter or click Sign Up
         Then I should see the message "Please enter a valid password. Use at least 6 characters and don't use spaces."

    Scenario: Create account with less 6 character password length
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in valid email
          And I fill in password with less than 6 valid characters
          And I press Enter or click Sign Up
         Then I should see the message "Please enter a valid password. Use at least 6 characters and don't use spaces."


    Scenario: Create account with more than 16 characters password length
        Given I go to "/signup"
         When I fill in Public Name
          And I fill in valid email
          And I fill in password with more than 16 valid characters
          And I press Enter or click Sign Up
         Then I should see the message "Password should not be more than 16 characters."


    Scenario: Redirect to pricing page after signing up
        Given I am an anonymous user
         When I click Live Pricing on the left panel
          And I click the Go live button on the /pricing page
         Then I should be redirected to Log In screen
         When I navigate to Sign Up tab
          And I sign up successfully
         Then I should be redirected to Pricing page

    Scenario: check the link Terms & Privacy Policy
        Given I go to "/signup"
         When I click the Terms & Privacy Policy link
         Then I should be redirected to the "/termsconditions"

    Scenario: check the links on Sign Up page
        Given I am at "/signup"
         When I click any link on the left
         Then I should be redirected to the correct page

