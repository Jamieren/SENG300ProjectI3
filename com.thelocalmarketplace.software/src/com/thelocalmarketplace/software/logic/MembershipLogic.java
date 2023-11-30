package com.thelocalmarketplace.software.logic;

import com.jjjwelectronics.card.Card;
import com.jjjwelectronics.card.Card.CardData;
import com.jjjwelectronics.card.Card.CardSwipeData;
import com.jjjwelectronics.card.Card.CardTapData;
import com.thelocalmarketplace.software.AbstractLogicDependant;

public class MembershipLogic extends AbstractLogicDependant{
	private String number = "";
	private String cardHolder = "";
	public Error code = Error.NO_ERROR;
	
	private enum Error{
		WRONG_CARD_TYPE, NO_SUCH_MEMBER_FOUND, NO_ERROR;
	}
	public String getNumber() {
		return this.number;
	}
	
	public String getCardHolder() {
		return this.cardHolder;
	}
	
	
	public MembershipLogic(CentralStationLogic logic) throws NullPointerException {
		super(logic);
		// TODO Auto-generated constructor stub
	}

	public boolean enterMembershipByNumber(String number) {
		this.code = Error.NO_ERROR;
		this.setMemberData(number);
		//If number is not a valid membership{return false;}
		if (!isValidMembership()) {
			return false;
		}
		return true;
	}
	
	public boolean enterMembershipBySwipe(CardSwipeData csd) {
		this.code = Error.NO_ERROR;
		this.setMemberData(csd);
		//If number is not a valid membership{return false;}
		if (!isValidMembership()) {
			return false;
		}
		return true;
	}
	
	public boolean enterMembershipByScan(CardTapData ctd) {
		this.code = Error.NO_ERROR;
		this.setMemberData(ctd);
		//If number is not a valid membership{return false;}
		if (!isValidMembership()) {
			return false;
		}
		return true;
	}
	
	private boolean isValidMembership() {
		if(MembershipDatabase.NUMBER_TO_CARDHOLDER.get(number).equals(null)) {
			this.code = Error.NO_SUCH_MEMBER_FOUND;
			return false;
		}
		return true;
	}
	
	//only get data once or multiplying chance of failure
	private void setMemberData(CardData data) {
		if (!data.getType().equals("Membership")) {
			this.code = Error.WRONG_CARD_TYPE;
			return;
		}
		this.number = data.getNumber();
		this.cardHolder = data.getCardholder();
	}
	
	private void setMemberData(String number) {
		this.number = number;
		this.cardHolder = MembershipDatabase.NUMBER_TO_CARDHOLDER.get(number);
		if (this.cardHolder.equals(null)) {
			this.code = Error.NO_SUCH_MEMBER_FOUND;
		}
	}
	
	
	
}