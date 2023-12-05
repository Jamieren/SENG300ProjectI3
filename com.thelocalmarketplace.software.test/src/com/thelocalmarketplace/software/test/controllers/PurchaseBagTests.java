/**
 * @author Camila Hernandez (30134911)
 * @author Alan Yong (30105707)
 * @author Andrew Matti (30182547)
 * @author Olivia Crosby (30099224)
 * @author Rico Manalastas (30164386)
 * @author Shanza Raza (30192765)
 * @author Danny Ly (30127144)
 * @author Maheen Nizmani (30172615)
 * @author Christopher Lo (30113400)
 * @author Michael Svoboda (30039040)
 * @author Sukhnaaz Sidhu (30161587)
 * @author Ian Beler (30174903)
 * @author Gareth Jenkins (30102127)
 * @author Jahnissi Nwakanma (30174827)
 * @author Ananya Jain (30196069)
 * @author Zhenhui Ren (30139966)
 * @author Eric George (30173268)
 * @author Jenny Dang (30153821)
 * @author Tanmay Mishra (30127407)
 * @author Adrian Brisebois (30170764)
 * @author Atique Muhammad (30038650)
 * @author Ryan Korsrud (30173204)
 */

package com.thelocalmarketplace.software.test.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jjjwelectronics.EmptyDevice;
import com.jjjwelectronics.OverloadedDevice;
import com.jjjwelectronics.bag.ReusableBag;
import com.thelocalmarketplace.hardware.AbstractSelfCheckoutStation;
import com.thelocalmarketplace.hardware.SelfCheckoutStationGold;
import com.thelocalmarketplace.software.logic.CentralStationLogic;
import com.thelocalmarketplace.software.logic.StateLogic.States;

import powerutility.PowerGrid;

public class PurchaseBagTests {
	AbstractSelfCheckoutStation station;
	CentralStationLogic session;
	
	@Before
	public void setup() {
		station = new SelfCheckoutStationGold();
		station.plugIn(PowerGrid.instance());
		station.turnOn();
		session = new CentralStationLogic(station);
		session.setBypassIssuePrediction(true);
		session.startSession();
	}
	
	@Test
	public void normalUse() throws EmptyDevice, OverloadedDevice {
		station.getReusableBagDispenser().load(new ReusableBag());
		session.purchaseBagsLogic.purchaseBags(1);
		station.getBaggingArea().addAnItem(new ReusableBag());
		assertTrue(session.stateLogic.getState().equals(States.NORMAL));
		assertTrue(session.cartLogic.getCart().size() == 1);
	}
}
