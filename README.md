JavaBrainfuck
=======

A Java library for reading and evaluating Brainfuck scripts.

Installation
------

To install JavaBrainfuck, you can build from the repo, or alternatively download the pre-built JAR.

If you're just wanting to run Brainfuck scripts (or just see the library in action), a command-line application is provided.

Initial setup
------

To read and evaluate a Brainfuck script, you'll need to create a new `Script` object, and pass the script that you want to evaluate.

```java
Script script = new Script("++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.");
```

The above example will output "Hello World!" to the output stream.

In order to be able to evaluate the script, you'll need to call the `evaluate()` method, and pass the InputStream/OutputStream you want to use as standard input/standard output (for example, ```System.in``` and ```System.out```, respectively.

```java
script.evaluate(System.in, System.out);
```

Roadmap
------

- Add support for configurable tokens