Feature: Fit Finder API functionalities

  ## PUBLIC endpoints

  # PUBLIC - POST /api/owners/register (owner user story)
  Scenario: User (gym owner) can create an account
    Given An email address is unused
    When I create an account with the email address
    Then The account is created

  Scenario: User (gym owner) can log in
    # PUBLIC - GET /api/owners/{ownerId} (owner user story)
    Given A gym owner account is available
    # PUBLIC - POST /api/owners/login (owner user story)
    When I log in with valid credentials
    Then I see I am logged in

  Scenario: User can see gym owner details
    # PUBLIC - GET /api/owners/{ownerId} (owner user story)
    Given A gym owner account is available
    # PUBLIC - GET /api/owners/{ownerId}/gyms (owner user story)
    When A list of gyms is available for the owner
    Then I see a list of gyms belonging to the owner

  # PUBLIC - GET /api/gyms (gym user story)
  Scenario: User can see all gyms
    Given A list of gyms is available
    When I search for gyms
    Then I see a list of all gyms

  Scenario: User can retrieve gym details
    Given A gym is available
    # PUBLIC - GET /api/gyms/{gymId} (gym user story)
    When I search for the gym
    Then I see the details of gym
    # PUBLIC - GET /api/gyms/{gymId}/equipment (equipment user story)
    When I search for the gym equipment
    Then I see a list of all equipment for the gym
    # PUBLIC - GET /api/gyms/{gymId}/amenities (amenity user story)
    When I search for the gym amenities
    Then I see a list of all amenities for the gym

  ## PRIVATE endpoints

  Scenario: User (gym owner) can manage accounts details
    Given I am logged into my account - kim
    # PRIVATE - PUT /api/owners/{ownerId} (owner user story)
    When I update my account details
    Then I see my profile is updated
    # PRIVATE - DELETE /api/owners/{ownerId} (owner user story)
    When I delete my account
    Then I see my account is deleted

  Scenario: User (gym owner) manage a gym
    Given I am logged into my account - ash
    # PRIVATE - POST /api/gyms (gym user story)
    When I create a gym
    Then I see the gym is created
    # PRIVATE - PUT /api/gyms/{gymId} (gym user story)
    When I update the details for gym
    Then I see the gym is updated
    # PRIVATE - DELETE /api/gyms/{gymId} (gym user story)
    When I delete gym
    Then I see the gym is deleted

  Scenario: User (gym owner) can manage equipment for a gym
    Given I am logged into my account - ash
    # PRIVATE - POST /api/gyms/{gymId}/equipment (equipment user story)
    When I create equipment for the gym
    Then I see the equipment is created for the gym
    # PRIVATE - PUT /api/gyms/{gymId}/equipment/{equipmentId} (equipment user story)
    When I update the equipment details for the gym
    Then I see the equipment is updated
    # PRIVATE - DELETE /api/gyms/{gymId}/equipment/{equipmentId} (equipment user story)
    When I delete the equipment from the gym
    Then I see the equipment is deleted

  Scenario: User (gym owner) can manage amenities for a gym
    Given I am logged into my account - ash
    # PRIVATE - POST /api/gyms/{gymId}/amenities (amenity user story)
    When I create an amenity for the gym
    Then I see the amenity is created for the gym
    # PRIVATE - PUT /api/gyms/{gymId}/amenities/{amenityId} (amenity user story)
    When I update the amenity details for the gym
    Then I see the amenity is updated
    # PRIVATE - DELETE /api/gyms/{gymId}/amenities/{amenityId} (amenity user story)
    When I delete the amenity from the gym
    Then I see the amenity is deleted