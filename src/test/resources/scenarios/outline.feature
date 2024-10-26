Feature: Калькулятор

#  Background: Я нахожусь в главном фрейме калькулятора

  Scenario Outline: Стандартные сценарии со всеми операциями и с учётом нажатия всех кнопок
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue>"
    When Я ввожу операцию "<action>"
    When Я нажимаю на калькуляторе цифры "<secondValue>"
    When Я нажимаю на равно
    Then Я вижу результат "<result>"

    Examples:
      | firstValue | secondValue | action | result |
      | #buttonNum1, #buttonNum2| #buttonNum3, #buttonNum4| #buttonPlus | 46,0 |
      | #buttonNum5, #buttonNum6| #buttonNum7, #buttonNum1| #buttonMinus | -15,0 |
      | #buttonNum2, #buttonNum9| #buttonNum1, #buttonNum0| #buttonMultiply | 290,0 |
      | #buttonNum8, #buttonNum6, #buttonNum5 | #buttonNum4| #buttonDivide | 216,25 |

  Scenario Outline: Сценарии со всеми операциями без второго аргумента
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue>"
    When Я ввожу операцию "<action>"
    When Я нажимаю на равно
    Then Я вижу результат "<result>"
    Examples:
      | firstValue | action | result |
      | #buttonNum1, #buttonNum2| #buttonPlus | 24,0 |
      | #buttonNum5, #buttonNum6, #buttonNegativeOp| #buttonMinus | 0,0 |
      | #buttonNum2, #buttonNum1, #buttonNegativeOp| #buttonMultiply | 441,0 |
      | #buttonNum8, #buttonNum6, #buttonNum5 | #buttonDivide | 1,0 |

  Scenario Outline: Сценарии со всеми махинациями с минусом
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue>"
    When Я ввожу операцию "<action>"
    When Я нажимаю на калькуляторе цифры "<secondValue>"
    When Я нажимаю на равно
    Then Я вижу результат "<result>"
    Examples:
      | firstValue | secondValue | action | result |
      | #buttonNum1, #buttonNegativeOp| #buttonNum3, #buttonNegativeOp| #buttonPlus | -4,0 |
      | #buttonNum1, #buttonNegativeOp, #buttonNum2| #buttonNum7, #buttonNegativeOp| #buttonMinus | -5,0 |
      | #buttonNegativeOp, #buttonNum9| #buttonNum8| #buttonMultiply | 72,0 |
      | #buttonNum8, #buttonNegativeOp, #buttonNegativeOp, #buttonNum5 | #buttonNum2| #buttonDivide | 42,5 |


  Scenario Outline: Сценарии со всеми махинациями с запятой
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue>"
    When Я ввожу операцию "<action>"
    When Я нажимаю на калькуляторе цифры "<secondValue>"
    When Я нажимаю на равно
    Then Я вижу результат "<result>"
    Examples:
      | firstValue | secondValue | action  | result |
      | #buttonComma, #buttonNegativeOp, #buttonNum3|#buttonNegativeOp, #buttonNum9| #buttonPlus | 8,7 |
      | #buttonNum1, #buttonNum2, #buttonComma, #buttonNum3| #buttonNum7, #buttonComma, #buttonNum7, #buttonComma| #buttonMinus | 4,6 |

  Scenario: Сценарий с вводом нескольких операций
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "#buttonNum1, #buttonNum4"
    When Я ввожу операцию "#buttonDivide"
    When Я ввожу операцию "#buttonMultiply"
    When Я ввожу операцию "#buttonPlus"
    When Я нажимаю на калькуляторе цифры "#buttonNum5, #buttonNum6"
    When Я нажимаю на равно
    Then Я вижу результат "70,0"

  Scenario Outline: Сценарии c несколькими операциями и более чем 2-мя переменными
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue>"
    When Я ввожу операцию "<action1>"
    When Я нажимаю на калькуляторе цифры "<secondValue>"
    When Я ввожу операцию "<action2>"
    When Я нажимаю на калькуляторе цифры "<thirdValue>"
    When Я нажимаю на равно
    Then Я вижу результат "<result>"
    Examples:
      | firstValue | action1 | secondValue| action2 | thirdValue | result |
      | #buttonNum3 | #buttonPlus | #buttonNum2 | #buttonMinus | #buttonNum1 | 4,0 |
      | #buttonNum8 | #buttonMultiply | #buttonNum4 | #buttonDivide | #buttonNum2 | 16,0 |

  Scenario Outline: Сценарии с делением на 0 и на слишком малое число
    Given Открываю главное окно калькулятора
    When Я нажимаю на калькуляторе цифры "<firstValue> "
    When Я ввожу операцию "<action>"
    When Я нажимаю на калькуляторе цифры "<secondValue>"
    When Я нажимаю на равно
    Then Я вижу сообщение об ошибке "<errorText>"
    Examples:
      | firstValue   | secondValue | action | errorText |
      | #buttonNum3  | #buttonNum0 | #buttonDivide  | Ошибка: Значение второго аргумента b слишком мало: \|b\| < 10e-8 |
      | #buttonNum8  | #buttonComma, #buttonNum0, #buttonNum0, #buttonNum0, #buttonNum0, #buttonNum0, #buttonNum0, #buttonNum1  | #buttonDivide | Ошибка: Значение второго аргумента b слишком мало: \|b\| < 10e-8 |