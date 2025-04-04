package br.com.dio.model;

/**
 * Representa um espaço em um tabuleiro de Sudoku.
 * Pode ser fixo (pré-definido) ou editável.
 */
public class Space {

    private Integer actual;
    private final int expected;
    private final boolean fixed;


    public Space(final int expected, final boolean fixed) {
        this.expected = expected;
        this.fixed = fixed;
        if (fixed){
            actual = expected;
        }
    }

    public Integer getActual() {
        return actual;
    }

    /**
     * Define o valor atual do espaço.
     * @param actual O valor a ser definido (entre 1 e 9).
     * @throws IllegalArgumentException se o valor estiver fora do intervalo permitido.
     */
    public void setActual(final Integer actual) {
        if (fixed) return;
        if (actual != null && (actual < 1 || actual > 9)) {
            throw new IllegalArgumentException("O valor deve estar entre 1 e 9.");
        }
        this.actual = actual;
    }

    /**
     * Limpa o valor atual do espaço, definindo-o como null.
     */
    public void clearSpace(){
        setActual(null);
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }

    @Override
    public String toString() {
        return "Space{" +
                "actual=" + actual +
                ", expected=" + expected +
                ", fixed=" + fixed +
                '}';
    }
}
