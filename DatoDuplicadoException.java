package integrado.prog2.exception;

public class DatoDuplicadoException extends RuntimeException {
    public DatoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
