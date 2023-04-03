package com.board.icia.exception;

public class DBException extends RuntimeException {
	public DBException() {
//		@Transactional은 기본적으로 RuntimeException 예외 발생하면 롤백
		super("DBException 예외 발생 rollback 실시");
	}
}
