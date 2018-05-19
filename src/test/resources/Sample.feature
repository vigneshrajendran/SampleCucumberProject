Feature: Amazon test

  Scenario: Assert Home Page
    Given Open 'https://www.amazon.com/'
    Then assert page header to 'Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more'

  @LoginNegative
  Scenario Outline: Negative cases for Login flow
    Given Open 'https://www.amazon.com/'
    When click 'Button_AccountsList'
    And type '<Email>' into 'Text_Email'
    And click 'Button_Continue'
    And type '<Password>' into 'Text_Password'
    And click 'Button_SignIn'
    Then assert '<ErrorMsg>' with 'Label_ErrorMsg'

    Examples:
      | Email                     | Password  | ErrorMsg                                                                                                                          |
      | abc@abc.com               | vignesh3r | To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below. |
      | amazonsample142@gmail.com | abc       | Your password is incorrect                                                                                                        |

  @LoginPositive
  Scenario Outline: Positive case for Adding and veryfing Product in Cart
    Given Open 'https://www.amazon.com/'
    When click 'Button_AccountsList'
    And type '<Email>' into 'Text_Email'
    And click 'Button_Continue'
    And type '<Password>' into 'Text_Password'
    And click 'Button_SignIn'
    Then assert 'Hello, Sample' with 'Lable_UserName'
    And type 'iPhone X 64GB' into 'Text_Search'
    And click 'Button_Go'
    And click on 1st product
    And get product name from 'Lable_ProductName'
    And get price from 'Lable_Price'
    And click 'Button_AddToCart'
    Then assert 'Added to Cart' with 'Lable_AddedToCard'
    And click 'Button_Cart'
    Then assert product name in 'Lable_CartProductName'
    Then assert price in 'Lable_CartPrice'
    And click signOut under 'Button_AccountsList'
    And type '<Email>' into 'Text_Email'
    And click 'Button_Continue'
    And type '<Password>' into 'Text_Password'
    And click 'Button_SignIn'
    Then assert 'Hello, Sample' with 'Lable_UserName'
    And click 'Button_Cart'
    Then assert product name in 'Lable_CartProductName'
    Then assert price in 'Lable_CartPrice'
    And click signOut under 'Button_AccountsList'

    Examples:
      | Email                     | Password  |
      | amazonsample142@gmail.com | vignesh3r |