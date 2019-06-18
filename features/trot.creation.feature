Feature: Trot creation
  An administrator wants to add a Trot into the catalog

  @Catalog
  Scenario: Create a Trot by an administrator
    Given an admin user existing in the system
    Given a Trot of brand Xiaomi
    When he want to create a trot
    Then the trot is created

  @Catalog
  Scenario: Create a Trot by an administrator
    Given an admin user existing in the system - REST-assured
    Given a Trot of brand Xiaomi - REST-assured
    When he want to create a trot - REST-assured
    Then the trot is created - REST-assured
