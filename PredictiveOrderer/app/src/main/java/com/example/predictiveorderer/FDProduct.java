package com.example.predictiveorderer;

public class FDProduct extends Product
{
    private int quantity;

    public FDProduct()
    {
        super();
        quantity = 0;
    }

    public FDProduct(int id, String nm, double ct, String desc, int quant)
    {
        super(id, nm, ct, desc);
        quantity = quant;
    }

    public void setQuantity(int quant)
    {
        quantity = quant;
    }

    public int getQuantity()
    {
        return quantity;
    }
}
