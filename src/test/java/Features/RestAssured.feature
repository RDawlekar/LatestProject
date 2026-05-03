#Author: reshmadawlekar41@gma.domain.com


Feature: Verify place api
  
  @RestAssured
  Scenario: add place for map
    Given User calls "Add Place" payload
   	When user calls the "PostAPI"   
    Then "status" must be added with response code for "OK"
     Then "scope" must be added with response code for "APP"
    