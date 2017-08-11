Feature: Personal Settings

    As a logged-in user,
    I want to access my personal settings page
    so that I could view and update my personal information, including  Public Nam, Email, Company name, change password, and avatar


    Background:
        Given I am a logged-in user

    @smoke
    Scenario: view the default personal information 
         When I click Account on the left panel
          And I navigate to the Personal Information tab
         Then I should be able to see the info: Public Name, Company name (if any), Email and Avatar
          And I should the Change button below the Password field

    Scenario: Public Name is empty
        Given I am at the Personal Settings tab
         When I remove the existing public name
          And I fill in nothing or space keys into the Public Name
          And I press Enter
         Then I should see a notification "Public Name is required."
          And The previous name should be auto kept

    Scenario: update Company Name
        Given I am at the Personal Information tab
         When I update Company Name
          And I press Enter
         Then All the changes should be auto-saved
          And I should see the message "Company Name Updated."

    Scenario: keep Company Name empty 
        Given I am at the Personal Information tab
         When I keep the company Name empty
          And I press Enter
         Then No notification should be shown

    Scenario: update Email 
        Given I am at the Personal Information tab
         When I click the Email field
         Then An error notification "Sorry you can't change your email address." should be shown

    Scenario: change avatar with the origin size of the image
        Given I am logged in as an unverified/ verified user
          And I am at the Personal Settings tab
         When I hover the mouse on the avatar
         Then I should see the "Change picture" button
         When I click the avatar or "Change picture" button
         Then I should see a window to select an image from your local device
         When I have browsed an image
         Then The image should be displayed
          And I should see two buttons: Crop and Apply below the image
          And I should see the X icon allowing me to cancel changing the avatar
         When I click Apply
         Then The avatar should be applied with the origin size 
          And The avatar should be updated to the avatar on the left panel

    Scenario: change avatar with image cropped
         Given I am logged in as an unverified/ verified user
          And I am at the Personal Settings tab
         When I hover the mouse on the avatar
         Then I should see the "Change picture" button
         When I click the avatar or "Change picture" button
         Then I should see a window to select an image from your local device
         When I have browsed an image
         Then The image should be displayed
          And I should see two buttons: Crop and Apply below the image
          And I should see the X icon allowing me to cancel changing the avatar
         When I click Crop
         Then I should be redirected to the Crop page
          And I should see the Apply button
          And I should see the X icon allowing me to cancel changing the avatar
         When I have finished cropping the image
          And I click Apply
         Then The avatar should be applied with the cropped size 
          And The avatar should be updated to the avatar on the left panel

    Scenario: change avatar with invalid file
        Given I am at the Personal Settings tab
         When I change the avatar with invalid file, e.g doc, or a .doc file changed its extension into the image file
         Then I should see the error message "Invalid extension, supported types are png, jpeg, jpg."

    Scenario: change password with valid values 
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
          And The Save changes button should be disabled
          And I should see the Cancel link and X icon to cancel changing the password
         When I enter my current password
          And I enter my new password
          And I re-enter my new password
          And I click Save changes
         Then The password should be changed successfully with the notification "You're all set. Your password has been changed."

    Scenario: change password with empty values
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I click Current password
          And I move the mouse to another place
         Then I should see the message "Please enter your password."
         When I click New password
          And I move the mouse to another place
         Then I should see the message "Please enter your password."
         When I click Re-type new password
          And I move the mouse to another place
         Then I should see the message "Passwords do not match."


    Scenario: change password with illegal characters in current password 
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I enter space-key characters into my current password
          And I enter my new password
          And I re-enter my new password
          And I click Change
         Then I should see the message "Please enter a valid password. Use at least 6 characters, and don't use spaces."

    Scenario: change password with illegal characters in new password 
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I enter my valid current password
          And I enter my new password containing space keys
         Then I should see the error message "Please enter a valid password. Use at least 6 characters, and don't use spaces."
          And The Save changes should be disabled

    Scenario: change password with mismatched new pw and confirm pw 
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I enter my valid current password
          And I enter the valid new password
          And I fill in different password in to Confirm new password
         Then I should see the message "Passwords do not match"
          And The Save changes should be disabled

    Scenario: change password with incorrect current password
        Given I am at the Personal Settings tab
         When I click "Change Password"
         Then I should see the Change Password form
         When I enter my incorrect current password
          And I enter my valid new password
          And I re-enter my new password
          And I click Change
         Then I should see the message "Current password incorrect"

    Scenario: change password with less than 6 character in new password
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I enter my valid current password
          And I enter my new password containing less than 6 valid characters
         Then I should see the message "Please enter a valid password. Use at least 6 characters, and don't use spaces."
          And The Save changes button should be disabled
         When I retype the same new password
         Then The Save changes button should be disabled

    Scenario: change password with more 16 characters
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I enter my valid current password
          And I enter my new password containing more than 16 valid characters
         Then I should see the message "Password should not be more than 16 characters."
          And The Save changes button should be disabled
         When I retype the same new password
         Then The Save changes button should be disabled


    Scenario: cancel changing password by Cancel button
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I click Cancel button
         Then The Change Password form should be closed

    Scenario: cancel changing password by X button
        Given I am at the Personal Settings tab
         When I click "Change"
         Then I should see the Change Password form
         When I click X button
         Then The Change Password form should be closed




