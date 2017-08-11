Feature: Unverified user activates account

    As an unverified user,
    I want to activate my account
    so that I can user the features for verified user.

    @smoke
    @low_priority
    Scenario: Activate account successfully as logged-in user
        Given I created a new account
          And I haven't activated my account yet
          And I am logged in
         When I open the activation email
          And I click the link in the email
         When I should be redirected to My Tutorials page with the notification "Thanks. You're good to go!"

    @smoke
    Scenario: Activate account as an anonymous user
        Given I created a new account
          And I haven't activated my account yet
          And I am not logged in yet
         When I open the activation email
          And I click the link in the email
         When I should be redirected to the Log in screen with the email filled in the Email field
          And I log in successfully
         Then I should be redirected to My Tutorials page with the notification "Thanks. You're good to go!"

    @low_priority
    Scenario: The activation email is expired
        Given I created a new account
          And I am unverified user
          And I went to Account and deleted my account
          And I followed the instruction in the email to confirm deletion
         When I open the Activation Account email
          And I click the link in the email
         Then I should be redirected to Homepage
          And I should not log in successfully

# Need to check the outcome
    @low_priority
    Scenario: Logged-in user - The activation email is already used
        Given I created a new account
          And I activated my account
          And I am logged-in
         When I open the Activation Account email again
          And I click the link in the email
         Then I should be redirected to My Tutorials page
          And I should see an informative message "Thanks. You're good to go"

# need to check expected result
    Scenario: Anonymous user - The activation email is already used
        Given I created a new account
          And I activated my account
          And I am not logged-in yet
         When I open the Activation Account email again
          And I click the link in the email
         Then I should be redirected to Log in screen
 