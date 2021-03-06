package evoblackjack;/*
  	Author: Brian Carrigan
  	Date: 1/10/2013
  	Email: brian.c.carrigan@gmail.com
 
   This file is part of the Evolutionary Blackjack Strategy Solver.

    The Evolutionary Blackjack Strategy Solver is free software: you 
    can redistribute it and/or modify it under the terms of the GNU 
    General Public License as published by the Free Software Foundation, 
    either version 3 of the License, or (at your option) any later 
    version.

    The Evolutionary Blackjack Strategy Solver is distributed in 
    the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
    even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
    PARTICULAR PURPOSE.  See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with the Evolutionary Blackjack Strategy Solver.  If not, 
    see <http://www.gnu.org/licenses/>.
 */


// A dealerhand represents which card a dealer has showing.
public enum DealerHand {

	ACE,
	TEN,
	NINE,
	EIGHT,
	SEVEN,
	SIX,
	FIVE,
	FOUR,
	THREE,
	TWO;
	
	public String convertHandToString()
	{
		switch(this)
		{
		case  ACE:
			return "A";
		case  TEN:
			return "10";
		case  NINE: 
			return "9";
		case  EIGHT:
			return "8";
		case  SEVEN:
			return "7";
		case  SIX:  
			return "6";
		case  FIVE: 
			return "5";
		case  FOUR:
			return "4";
		case  THREE:
			return "3";
		default:
			return "2";	
		}
	}
	
}
