Feature: Account Summary

    As a logged-in user,
    I want to see my account summary
    so that I could see all the details of my account.


    @smoke
    Scenario: view account summary of a unverified user (1)
        Given I am logged-in as a unverified user
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see the "Personal Settings" tab below "Account Summary"
          And I should be in Free Plan with the price as $0
          And I should see the Upgrade Plan button referring to the "/pricing" page
          And I should see TUTOTIRAL STATS: Public, Private, Live and Themes
          And I should have unlimited public tutorials and 5 live tutorials
          And There should be the Upgrade link pointing to the "/pricing" page below Private and Themes
          And The Account Status should be shown as "Unverified"
          And I should see the link Resend next to the Account info
          And I should see the "DELETE ACCOUNT" link

    @smoke
    Scenario: view account summary as a free user (2)
        Given I am logged-in as a free verified user
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see 2 tabs: "Account Summary", "Personal Settings"
          And I should be in Free Plan with the price as $0
          And I should see the Upgrade Plan button referring to the "/pricing" page
          And I should see TUTOTIRAL STATS: Public, Private, Live and Themes
          And I should have unlimited public tutorials and 5 live tutorials
          And There should be the Upgrade link pointing to the "/pricing" page below Private and Themes
          And The Account Status should be shown as "Active"
          And I should see the "DELETE ACCOUNT" link

    @smoke
    Scenario: view account summary as a premium individual user(3)
        Given I am logged-in as a premium individual user
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see 3 tabs: "Account Summary", "Personal Settings", "Billing Information"
          And I should be in Premium Private Plan
          And I should see the plan price I have to pay
          And I should see my current plan info
          And I should see the creator number
          And I should see the "Change Plan" button referring to the "/checkout" page
          And I should see TUTOTIRAL STATS: Public, Private, Live and Themes
          And I should have unlimited public, private and live tutorials
          And I should see the info about existing tutorial number for Public, Private and Live
          And I should see the info about theme number
          And The Billing Status should be "Active"
          And The Account Status should be "Active"
          And I should see the "DELETE ACCOUNT" link

    @smoke
    Scenario: view account summary as a team owner user(3)
        Given I am logged-in as a team owner
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see 3 tabs: "Account Summary", "Personal Settings", and "Billing Information"
          And I should be in Premium Private Plan
          And I should see the plan price I have to pay
          And I should see my current plan info
          And I should see the creator number
          And I should see the "Change Plan" button referring to the "/checkout" page
          And I should see TUTOTIRAL STATS: Public, Private, Live and Themes
          And I should have unlimited public, private and live tutorials
          And I should see the info about existing tutorial number for Public, Private and Live
          And I should see the info about theme number
          And The Billing Status should be "Active"
          And The Account Status should be "Active"
          And I should see the "DELETE ACCOUNT" link

    @smoke
    Scenario: view account summary as a team member(4)
        Given I am logged-in as a team member
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see 2 tabs: "Account Summary" and "Personal Settings"
          And I should be in Premium Private Plan
          And I should see the plan price as $0
          And I should see my current plan info
          And I should see the creator number
          And I should see the info Team member - no charge
          And I should see the "Leave the Team" button
          And I should see TUTOTIRAL STATS: Public, Private, Live and Themes
          And I should have unlimited public, private and live tutorials
          And I should see the info about existing tutorial number for Public, Private and Live
          And I should see the info about theme number
          And The Billing Status should be "Active"
          And The Account Status should be "Active"
          And I should see the "DELETE ACCOUNT" link

    @smoke
    Scenario: view account summary as a locked user
        Given I am logged-in as a locked user
          And I have locked live tutorials
         When I click Account on the left panel
         Then I should be redirected to the "Account Summary" tab at the URL "/account"
          And I should see 2 tabs: "Account Summary" and "Personal Settings"
          And I should be in Free Plan with the price as $0
          And I should see the Upgrade Plan button referring to the "/pricing" page
          And I should see TUTOTIRAL STATS: Public, Private, Locked, Live and Themes
          And I should see the number of existing public, locked tutorials
          And I should see 0 of 5 for Live tutorials
          And I should see the Upgrade link below Private and Themes
          And The Account Status should be "Active"
          And I should see the "DELETE ACCOUNT" link

    Scenario: check the Export Links link
        Given I am logged-in
          And I am at the "Account Summary" tab
         When I click Export Links
         Then I should be able to save a .scv file
         When I view the .scv file
         Then The file should contain the info of 4 columns: Name, Direct Link, Embed Code and Steps


    Scenario: resend email verification when current plan is unverified (5)
        Given I am at "/account"
          And I am a unverified free user
          And I should see the RESEND link
         When I click the RESEND link
         Then I should see the informative message
          And I should receive an email for account verification instruction
         When I follow the instruction in the email
         Then My account should be verified


    Scenario: delete account with valid password (6)
        Given I am at "/account"
         When I click "Delete Account"
         Then I should see the deletion confirmation
         When I fill in my password
          And I click "Delete"
         Then I should see an informative message
          And I should  be logged out
          And I should receive an email for account deletion confirmation
         When I follow the instruction in the email
         Then My account should be deleted
          And I should be redirected to Home page
          And I cannot log in with my credentials


    Scenario: delete account with empty password (7)
        Given I am at "/account"
         When I click "Delete Account"
         Then I should see the deletion confirmation window
         When I click "Delete"
         Then I should see an informative message "Password Incorrect."


    Scenario: delete account with incorrect password (8)
        Given I am at "/account"
         When I click "Delete Account"
         Then I should see the deletion confirmation window
         When I fill in incorrect password
          And I click "Delete"
         Then I should see an informative message "Password Incorrect."


    Scenario: cancel deleting account with Cancel link (9)
        Given I am at "/account"
         When I click "Delete Account"
         Then I should see the deletion confirmation window
         When I click "Cancel"
         Then I should not see the deletion confirmation window.

    Scenario: cancel deleting account with X icon(10)
        Given I am at "/account"
         When I click "Delete Account"
         Then I should see the deletion confirmation window
         When I click "X"
         Then I should not see the deletion confirmation window.

