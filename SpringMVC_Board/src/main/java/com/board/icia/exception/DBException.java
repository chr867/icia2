package com.board.icia.exception;

public class DBException extends RuntimeException {
	public DBException() {
		super("DBException 예외 발생 rollback 실시");
	}
}
