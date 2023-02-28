package hr.fer.oprpp1.hw02.prob1;

public enum TokenType {
    /* Označava da nema više tokena. */
    EOF,
    /* Označava niz brojeva, slova te simbola.*/
    WORD,
    /* Svaki niz brojeva od jedne ili više znamenki. */
    NUMBER,
    /* Svaki znak koji se dobije kad se iz teksta uklone sve riječi, brojevi i praznine. */
    SYMBOL;
}
