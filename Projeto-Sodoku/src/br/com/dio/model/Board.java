package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatusEnum.COMPLETE;
import static br.com.dio.model.GameStatusEnum.INCOMPLETE;
import static br.com.dio.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        if (spaces == null || spaces.isEmpty()) {
            throw new IllegalArgumentException("A lista de espaços não pode ser nula ou vazia.");
        }
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus() {
        if (isNonStarted()) {
            return NON_STARTED;
        }
        return hasIncompleteSpaces() ? INCOMPLETE : COMPLETE;
    }

    private boolean isNonStarted() {
        return spaces.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && nonNull(s.getActual()));
    }

    private boolean hasIncompleteSpaces() {
        return spaces.stream()
                .flatMap(Collection::stream)
                .anyMatch(s -> isNull(s.getActual()));
    }

    public boolean hasErrors(){
        if(getStatus() == NON_STARTED){
            return false;
        }

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    private boolean isValidIndex(final int col, final int row) {
        return col >= 0 && col < spaces.size() && row >= 0 && row < spaces.get(col).size();
    }

    private boolean isSpaceFixed(final int col, final int row) {
        return spaces.get(col).get(row).isFixed();
    }

    public boolean changeValue(final int col, final int row, final int value) {
        if (!isValidIndex(col, row) || isSpaceFixed(col, row)) {
            return false;
        }
        spaces.get(col).get(row).setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        if (!isValidIndex(col, row) || isSpaceFixed(col, row)) {
            return false;
        }
        spaces.get(col).get(row).clearSpace();
        return true;
    }

    public void reset(){
        spaces.stream()
              .flatMap(Collection::stream)
              .forEach(Space::clearSpace);
    }

    public boolean gameIsFinished(){
        return !hasErrors() && getStatus().equals(COMPLETE);
    }

}
