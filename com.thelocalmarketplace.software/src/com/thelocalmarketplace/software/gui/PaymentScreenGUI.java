package com.thelocalmarketplace.software.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentScreenGUI {
    private JFrame paymentPageFrame;
    private JPanel paymentPagePanel;
    private JButton cashButton;
    private JButton creditButton;
    private JButton debitButton;
    private JButton notifyAttendantButton;
    private JButton backToCartButton;
    private JButton finishCheckoutButton;
    private JLabel totalPriceLabel;
    private JLabel itemsInCartLabel;
    private JList<String> cartItemList;
    private DefaultListModel<String> cartListModel;

    public PaymentScreenGUI() {
        paymentPageFrame = new JFrame("The LocalMarketplace Self-Checkout Station");
        paymentPagePanel = new JPanel();

        addWidgets();

        paymentPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentPageFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        paymentPageFrame.setContentPane(paymentPagePanel);  // Set the content pane to the panel
        paymentPageFrame.setVisible(true);
    }

    private void addWidgets() {
        paymentPagePanel.setLayout(new GridLayout(1, 3));  // 1 row, 2 columns


        // left panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 1, 0, 20));

        // buttons
        cashButton = new JButton("Cash");
        creditButton = new JButton("Credit");
        debitButton = new JButton("Debit");
        notifyAttendantButton = new JButton("Notify Attendant");
        backToCartButton = new JButton("Back to cart");

        buttonsPanel.add(cashButton);
        buttonsPanel.add(creditButton);
        buttonsPanel.add(debitButton);
        buttonsPanel.add(notifyAttendantButton);
        buttonsPanel.add(backToCartButton);

        paymentPagePanel.add(buttonsPanel);

        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(10,1));

        finishCheckoutButton = new JButton("Finish Checkout");
        totalPriceLabel = new JLabel("Total Price:");
        itemsInCartLabel = new JLabel("Items in cart");

        cartListModel = new DefaultListModel<>();
        cartItemList = new JList<>(cartListModel);
        JScrollPane cartScrollPane = new JScrollPane(cartItemList);

        rightPanel.add(itemsInCartLabel);
        rightPanel.add(cartScrollPane, BorderLayout.SOUTH);
        rightPanel.add(totalPriceLabel);
        rightPanel.add(finishCheckoutButton, BorderLayout.SOUTH);

        paymentPagePanel.add(rightPanel);
    }

    public static void main(String[] args) {
        PaymentScreenGUI paymentScreen = new PaymentScreenGUI();
    }
}
