package com.thelocalmarketplace.software.issues;

import java.math.BigDecimal;
import java.util.Map;

import com.tdc.IComponent;
import com.tdc.IComponentObserver;
import com.tdc.banknote.AbstractBanknoteDispenser;
import com.tdc.banknote.Banknote;
import com.tdc.banknote.BanknoteDispensationSlot;
import com.tdc.banknote.BanknoteDispenserObserver;
import com.tdc.banknote.IBanknoteDispenser;
import com.thelocalmarketplace.software.AbstractLogicDependant;
import com.thelocalmarketplace.software.logic.CentralStationLogic;

public class BanknoteIssueController extends AbstractLogicDependant implements BanknoteDispenserObserver {
	/** 
	 * Percentage full/empty to trigger respective warnings.
	 * For example, when set to 20 (%), the full warning or empty warning 
	 * will be triggered when there >= 8 or <= 2 banknotes respectively 
	 * in a dispenser with a maximum capacity of 10. 
	 */
	static double warnAtPercentage = 20; //this can be modified by the customer as needed
	IBanknoteDispenser dispenser;
	int maxCapacity;
	int quantity;
	
	BanknoteIssueController(CentralStationLogic logic, IBanknoteDispenser d) {
		super(logic);
	    this.dispenser = d;
	}

	public boolean shouldWarnFull() {
		if(maxCapacity - quantity >= maxCapacity * warnAtPercentage) return true;
		return false;
	}
	
	public boolean shouldWarnEmpty() {
		if(quantity <= maxCapacity * warnAtPercentage) return true;
		return false;
	}
	
	@Override
	public void banknoteAdded(IBanknoteDispenser dispenser, Banknote banknote) {
		quantity++;
	}
	@Override
	public void banknoteRemoved(IBanknoteDispenser dispenser, Banknote banknote) {
		quantity--;
	}
	
	//Unused methods
	@Override
	public void enabled(IComponent<? extends IComponentObserver> component) {}
	@Override
	public void disabled(IComponent<? extends IComponentObserver> component) {}
	@Override
	public void turnedOn(IComponent<? extends IComponentObserver> component) {}
	@Override
	public void turnedOff(IComponent<? extends IComponentObserver> component) {}
	@Override
	public void moneyFull(IBanknoteDispenser dispenser) {}
	@Override
	public void banknotesEmpty(IBanknoteDispenser dispenser) {}
	@Override
	public void banknotesLoaded(IBanknoteDispenser dispenser, Banknote... banknotes) {}
	@Override
	public void banknotesUnloaded(IBanknoteDispenser dispenser, Banknote... banknotes) {}

}
