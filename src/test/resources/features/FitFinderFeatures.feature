Feature: Fit Finder API functionalities

#  # Owner user stories
#  # PUBLIC - POST /api/auth/register
#  Scenario: User (gym owner) can create an account
#    Given An email address is unused
#    When I create an account with the email address
#    Then I see my account is created
#
#  # PUBLIC - POST /api/auth/login
#  Scenario: User (gym owner) can log in
#    Given An account is not logged in
#    When I log in with valid credentials
#    Then I see I am logged in
#
#  # PRIVATE - /api/owners/{ownerId}
#  Scenario: User (gym owner) can manage accounts details
#    Given I am logged into my account
#    # GET
#    When I go to my account details page
#    Then I see my account details
#    # PUT
#    When I update my account details
#    Then I see my profile is updated
#    # DELETE
#    When I delete my account
#    Then I see my account is deleted

  # public test 1/5 ##############################################
  # PUBLIC - GET /api/owners/{ownerId}/gyms
  Scenario: User can see all gyms belonging to an owner
    Given A gym owner account is available
    When I search for gyms belonging to the owner
#    Then I see a list of gyms belonging to the owner

#  # Gym user stories
#  # PRIVATE - POST /api/gyms
#  Scenario: User (gym owner) can create a gym
#    Given I am logged into my account
#    When I create a gym
#    Then I see the gym is created
#
#  # public test 2/5 ##############################################
#  # PUBLIC - GET /api/gyms
#  Scenario: User can retrieve all gyms
#    Given There are gyms available
#    When I search for gyms
#    Then I see a list of all gyms
#
#  # public test 3/5 ##############################################
#  # PUBLIC - GET /api/gyms/{gymId}
#  Scenario: User can retrieve gym details
#    Given A gym exists
#    When I search for the gym
#    Then I see the details of gym
#
#  # PRIVATE - /api/gyms/{gymId}
#  Scenario: User (gym owner) can manage gyms
#    Given I am logged into my account
#    And A gym exists
#    # PUT
#    When I update the details for gym
#    Then I see the gym is updated
#    # DELETE
#    When I delete gym
#    Then I see the gym is deleted
#
#  # Equipment user stories
#  # PRIVATE - POST /api/gyms/{gymId}/equipment
#  Scenario: User (gym owner) can create equipment for a gym
#    Given I am logged into my account
#    And A gym exists
#    When I create equipment for the gym
#    Then I see the equipment is created for the gym
#
#  # public test 4/5 ##############################################
#  # PUBLIC - GET /api/gyms/{gymId}/equipment
#  Scenario: User can see all equipment for a gym
#    Given A gym exists
#    When I search for a gym's equipment
#    Then I see a list of all equipment for the gym
#
#  # PRIVATE - /api/gyms/{gymId}/equipment/{equipmentId}
#  Scenario: User (gym owner) can manage equipment for a gym
#    Given I am logged into my account
#    And A gym exists
#    # PUT
#    When I update the equipment details for the gym
#    Then I see the equipment is updated
#    # DELETE
#    When I delete the equipment equipment from the gym
#    Then I see the equipment is deleted
#
#  # Amenity user stories
#  # PRIVATE - POST /api/gyms/{gymId}/amenity
#  Scenario: User (gym owner) can create an amenity for a gym
#    Given I am logged into my account
#    And A gym exists
#    When I create an amenity for the gym
#    Then I see the amenity is created for the gym
#
#  # public test 5/5 ##############################################
#  # PUBLIC - GET /api/gyms/{gymId}/amenity
#  Scenario: User can see all amenities for a gym
#    Given A gym exists
#    When I search for a gym's amenities
#    Then I see a list of all amenities for the gym
#
#  # PRIVATE - /api/gyms/{gymId}/amenity/{amenityId}
#  Scenario: User (gym owner) can manage amenities for a gym
#    Given I am logged into my account
#    And A gym exists
#    # PUT
#    When I update the amenity details for the gym
#    Then I see the amenity is updated
#    # DELETE
#    When I delete an amenity from the gym
#    Then I see the amenity is deleted