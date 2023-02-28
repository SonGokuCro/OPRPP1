package hr.fer.oprpp1.hw02.prob1;

public class Lexer {
    private char[] data; // ulazni tekst
    private Token token; // trenutni token
    private int currentIndex; // indeks prvog neobrađenog znaka

    // konstruktor prima ulazni tekst koji se tokenizira
    public Lexer(String text) {
        if(text == null) {
            throw new NullPointerException("msg");
        } else if(text.isBlank()) {
            this.token = new Token(TokenType.EOF, null);
        }
//        else {
//            text = text.trim();
//            this.data = text.toCharArray();
//            this.currentIndex = 0;
//        }
    }

    // generira i vraća sljedeći token
    // baca LexerException ako dođe do pogreške
    public Token nextToken() {
        if(this.token.getType() != TokenType.EOF) {
            Token tolkien = new Token(null, null);
            this.token = tolkien;
        }
        if(this.token.getType() == TokenType.EOF) {
            throw new LexerException("msg");
        }
        return this.token;
    }
    // vraća zadnji generirani token; može se pozivati
    // više puta; ne pokreće generiranje sljedećeg tokena
    public Token getToken() {

        return token;
    }
}