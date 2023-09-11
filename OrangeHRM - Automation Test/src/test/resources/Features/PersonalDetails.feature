@PersonalDetails
Feature: The Ability to edit Personal Details in My Info


  @EditableFields
  Scenario: Verify only some fields are editable
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    Then Only First Name, Middle Name, Last Name,Other Id, Licence Expiry date,Nationality, Marital Status, Gender are Editable
    Then Employee Id, Driver Licence Number are not editable

  @FirstNameField @ValidScenario
  Scenario Outline: Verify the First Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "First name" field with credential <value>
    Then Click save and Verify "First name" field with <value>

    Examples: 
      | value                            |
      | "   Rei   "                      |
      | "Exactly 29 characters!!!!!!!!"  |
      | "Exactly 30 characters!!!!!!!!!" |
      | "Naomi"                          |

  @FirstNameField @InvalidScenario
  Scenario Outline: Verify the First Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "First name" field with credential <value>
    Then Click save and Verify the "First name" field with error_message <error_message>

    Examples: 
      | value                             | error_message                     |
      | ""                                | "Required"                        |
      | "Exactly 31 characters!!!!!!!!!!" | "Should not exceed 30 characters" |
      | "     "                           | "Required"                        |

  @OtherIDField @ValidScenario
  Scenario Outline: Verify the Other ID Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Other ID" field with credential <value>
    Then Click save and Verify "Other ID" field with <value>

    Examples: 
      | value                            |
      | "   new@1   "                    |
      | "Exactly 29 characters!!!!!!!!"  |
      | "Exactly 30 characters!!!!!!!!!" |
      | "old@2"                          |
      | "     "                          |
      | ""                               |

  @OtherIDField @InvalidScenario
  Scenario Outline: Verify the Other ID Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Other ID" field with credential <value>
    Then Click save and Verify the "Other ID" field with error_message <error_message>

    Examples: 
      | value                             | error_message                     |
      | "Exactly 31 characters!!!!!!!!!!" | "Should not exceed 30 characters" |

  @MiddleNameField @ValidScenario
  Scenario Outline: Verify the Middle Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Middle name" field with credential <value>
    Then Click save and Verify "Middle name" field with <value>

    Examples: 
      | value                            |
      | "   Yagami   "                   |
      | "Exactly 29 characters!!!!!!!!"  |
      | "Exactly 30 characters!!!!!!!!!" |
      | "Moon"                           |
      | "     "                          |
      | ""                               |

  @MiddleNameField @InvalidScenario
  Scenario Outline: Verify the Middle Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Middle name" field with credential <value>
    Then Click save and Verify the "Middle name" field with error_message <error_message>

    Examples: 
      | value                             | error_message                     |
      | "Exactly 31 characters!!!!!!!!!!" | "Should not exceed 30 characters" |

  @LastNameField @ValidScenario
  Scenario Outline: Verify the Last Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Last name" field with credential <value>
    Then Click save and Verify "Last name" field with <value>

    Examples: 
      | value                            |
      | "   Penber   "                   |
      | "Exactly 29 characters!!!!!!!!"  |
      | "Exactly 30 characters!!!!!!!!!" |
      | "Misora"                         |

  @LastNameField @InvalidScenario
  Scenario Outline: Verify the Last Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "Last name" field with credential <value>
    Then Click save and Verify the "Last name" field with error_message <error_message>

    Examples: 
      | value                             | error_message                     |
      | ""                                | "Required"                        |
      | "Exactly 31 characters!!!!!!!!!!" | "Should not exceed 30 characters" |
      | "     "                           | "Required"                        |

  @LicenseExpiryDate @ValidScenario
  Scenario Outline: Verify the Last Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "License Expiry Date" field with credential <value>
    Then Click save and Verify "License Expiry Date" field with <value>

    Examples: 
      | value        |
      | "2020-09-01" |
      | ""           |

  @LicenseExpiryDate @InvalidScenario
  Scenario Outline: Verify the Last Name Text Field
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    When Fill the "License Expiry Date" field with credential <value>
    Then Click save and Verify the "License Expiry Date" field with error_message <error_message>

    Examples: 
      | value                    | error_message                                 |
      | "09-01-2020"             | "Should be a valid date in yyyy-mm-dd format" |
      | "2020-02-30"             | "Should be a valid date in yyyy-mm-dd format" |
      | "                      " | "Should be a valid date in yyyy-mm-dd format" |
      | "31-02-2020"             | "Should be a valid date in yyyy-mm-dd format" |

  @LicenceExpiryDatePicker
  Scenario: Verify the Date Picker Buttons
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And The User clicks on Licence Expiry Date Field
    And The User clicks on close button to close the Date Picker
    And The User clicks on Date Picker Icon Button
    And The User clicks on Today Button and Verify
    Then The User clicks on Clear Button and verify

  @LicenceExpiryDatePicker
  Scenario: Verify the Date Picker functions
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And Picking a valid date as "2002-12-12" and Verify
    Then Picking a valid date as "2023-09-09" and Verify

  @HelpButton
  Scenario: Verify the functionality of Help Btn
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And Click the Help Button
    Then Verify the Help page

  @NationalityDropDown
  Scenario: Verify the functionality to select Nation
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And Select Nation as "Indian"
    Then Verify the Nation is selected as "Indian"
    And Select Nation as "Egyptian"
    Then Verify the Nation is selected as "Egyptian"

  @MaritalStatusDropDown
  Scenario: Verify the functionality to select Nation
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And Select Marital Status as "Married"
    Then Verify the Marital Status is selected as "Married"
    And Select Marital Status as "Other"
    Then Verify the Marital Status is selected as "Other"

  @GenderRadios
  Scenario: Verify the Gender Radio buttons
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And Select the Male radio btn and verify
    And Select the Female radio btn and verify

  @PersonalDetailAttachments @Validscenario
  Scenario: Verify the ability to add Attachements
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    And The User Upload a "small_size.png" file size "Without Comment" as ""
    Then Verify the "small_size.png" file size "Without Comment" as ""
    And The User Upload a "small_size.png" file size "Without Comment" as ""
    Then Verify the "small_size.png" file size "Without Comment" as ""
    And The User Upload a "small_size.png" file size "With Comment" as "A small file"
    Then Verify the "small_size.png" file size "With Comment" as "A small file"
    And The User Upload a "maximum_size.jpg" file size "Without Comment" as ""
    Then Verify the "maximum_size.jpg" file size "Without Comment" as ""

  @PersonalDetailAttachments @Invalidscenario
  Scenario Outline: Verify the ability to add Attachements
    Given The User is Logged in using valid ESS credentials
    When The User Clicks My Info Button
    And The User Clicks on Personal Details Button
    Then The User attempts to upload the file_size <file_name> and verify the error message

    Examples: 
      | file_name                                |
      | "more_than_maximum_size.jpg"             |
      | "small_size_unsupported.exe"             |
      | "maximum_size_unsupported.exe"           |
      | "more_than_maximum_size_unsupported.exe" |
