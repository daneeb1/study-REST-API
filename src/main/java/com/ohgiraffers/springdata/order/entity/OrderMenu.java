package com.ohgiraffers.springdata.order.entity;

import com.ohgiraffers.springdata.menu.entity.Menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class OrderMenu {

    @Entity
    @Table(name = "tbl_order_menu")
    public class OrderMenu {

        private OrderPK orderPk;

        @Column(name = "order_code")
        private Order order;

        @Column(name = "menu_code")
        private Menu menu;
        @Column(name = "order_amount")
        private int orderAmount;

    }






}
