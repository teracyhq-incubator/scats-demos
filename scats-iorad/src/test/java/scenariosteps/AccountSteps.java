package scenariosteps;

import org.teracy.scats.core.steps.BaseSteps;
import org.teracy.scats.core.steps.MainActionSteps;
import org.teracy.scats.core.steps.VerificationSteps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class AccountSteps {
	@Steps
	MainActionSteps mainActionSteps;

	@Steps
	BaseSteps baseSteps;

	@Steps
	VerificationSteps verificationSteps;

	/**
	 * input email and password to login form
	 * 
	 * @param email
	 * @param password
	 * @throws Throwable
	 */
	public void inputEmailAndPassword(String email, String password) throws Throwable {
		mainActionSteps.enterIntoFieldWithValue(email,"login.email");
		mainActionSteps.enterIntoFieldWithValue(password,"login.password");
		mainActionSteps.clickOn("login.login_button");
	}

	/**
	 * open left menu panel
	 * 
	 * @throws Throwable
	 */
	public void openLeftMenuPanel() throws Throwable {
		if (baseSteps.waitForGetElementPresent("left_menu.left_menu_panel") == null) {
			if (baseSteps.waitForGetElementPresent("left_menu.left_menu_icon") == null) {
				mainActionSteps.switchToIFrame("left_menu.iorad_app_iframe");
				mainActionSteps.clickOn("left_menu.left_menu_icon_iframe");
				mainActionSteps.switchToParentIFrame();
			} else {
				mainActionSteps.clickOn("left_menu.left_menu_icon");
			}
		}
	}

	@Given("^I am logged-in as a unverified user$")
	public void i_am_logged_in_as_a_unverified_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("unverifieduser", "validpassword");
		}
	}

	@When("^I click Account on the left panel$")
	public void i_click_Account_on_the_left_panel() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		openLeftMenuPanel();
		mainActionSteps.clickOn("left_menu.account");
	}

	@Then("^I should be redirected to the \"([^\"]*)\" tab at the URL \"([^\"]*)\"$")
	public void i_should_be_redirected_to_the_tab_at_the_URL(String arg1, String url) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.iShouldBeRedirectedToPage(BaseSteps.getConfig("webdriver.base.url") + "/" + url);
		verificationSteps.theElementShouldBeShown("account.active_tab",arg1);

	}

	@Then("^I should see the \"([^\"]*)\" tab below \"([^\"]*)\"$")
	public void i_should_see_the_tab_below(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account.order_2_tab",arg2,arg1);
	}

	@Then("^I should be in Free Plan with the price as \\$(\\d+)$")
	public void i_should_be_in_Free_Plan_with_the_price_as_$(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.price_plan",String.valueOf(arg1),String.valueOf(arg1)+String.valueOf(arg1));
	}

	@Then("^I should see the Upgrade Plan button referring to the \"([^\"]*)\" page$")
	public void i_should_see_the_Upgrade_Plan_button_referring_to_the_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.upgrade_plan_button");
		mainActionSteps.clickOn("account_summary.upgrade_plan_button");
		verificationSteps.iShouldBeRedirectedToPage(BaseSteps.getConfig("webdriver.base.url") + "/" + arg1);
		baseSteps.moveBackwardOnePage();
	}

	@Then("^I should see TUTOTIRAL STATS: Public, Private, Live and Themes$")
	public void i_should_see_TUTOTIRAL_STATS_Public_Private_Live_and_Themes() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_public");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_private");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_live");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_themes");
	}

	@Then("^I should have unlimited public tutorials and (\\d+) live tutorials$")
	public void i_should_have_unlimited_public_tutorials_and_live_tutorials(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.unlimited_public_tutorials");
		verificationSteps.theElementShouldBeShown("account_summary.live_tutorial",String.valueOf(arg1));
	}

	@Then("^There should be the Upgrade link pointing to the \"([^\"]*)\" page below Private and Themes$")
	public void there_should_be_the_Upgrade_link_pointing_to_the_page_below_Private_and_Themes(String arg1)
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.upgrade_themes_link");
		verificationSteps.theElementShouldBeShown("account_summary.upgrade_private_link");
		mainActionSteps.clickOn("account_summary.upgrade_themes_link");
		verificationSteps.iShouldBeRedirectedToPage(BaseSteps.getConfig("webdriver.base.url") + "/" + arg1);
		baseSteps.moveBackwardOnePage();
		mainActionSteps.clickOn("account_summary.upgrade_private_link");
		verificationSteps.iShouldBeRedirectedToPage(BaseSteps.getConfig("webdriver.base.url") + "/" + arg1);
		baseSteps.moveBackwardOnePage();
	}

	@Then("^The Account Status should be shown as \"([^\"]*)\"$")
	public void the_Account_Status_should_be_shown_as(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.status_account",arg1);
	}

	@Then("^I should see the link Resend next to the Account info$")
	public void i_should_see_the_link_Resend_next_to_the_Account_info() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.resend_link");
	}

	@Then("^I should see the \"([^\"]*)\" link$")
	public void i_should_see_the_link(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.link",arg1);
	}

	@Given("^I am logged-in as a free verified user$")
	public void i_am_logged_in_as_a_free_verified_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("freeverifieduser", "validpassword");
		}
	}

	@Then("^I should see (\\d+) tabs: \"([^\"]*)\", \"([^\"]*)\"$")
	public void i_should_see_tabs(int arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theNumberOfElementShouldBe("account.number_tab", arg1);
		verificationSteps.theElementShouldBeShown("account.order_2_tab",arg2,arg3);
	}

	@Given("^I am logged-in as a premium individual user$")
	public void i_am_logged_in_as_a_premium_individual_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("premiumindividualuser", "validpassword");
		}
	}

	@Then("^I should see (\\d+) tabs: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void i_should_see_tabs(int arg1, String arg2, String arg3, String arg4) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theNumberOfElementShouldBe("account.number_tab", arg1);
		verificationSteps.theElementShouldBeShown("account.order_3_tab",arg2,arg3,arg4);
	}

	@Then("^I should be in Premium Private Plan$")
	public void i_should_be_in_Premium_Private_Plan() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.premium_private_plan");
	}

	@Then("^I should see the plan price I have to pay$")
	public void i_should_see_the_plan_price_I_have_to_pay() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.price_plan");
	}

	@Then("^I should see my current plan info$")
	public void i_should_see_my_current_plan_info() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.plan_info");
	}

	@Then("^I should see the creator number$")
	public void i_should_see_the_creator_number() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.creator_number");
	}

	@Then("^I should see the \"([^\"]*)\" button referring to the \"([^\"]*)\" page$")
	public void i_should_see_the_button_referring_to_the_page(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.plan_button",arg1);
		mainActionSteps.clickOn("account_summary.plan_button",arg1);
		verificationSteps.iShouldBeRedirectedToPage(BaseSteps.getConfig("webdriver.base.url") + "/" + arg2);
		baseSteps.moveBackwardOnePage();
	}

	@Then("^I should have unlimited public, private and live tutorials$")
	public void i_should_have_unlimited_public_private_and_live_tutorials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.unlimited_public_tutorials");
		verificationSteps.theElementShouldBeShown("account_summary.unlimited_private_tutorials");
		verificationSteps.theElementShouldBeShown("account_summary.unlimited_live_tutorials");
	}

	@Then("^I should see the info about existing tutorial number for Public, Private and Live$")
	public void i_should_see_the_info_about_existing_tutorial_number_for_Public_Private_and_Live() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.public_tutorial_info");
		verificationSteps.theElementShouldBeShown("account_summary.private_tutorial_info");
		verificationSteps.theElementShouldBeShown("account_summary.live_tutorial_info");
	}

	@Then("^I should see the info about theme number$")
	public void i_should_see_the_info_about_theme_number() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.themes_info");
	}

	@Then("^The Billing Status should be \"([^\"]*)\"$")
	public void the_Billing_Status_should_be(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.status_billing",arg1);
	}

	@Then("^The Account Status should be \"([^\"]*)\"$")
	public void the_Account_Status_should_be(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.status_account",arg1);
	}

	@Given("^I am logged-in as a team owner$")
	public void i_am_logged_in_as_a_team_owner() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("teamowneruser", "validpassword");
		}
	}

	@Then("^I should see (\\d+) tabs: \"([^\"]*)\", \"([^\"]*)\", and \"([^\"]*)\"$")
	public void i_should_see_tabs_and(int arg1, String arg2, String arg3, String arg4) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theNumberOfElementShouldBe("account.number_tab", arg1);
		verificationSteps.theElementShouldBeShown("account.order_3_tab",arg2,arg3,arg4);
	}

	@Given("^I am logged-in as a team member$")
	public void i_am_logged_in_as_a_team_member() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("teammemberuser", "validpassword");
		}
	}

	@Then("^I should see (\\d+) tabs: \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_should_see_tabs_and(int arg1, String arg2, String arg3) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theNumberOfElementShouldBe("account.number_tab", arg1);
		verificationSteps.theElementShouldBeShown("account.order_2_tab",arg2,arg3);
	}

	@Then("^I should see the plan price as \\$(\\d+)$")
	public void i_should_see_the_plan_price_as_$(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.price_plan_value",String.valueOf(arg1),String.valueOf(arg1)+String.valueOf(arg1));
	}

	@Then("^I should see the info Team member - no charge$")
	public void i_should_see_the_info_Team_member_no_charge() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.creator_number");
	}

	@Then("^I should see the \"([^\"]*)\" button$")
	public void i_should_see_the_button(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.team_button",arg1);
	}

	@Given("^I am logged-in as a locked user$")
	public void i_am_logged_in_as_a_locked_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		baseSteps.getDriver().get((BaseSteps.getConfig("webdriver.base.url") + "/login"));
		if (baseSteps.waitForGetElementPresent("login.login_button") != null) {
			inputEmailAndPassword("lockeduser", "validpassword");
		}
	}

	@Given("^I have locked live tutorials$")
	public void i_have_locked_live_tutorials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see TUTOTIRAL STATS: Public, Private, Locked, Live and Themes$")
	public void i_should_see_TUTOTIRAL_STATS_Public_Private_Locked_Live_and_Themes() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_public");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_private");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_live");
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_themes");
	}

	@Then("^I should see the number of existing public, locked tutorials$")
	public void i_should_see_the_number_of_existing_public_locked_tutorials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.tutorial_stats_public");
	}

	@Then("^I should see (\\d+) of (\\d+) for Live tutorials$")
	public void i_should_see_of_for_Live_tutorials(int arg1, int arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the Upgrade link below Private and Themes$")
	public void i_should_see_the_Upgrade_link_below_Private_and_Themes() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		verificationSteps.theElementShouldBeShown("account_summary.upgrade_themes_link");
		verificationSteps.theElementShouldBeShown("account_summary.upgrade_private_link");
	}

	@Given("^I created a new account$")
	public void i_created_a_new_account() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I haven't activated my account yet$")
	public void i_haven_t_activated_my_account_yet() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am logged in$")
	public void i_am_logged_in() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I open the activation email$")
	public void i_open_the_activation_email() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click the link in the email$")
	public void i_click_the_link_in_the_email() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I should be redirected to My Tutorials page with the notification \"([^\"]*)\"$")
	public void i_should_be_redirected_to_My_Tutorials_page_with_the_notification(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am not logged in yet$")
	public void i_am_not_logged_in_yet() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I should be redirected to the Log in screen with the email filled in the Email field$")
	public void i_should_be_redirected_to_the_Log_in_screen_with_the_email_filled_in_the_Email_field()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I log in successfully$")
	public void i_log_in_successfully() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am logged-in as an owner / individual premium user$")
	public void i_am_logged_in_as_an_owner_individual_premium_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click Account$")
	public void i_click_Account() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Account Summary tab$")
	public void i_should_be_redirected_to_the_Account_Summary_tab() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I navigate to the Billing Information tab at the \"([^\"]*)\" URL$")
	public void i_navigate_to_the_Billing_Information_tab_at_the_URL(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the My Current Credit Card text$")
	public void i_should_see_the_My_Current_Credit_Card_text() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the Visa icon$")
	public void i_should_see_the_Visa_icon() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see four ending numbers of my credit card$")
	public void i_should_see_four_ending_numbers_of_my_credit_card() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see my Transaction History$")
	public void i_should_see_my_Transaction_History() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the View link corresponding to a transaction$")
	public void i_should_see_the_View_link_corresponding_to_a_transaction() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click the View link$")
	public void i_click_the_View_link() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be able to view the invoice in a new tab$")
	public void i_should_be_able_to_view_the_invoice_in_a_new_tab() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am at \"([^\"]*)\"$")
	public void i_am_at(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click \"([^\"]*)\" link$")
	public void i_click_link(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the \"([^\"]*)\" page$")
	public void i_should_be_redirected_to_the_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The \"([^\"]*)\" button should be disabled$")
	public void the_button_should_be_disabled(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I input all valid values into the required fields$")
	public void i_input_all_valid_values_into_the_required_fields() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The \"([^\"]*)\" button should be enabled$")
	public void the_button_should_be_enabled(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click Save$")
	public void i_click_Save() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see an informative message \"([^\"]*)\"$")
	public void i_should_see_an_informative_message(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be back to the Account Summary tab$")
	public void i_should_be_back_to_the_Account_Summary_tab() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am an anonymous user$")
	public void i_am_an_anonymous_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am at \"([^\"]*)\" page$")
	public void i_am_at_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click on \"([^\"]*)\" link$")
	public void i_click_on_link(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am at the \"([^\"]*)\" page$")
	public void i_am_at_the_page(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I fill in my valid email$")
	public void i_fill_in_my_valid_email() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click \"([^\"]*)\"$")
	public void i_click(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the homepage with the message \"([^\"]*)\"$")
	public void i_should_be_redirected_to_the_homepage_with_the_message(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The email should be sent to my email address$")
	public void the_email_should_be_sent_to_my_email_address() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I follow that reset password instruction$")
	public void i_follow_that_reset_password_instruction() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should reset the password successfully$")
	public void i_should_reset_the_password_successfully() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should see the message \"([^\"]*)\"$")
	public void i_should_see_the_message(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Log in screen$")
	public void i_should_be_redirected_to_the_Log_in_screen() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have tutorials in My tutorials$")
	public void i_have_tutorials_in_My_tutorials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The Log In button should be enabled by default$")
	public void the_Log_In_button_should_be_enabled_by_default() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The \"([^\"]*)\" checkbox should be unchecked by default$")
	public void the_checkbox_should_be_unchecked_by_default(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I fill in valid registered username and password$")
	public void i_fill_in_valid_registered_username_and_password() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be logged in and redirected to the \"([^\"]*)\" page at the URL \"([^\"]*)\"$")
	public void i_should_be_logged_in_and_redirected_to_the_page_at_the_URL(String arg1, String arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have no tutorials into My Tutorials$")
	public void i_have_no_tutorials_into_My_Tutorials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have tutorials in My learnings$")
	public void i_have_tutorials_in_My_learnings() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the My Learning tab$")
	public void i_should_be_redirected_to_the_My_Learning_tab() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have no tutorials into My tutorials and My learning$")
	public void i_have_no_tutorials_into_My_tutorials_and_My_learning() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have tutorials in Shared Edit$")
	public void i_have_tutorials_in_Shared_Edit() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I log in as a team member$")
	public void i_log_in_as_a_team_member() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to Shared Edit$")
	public void i_should_be_redirected_to_Shared_Edit() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I have no tutorials in My tutorials, My learning any Shared Edit tabs$")
	public void i_have_no_tutorials_in_My_tutorials_My_learning_any_Shared_Edit_tabs() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I log in$")
	public void i_log_in() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Choose page$")
	public void i_should_be_redirected_to_the_Choose_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I installed the iorad app$")
	public void i_installed_the_iorad_app() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Connecting page$")
	public void i_should_be_redirected_to_the_Connecting_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The capture frame should launch$")
	public void the_capture_frame_should_launch() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I haven't installed the iorad app yet$")
	public void i_haven_t_installed_the_iorad_app_yet() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Download page$")
	public void i_should_be_redirected_to_the_Download_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be able to download the iorad app$")
	public void i_should_be_able_to_download_the_iorad_app() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I am a logged in user$")
	public void i_am_a_logged_in_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Given("^I go to \"([^\"]*)\"$")
	public void i_go_to(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I click on the \"([^\"]*)\" button from the slide out menu$")
	public void i_click_on_the_button_from_the_slide_out_menu(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be logged out and redirected to the home page$")
	public void i_should_be_logged_out_and_redirected_to_the_home_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I navigate to the Personal Information tab$")
	public void i_navigate_to_the_Personal_Information_tab() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be able to see the info: Public Name, Company name \\(if any\\), Email and Avatar$")
	public void i_should_be_able_to_see_the_info_Public_Name_Company_name_if_any_Email_and_Avatar() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should the Change button below the Password field$")
	public void i_should_the_Change_button_below_the_Password_field() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^The Sign Up button should be enabled by default$")
	public void the_Sign_Up_button_should_be_enabled_by_default() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I fill in valid values into the placeholders: Public Name, Email and Password$")
	public void i_fill_in_valid_values_into_the_placeholders_Public_Name_Email_and_Password() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should be redirected to the Choose mode page$")
	public void i_should_be_redirected_to_the_Choose_mode_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should receive an account activation email$")
	public void i_should_receive_an_account_activation_email() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
