Feature: Description is automatically set

  I can create a Workspace document, and the description will be set automatically.

  Scenario: Create a Workspace
    When I login as "Administrator"
    And I have a Workspace document
    And I browse to the document with path "/default-domain/workspaces"
    And I click the Create Document button
    And I select Workspace from the Document Type menu
    And I create a document with the following properties:
      | name         | myWorkspace            |
      | title        | My Workspace           |
    Then I see the Workspace page
    And I can see Workspace metadata with the following properties:
      | name         | myWorkspace                |
      | title        | My Workspace               |
      | description  | Nuxeo Cloud document       |
