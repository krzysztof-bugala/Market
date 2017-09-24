package com.shop.service.cart;

/**
 * Created by kb on 2017-09-09.
 */
public class DefaultSelectedItem implements SelectedItem {

    protected long productBarcode;

    protected int quantity;

    public DefaultSelectedItem(){

    }

    public DefaultSelectedItem(long productBarcode, int quantity)
    {
        this.productBarcode = productBarcode;
        this.quantity = quantity;
    }

    public DefaultSelectedItem(int quantity)
    {
        this.quantity = quantity;
    }

    public long getProductBarcode() {
        return productBarcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean equals(Object object) {
        if (object != null && object instanceof SelectedItem) {
            SelectedItem selectedItem = (SelectedItem)object;

            return this.productBarcode == selectedItem.getProductBarcode()
                    && this.quantity == selectedItem.getQuantity();
        }

        return false;
    }

    public int  hashCode() {
        return (int)this.productBarcode + this.quantity;
    }
}
