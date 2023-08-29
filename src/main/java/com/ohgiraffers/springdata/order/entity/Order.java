package com.ohgiraffers.springdata.order.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class Order {

    @Id
    @Column(name = "order_code")
    private int orderCode;

    @Column(name = "total_order_price")
    private int totalOrderPrice;

    @Column(name = "order_date",length = 8)
    private String orderDate;

    @Column(name = "order_time",length = 8)
    private String orderTime;
}
