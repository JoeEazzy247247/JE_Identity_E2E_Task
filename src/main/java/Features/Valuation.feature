Feature: Car Valuation

  Scenario Outline: Valuation test by Registration Type (BMW|SKODA|Volkswagen)
    Given I am on the valuation page
    When I search vehicle registration by model name '<Make>'
    Then search result is displayed '<Make>'

    Examples:
    |Make      |
    |BMW       |
    |SKODA     |
    |Volkswagen|

  Scenario Outline: Valuation test by Registration Type (Toyota)
    Given I am on the valuation page
    When I search vehicle registration by model name '<Make>'
    Then errorMsg is displayed
    Examples:
      |Make      |
      |Toyota    |