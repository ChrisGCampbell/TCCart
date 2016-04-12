package edu.gatech.seclass.tccart;

/**
 * Created by Kevin Hall on 3/23/2016.
 * Item Class
 */

public class Item {
    private long hex_id;
    private String item_name;
    private double item_cost;
    private int item_quantity;

    Item(long id, String name, double cost, int quantity)
    {
        hex_id = id;
        item_cost = (cost >= 0) ? cost:0;
        item_quantity = quantity;
        item_name = name;
    }

    Item(long id, String name, double cost)
    {
        this(id, name, cost, 1);
    }

    Item(String name, double cost)
    {
        this(0, name, cost, 1);
    }

    Item(String name, double cost, int quantity)
    {
        this(0, name, cost, quantity);
    }

    double getCost()
    {
        return item_cost*item_quantity;
    }

    // Overload to get cost of product * quantity you specify
    double getCost(int quantity){return item_cost*quantity;}

    String getName()
    {
        return item_name;
    }

    public void addQuantity(int quantity)
    {
        item_quantity += ((item_quantity+quantity >= 0) ? quantity:0);
    }
    public int getQuantity()
    {
        return item_quantity;
    }

    // Tea and Coffee Child classes
    public class Tea extends Item {

        Tea(long id, String n, double v)
        {
            super(id, n, v);
        }
    }

    public class Coffee extends Item {

        Coffee(long id, String n, double v)
        {
            super(id, n, v);
        }
    }
}
