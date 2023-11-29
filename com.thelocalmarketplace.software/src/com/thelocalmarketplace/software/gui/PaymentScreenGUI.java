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
    private JLabel selectPaymentLabel;
    private JList<String> cartItemList;

    public PaymentScreenGUI() {
        paymentPageFrame = new JFrame("The LocalMarketplace Self-Checkout Station");
        paymentPagePanel = new JPanel();

        addWidgets();

        paymentPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paymentPageFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        paymentPageFrame.setContentPane(paymentPagePanel);
        paymentPageFrame.setVisible(true);
    }
    private void addWidgets() {
        paymentPagePanel.setLayout(new BorderLayout());

        // top panel
        JPanel topPanel = new JPanel();
        selectPaymentLabel = new JLabel("Select Payment Method");  // Fix this line
        selectPaymentLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(selectPaymentLabel);
        paymentPagePanel.add(topPanel, BorderLayout.NORTH);


        // center panel (containing left and right panels)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 2));

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

        centerPanel.add(buttonsPanel);

        // right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        finishCheckoutButton = new JButton("Finish Checkout");
        finishCheckoutButton.setFont(new Font("Arial", Font.BOLD, 16));  
        finishCheckoutButton.setPreferredSize(new Dimension(150, 50));
        itemsInCartLabel = new JLabel("Items in cart:");
        itemsInCartLabel.setFont(new Font("Arial", Font.BOLD, 15));

        cartItemList = new JList<>();
        cartItemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        cartItemList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        cartItemList.setVisibleRowCount(-1);

        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(cartItemList, BorderLayout.CENTER);
        listPanel.add(itemsInCartLabel, BorderLayout.NORTH);

        totalPriceLabel = new JLabel("Total Price:");
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 15));

        rightPanel.add(listPanel, BorderLayout.CENTER);
        rightPanel.add(totalPriceLabel, BorderLayout.NORTH);
        rightPanel.add(finishCheckoutButton, BorderLayout.SOUTH);

        centerPanel.add(rightPanel);

        paymentPagePanel.add(centerPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        PaymentScreenGUI paymentScreen = new PaymentScreenGUI();
    }
}
