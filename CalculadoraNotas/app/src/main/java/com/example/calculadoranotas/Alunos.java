package com.example.calculadoranotas;

public class Alunos {
    private String nome;
    private String nomeDisciplina;
    private double notaAtividade1;
    private double notaAtividade2;
    private double notaProva;
    private int faltas;
    private double media;
    private double pesoAtividade = 0.40;
    private double pesoProva = 0.60;


    public Alunos(String nome, String nomeDisciplina, double notaAtividade1,
                  double notaAtividade2, double notaProva, int faltas) {
        this.nome = nome;
        this.nomeDisciplina = nomeDisciplina;
        this.notaAtividade1 = notaAtividade1;
        this.notaAtividade2 = notaAtividade2;
        this.notaProva = notaProva;
        this.faltas = faltas;
    }

    public double calcularMedia() {
        double somaAtividade = (this.notaAtividade1 + this.notaAtividade2 + this.notaProva) * pesoAtividade;
        double notaProva = this.notaProva * this.pesoProva;
        this.media = somaAtividade + notaProva;
        return this.media;
    }

    public boolean verificarAprovacao() {
        if (this.faltas > 4) {
                return false;
            } else if (media >= 6.8) {
                return true;
            } else {
                return false;
            }
        }
}
