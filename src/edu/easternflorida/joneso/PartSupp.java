package edu.easternflorida.joneso;

public class PartSupp {

    private int psPartKey;
    private int psSuppKey;
    private int psAvailQty;
    private double psSupplyCost;
    private String psComment;

    public PartSupp(int psPartKey, int psSuppKey, int psAvailQty, double psSupplyCost, String psComment) {
        this.psPartKey = psPartKey;
        this.psSuppKey = psSuppKey;
        this.psAvailQty = psAvailQty;
        this.psSupplyCost = psSupplyCost;
        this.psComment = psComment;
    }

    public int getPsPartKey() { return psPartKey; }
    public int getPsSuppKey() { return psSuppKey; }
    public int getPsAvailQty() { return psAvailQty; }
    public double getPsSupplyCost() { return psSupplyCost; }
    public String getPsComment() { return psComment; }
}
