package br.com.dio.model;

import java.util.Arrays;

public enum GameStatusEnum {

    NON_STARTED("não iniciado"),
    INCOMPLETE("incompleto"),
    COMPLETE("completo");

    private final String label;

    GameStatusEnum(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static GameStatusEnum fromLabel(String label) {
        return Arrays.stream(GameStatusEnum.values())
                     .filter(status -> status.label.equalsIgnoreCase(label))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("Status inválido: " + label));
    }
}
