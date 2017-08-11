Feature: Forgot Password

    As an anonymous user,
    I want to reset my password,
    so that I can log in and use the features of iorad.


    Background:
        Given I am an anonymous user


    @smoke
    Scenario: access reset password page
        Given I am at "/login" page
         When I click on "I forgot my password" link
         Then I should be redirected to the "Forgot your Password?" page

    @smoke
    Scenario: reset password successful
        Given I am at the "Forgot your Password?" page
         When I fill in my valid email
          And I click "Send reset link"
         Then I should be redirected to the homepage with the message "Check your email and click the Change Password link."
          And The email should be sent to my email address
         When I follow that reset password instruction
         Then I should reset the password successfully
          And I should see the message "Password changed. Please log in again!"
          And I should be redirected to the Log in screen

    Scenario: send reset link with empty email
        Given I am at the "Forgot your Password?" page
         When I fill in nothing or space keys into the email field
          And I click "Send reset link"
         Then I should see a message "Please enter your email."

    Scenario: send reset link with invalid email
        Given I am at the "Forgot your Password?" page
         When I fill in the invalid email
          And I click "Send reset link"
         Then I should see an message "Please enter a valid email address."

    Scenario: send reset link with unregistered email
        Given I am at the "Forgot your Password?" page
         When I fill in an email which is not used to register
          And I click "Send reset link"
         Then I should see an message "User with this email address not found."

    Scenario: send reset link with deleted account's email
        Given I deleted my account
          And I am at the "Forgot your Password?" page
         When I input my email of the account deleted
          And I press Enter or click Send reset link
         Then I should see the message "User with this email address not found."

    Scenario: reset password with empty new password
        Given I received the Reset Password instruction email
         When I open the email and follow the instruction in the email
          And I don't put anything in the New password
          And I click Change Password
         Then I should see the message "Please enter your password."

    Scenario: reset password with new password containing of less than 6 characters
        Given I received the Reset Password instruction email
         When I open the email and follow the instruction in the email
          And I fill less than 6 characters into the new password
          And I click Change Password
         Then I should see the message "Please enter a valid password. Use at least 6 characters and don't use spaces."

    Scenario: reset password with new password containing of more 16 characters
        Given I received the Reset Password instruction email
         When I open the email and follow the instruction in the email
          And I fill more 16 characters into the new password
          And I click Change Password
         Then I should see the error message "Please enter a valid password. Use at least 6 characters and don't use spaces."

    Scenario: reset password with space-key password
        Given I received the Reset Password instruction email
         When I open the email and follow the instruction in the email
          And I input new password having space characters into the New Password placeholder
          And I click Change Password
         Then I should see the message "Please enter a valid password. Use at least 6 characters and don't use spaces."

