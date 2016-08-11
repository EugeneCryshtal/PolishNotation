# PolishNotation (EN) 
*описание на русском ниже*

The main class is - `Notation`. It's abstract class which has all the functionality of the project. Because the algorithms of prefix and postfix notations are very similar - abstract class turned up as great solution. 

Full code cycle is: `new InfixNotation('expression')`->`validate()`->`toPostfix/toPrefix`->`operate()` which includes `convert()` and `calculate()`.

The usage is quite simple:

1. Create InfixNotation object with only parameter 'input string' *input string form is written below*;
2. Convert to Prefix or Postfix notations via methods `toPrefixNotation()``toPostfixNotation`. Also they return new appropriated objects (postfix or prefix);
3. Get output data by methods:

* `getResult()` for expression valuation result;
* `getResultOutput()` or `getOutputExpression()` for getting converted expression (string or list corresponding);

Input string form is here:
Put whitespaces between **every** character **but negative numbers**. New characters in parentheses should also start after whitespace.
For example: `4 + 3 - 20`, `( 700.0 * 24 / -20 )`, `( ( ( 33 + 11 ) - 2 ^ 7 ) * -44 )`;

Operations available: addition (+), subtraction (-), multiplication (*), division (/), exponentiation (^).
Also floating numbers are available. The result will be presented as integer or floating number depending. Multi brackets are available too.

The validation is quite weak due to lack of time and not such good relations with RegEx I've made simple validation that validates just expression without brackets.

For best testing I used results of both notations and compare them with inputed expression's result. You can easily put your own tests in json file at resources package. Just put the expression as a key and evaluation as a value. 

For all questions and suggestions please contact me: ugncry@gmail.com or in gitter @ugncry.
Best wishes!

# PolishNotation (RU)

Главный класс - `Notation`. Абстрактный класс выполняющий прктически всю функциональность проекта. Поскольку алгоритмы постфиксной и префиксной нотации очень похожи - абстрактный класс оказался отличным решением проблемы. 

Полный цикл приложения следующий: `new InfixNotation('выражение')`->`validate()`->`toPrefix/Postfix`->`operate()` который включает в себя методы `convert()` и `calculate()`.

Использование класса крайне простое:

1. Создайте объект `InfixNotation` с параметрами входной строки. *форма вводного выражения будет описано ниже*;
2. Конвертируйте в постфиксную или префиксную нотацию с помощью соответствующих методов `toPrefixNotation()``toPostfixNotation`. Также эти методы возвращают соответствующие объекты с уже готовыми выходными данными;
3. Достаньте выходные данные:

* `getResult()` для результата вычисления;
* `getResultOutput()` или `getOutputExpression()` для конвертированного выражния (в форме строки или коллекции соответственно).

Форма входной строки:
Разделите каждый символ пробелами кроме отрицательных чисел например `-4`, `5 / -4`. Выражение в скобке должно также начинаться и заканчиваться пробелом. Примеры: `4 + 3 - 20`, `700.0 * 24 / -20`, `( ( ( 33 + 11 ) - 2 ^ 7 ) * -44 )`; Примеры **некорректных** выражений: `5-5 + 3`, `((( 25 + 25 )))`, `- 4 * 8`;

Доступные операции: сложение (+), вычитание (-), умножение (*), деление (/), возведение в степень (^). Доступны вложенные скокби. 
Также можно использовать числа с плавающей точкой. Результатом вычисления выражения будет строка в виде целого числа или с плавающей точкой в зависимости от ответа.

Валидация вышла весьма посредственной, на данном этапе я успел выполнить валидацию только простых выражений (без скобок)

Лучшим методом для тестирования оказалось сравнивание результатов вычисления постфиксной и префиксной записей. По скольку перевод в постфиксную запись мануально (особенно в префиксную) занимает куда больше времени, нежели обычное вычисление выражения, было принято использовать следующую методику:

В папке `resources` имеется документ JSON-формата, вы легко можете добавлять входящее выражение в инфиксной записи в качестве ключа и его результат в качестве значения. В тесте же происходит сравнение введенного вами выражения, а вернее сказать его результат и сравнивается с результатами вычисления префиксной и постфиксной записи. 

Для всех вопросов и предложений пожалуйста свяжитесь со мной ugncry@gmail.com или в гиттере @ugncry.
Всего самого наилучшего!
