package com.thelocalmarketplace.software.test.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.Mass;
import com.jjjwelectronics.scanner.BarcodedItem;
import com.tdc.banknote.Banknote;
import com.tdc.coin.Coin;
import com.thelocalmarketplace.hardware.SelfCheckoutStationBronze;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.hardware.SelfCheckoutStationSilver;
import com.thelocalmarketplace.software.controllers.pay.cash.BanknoteDispenserController;
import com.thelocalmarketplace.software.controllers.pay.cash.CoinDispenserController;
import com.thelocalmarketplace.software.logic.CentralStationLogic;

public class CoinDispenserControllerTest {
	private CoinDispenserController inputBronze;
	private CoinDispenserController inputSilver;
	private CoinDispenserController inputGold;

	private CentralStationLogic logicBronze;
	private CentralStationLogic logicSilver;
	private CentralStationLogic logicGold;
	
	
	private BigDecimal dollar;
	private BigDecimal quarter;
	private BigDecimal halfDollar;
	private BigDecimal[] coinList;
	
	private SelfCheckoutStationBronze stationBronze;
	private SelfCheckoutStationSilver stationSilver;
	private SelfCheckoutStationGold stationGold;
	private Currency cad;
	
	private Coin dollarcoin;

	
	

	
	private ICoinDispenserStub stub;
	
	@Before
	public void setup() {
		dollar = new BigDecimal(1);
		halfDollar = new BigDecimal(0.50);
		quarter = new BigDecimal(0.25);
		
		coinList = new BigDecimal[] {quarter, halfDollar, dollar};
		
		
		cad = Currency.getInstance("CAD"); 
		
		
		stub = new ICoinDispenserStub();
		
		dollarcoin = new Coin(cad, dollar);
		
		
		SelfCheckoutStationBronze.configureCurrency(cad);
		SelfCheckoutStationSilver.configureCurrency(cad);
		SelfCheckoutStationGold.configureCurrency(cad);
		
		SelfCheckoutStationBronze.configureCoinDenominations(coinList);
		SelfCheckoutStationSilver.configureCoinDenominations(coinList);
		SelfCheckoutStationGold.configureCoinDenominations(coinList);
		
		
		
		SelfCheckoutStationBronze.configureBanknoteStorageUnitCapacity(10);
		SelfCheckoutStationSilver.configureBanknoteStorageUnitCapacity(10);
		SelfCheckoutStationGold.configureBanknoteStorageUnitCapacity(10);
		
		
		SelfCheckoutStationBronze.configureCoinStorageUnitCapacity(10);
		SelfCheckoutStationSilver.configureCoinStorageUnitCapacity(10);
		SelfCheckoutStationGold.configureCoinStorageUnitCapacity(10);
		
		SelfCheckoutStationBronze.configureCoinTrayCapacity(5);
		SelfCheckoutStationSilver.configureCoinTrayCapacity(5);
		SelfCheckoutStationGold.configureCoinTrayCapacity(5);
		
		SelfCheckoutStationBronze.configureCoinDispenserCapacity(10);
		SelfCheckoutStationSilver.configureCoinDispenserCapacity(10);
		SelfCheckoutStationGold.configureCoinDispenserCapacity(10);
		
		stationBronze = new SelfCheckoutStationBronze();
		stationSilver = new SelfCheckoutStationSilver();
		stationGold = new SelfCheckoutStationGold();
		logicBronze = new CentralStationLogic(stationBronze);
		logicSilver = new CentralStationLogic(stationSilver);
		logicGold = new CentralStationLogic(stationGold);
		inputBronze = new CoinDispenserController(logicBronze, dollar);
		inputSilver = new CoinDispenserController(logicSilver, dollar);
		inputGold = new CoinDispenserController(logicGold, dollar);
	}
		
		@Test
		public void shouldNotWarnEmptyTest() {
			// should not warn if dispenser banknote > 2 
			
			
			
			for (int i = 0; i < 2; i++) {
				inputBronze.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 2; i++) {
				inputSilver.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 2; i++) {
				inputGold.coinAdded(stub, dollarcoin);
			}
			
			inputBronze.coinAdded(stub, dollarcoin);
			inputSilver.coinAdded(stub, dollarcoin);
			inputGold.coinAdded(stub, dollarcoin);
			
			assertFalse(inputBronze.shouldWarnEmpty());
			assertFalse(inputSilver.shouldWarnEmpty());
			assertFalse(inputGold.shouldWarnEmpty());
			
		}
		
		@Test
		public void shouldWarnEmptyTest() {
			// should not warn if dispenser banknote <= 2
			
			
			inputBronze.coinAdded(stub, dollarcoin);
			inputSilver.coinAdded(stub, dollarcoin);
			inputGold.coinAdded(stub, dollarcoin);
			
			assertTrue(inputBronze.shouldWarnEmpty());
			assertTrue(inputSilver.shouldWarnEmpty());
			assertTrue(inputGold.shouldWarnEmpty());
			
		}
		@Test
		public void shouldNotWarnFullTest() {
			// should  not warn if dispenser banknote < 8
			for (int i = 0; i < 7; i++) {
				inputBronze.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 7; i++) {
				inputSilver.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 7; i++) {
				inputGold.coinAdded(stub, dollarcoin);
			}
			
			assertFalse(inputBronze.shouldWarnFull());
			assertFalse(inputSilver.shouldWarnFull());
			assertFalse(inputGold.shouldWarnFull());
		}
		@Test
		public void shouldWarnFullTest() {
			
			// should  warn if dispenser banknote >= 8
			
			for (int i = 0; i < 8; i++) {
				inputBronze.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 8; i++) {
				inputSilver.coinAdded(stub, dollarcoin);
			}
			for (int i = 0; i < 8; i++) {
				inputGold.coinAdded(stub, dollarcoin);
			}
			
			assertTrue(inputBronze.shouldWarnFull());
			assertTrue(inputSilver.shouldWarnFull());
			assertTrue(inputGold.shouldWarnFull());
		}
		
		 

}
