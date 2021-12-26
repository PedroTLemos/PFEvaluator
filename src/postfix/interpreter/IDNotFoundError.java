package postfix.interpreter;

public class IDNotFoundError extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public IDNotFoundError(String msg) {
        super(msg);
    }
}
