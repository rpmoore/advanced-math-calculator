package bptree;


import java.util.Enumeration;

public interface Lexer<E> extends Enumeration<E> {
	E peek();
}
