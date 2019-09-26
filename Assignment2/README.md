# Assignment 2 - Syntax Analysis / Expression Parsing / AST building

_Ruslan Shakirov, B17-SE-01_

## Usage example

Import packages
```java
import ExpressionsAST.ASTBuilder;
import ExpressionsAST.Expression;
import ExpressionsAST.InvalidExpressionException;
```

Use `ASTBuilder` to build an AST
```java
String expr = "1 + (26 - 98) * 15 + 777";

ASTBuilder builder = new ASTBuilder(expr);
Expression tree = builder.build();
```

Evaluate an expression
```java
long result = tree.eval();
```
