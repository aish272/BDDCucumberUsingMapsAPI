Feature: Maps APIs are working to add, delete and update places
  @AddPlace
  Scenario Outline: Verify by using add place api new place is successfully added
    Given Add place payload with "<name>" "<phoneNumber>" "<address>"
    When user calls "ADDPLACEAPI" api with "POST" Http request
    Then API call should return 200 success code
    And response body should contain "status" key with value "OK"
    And response body should contain "scope" key with value "APP"
    And verify "<name>" attribute value in the created place using "GETPLACEAPI" http method
    Examples:
    | name            | phoneNumber  | address        |
    | Venuvan, Bharat | 983 893 3334 | East UP, India |
    #| Project Neer    | 983 893 3334 | Banaras        |

  @DeletePlace
  Scenario: Verify by using delete place api new place is successfully delete
    Given Delete place payload
    When  user calls "DELETEPLACEAPI" api with "DELETE" Http request
    Then API call should return 200 success code
    And response body should contain "status" key with value "OK"