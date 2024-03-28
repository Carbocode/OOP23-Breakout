package it.unibo.model;

import java.util.Timer;

import it.unibo.api.LargeBar;

public class LargeBarImpl implements LargeBar extends Bar{
    //these will help when the powerups are on
    private int timer=20;
    private boolean isLarge=false;

    public void large(){
        //Checks the conditions of the Large powerup
		if(this.isLarge){
			timer--;
			if(timer <= 0){
				this.isLarge = false;
				//have to set the width of the bar to the normal size
				timer = 20;
				return;
			}
		}
    }
    
    public void setLarge(boolean isLarge){
		this.isLarge = true;
	}
	
	public boolean isLarge(){
		return this.isLarge;
	}


}