# user logs in with Gmail
    Scenario: Google user - Delete account with valid email
        Given I logged in iroad with my gmail
          And I am at "/account"
         When I "Delete Account"
         Then The confirmation pop-up should be shown
         When I input my email into the Email field
          And I click Delete
         Then I should see an information message
          And I should be logged-out
          And I should receive an email for account deletion confirmation
         When I follow the instruction in the email
         Then My account should be deleted
          And I should be redirected to Home page
         When I log in with gmail again
         Then No tutorials should be kept 

    Scenario: Google users - Delete account with empty info
        Given I logged in iroad with my gmail
          And I am at "/account"
         When I "Delete Account"
         Then The confirmation pop-up should be shown
         When I don't input any thing into the Email field
          And I click Delete
         Then I should see the error info "Email incorrect."

    Scenario: Google users - Delete account with invalid email
        Given I logged in iroad with my gmail
          And I am at "/account"
         When I "Delete Account"
         Then The confirmation pop-up should be shown
         When I fill in an invalid email, for example, "abc@" into the Email field
          And I click Delete
         Then I should see the error info "Email incorrect."

    Scenario: Google users - incorrect email
        Given I logged in iroad with my gmail
          And I am at "/account"
         When I "Delete Account"
         Then The confirmation pop-up should be shown
         When I fill in a valid email but not mine into the Email field
          And I click Delete
         Then I should see the error info "Email incorrect."

    Scenario: Google users - cancel deleting email with X/Cancel button
        Given I logged in iroad with my gmail
          And I am at "/account"
         When I "Delete Account"
         Then The confirmation pop-up should be shown
         When I click X icon /Cancel link
         Then The pop-up should be closed
          And No email should be sent to my email address