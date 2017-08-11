Feature: Billing Information

    As a logged-in premium user,
    I want to access the Billing Information page
    so that I could know and update my current credit card, and view the transaction history.

    @smoke
    Scenario: view billing information as an owner
        Given I am logged-in as an owner / individual premium user
         When I click Account
         Then I should be redirected to the Account Summary tab
         When I navigate to the Billing Information tab at the "/account/billing" URL
         Then I should see the My Current Credit Card text
          And I should see the Visa icon
          And I should see four ending numbers of my credit card
          And I should see the "Update" link
          And I should see my Transaction History
          And I should see the View link corresponding to a transaction
         When I click the View link
         Then I should be able to view the invoice in a new tab

    @smoke
    Scenario: Update credit card with valid info
        Given I am at "/account/billing"
         When I click "Update" link
         Then I should be redirected to the "UPDATE CREDIT CARD" page
          And The "Save" button should be disabled
         When I input all valid values into the required fields
         Then The "Save" button should be enabled
         When I click Save
         Then I should see an informative message "Credit card has been updated."
          And I should be back to the Account Summary tab

    Scenario: Update credit card with invalid card number
        Given I am at "/user/billingInformation/"
         When I click "Update" link
          And I input valid email
          And I fill in invalid card number
          And I fill valid values into all other fields
          And I click Save
         Then I should see the message "Your card number is incorrect."

    Scenario: Update credit card with locked card number
        Given I am at "/user/billingInformation/"
         When I click "Update" link
          And I input valid email
          And I fill in valid card number bu the card is locked
          And I fill valid values into all other fields
          And I click Save
         Then I should see the message "Your card was declined."

    Scenario: Update credit card with invalid expired year
        Given I am at "/account/billing"
         When I click "Update" link
         Then I should be redirected to the "UPDATE CREDIT CARD" page
          And The "Save" button should be disabled
         When I input the valid cc number and CVC
          And I input invalid year
         Then The "Save" button should be enabled
         When I click Save
         Then I should see the message "Your card's expiration year is invalid."

    Scenario: Update credit card with invalid expired month
        Given I am at "/account/billing"
         When I click "Update" link
         Then I should be redirected to the "UPDATE CREDIT CARD" page
          And The "Save" button should be disabled
         When I input the valid cc number and CVC
          And I input invalid month, valid year
         Then The "Save" button should be enabled
         When I click Save
         Then I should see the message "Your card's expiration month is invalid."

    Scenario: update cc with any empty required fields
        Given I am at "/account/billing"
         When I click "Update" link
          And I keep one or two required fields empty
         Then The Save button should be disabled

    Scenario: Cancel updating credit card
        Given I am at "/account/billing"
         When I click "Update" link
         Then I should be redirected to the "UPDATE CREDIT CARD" page
         When I click the "Cancel" link or X icon
         Then The "UPDATE CREDIT CARD" page should be closed
          And I should back to the Billing Information tab

    Scenario: check the Stripe link
        Given I am at "/account/billing"
         When I click "Update" link
         Then I should be redirected to the "UPDATE CREDIT CARD" page
         When I click the "Stripe" link
         Then The page "https://stripe.com/" should be opened in a new tab